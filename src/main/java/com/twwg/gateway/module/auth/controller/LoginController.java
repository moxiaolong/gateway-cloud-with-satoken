package com.twwg.gateway.module.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.digest.BCrypt;
import com.diboot.core.vo.JsonResult;
import com.twwg.gateway.module.auth.entity.User;
import com.twwg.gateway.module.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DefaultDataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;

/**
 * 登录控制器
 *
 * @author dragon
 * @date 2022/01/24
 */
@RestController
@RequestMapping("/login/")
@Slf4j
public class LoginController {

    @Autowired
    UserService userService;

    TimedCache<String, String> captchaCache = new TimedCache<>(30000L);

    /**
     * 获取验证码
     * http://localhost:8888/auth/login/captcha
     */
    @GetMapping("captcha")
    public Mono<Void> captcha(ServerHttpResponse response) {
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(250, 120, 4, 4);
        String code = circleCaptcha.getCode();
        String uuid = UUID.fastUUID().toString(true);
        captchaCache.put(uuid, code);
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        circleCaptcha.write(os);
        Flux<DefaultDataBuffer> flux = Flux.create(fluxSink -> {
            DefaultDataBufferFactory factory = new DefaultDataBufferFactory();
            DefaultDataBuffer wrap = factory.wrap(ByteBuffer.wrap(os.toByteArray()));
            fluxSink.next(wrap);
            fluxSink.complete();
        });
        response.getHeaders().set("captchaToken", uuid);
        log.info("验证码#{}，token#{}", code, uuid);
        return response.writeWith(flux);
    }

    /**
     * 登录
     * <p>
     * http://localhost:8888/auth/login/doLogin?username=admin&password=admin&captchaToken=XXXXXXXXXXXX&captchaCode=XXXX
     *
     * @param username     用户名
     * @param password     密码
     * @param captchaToken 验证码牌
     * @param captchaCode  验证码代码
     * @return {@link String}
     */
    @PostMapping("/doLogin")
    public JsonResult doLogin(String username, String password, String captchaToken, String captchaCode) {
        String captcha = captchaCache.get(captchaToken);
        captchaCache.remove(captchaCode);
        if (captchaCode == null || !captchaCode.equals(captcha)) {
            return JsonResult.FAIL_EXCEPTION("验证码校验失败");
        }
        User user = userService.getByUsername(username);
        //BCrypt
        if (user == null || !BCrypt.checkpw(password, user.getPasswd())) {
            return JsonResult.FAIL_EXCEPTION("登录失败");
        }
        StpUtil.login(user.getId());
        return JsonResult.OK("登录成功");
    }

    /**
     * 查询登录状态登录
     *
     * @return {@link String}
     */
    @GetMapping("isLogin")
    public JsonResult isLogin() {
        return JsonResult.OK(StpUtil.isLogin());
    }

    /**
     * 注销
     *
     * @return {@link String}
     */
    @GetMapping("logout")
    public JsonResult logout() {
        StpUtil.logout();
        return JsonResult.OK("注销成功");
    }

}
package com.twwg.gateway.module.auth.controller;

import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.digest.BCrypt;
import com.twwg.gateway.module.auth.entity.User;
import com.twwg.gateway.module.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DefaultDataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户控制器
 *
 * @author dragon
 * @date 2022/01/24
 */
@RestController
@RequestMapping("/auth/")
public class LoginController {

    @Autowired
    UserService userService;

    TimedCache<String, String> captchaCache = new TimedCache<>(5000L);


    @RequestMapping("login/doLogin")
    public String doLogin(String username, String password, String captchaToken, String captchaCode) {

        String captcha = captchaCache.get(captchaToken);
        if (captchaCode == null || !captchaToken.equals(captcha)) {
            captchaCache.remove(captchaCode);
            return "验证码校验失败";
        }

        User user = userService.getByUsername(username);
        //BCrypt
        if (user == null|| !BCrypt.checkpw(password,user.getPasswd())) {
            return "登录失败";
        }

        StpUtil.login(user.getId());
        return "登录成功";
    }

    /**
     * 获取验证码
     */
    @GetMapping("login/captcha")
    public Mono<Void> captcha(ServerHttpResponse response) throws Exception {
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
        return response.writeWith(flux);
    }

    /**
     * 查询登录状态登录
     *
     * @return {@link String}
     */
    @RequestMapping("login/isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    /**
     * 获取角色列表
     *
     * @return {@link List}<{@link String}>
     */
    @RequestMapping("getRoleList")
    public List<String> getRoleList() {
        return StpUtil.getRoleList();
    }

    /**
     * 获得权限列表
     *
     * @return {@link List}<{@link String}>
     */
    @RequestMapping("getPermissionList")
    public List<String> getPermissionList() {
        return StpUtil.getPermissionList();
    }

    /**
     * 有网址访问权
     *
     * @param urls url 网址数组
     * @return {@link Map}<{@link String}, {@link Boolean}> url1:true,url2:false
     */
    @RequestMapping("hasUrlPermission")
    public Map<String, Boolean> hasUrlPermission(String[] urls) {
        HashMap<String, Boolean> result = new HashMap<>();
        List<String> permissionList = StpUtil.getPermissionList();
        for (String url : urls) {
            boolean match = SaRouter.isMatch(permissionList, url);
            result.put(url, match);
        }
        return result;
    }

    /**
     * 注销
     *
     * @return {@link String}
     */
    @RequestMapping("logout")
    public String logout() {
        StpUtil.logout();
        return "注销成功";
    }

}
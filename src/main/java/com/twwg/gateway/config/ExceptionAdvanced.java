package com.twwg.gateway.config;

import com.diboot.core.vo.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常拦截
 *
 * @author dragon
 * @date 2022/01/25
 */
@RestControllerAdvice
public class ExceptionAdvanced {
    @ExceptionHandler(Throwable.class)
    public JsonResult onerror(Throwable e){
        return JsonResult.FAIL_EXCEPTION(e.getMessage());
    }
}

package com.mufiye.exception;

import com.mufiye.util.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyException {

    @ExceptionHandler
    public R r(Exception e){
        e.printStackTrace();
        return new R(false,null,"发生异常，请联系管理员");
    }
}

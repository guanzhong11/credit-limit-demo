package com.demo.creditlimit.exception;


import com.demo.creditlimit.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({ CustomException.class })
    public Result handleCustomException(CustomException ex) {
        return Result.error(ex.getMessage());
    }
}

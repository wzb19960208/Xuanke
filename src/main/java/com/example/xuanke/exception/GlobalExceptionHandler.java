package com.example.xuanke.exception;

import com.example.xuanke.VO.Result;
import com.example.xuanke.config.ErrorCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//切面Advice，懂我意思吧
@ControllerAdvice
//拦截了全局异常之后，返回Reuslt
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result exceptionHandler(Exception e){

        //先打印
        e.printStackTrace();

        if(e instanceof GlobalException){
            GlobalException ex = (GlobalException)e;
            return Result.fail(ex.getCode(),ex.getMessage());
        }else {
            return Result.fail(ErrorCode.SERVER_ERROR,e.getMessage());
        }

    }

}

package com.ymj.mytodolist.exception.handler;


import com.ymj.mytodolist.exception.DuplicateUsernameException;
import com.ymj.mytodolist.exception.NotLoggedInException;
import com.ymj.mytodolist.exception.WrongUsernameOrPasswordException;
import com.ymj.mytodolist.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotLoggedInException.class)
    public Result handleNotLoggedInException(NotLoggedInException ex){
        return Result.error(ex.getMessage());
    }

    @ExceptionHandler(WrongUsernameOrPasswordException.class)
    public Result handleWrongInformationException(NotLoggedInException ex){
        return Result.error(ex.getMessage());
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    public Result handleDuplicateUsernameException(DuplicateUsernameException ex){
        return Result.error(ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception ex){
        return Result.error(ex.getMessage());
    }
}

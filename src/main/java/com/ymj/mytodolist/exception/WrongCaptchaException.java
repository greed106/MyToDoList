package com.ymj.mytodolist.exception;

public class WrongCaptchaException extends RuntimeException{
    public WrongCaptchaException(String message) {
        super(message);
    }
}

package com.ymj.mytodolist.exception;

public class WrongUsernameOrPasswordException extends RuntimeException{
    public WrongUsernameOrPasswordException(String message) {
        super(message);
    }

}

package com.dxd.demo01.exception;
import org.springframework.security.core.AuthenticationException;

public class MyException extends AuthenticationException{
    private static final long serialVersionUID = 1L;

    public MyException(String msg) {
        super(msg);
    }
}

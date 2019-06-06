package com.dxd.demo01.service;

/**
 * 短信验证码发送接口
 */
public interface SmsCodeSender {
    //　至少需要手机号和验证码
    void send(String mobile, String code);
}

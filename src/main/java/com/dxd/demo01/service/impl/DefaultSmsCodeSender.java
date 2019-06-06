package com.dxd.demo01.service.impl;

import com.dxd.demo01.service.SmsCodeSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        log.debug("send to mobile ：{}, code : {}", mobile, code);
    }
}

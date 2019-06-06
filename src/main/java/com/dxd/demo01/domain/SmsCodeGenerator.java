package com.dxd.demo01.domain;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import static com.dxd.demo01.common.MyConstants.SMS_RANDOM_SIZE;

// 短信验证码生成器
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator{
    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(SMS_RANDOM_SIZE);
        return new ValidateCode(code, SMS_RANDOM_SIZE);
    }
}

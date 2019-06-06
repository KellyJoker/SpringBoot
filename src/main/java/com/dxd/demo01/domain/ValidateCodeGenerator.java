package com.dxd.demo01.domain;

import org.springframework.web.context.request.ServletWebRequest;

//验证码生成接口
public interface ValidateCodeGenerator {
    ValidateCode generate(ServletWebRequest request);
}

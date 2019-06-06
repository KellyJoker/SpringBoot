package com.dxd.demo01.web;

import com.dxd.demo01.domain.ImageCode;
import com.dxd.demo01.domain.ImageCodeGenerator;
import com.dxd.demo01.domain.ValidateCode;
import com.dxd.demo01.domain.ValidateCodeGenerator;
import com.dxd.demo01.service.impl.DefaultSmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.dxd.demo01.common.MyConstants.SESSION_KEY;

@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
    @Autowired
    private SessionStrategy sessionStrategy;
    @Autowired
    private ImageCodeGenerator imageCodeGenerator;
    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;
    @Autowired
    private DefaultSmsCodeSender defaultSmsCodeSender;

    /**
     * 图形验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = (ImageCode) imageCodeGenerator.generate(new ServletWebRequest(request));
        System.out.println(imageCode.getCode());
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

    /**
     * 短信验证码
     * @param request
     * @param response
     * @throws ServletRequestBindingException
     */
    @GetMapping("code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
        ValidateCode smsCode = smsCodeGenerator.generate(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, smsCode);
        String mobile = ServletRequestUtils.getStringParameter(request, "mobile");
        defaultSmsCodeSender.send(mobile, smsCode.getCode());
    }
}

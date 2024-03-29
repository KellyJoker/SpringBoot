package com.dxd.demo01.filter;

import com.dxd.demo01.domain.ImageCode;
import com.dxd.demo01.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.dxd.demo01.common.MyConstants.SESSION_KEY;

@Slf4j
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements Filter {

    /**
     * 验证码校验失败处理器
     */
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private SessionStrategy sessionStrategy;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 必须是登录的post请求才能进行验证，其他的直接放行
        //这里的登录路径必须和表单上的登录路径一致
        if(StringUtils.equals("/login", request.getRequestURI()) && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
            log.info("request : {}", request.getRequestURI());
            try {
                // 1. 进行验证码的校验
                validate(new ServletWebRequest(request));
            } catch (AuthenticationException e) {
                // 2. 捕获步骤1中校验出现异常，交给失败处理类进行进行处理
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        // 3. 校验通过，就放行
        filterChain.doFilter(request, response);

    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        // 1. 获取请求中的验证码
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
        // 2. 校验空值情况
        if(StringUtils.isEmpty(codeInRequest)) {
            throw new MyException("验证码不能为空");
        }

        // 3. 获取服务器session池中的验证码
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, SESSION_KEY);
        if(Objects.isNull(codeInSession)) {
            throw new MyException("验证码不存在");
        }

        // 4. 校验服务器session池中的验证码是否过期
        if(codeInSession.isExpried()) {
            sessionStrategy.removeAttribute(request, SESSION_KEY);
            throw new MyException("验证码过期了");
        }

        // 5. 请求验证码校验
        if(!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new MyException("验证码不匹配");
        }

        // 6. 移除已完成校验的验证码
        sessionStrategy.removeAttribute(request, SESSION_KEY);
    }
}

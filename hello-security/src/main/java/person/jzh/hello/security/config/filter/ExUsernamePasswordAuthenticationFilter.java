package person.jzh.hello.security.config.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import person.jzh.hello.security.exception.ValidateCodeException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/4/24 22:20
 * @description
 */
public class ExUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public ExUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.setAuthenticationManager(authenticationManager);
        // 使用跳转至指定url的处理方法来处理错误（认证失败）
        // setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
        // 也可以直接向前端返回错误信息
        setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                String error = (String) request.getAttribute("error");
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(error + "登录失败!");
            }
        });
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 拦截登录的 form 表单路径
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            // 获取表单提交的验证码
            String code = request.getParameter("code");
            // 方便测试，这里写死，正常应该写业务处理过程
            if (!"1111".equals(code)) {
                request.setAttribute("error", "验证码错误，");
                throw new ValidateCodeException("验证码错误");
            }
        }
        // 处理完自定义参数，就让 UsernamePasswordAuthenticationFilter 继续处理账号和密码，当然，也可以自定义
        return super.attemptAuthentication(request, response);
    }
}
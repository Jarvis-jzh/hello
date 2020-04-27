package person.jzh.hello.security.config.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import person.jzh.hello.security.exception.ValidateCodeException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/3/25 19:51
 * @description security验证码过渡器，用于校验验证码是否正确
 */
public class ValidateCodeFilter extends AbstractAuthenticationProcessingFilter {
    //拦截的url
    private String processUrl;
    /**
     *
     * @param defaultFilterProcessesUrl     默认拦截的路径
     * @param failureUrl                    验证失败返回的路径
     */
    public ValidateCodeFilter(String defaultFilterProcessesUrl, String failureUrl) {
        super(defaultFilterProcessesUrl);
        this.processUrl = defaultFilterProcessesUrl;
        // 设置认证失败（错误）的处理器
        setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(failureUrl));
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        // 拦截登录的 form 表单路径
        if (processUrl.equals(req.getServletPath()) && "POST".equalsIgnoreCase(req.getMethod())) {
            // 获取表单提交的验证码
            String code = req.getParameter("code");
            // 方便测试，这里写死
            if (!"1111".equals(code)) {
                // 验证失败，交给 SimpleUrlAuthenticationFailureHandler 解决
                this.getFailureHandler().onAuthenticationFailure(req, resp, new ValidateCodeException("验证码错误"));
                // 打断过滤器链
                return;
            }
        }
        // 放行
        chain.doFilter(request, response);
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        return null;
    }
}
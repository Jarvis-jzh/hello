package person.jzh.hello.security.config.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/3/25 18:31
 * @description 自定义用户身份认证
 */
public class UserAuthenticationProviderImpl implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

    public UserAuthenticationProviderImpl(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 认证处理，返回一个Authentication的实现类则代表认证成功，返回null则代表认证失败
     *
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        System.out.println("自定义用户身份认证");
        String phone = authentication.getName();
        String password = (String) authentication.getCredentials();

        if (StringUtils.isBlank(phone)) {
            throw new UsernameNotFoundException("手机号不能为空");
        }

        if (StringUtils.isBlank(password)) {
            throw new BadCredentialsException("密码不能为空");
        }

        // 获取用户信息
        UserDetails user = userDetailsService.loadUserByUsername(phone);
        // 判断输入的密码与数据库的是否相同
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }

        // 返回用户密码认证token
        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    /**
     * 如果该AuthenticationProvider支持传入的Authentication对象，则返回true
     *
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}


package person.jzh.hello.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import person.jzh.hello.security.config.filter.ExUsernamePasswordAuthenticationFilter;
import person.jzh.hello.security.config.filter.ValidateCodeFilter;
import person.jzh.hello.security.config.impl.UserAuthenticationProviderImpl;
import person.jzh.hello.security.config.impl.UserDetailsServiceImpl;

/**
 * @author jzh
 * @version 1.0.0
 * @date 2020/2/29 14:48
 * @description Security 配置
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
        // 一定要通过 WebSecurityConfigurerAdapter.authenticationManager() 方法获取 AuthenticationManager 给 AbstractAuthenticationProcessingFilter，不然会报空指针异常
        // 如果使用 WebSecurityConfigurerAdapter.authenticationManagerBean() 方法获取 AuthenticationManager，会报栈溢出异常，会在 org.springframework.security.authentication.ProviderManager#authenticate 这个方法中疯狂调用自己造成死循环然后栈溢出，因为对 Security 源码了解还不深，不清楚问题所在，此处留坑
        return new ExUsernamePasswordAuthenticationFilter(authenticationManager());
    }

    /**
     * 配置 userDetails 的数据源，密码加密格式
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder())
                .and()
                // 自定义 AuthenticationProvider，实际使用可以去掉
                .authenticationProvider(new UserAuthenticationProviderImpl(userDetailsService(), passwordEncoder()));
    }

    /**
     * 配置放行的资源
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 关闭跨域
                .csrf()
                .disable()
                .exceptionHandling()
//                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/superadmin").hasRole("SUPERADMIN")
                .antMatchers("/user", "/admin").hasRole("ADMIN")
                .and()
                // 这里是关键，通过这里将自己写的 Filter 放到 UsernamePasswordAuthenticationFilter 前面，进行验证码判断
                .addFilterBefore(new ValidateCodeFilter("/login", "/login?error"), UsernamePasswordAuthenticationFilter.class)
                // 将自定义的 usernamePasswordAuthenticationFilter 代替原来的 UsernamePasswordAuthenticationFilter
                // .addFilterAt(usernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin().loginPage("/login").failureUrl("/login?error").defaultSuccessUrl("/")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/");
    }
}

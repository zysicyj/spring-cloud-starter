package com.huan.sso.config;

import com.huan.sso.handler.UrlAccessDeniedHandler;
import com.huan.sso.service.UrlFilterInvocationSecurityMetadataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 配置用户密码校验过滤器
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/9
 * @since 1.0.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
    @Resource
    private
    DataSource dataSource;
    @Resource
    private
    UrlAccessDeniedHandler urlAccessDeniedHandler;

    /**
     * 加密
     *
     * @return org.springframework.security.crypto.password.PasswordEncoder
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/12
     * @since 1.0.0
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder());
    }


    /**
     * 登录处理
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.antMatcher("/**").authorizeRequests();
        // 禁用CSRF 开启跨域
        http.csrf().disable().cors();
        // 登录过后访问无权限的接口时自定义403响应内容
        http.exceptionHandling().accessDeniedHandler(urlAccessDeniedHandler);
        // 开启登录配置
        http.authorizeRequests()
                .antMatchers("/resource", "/oauth/**", "/logout").permitAll()
                // 其余所有请求都需要认证e
                .anyRequest().authenticated()
                // 防止iframe 造成跨域
                .and().headers().frameOptions().disable()
                .and()
                .httpBasic()
                .and()
                // 关闭CSRF跨域，开启跨域
                .csrf().disable().cors();
    }

    /**
     * 忽略拦截
     */
    @Override
    public void configure(WebSecurity web) {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/css/**", "/js/**", "/*.html", "/logout");
    }
}

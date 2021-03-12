package com.huan.sso.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * 授权服务器配置
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/12
 * @since 1.0.0
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    /**
     * 定时数据源是SSO数据库，存放授权数据
     */
    @Resource
    @Qualifier("dataSource1")
    DataSource dataSource;

    /**
     * 定义token存储形式，我这边用到是JWT形式
     */
    @Resource
    private TokenStore tokenStore;
    /**
     * 定义密码编码，对用户传的明文密码进行加密
     */
    @Resource
    private PasswordEncoder passwordEncoder;
    /**
     * 认证管理
     */
    @Resource
    private AuthenticationManager authenticationManager;
    /**
     * jwt增强，存放我想给客户端的数据
     */
    @Resource
    private TokenEnhancer jwtTokenEnhancer;

    /**
     * 客户端服务配置，这边使用jdbc的形式落地
     *
     * @param clients 客户端配置
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/12
     * @since 1.0.0
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
    }

    /**
     * 配置授权安全策略
     *
     * @param security 安全配置
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/12
     * @since 1.0.0
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.passwordEncoder(passwordEncoder);
        security.allowFormAuthenticationForClients();
        security.checkTokenAccess("isAuthenticated()");
        security.tokenKeyAccess("isAuthenticated()");
    }

    /**
     * 1、配置我们的Token存放方式不是内存、数据库或Redis方式，而是JWT方式。
     * JWT是Json Web Token缩写也就是使用JSON数据格式包装的Token，由.句号把整个JWT分隔为头、数据体、签名三部分。
     * JWT保存Token虽然易于使用但是不是那么安全，一般用于内部，并且需要走HTTPS+配置比较短的失效时间。
     * 2、配置了JWT Token的非对称加密来进行签名
     * 3、配置了一个自定义的Token增强器，把更多信息放入Token中
     * 4、配置了使用JDBC数据库方式来保存用户的授权批准记录
     *
     * @param endpoints 配置授权服务器端点的属性和增强的功能
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/12
     * @since 1.0.0
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancerList = new ArrayList<>();
        enhancerList.add(jwtTokenEnhancer);
        enhancerChain.setTokenEnhancers(enhancerList);

        endpoints
                .approvalStore(approvalStore())
                .tokenStore(tokenStore)
                .authorizationCodeServices(authorizationCodeServices())
                .authenticationManager(authenticationManager)
                .tokenEnhancer(enhancerChain);
    }

    /**
     * 使用JDBC数据库方式来保存用户的授权批准记录
     *
     * @return org.springframework.security.oauth2.provider.approval.JdbcApprovalStore
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/12
     * @since 1.0.0
     */
    @Bean
    public JdbcApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }


    /**
     * 使用JDBC数据库方式来保存授权码
     *
     * @return org.springframework.security.oauth2.provider.code.AuthorizationCodeServices
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/12
     * @since 1.0.0
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }
}

package com.huan.common.model;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 该类返回的是安全的，能够提供给用户看到的信息，即脱敏后的信息
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/9
 * @since 1.0.0
 */
@Data
@Slf4j
public class SecurityUser implements UserDetails {
    private static final long serialVersionUID = 8689435103879098852L;
    /**
     * 盐
     */
    private String salt;

    /**
     * 用户token
     */
    private String token;

    /**
     * 用户状态
     */
    private String status;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户登录账号
     */
    private String loginName;

    private Long userId;

    /**
     * 用户角色
     *
     * @date 2021/1/10
     * @since 1.0.0
     */
    private List<UserRole> roleList;
    /**
     * 权限列表
     *
     * @date 2021/1/11
     * @since 1.0.0
     */
    private List<UserPermission> permissionList;


    /**
     * 客户端用户
     *
     * @param client 客户端
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/13
     * @since 1.0.0
     */
    public SecurityUser(OauthClientDetails client) {
        if (client != null) {
            password = client.getClientSecret();
            loginName = client.getClientId();
            String authorities = client.getAuthorities();
            StringTokenizer stringTokenizer = new StringTokenizer(authorities, ", ");
            roleList = new ArrayList<>();
            if (stringTokenizer.hasMoreTokens()) {
                UserRole userRole = new UserRole();
                userRole.setCode(stringTokenizer.nextToken());
                roleList.add(userRole);
            }
        }
    }

    /**
     * 普通用户
     *
     * @param user           用户
     * @param roleList       角色
     * @param permissionList 权限
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/13
     * @since 1.0.0
     */
    public SecurityUser(User user, List<UserRole> roleList, List<UserPermission> permissionList) {
        if (user != null) {
            salt = user.getSalt();
            token = user.getToken();
            status = user.getStatus();
            password = user.getPassword();
            loginName = user.getLoginName();
            userId = user.getId();
            this.roleList = roleList;
            this.permissionList = permissionList;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (!CollUtil.isEmpty(roleList)) {
            for (UserRole role : roleList) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getCode());
                authorities.add(authority);
            }
        }
        log.debug("获取到的用户权限：{}", authorities);
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return loginName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
package com.huan.sso.dao;

import com.huan.common.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户SQL接口
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/1/9
 */
public interface UserDao extends JpaRepository<User, Long> {

    /**
     * 根据登录名返回用户信息
     *
     * @param username 用户账号
     *
     * @return com.huan.sso.model.User
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/9
     * @since 1.0.0
     */
    User findByLoginName(String username);


    /**
     * 根据token返回用户信息
     *
     * @param tooken 用户Token
     *
     * @return com.huan.sso.model.User
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/9
     * @since 1.0.0
     */
    User findByToken(String tooken);
}

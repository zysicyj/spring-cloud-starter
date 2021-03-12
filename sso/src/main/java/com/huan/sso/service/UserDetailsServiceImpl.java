package com.huan.sso.service;

import cn.hutool.core.collection.CollUtil;
import com.huan.common.model.*;
import com.huan.sso.dao.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 配置安全用户
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/9
 * @since 1.0.0
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private
    UserDao userDao;
    @Resource
    private
    UserRoleRelationDao userRoleRelationDao;
    @Resource
    private
    UserRoleDao userRoleDao;
    @Resource
    private
    UserRolePermissionRelationDao userRolePermissionRelationDao;
    @Resource
    private
    UserPermissionDao userPermissionDao;

    /***
     * 根据账号获取用户信息
     * @param username: 用户账号
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        User user = userDao.findByLoginName(username);
        // 判断用户是否存在
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        // 返回UserDetails实现类
        return new SecurityUser(user, getUserRoles(user.getId()), getUserPermissions(getUserRoles(user.getId())));
    }

    /**
     * 根据用户id获取角色权限信息
     *
     * @param userId 用户id
     */
    private List<UserRole> getUserRoles(Long userId) {
        List<UserRoleRelation> userRoles = userRoleRelationDao.findByUserId(userId);
        if (CollUtil.isEmpty(userRoles)) {
            return new ArrayList<>();
        }
        return userRoleDao.findAllByIdIn(userRoles.stream().map(UserRoleRelation::getRoleId).collect(Collectors.toList()));
    }

    /**
     * 根据角色获取用户权限
     *
     * @param roleList 角色列表
     *
     * @return java.util.List<com.huan.sso.model.UserPermission>
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/2/8
     * @since 1.0.0
     */
    private List<UserPermission> getUserPermissions(List<UserRole> roleList) {
        List<Long> roleIdList = roleList.stream().map(UserRole::getId).collect(Collectors.toList());
        List<UserRolePermissionRelation> allPermissionRelation = userRolePermissionRelationDao.findAllByRoleIdIn(roleIdList);
        List<Long> allPermissionId = allPermissionRelation.stream().map(UserRolePermissionRelation::getPermissionId).collect(Collectors.toList());
        return userPermissionDao.findAllById(allPermissionId);
    }
}
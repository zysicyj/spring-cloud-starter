package com.huan.common.model;

import com.huan.common.constants.regex.UserRegex;
import com.huan.common.groups.basic.InsertGroup;
import com.huan.common.groups.basic.UpdateGroup;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "customIdGenerator")
    @GenericGenerator(name = "customIdGenerator", strategy = "com.huan.common.util.CustomIdGenerator")
    @NotNull(message = "id不能为空", groups = {UpdateGroup.class})
    private Long id;
    /**
     * 用户登录账号
     */
    @NotBlank(message = "用户账号不能为空！", groups = {InsertGroup.class})
    @Pattern(regexp = UserRegex.LOGIN_NAME, message = "您输入的账号:'${validatedValue}'必须以字母开头，允许5-16字节，允许字母数字下划线", groups = {InsertGroup.class})
    private String loginName;
    /**
     * 密码
     */
    @NotBlank(message = "用户密码不能为空！", groups = {InsertGroup.class})
    @Pattern(regexp = UserRegex.PASSWORD, message = "您输入的密码:'${validatedValue}'必须包含大小写字母和数字的组合，可以使用特殊字符，长度在6-20之间", groups = {InsertGroup.class, UpdateGroup.class})
    private String password;
    /**
     * 昵称
     */
    @NotBlank(message = "用户昵称不能为空！", groups = {InsertGroup.class})
    @Pattern(regexp = UserRegex.NICK_NAME, message = "您输入的昵称:'${validatedValue}'应当是2-16字节，且只允许字母数字下划线汉字", groups = {InsertGroup.class, UpdateGroup.class})
    private String nickName;
    /**
     * 所属区域
     */
    @NotNull(message = "区域id不能为空", groups = {InsertGroup.class})
    private Long areaId;
    /**
     * 所属服务组织/机构
     */
    private Long orgId;
    /**
     * 所属评估机构
     */
    private Long assessOrgId;
    /**
     * 电话
     */
    @NotBlank(message = "手机号码不能为空！", groups = {InsertGroup.class})
    @Pattern(regexp = UserRegex.PHONE, message = "您输入的手机号:'${validatedValue}'号码非法", groups = {InsertGroup.class})
    private String phone;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 性别：1男,2女,0保密
     */
    private String gender;
    /**
     * 用户认证token
     */
    private String token;
    /**
     * app验证信息
     */
    private String tokenApp;
    /**
     * 0:待激活 1:正常 2：已删除 3已禁用
     */
    private String status;
    /**
     * 联系人
     */
    private String linkName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 盐
     */
    @NotNull(message = "私钥不能为空", groups = {InsertGroup.class})
    private String salt;
    /**
     * 记录创建时间
     */
    @CreatedDate
    private LocalDateTime createTime;
    /**
     * 记录更新时间
     */
    @CreatedDate
    private LocalDateTime modifyTime;
    /**
     * 组织名称
     */
    private String orgName;
    /**
     * 省名称
     */
    private String provinceName;
    /**
     * 市名称
     */
    private String cityName;
    /**
     * 区县名称
     */
    private String countyName;
    /**
     * 镇名称
     */
    private String townName;
    /**
     * 村名称
     */
    private String villageName;
    /**
     * 创建用户
     */
    private Long createUid;


}


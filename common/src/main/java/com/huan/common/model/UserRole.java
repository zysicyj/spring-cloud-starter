package com.huan.common.model;

import com.huan.common.groups.basic.InsertGroup;
import com.huan.common.groups.basic.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @Id
    @GeneratedValue(generator = "customIdGenerator")
    @GenericGenerator(name = "customIdGenerator", strategy = "com.huan.common.util.CustomIdGenerator")
    @NotNull(message = "id不存在", groups = {UpdateGroup.class})
    private Long id;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "角色名称不存在", groups = {InsertGroup.class})
    private String name;
    /**
     * 角色值
     */
    @ApiModelProperty(value = "角色值")
    @NotNull(message = "角色值不存在", groups = {InsertGroup.class})
    private String code;
    /**
     * 角色描述
     */
    @ApiModelProperty(value = "角色描述")
    private String description;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime modifyTime;
    /**
     * 级别、角色类型1超级管理员；2省；3市级；4区级；5县级；6乡镇级；7管理员；8员工
     */
    @ApiModelProperty(value = "级别、角色类型1超级管理员；2省；3市级；4区级；5县级；6乡镇级；7管理员；8员工")
    @NotBlank(message = "角色级别不能为空", groups = {InsertGroup.class})
    private String level;
    /**
     * 创建人id
     */
    @ApiModelProperty(value = "创建人id")
    private Long createUid;
}


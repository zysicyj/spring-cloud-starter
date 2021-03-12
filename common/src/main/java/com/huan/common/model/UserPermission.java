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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_permission")
public class UserPermission implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 自增id
     */
    @ApiModelProperty(value = "id")
    @Id
    @GeneratedValue(generator = "customIdGenerator")
    @GenericGenerator(name = "customIdGenerator", strategy = "com.huan.common.util.CustomIdGenerator")
    @NotNull(message = "id不能为空", groups = {UpdateGroup.class})
    private Long id;
    /**
     * 父ID，顶级id设置为null
     */
    @ApiModelProperty(value = "父ID，顶级id设置为null")
    @NotNull(message = "父节点id不能为空，若是根节点，则id为0", groups = {InsertGroup.class})
    @Min(value = 0, message = "节点最小id为{value}", groups = {InsertGroup.class})
    private Long parentId;
    /**
     * 权限名称
     */
    @NotBlank(message = "权限名称不能为空", groups = {InsertGroup.class})
    @ApiModelProperty(value = "权限名称")
    private String name;
    /**
     * 权限值
     */
    @ApiModelProperty(value = "权限值")
    @NotBlank(message = "权限值不能为空", groups = {InsertGroup.class})
    private String code;
    /**
     * 权限url
     */
    @ApiModelProperty(value = "权限url")
    @NotBlank(message = "权限url不能为空", groups = {InsertGroup.class})
    private String url;
    /**
     * 1菜单 2功能 3接口
     */
    @ApiModelProperty(value = "1菜单 2功能 3接口")
    @NotBlank(message = "权限类型不能为空", groups = {InsertGroup.class})
    private String type;
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
     * 排序权值
     */
    @ApiModelProperty(value = "排序权值")
    private Integer sort;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Long createUid;
}


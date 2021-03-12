package com.huan.common.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 客户端用户
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/13
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "oauth_client_details")
public class OauthClientDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 客户端id
     */
    @Id
    private String clientId;
    /**
     * 资源id
     */
    private String resourceIds;
    /**
     * 客户端密钥
     */
    private String clientSecret;
    /**
     * 授权范围
     */
    private String scope;
    /**
     * 支持的授权类型
     */
    private String authorizedGrantTypes;
    /**
     * 权限
     */
    private String authorities;
    /**
     * 额外信息
     */
    private String additionalInformation;
    /**
     * 自动认证
     */
    private String autoapprove;
}

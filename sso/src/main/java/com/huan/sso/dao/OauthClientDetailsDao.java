package com.huan.sso.dao;

import com.huan.common.model.OauthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * OauthClientSQL接口
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/1/9
 */
public interface OauthClientDetailsDao extends JpaRepository<OauthClientDetails, Long> {
}

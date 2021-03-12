package com.huan.common.basic;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 基础接口
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/3/1
 */
public interface BasicDao<T, L extends Number> extends JpaRepository<T, L> {
}

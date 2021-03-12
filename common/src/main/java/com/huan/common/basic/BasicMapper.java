package com.huan.common.basic;

import org.mapstruct.MappingTarget;

import java.util.List;


/**
 * 基础转换
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/3/1
 */
public interface BasicMapper<T, Q, D, V> {
    T query2do(Q query);

    V dto2View(D dto);

    D do2dto(T role);

    T dto2do(D role);

    void update(Q query, @MappingTarget T news);

    List<V> dto2ViewPage(List<T> dto);

    List<T> dto2doList(List<D> d);
}

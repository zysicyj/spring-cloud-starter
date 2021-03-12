package com.huan.common.basic;

import com.huan.common.aspect.check.CheckParam;
import com.huan.common.groups.basic.InsertGroup;
import com.huan.common.groups.basic.ListGroup;
import com.huan.common.groups.basic.UpdateGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;


/**
 * 基础服务
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/3/1
 */
@SuppressWarnings("unchecked")
@Slf4j
public class BasicService<T, Q extends BasicQuery, V, D, M extends BasicMapper<T, Q, D, V>> {
    @Resource
    public BasicDao<T, Long> basicDao;

    @Resource
    public M basicMapper;


    /**
     * 添加
     *
     * @param query {@link Q}
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/3/1
     * @since 1.0.0
     */
    @CheckParam(InsertGroup.class)
    public void insert(@Valid Q query) {
        basicDao.save(basicMapper.query2do(query));
    }

    /**
     * 删除
     *
     * @param id {@link Q}
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/3/1
     * @since 1.0.0
     */
    @CheckParam
    public void del(@NotNull Long id) {
        basicDao.deleteById(id);
    }

    /**
     * 根据用户id更新
     *
     * @param query @{@link Q}
     *
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/3/1
     * @since 1.0.0
     */
    @CheckParam(UpdateGroup.class)
    public D update(@Valid Q query) {
        Optional<T> entity = basicDao.findById(query.getId());
        if (entity.isEmpty()) {
            D dto = null;
            try {
                dto = (D) GenericSuperclassUtil.getActualTypeArgument(getClass()).newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return dto;
        }
        T saveEntity = entity.get();
        basicMapper.update(query, saveEntity);
        basicDao.save(saveEntity);
        return basicMapper.do2dto(saveEntity);
    }

    /**
     * 查询
     *
     * @param query {@link Q}
     *
     * @return com.huan.sys.provider.dto.UserRoleDto
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/3/1
     * @since 1.0.0
     */
    @CheckParam
    public D select(@NotNull Q query) {
        T queryEntity = basicMapper.query2do(query);
        Example<T> example = Example.of(queryEntity);
        Optional<T> one = basicDao.findOne(example);
        D dto = null;
        try {
            dto = basicMapper.do2dto(one.orElse((T) GenericSuperclassUtil.getActualTypeArgument(getClass()).newInstance()));
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return dto;
    }


    /**
     * 查询多条记录
     *
     * @param query {@link Q}
     *
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/2/23
     * @since 1.0.0
     */
    @CheckParam(ListGroup.class)
    public Page<T> list(@Valid Q query) {
        T user = basicMapper.query2do(query);
        Example<T> example = Example.of(user);
        PageRequest pageRequest = PageRequest.of(query.getPageNum(), query.getPageSize());
        return basicDao.findAll(example, pageRequest);
    }
}

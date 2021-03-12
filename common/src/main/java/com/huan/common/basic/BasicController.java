package com.huan.common.basic;

import com.huan.common.util.result.R;
import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;


/**
 * 基础接口管理
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/3/1
 */
@Slf4j
public class BasicController<T, Q extends BasicQuery, V, D, M extends BasicMapper<T, Q, D, V>> {
    @Resource
    private
    BasicMapper<T, Q, D, V> basicMapper;
    @Resource
    private
    BasicService<T, Q, V, D, M> basicService;


    @GlobalTransactional
    @PostMapping("insert")
    @ApiOperation(value = "添加")
    public R<Object> insert(@RequestBody Q query) {
        basicService.insert(query);
        return R.success("添加成功！");
    }


    @GlobalTransactional
    @PostMapping("del")
    @ApiOperation(value = "根据id删除")
    public R<Object> del(@NotNull Long id) {
        basicService.del(id);
        return R.success("删除成功！");
    }


    @GlobalTransactional
    @PutMapping("update")
    @ApiOperation(value = "根据id更新信息")
    public R<V> update(@RequestBody Q query) {
        return R.successWithData(basicMapper.dto2View(basicService.update(query)));
    }


    @GetMapping("select")
    @ApiOperation(value = "获取单个")
    public R<V> select(Q query) {
        return R.successWithData(basicMapper.dto2View(basicService.select(query)));
    }

    @GetMapping("list")
    @ApiOperation(value = "获取多条记录")
    public R<Page<V>> list(Q query) {
        Page<T> page = basicService.list(query);
        PageImpl<V> viewPage = new PageImpl<>(basicMapper.dto2ViewPage(page.getContent()), page.getPageable(), page.getTotalElements());
        return R.successWithData(viewPage);
    }
}

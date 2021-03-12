package com.huan.common.basic;

import com.huan.common.groups.basic.ListGroup;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 基础查询类
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/3/1
 */
@Slf4j
@Data
public class BasicQuery implements Serializable {
    private static final long serialVersionUID = -3653969528325390933L;

    private Long id;


    /**
     * 页码
     */
    @NotNull(message = "页码不能为空！", groups = {ListGroup.class})
    @Min(value = 0, message = "页码范围需大于${value}", groups = {ListGroup.class})
    private Integer pageNum;

    /**
     * 分页大小
     */
    @NotNull(message = "分页大小不能为空！", groups = {ListGroup.class})
    @Min(value = 1, message = "页码范围需大于${value}", groups = {ListGroup.class})
    @Max(value = 30, message = "页码范围需小于${value}", groups = {ListGroup.class})
    private Integer pageSize;
}

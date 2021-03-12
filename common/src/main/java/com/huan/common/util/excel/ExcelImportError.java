package com.huan.common.util.excel;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;


/**
 * 表格导入异常
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/3/4
 */
@Slf4j
@Data
class ExcelImportError implements Serializable {

    private static final long serialVersionUID = -3817619424185581671L;
    private Integer cellNumber;

    private String message;
}

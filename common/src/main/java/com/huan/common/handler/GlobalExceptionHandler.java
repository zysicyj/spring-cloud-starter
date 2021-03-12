package com.huan.common.handler;

import cn.hutool.core.io.resource.NoResourceException;
import com.huan.common.exception.*;
import com.huan.common.util.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局异常拦截
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/2/19
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 统一异常处理
     *
     * @return com.huan.common.util.result.R
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/2/19
     * @since 1.0.0
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R<String> exception(Exception e) {
        e.printStackTrace();
        return R.fail(e.getMessage());
    }

    /**
     * 资源不村在
     *
     * @return com.huan.common.util.result.R
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/2/19
     * @since 1.0.0
     */
    @ExceptionHandler(value = NoResourceException.class)
    @ResponseBody
    public R<String> exception(NoResourceException e) {
        e.printStackTrace();
        return R.fail(e.getMessage()).code(404);
    }

    /**
     * 参数异常统一处理
     *
     * @return com.huan.common.util.result.R
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/2/19
     * @since 1.0.0
     */
    @ExceptionHandler(value = CheckParamException.class)
    @ResponseBody
    public R<String> exception(CheckParamException e) {
        e.printStackTrace();
        return R.fail(e.getMessage());
    }

    /**
     * 业务异常
     *
     * @return com.huan.common.util.result.R
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/2/19
     * @since 1.0.0
     */
    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public R<String> exception(ServiceException e) {
        e.printStackTrace();
        return R.fail(e.getMessage());
    }

    /**
     * Dao层抛出的异常
     *
     * @return com.huan.common.util.result.R
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/2/19
     * @since 1.0.0
     */
    @ExceptionHandler(value = DaoException.class)
    @ResponseBody
    public R<String> exception(DaoException e) {
        e.printStackTrace();
        return R.fail(e.getMessage());
    }

    /**
     * 重复提交异常
     *
     * @return com.huan.common.util.result.R
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/2/19
     * @since 1.0.0
     */
    @ExceptionHandler(value = DuplicateSubmitException.class)
    @ResponseBody
    public R<String> exception(DuplicateSubmitException e) {
        e.printStackTrace();
        return R.fail(e.getMessage());
    }

    /**
     * 文件已存在异常
     *
     * @return com.huan.common.util.result.R
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/2/19
     * @since 1.0.0
     */
    @ExceptionHandler(value = FileExistsException.class)
    @ResponseBody
    public R<String> exception(FileExistsException e) {
        e.printStackTrace();
        return R.fail(e.getMessage());
    }

    /**
     * 版本异常
     *
     * @return com.huan.common.util.result.R
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/2/19
     * @since 1.0.0
     */
    @ExceptionHandler(value = VersionBlockedException.class)
    @ResponseBody
    public R<String> exception(VersionBlockedException e) {
        e.printStackTrace();
        return R.fail(e.getMessage());
    }


    /**
     * 属性未读取到
     *
     * @return com.huan.common.util.result.R
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/2/19
     * @since 1.0.0
     */
    @ExceptionHandler(value = NotReadablePropertyException.class)
    @ResponseBody
    public R<String> exception(NotReadablePropertyException e) {
        e.printStackTrace();
        return R.fail(e.getMessage());
    }
}

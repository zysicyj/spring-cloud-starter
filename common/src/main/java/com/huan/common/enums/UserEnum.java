package com.huan.common.enums;

/**
 * 用户枚举
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/3/1
 */
public class UserEnum {
    /**
     * 0:待激活 1:正常 2：已删除 3已禁用
     */
    public enum Status {
        NO_ACTIVE,
        OK,
        DEL,
        FORBID
    }
}

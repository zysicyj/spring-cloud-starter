package com.huan.sso.constants.serviceEnum;

/**
 * 用户状态美剧
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/1/9
 */
public class UserEnum {
    /**
     * 表示当前用户账号还未激活
     */
    public static final int UN_REGISTER = 0;

    /**
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/9
     * @since 1.0.0
     */
    public enum Status {
        /**
         * 表示当前用户账号还未激活
         */
        UN_REGISTER("0"),
        /**
         * 表示当前用户账户正常使用
         */
        USUAL("1"),
        /**
         * 表示当前用户账号已被删除，无法使用
         */
        DELETED("2"),
        /**
         * 表示当前用户被锁定，需解锁后使用
         */
        LOCKED("3");
        private final String value;

        Status(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}

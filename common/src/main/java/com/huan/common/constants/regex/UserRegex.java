package com.huan.common.constants.regex;


/**
 * 用户相关正则
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/2/19
 */
public interface UserRegex {
    /**
     * 必须包含大小写字母和数字的组合，可以使用特殊字符，长度在6-20之间
     */
    String PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$";

    /**
     * 字母开头，允许5-16字节，允许字母数字下划线
     */
    String LOGIN_NAME = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$";

    /**
     * 允许2-16字节，允许字母数字下划线汉字
     */
    String NICK_NAME = "^[\\u4e00-\\u9fa5a-zA-Z0-9_]{2,15}$";

    /**
     * 手机号码
     */
    String PHONE = "^1(3\\d|4[5-8]|5[0-35-9]|6[567]|7[01345-8]|8\\d|9[025-9])\\d{8}$";

    /**
     * 身份证
     */
    String ID_CARD_NO = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";

}

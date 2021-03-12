package com.huan.sso.util.login;

import cn.hutool.core.util.HexUtil;
import com.huan.common.util.sm2.SM2Utils;
import com.huan.common.util.sm2.Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.security.MessageDigest;

/**
 * 加密工具类
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/9
 * @since 1.0.0
 */
@Slf4j
public class PasswordUtils {

    /**
     * 校验密码是否一致
     *
     * @param password       明文密码
     * @param hashedPassword 数据库密码
     * @param salt           盐
     *
     * @return boolean
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/9
     * @since 1.0.0
     */
    public static boolean isValidPassword(String password, String hashedPassword, String salt) {
        try {
            return StringUtils.equals(password, new String((SM2Utils.decrypt(Util.hexToByte(salt), Util.hexToByte(hashedPassword)))));
        } catch (IOException e) {
            log.debug("密码解密异常！", e);
        }
        return false;
    }

    /**
     * 通过SHA1对密码进行编码
     *
     * @param password 密码
     * @param salt     盐
     *
     * @return java.lang.String
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/9
     * @since 1.0.0
     */
    public static String encodePassword(String password, String salt) {
        String encodedPassword;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            if (salt != null) {
                digest.reset();
                digest.update(salt.getBytes());
            }
            byte[] hashed = digest.digest(password.getBytes());
            int iterations = 0;
            encodedPassword = new String(HexUtil.encodeHex(hashed));
        } catch (Exception e) {
            log.error("验证密码异常:", e);
            return null;
        }
        return encodedPassword;
    }
}

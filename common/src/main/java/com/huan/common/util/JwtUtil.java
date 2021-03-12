package com.huan.common.util;

import cn.hutool.core.date.DateUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;


/**
 * JWT工具
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/2/22
 */
@Slf4j
public class JwtUtil {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = Jwts.builder()
                .setSubject("1234567890")
                .setId("68e46077-5560-4593-a603-df5c5541412b")
                .setIssuedAt(DateUtil.nextMonth())
                .setExpiration(DateUtil.nextMonth())
                .claim("password", "123456")
                .signWith(SignatureAlgorithm.HS256, "secret".getBytes(StandardCharsets.UTF_8))
                .compact();
        System.out.println(s);
    }
}

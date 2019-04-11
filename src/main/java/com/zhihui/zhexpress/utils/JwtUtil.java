package com.zhihui.zhexpress.utils;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zhihui.zhexpress.model.UserToken;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/**
 * JSON WEB TOKEN
 */
public class JwtUtil {

    public static final String KEY_TOKEN = "token";

    /**
     * 加密token
     *
     * @param id
     * @param userToken
     * @param secrete
     * @return
     */
    public static String createJWT(String id, UserToken userToken, String secrete) {

        // header Map
        Map<String, Object> map = new HashMap<>(3);
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        // build token
        // param backups {iss:Service, aud:APP}
        String token = null; // signature
        try {
            token = JWT.create().withHeader(map) // header
                    .withJWTId(id)
                    .withClaim(KEY_TOKEN, JSONObject.toJSONString(userToken))
                    .withClaim("currenttimestamp", String.valueOf(System.currentTimeMillis()))
                    .sign(Algorithm.HMAC256(secrete));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return token;
    }

    /**
     * 解密
     *
     * @param token
     * @param secrete
     * @return
     */
    public static String parseJWT(String token, String secrete) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secrete)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            // e.printStackTrace();
            // token 校验失败, 抛出Token验证非法异常
        }
        return jwt.getClaim(KEY_TOKEN).asString();
    }
}

package com.muye.utils;

import java.util.UUID;

/**
 * @Author: malz
 * @Date: 2019/7/11 15:27
 */
public class UUIDUtils {
    /**
     * 生成uuid
     * @return uuid字符串
     */
    public static String uuid(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}

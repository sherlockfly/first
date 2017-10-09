package com.drbwx.admin.utils;

import java.util.UUID;
/**
 * sessionid 生成工具类
 *
 * @author fanmq
 */
public class UniqueUtil {

    /**
     * 生成客户端标识码
     *
     * @return 客户端标识码
     */
    public static String gernateSessionId() {
        return UUID.randomUUID().hashCode() + "" + (int) (Math.random() * 10000);
    }

    /**
     * 获取缓存的类型key
     *
     * @param userId 用户ID
     * @param code   类型代码
     * @return
     */
/*    public static String getCacheKey(String userId, CacheCode cacheCode) {
        if (StringUtils.isEmpty(userId) || cacheCode == null) {
            return null;
        }
        
        return userId.hashCode() + "" + cacheCode.code.hashCode();
    }*/

}

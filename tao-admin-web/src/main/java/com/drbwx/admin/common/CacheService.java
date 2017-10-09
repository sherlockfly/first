package com.drbwx.admin.common;
/*package com.yufusoft.wireless.admin.web.common;


import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yufusoft.wireless.admin.web.cons.RespCode;
import com.yufusoft.wireless.admin.web.exceptions.BusinessRuntimeException;
import com.yufusoft.wireless.cache.rpc.FuCache;
import com.yufusoft.wireless.cache.rpc.domain.OWrapper;


*//**
 * 封装cacheService,捕获其异常并统一处理
 *
 * @author yanjiang
 *//*
@Service
public class CacheService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String sysName = "pyment_walletApp";

    @Autowired
    private FuCache fuCache;


    public Object getSystemCache(String key){
        try {
            OWrapper o = fuCache.getOWrapper(sysName, key);
            if (null == o)
                return null;
            return o.getObj();
        } catch (Exception e) {
            log.error(e.toString());
            throw new BusinessRuntimeException(RespCode.E_1005.getCode(), RespCode.E_1005.getName());
        }
    }

    public Boolean putSystemCache(String key, Serializable value) {
        OWrapper o = null;
        try {
            o = new OWrapper(value);
            return fuCache.put(sysName, key, o);
        } catch (Exception e) {
            log.error(e.toString());
            throw new BusinessRuntimeException(RespCode.E_1005.getCode(), RespCode.E_1005.getName());
        }
    }

    public Boolean putSystemCache(String key, Serializable value, int time){
        OWrapper o = null;
        try {
            o = new OWrapper(value);
            return fuCache.put(sysName, key, o, time);
        } catch (Exception e) {
            log.error(e.toString());
            throw new BusinessRuntimeException(RespCode.E_1005.getCode(), RespCode.E_1005.getName());
        }
    }

    public Object getGlobalCache(String key) {
        try {
            OWrapper o = fuCache.getOWrapper(key);
            if (null == o)
                return null;
            return o.getObj();
        } catch (Exception e) {
            log.error(e.toString());
            throw new BusinessRuntimeException(RespCode.E_1005.getCode(), RespCode.E_1005.getName());
        }
    }

    public Boolean putGlobalCache(String key, Serializable value){
        OWrapper o = null;
        try {
            o = new OWrapper(value);
            return fuCache.put(key, o);
        } catch (Exception e) {
            log.error(e.toString());
            throw new BusinessRuntimeException(RespCode.E_1005.getCode(), RespCode.E_1005.getName());
        }
    }

    public Boolean putGlobalCache(String key, Serializable value, int time){
        OWrapper o = null;
        try {
            o = new OWrapper(value);
            return fuCache.put(key, o, time);
        } catch (Exception e) {
            log.error(e.toString());
            throw new BusinessRuntimeException(RespCode.E_1005.getCode(), RespCode.E_1005.getName());
        }
    }

    public Boolean delGlobalCache(String key){
        try {
            return fuCache.del(key);
        } catch (Exception e) {
            log.error(e.toString());
            throw new BusinessRuntimeException(RespCode.E_1005.getCode(), RespCode.E_1005.getName());
        }
    }

    public Boolean delSystemCache(String key){
        try {
            return fuCache.del(sysName, key);
        } catch (Exception e) {
            log.error(e.toString());
            throw new BusinessRuntimeException(RespCode.E_1005.getCode(), RespCode.E_1005.getName());
        }
    }

    public Boolean existsSystemCache(String key){
        try {
            return fuCache.exists(sysName, key);
        } catch (Exception e) {
            log.error(e.toString());
            throw new BusinessRuntimeException(RespCode.E_1005.getCode(), RespCode.E_1005.getName());
        }
    }
    
    public boolean resetExpire(String key, int expireSeconds){
    	
    	try {
			return fuCache.resetExpire(key, expireSeconds);
		} catch (Exception e) {
			 log.error(e.toString());
            throw new BusinessRuntimeException(RespCode.E_1005.getCode(), RespCode.E_1005.getName());
		}
    	
    }

}
*/
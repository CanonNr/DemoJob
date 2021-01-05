package cn.lksun.demojob.admin.service;

import net.sf.ehcache.CacheManager;
import org.springframework.stereotype.Service;
import net.sf.ehcache.Cache;

@Service
public class CacheService {
    public Cache getCache(){
        CacheManager cacheManager = CacheManager.create();
        return cacheManager.getCache("NodeList");
    }
}


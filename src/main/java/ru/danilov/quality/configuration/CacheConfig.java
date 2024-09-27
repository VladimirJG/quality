package ru.danilov.quality.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        cacheManager.setCacheNames(getCacheNames());
        cacheManager.setAllowNullValues(false);
        return cacheManager;
    }

    private List<String> getCacheNames() {
        return List.of("linkTagCache", "metaTagCache");
    }
}

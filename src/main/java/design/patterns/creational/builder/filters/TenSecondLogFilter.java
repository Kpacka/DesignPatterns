package design.patterns.creational.builder.filters;


import java.util.concurrent.TimeUnit;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class TenSecondLogFilter implements Filter {

    static CacheLoader<String, String> loader;
    static LoadingCache<String, String> cache;

    static {
        loader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) {
                return key.toUpperCase();
            }
        };
        cache = CacheBuilder.newBuilder()
                            .expireAfterWrite(10,
                                              TimeUnit.SECONDS)
                            .build(loader);
    }

    @Override
    public boolean isLoggable(LogRecord record) {
        return cache.asMap()
                    .putIfAbsent(record.getMessage(),
                                 record.getMessage()) == null;

    }
}

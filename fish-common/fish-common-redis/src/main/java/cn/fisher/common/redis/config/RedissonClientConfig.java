package cn.fisher.common.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redisson客户端配置
 *
 * @author fisher
 */
@Configuration
public class RedissonClientConfig {

    @Autowired
    private RedisProperties redisProperties;
    @Autowired
    private RedissonConfigProperties redissonConfigProperties;

    /**
     * redisson客户端
     *
     * @return 客户端
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        SingleServerConfig singleServerConfig = redissonConfigProperties.useSingleServer();
        singleServerConfig.setDatabase(redisProperties.getDatabase());
        singleServerConfig.setAddress("redis://" + redisProperties.getHost() + ":" + redisProperties.getPort());
        singleServerConfig.setPassword(redisProperties.getPassword());
        return Redisson.create(redissonConfigProperties);
    }
}

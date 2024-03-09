package cn.fisher.common.redis.config;

import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * redisson 配置
 *
 * @author fisher
 */
@Configuration
@ConfigurationProperties("spring.redis.redisson")
public class RedissonConfigProperties extends Config {
}

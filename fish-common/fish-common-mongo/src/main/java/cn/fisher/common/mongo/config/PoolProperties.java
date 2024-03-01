package cn.fisher.common.mongo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author fisher
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb.pool")
public class PoolProperties {
    /**
     * TCP（socket）连接超时时间，毫秒
     */
    private int connectionTimeoutMs;
    /**
     * TCP（socket）连接闲置时间，毫秒
     */
    private int maxConnectionIdleTimeMs;
    /**
     * TCP（socket）连接最多可以使用多久，毫秒
     */
    private int maxConnectionLifeTimeMs;
    /**
     * TCP（socket）读取超时时间，毫秒
     */
    private int readTimeoutMs;
    /**
     * 当连接池无可用连接时客户端阻塞等待的最大时长，毫秒
     */
    private int maxWaitTimeMs;
    /**
     * 线程池允许的最大连接数
     */
    private int connectionsPerHost;
    /**
     * 线程池空闲时保持的最小连接数
     */
    private int minConnectionsPerHost;
    /**
     * 计算允许多少个线程阻塞等待时的乘数，算法：threadsAllowedToBlockForConnectionMultiplier*maxConnectionsPerHost
     */
    private int threadsAllowedToBlockForConnectionMultiplier;

}

package cn.fisher.common.mongo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author fisher
 */
@Component
public class MongoCustomerConfig {

    @Autowired
    private PoolProperties poolProperties;

    @Bean("poolSettingsProcess")
    public MongoClientSettingsBuilderCustomizer mongoClientSettingsBuilderCustomizer(){
        return builder -> {
            if (poolProperties != null){
                builder.applyToConnectionPoolSettings(setting -> {
                    setting.maxConnectionIdleTime(poolProperties.getMaxConnectionIdleTimeMs(), TimeUnit.MILLISECONDS);
                    setting.maxConnectionLifeTime(poolProperties.getMaxConnectionLifeTimeMs(), TimeUnit.MILLISECONDS);
                    setting.maxWaitTime(poolProperties.getMaxWaitTimeMs(), TimeUnit.MILLISECONDS);
                    setting.maxSize(poolProperties.getConnectionsPerHost());
                    setting.minSize(poolProperties.getMinConnectionsPerHost());
                });

                builder.applyToSocketSettings(setting -> {
                    setting.connectTimeout(poolProperties.getConnectionTimeoutMs(), TimeUnit.MILLISECONDS);
                    setting.readTimeout(poolProperties.getReadTimeoutMs(), TimeUnit.MILLISECONDS);
                });
            }
        };
    }

}

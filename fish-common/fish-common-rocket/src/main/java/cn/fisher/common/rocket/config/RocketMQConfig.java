package cn.fisher.common.rocket.config;

import lombok.Data;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "rocket")
public class RocketMQConfig {
    private String nameServer;
    private String producerGroup;

    @Bean
    public DefaultMQProducer producer() throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer();
//        producer.setProducerGroup(producerGroup);
        producer.setNamespace(nameServer);
        producer.start();
        return producer;
    }

}

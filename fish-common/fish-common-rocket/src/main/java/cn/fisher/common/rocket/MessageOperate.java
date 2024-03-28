package cn.fisher.common.rocket;

import cn.fisher.common.spring.env.SpringEnvUtil;
import cn.fisher.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * producer 的实现
 */
@Service
public class MessageOperate {

    public static final SendBack DEFAULT = new SendBack();

    @Autowired
    private SpringEnvUtil springEnvUtil;

    @Autowired
    private DefaultMQProducer producer;

    /**
     * send async
     */
    public void send(String topic, Object msg) throws RemotingException, InterruptedException, MQClientException {
        if (msg == null) {
            throw new RuntimeException("消息是空的");
        }
        if (StringUtils.isEmpty(topic)) {
            throw new RuntimeException("topic是空的");
        }
        Message message = new Message();
        if (topic.contains(":")) {
            String[] topics = topic.split(":");
            message.setTopic(topics[0]);
            message.setTags(topics[1]);
            if(springEnvUtil.isNotProd()){
                message.setTopic(topics[0] + "_" + springEnvUtil.getActive());
            }
        }
        if (StringUtils.isEmpty(message.getTopic())) {
            message.setTopic(topic);
        }
        message.setBody(JsonUtil.anyToString(msg).getBytes(StandardCharsets.UTF_8));
        producer.send(message, DEFAULT);
    }

    /**
     * 发送消息到broker
     */
    public void send(String topic, String tag, Object msg){
        if (StringUtils.isEmpty(tag)) {
            throw new RuntimeException("tag是空的");
        }

        try {
            send(topic + ":" + springEnvUtil.getActive() + "_" + tag, msg);
        } catch (Throwable e) {
            throw new RuntimeException("发送消息失败！" + e.getMessage() );
        }
    }

}

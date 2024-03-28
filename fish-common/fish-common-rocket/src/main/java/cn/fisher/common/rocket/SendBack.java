package cn.fisher.common.rocket;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;

/**
 * 发送回调的实现
 */
@Slf4j
public class SendBack implements SendCallback {
    @Override
    public void onSuccess(SendResult sendResult) {
        log.info("[rocketmq]:[{}]消息发送成功，Id:{}", sendResult.getMessageQueue().getTopic(), sendResult.getMsgId());
    }

    @Override
    public void onException(Throwable throwable) {
        log.error("[rocketmq]:消息发送异常: {}", ExceptionUtils.getStackTrace(throwable));
    }
}

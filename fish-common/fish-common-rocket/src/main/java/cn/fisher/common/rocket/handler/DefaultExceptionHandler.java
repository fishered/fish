package cn.fisher.common.rocket.handler;

import cn.fisher.common.rocket.AbstractMQListener;
import cn.fisher.common.rocket.ExceptionHandler;
import cn.fisher.common.rocket.RocketMQMessageListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultExceptionHandler implements ExceptionHandler {

    private static final String DESC = "Group:[{}]-Topic:[{}]-Tag:[{}]消息处理异常，原因:[{}],消息:[{}]";

    @Override
    public <T> void handler(Throwable throwable, AbstractMQListener<T> listener, T data) {
        throwable.printStackTrace();

        RocketMQMessageListener mqMessageListener = listener.getClass().getAnnotation(RocketMQMessageListener.class);
        if (mqMessageListener == null){
            return;
        }

        log.error(DESC,
                mqMessageListener.consumerGroup(),
                mqMessageListener.topic(),
                mqMessageListener.selectorExpression(),
                throwable.getMessage(),
                data
        );
    }
}

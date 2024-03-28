package cn.fisher.common.rocket;

import cn.fisher.common.rocket.handler.DefaultExceptionHandler;
import cn.fisher.common.spring.env.SpringEnvUtil;
import cn.fisher.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.client.consumer.MQConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 消息的抽象处理
 */
@Slf4j
public abstract class AbstractMQListener<T> implements MessageListenerConcurrently, InitializingBean, DisposableBean {

    private ExceptionHandler exceptionHandler = new DefaultExceptionHandler();

    private Class<T> targetClass;

    private DefaultLitePullConsumer consumer;;

    @Autowired
    private SpringEnvUtil springEnvUtil;

    public void setExceptionHandler(ExceptionHandler handler){
        exceptionHandler = handler;
    }

    public void handlerException(Throwable throwable, AbstractMQListener<T> listener, T data){
        exceptionHandler.handler(throwable, listener, data);
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        T data = null;
        try {
            for (MessageExt message : list){
                getTargetClass();
                data = JsonUtil.anyToData(message.getBody(), targetClass);
                if (data == null){
                    log.warn("丢弃了空的消息！{}", targetClass);
                    continue;
                }

                messageProcess(data);
            }
        } catch (Exception e) {
            exceptionHandler.handler(e, this, data);
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    /**
     * 获取目标class
     * @throws Exception
     */
    public void getTargetClass(){
        if (targetClass == null){
            return;
        }

        targetClass = (Class<T>)((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * 主要的消息处理实现
     *
     * @param data 数据
     */
    public abstract void messageProcess(T data);

    /**
     * Invoked by the containing {@code BeanFactory} on destruction of a bean.
     *
     * @throws Exception in case of shutdown errors. Exceptions will get logged
     *                   but not rethrown to allow other beans to release their resources as well.
     */
    @Override
    public void destroy() throws Exception {
        if (consumer != null){
            consumer.shutdown();
        }
    }

    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails for any other reason
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if (consumer != null){
            return;
        }
        RocketMQMessageListener rocketMQMessageListener =
                this.getClass().getDeclaredAnnotation(RocketMQMessageListener.class);
        if (rocketMQMessageListener == null) {
            throw new RuntimeException("无法绑定rocker实例");
        }

        DefaultLitePullConsumer consumer = new DefaultLitePullConsumer();
        consumer.setConsumerGroup(rocketMQMessageListener.consumerGroup());
        consumer.setPullThreadNums(rocketMQMessageListener.consumeThreadNumber());
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        String topic = rocketMQMessageListener.topic();
        consumer.subscribe(topic, "*");
        if (StringUtils.isNotBlank(topic) && springEnvUtil.isNotProd()){
            consumer.subscribe(topic + "_" + springEnvUtil.getActive(), "*");
        }
        consumer.start();
        this.consumer = consumer;
    }
}

package cn.fisher.common.biz.handler;

import cn.fisher.common.exception.BizException;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * handler 注册器
 */
public interface HandlerRegister {

    /**
     * 添加 handler
     */
    void addHandler(Handler handler);

    /**
     * 移除 handler
     */
    boolean removeHandler(Handler handler);

    /**
     * handler的处理
     */
    <E extends HandlerContext> void process(E data);

    /**
     * 是否支持操作
     */
    boolean isSupport(HandlerContext context);

    abstract class AbstractHandlerRegister implements HandlerRegister {

        /**
         * 是否允许注册
         */
        String isSupportRegisterThrowEx(Handler handler){
            if (Objects.isNull(handler)){
                throw new BizException("Handler上下文是空的");
            }
            HandlerDesc handlerDesc = Optional.ofNullable(handler.getHandlerDesc()).orElseThrow(
                    () -> new BizException("handler 描述是空的！"));
            return handlerDesc.getDesc();
        }

        /**
         * 是否允许注册
         */
        String isSupportRegisterThrowEx(HandlerContext handlerContext){
            if (Objects.isNull(handlerContext)){
                throw new BizException("Handler上下文是空的");
            }
            HandlerDesc handlerDesc = Optional.ofNullable(handlerContext.getHandlerDesc()).orElseThrow(
                    () -> new BizException("handler 描述是空的！"));
            return handlerDesc.getDesc();
        }

        /**
         * 是否允许注册带有顺序的handler
         */
        String isSupportRegisterOrderThrowEx(Handler handler){
            if (Objects.isNull(handler)){
                throw new BizException("Handler上下文是空的");
            }
            HandlerDesc handlerDesc = Optional.ofNullable(handler.getHandlerDesc()).orElseThrow(
                    () -> new BizException("handler 描述是空的！"));

            if (!(handler instanceof OrderHandler)){
                throw new BizException("当前handler只能注册orderHandler！");
            }
            return handlerDesc.getDesc();
        }

    }

    /**
     * 一对一handler 默认为一个handler只处理一个操作场景
     */
    class MapHandlerRegister extends AbstractHandlerRegister {

        private MapHandlerRegister(){}

        /**
         * instance
         */
        public final static HandlerRegister INSTANCE = new MapHandlerRegister();

        /**
         * 存储handler的对应关系
         */
        private final Map<String, Handler> handlerMap = new HashMap<>();

        /**
         * 添加handler
         */
        @Override
        public void addHandler(Handler handler) {
            String handlerDesc = isSupportRegisterThrowEx(handler);

            handlerMap.putIfAbsent(handlerDesc, handler);

//            Handler existsHandler = handlerMap.get(handlerDesc);
//            if (Optional.ofNullable(existsHandler).isPresent()){
//                return;
//            }
//            handlerMap.put(handlerDesc.getDesc(), handler);
        }

        @Override
        public boolean removeHandler(Handler handler) {
            String handlerDesc = isSupportRegisterThrowEx(handler);

            handlerMap.remove(handlerDesc);
            return true;
        }

        @Override
        public <E extends HandlerContext> void process(E data) {
            String handlerDesc = isSupportRegisterThrowEx(data);

            Handler<HandlerContext> existsHandler = handlerMap.get(handlerDesc);
            if (Objects.nonNull(existsHandler)){
                existsHandler.process(data);
                return;
            }
            throw new BizException("不支持[" + handlerDesc + "]业务");
        }

        @Override
        public boolean isSupport(HandlerContext context) {
            String handlerDesc = isSupportRegisterThrowEx(context);
            return handlerMap.containsKey(handlerDesc);
        }
    }

    /**
     * 一对多handler 默认一个handler可以处理多个场景
     */
    class ListHandlerRegister extends AbstractHandlerRegister {

        private ListHandlerRegister(){}

        public final static HandlerRegister INSTANCE = new ListHandlerRegister();

        /**
         * 存储handler的对应关系
         */
        private final Map<String, List<OrderHandler>> handlerMap = new HashMap<>();

        @Override
        public void addHandler(Handler handler) {
            String handlerDesc = isSupportRegisterOrderThrowEx(handler);

            List<OrderHandler> handlerList = handlerMap.get(handlerDesc);
            if (CollectionUtils.isEmpty(handlerList)){
                /**
                 * 如果当前没有指定handler 那么就创建一个handler
                 */
                handlerList = new ArrayList<>();
                handlerList.add((OrderHandler) handler);
                handlerMap.put(handlerDesc, handlerList);
            }
            if (handlerList.contains(handler)) {
                /**
                 * 如果当前已经存在 那么就返回
                 */
                return;
            }
            handlerList.add((OrderHandler) handler);
        }

        @Override
        public boolean removeHandler(Handler handler) {
            String handlerDesc = isSupportRegisterOrderThrowEx(handler);

            List<OrderHandler> handlerList = handlerMap.get(handlerDesc);
            if (handlerList == null) {
                return true;
            }
            return handlerList.remove(handler);
        }

        @Override
        public <E extends HandlerContext> void process(E data) {
            String handlerDesc = isSupportRegisterThrowEx(data);

            List<OrderHandler> handlerList = handlerMap.get(handlerDesc);
            if (handlerList != null) {
                handlerList.sort(Comparator.comparing(OrderHandler::getOrder));
                for (OrderHandler objectOrderHandler : handlerList) {
                    objectOrderHandler.process(data);
                }
                return;
            }
            throw new BizException("不支持[" + handlerDesc + "]业务");
        }

        @Override
        public boolean isSupport(HandlerContext context) {
            String handlerDesc = isSupportRegisterThrowEx(context);
            return handlerMap.containsKey(handlerDesc);
        }
    }


}

package cn.fisher.common.biz.handler;

/**
 * handler的抽象
 */
public interface Handler<T extends HandlerContext> {

    /**
     * handler的业务处理
     * @param handlerContext
     */
    void process(T handlerContext);

    /**
     * 获取desc
     * @return
     */
    HandlerDesc getHandlerDesc();


}

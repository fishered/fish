package cn.fisher.common.biz.handler;

/**
 * handler 的抽象处理
 * @param <E>
 */
public abstract class AbstractHandler<E extends HandlerContext> extends AutoHandlerRegister<E> implements Handler<E>{
}

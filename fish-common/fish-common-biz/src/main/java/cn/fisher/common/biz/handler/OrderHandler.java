package cn.fisher.common.biz.handler;

public interface OrderHandler<E extends HandlerContext> extends Handler<E> {

    /**
     * 添加多个handler 的执行顺序
     */
    Integer getOrder();

}

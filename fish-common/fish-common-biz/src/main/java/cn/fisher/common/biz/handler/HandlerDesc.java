package cn.fisher.common.biz.handler;

/**
 * handler的描述
 */
@FunctionalInterface
public interface HandlerDesc {

    /**
     * 每个handler获取desc的方法
     * @return
     */
    String getDesc();

}

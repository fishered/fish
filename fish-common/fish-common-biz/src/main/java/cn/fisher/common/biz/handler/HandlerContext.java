package cn.fisher.common.biz.handler;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 处理handler的上下文
 */
public interface HandlerContext {

    /**
     * 获取handler 的描述信息
     * @return
     */
    @JsonIgnore
    HandlerDesc getHandlerDesc();
}

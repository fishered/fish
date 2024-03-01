package cn.fisher.common.biz.handler;

import cn.fisher.common.exception.BizException;
import org.springframework.stereotype.Component;

/**
 * @author fisher
 *
 * handler 执行入口
 */
@Component
public class HandlerProcess {

    /**
     * 业务处理实现
     *
     * @param data 待处理的数据
     * @param <E>  类型
     */
    public <E extends HandlerContext> void process(E data) {
        if (HandlerRegister.MapHandlerRegister.INSTANCE.isSupport(data)) {
            HandlerRegister.MapHandlerRegister.INSTANCE.process(data);
            return;
        }

        if (HandlerRegister.ListHandlerRegister.INSTANCE.isSupport(data)) {
            HandlerRegister.ListHandlerRegister.INSTANCE.process(data);
            return;
        }

        throw new BizException(String.format("不支持的业务类型:[%s]", data.getHandlerDesc().getDesc()));
    }

}

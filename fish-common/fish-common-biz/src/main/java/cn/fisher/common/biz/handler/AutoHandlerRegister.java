package cn.fisher.common.biz.handler;

import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;

/**
 * 自动注册handler的抽象
 */
public abstract class AutoHandlerRegister<T extends HandlerContext> implements InitializingBean, Handler<T> {

    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     */
    @Override
    public void afterPropertiesSet() {
        /**
         * process register
         */
        if (this instanceof OrderHandler){
            HandlerRegister.ListHandlerRegister.INSTANCE.addHandler(this);
            return;
        }
        HandlerRegister.MapHandlerRegister.INSTANCE.addHandler(this);
    }
}

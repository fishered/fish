package cn.fisher.common.rocket;

/**
 * 异常的handler处理
 */
public interface ExceptionHandler {

    <T> void handler(Throwable throwable, AbstractMQListener<T> listener, T data);

}

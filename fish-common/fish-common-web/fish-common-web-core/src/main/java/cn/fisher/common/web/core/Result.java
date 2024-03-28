package cn.fisher.common.web.core;

import cn.fisher.common.web.core.data.EmptyData;
import cn.fisher.common.web.core.param.ResponseLayout;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回模型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Result<T> implements Protocol, ResponseLayout {

    @Schema(description = "响应数据")
    private T data;

    @Schema(description = "响应状态")
    private Integer code;

    @Schema(description = "响应消息")
    private String msg;

    /**
     * 单个参数的构造
     *
     * @param data   数据
     * @param status 状态
     * @param <T>    类型
     * @return 结果
     */
    public static <T> Result<T> of(T data, Status status) {
        return of(data, status.code, status.desc);
    }

    /**
     * 默认是 {@link Status#SUCCESS}状态的数据
     *
     * @param <T> 类型
     * @return 结果
     */
    public static <T> Result<T> success() {
        return data(Status.SUCCESS, null);
    }

    /**
     * 默认是 {@link Status#SUCCESS}状态的数据
     *
     * @param data 数据
     * @param <T>  类型
     * @return 结果
     */
    public static <T> Result<T> success(T data) {
        return data(Status.SUCCESS, data);
    }

    /**
     * 自定义状态的数据
     *
     * @param status 状态
     * @param data   要返回的数据
     * @param <T>    类型
     * @return 结果
     */
    public static <T> Result<T> data(Status status, T data) {
        return Result.of(data, status.code, status.desc);
    }

    /**
     * 只是一个消息
     *
     * @param msg 要返回的信息
     * @return 结果
     */
    public static Result<EmptyData> info(String msg) {
        return Result.of(null, Status.INFO.code, msg);
    }

    /**
     * 服务端异常的时候，用这个返回
     *
     * @return 结果
     */
    public static Result<EmptyData> error() {
        return Result.of(null, Status.ERROR.code, Status.ERROR.desc);
    }

    /**
     * 服务端异常的时候，用这个返回
     *
     * @return 结果
     */
    public static Result<EmptyData> error(String msg) {
        return Result.of(null, Status.ERROR.code, msg);
    }

    /**
     * 自定义状态的数据
     *
     * @param status 状态
     * @return 结果
     */
    public static Result<EmptyData> error(Status status) {
        return Result.of(null, status.code, status.desc);
    }

    /**
     * 自定义状态的数据
     *
     * @param status 状态
     * @param msg    消息
     * @return 结果
     */
    public static Result<EmptyData> error(Status status, String msg) {
        if (msg == null) {
            msg = status.desc;
        }

        return Result.of(null, status.code, msg);
    }

}

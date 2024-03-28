package cn.fisher.common.web.core;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 一些基础的状态描述
 */
public enum Status {
    /**
     * 2XX代表好运
     */
    SUCCESS(200, "请求成功"),
    /**
     * 4XX代表身份相关的状态
     */
    NO_AUTH(401, "未验证的用户"),
    INVADE(402, "无效的用户"),
    FREEZE(403, "用户被冻结"),
    USER_NOT_EXISTS(404, "用户不存在"),
    /**
     * 5XX系列都是错误或者异常
     */
    ERROR(500, "服务器异常"),
    FAILED(501, "业务执行失败"),
    /**
     * 6XX代表这是一个消息
     */
    INFO(600, ""),
    /**
     * 验证码不正确
     */
    VERIFY_CODE_EXCEPTION(701, "验证码不正确"),
    VERIFY_CODE_NOT_FOUND(702, "验证码不存在"),
    VERIFY_CODE_EXPIRE(703, "验证码过期"),
    ;

    @Schema(description = "请求代码")
    public final Integer code;
    @Schema(description = "代码描述")
    public final String desc;

    Status(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
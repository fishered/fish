package cn.fisher.common.exception;

/**
 * @author fisher
 * biz 异常
 */

public class BizException extends RuntimeException{

    private Integer code;

    public BizException(String msg){
        super(msg);
    }

    public BizException(Integer code, String msg){
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}

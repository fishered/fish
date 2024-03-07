package cn.fisher.common.oss;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author fisher
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OssResult extends OssInfo{
    /**
     * 是否上传成功
     */
    private boolean isSuccess;
    /**
     * 请求id
     */
    private String requestId;
    /**
     * url前缀
     */
    private String accessUrl;

    /**
     * 获取url路径
     * @return
     */
    public String getAccessPath(){
        if (!isSuccess){
            throw new RuntimeException("oss上传失败，获取结果失败！");
        }
        return accessUrl + getPath();
    }

}

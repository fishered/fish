package cn.fisher.common.oss.acs;

import lombok.Data;

/**
 * @author fisher
 */
@Data
public class AcsInfo {

    /**
     * 过期时间
     */
    private String expiration;

    /**
     * 安全key
     */
    private String accessKeyId;

    /**
     * 安全密钥
     */
    private String accessKeySecret;

    /**
     * 令牌
     */
    private String secretToken;

    /**
     * 访问路径
     */
    private String accessUrl;

    /**
     * 访问端点
     */
    private String endPoint;

    /**
     * bucket的名称
     */
    private String bucketName;

    /**
     * 请求ID
     */
    private String requestId;

}

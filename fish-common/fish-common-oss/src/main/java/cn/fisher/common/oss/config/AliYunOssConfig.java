package cn.fisher.common.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author fisher
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "ali.oss")
public class AliYunOssConfig {
    private String prefix;
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String securityBucketName;

    /**
     * 检查
     */
    public void check() {
        if (endpoint == null || accessKeyId == null || accessKeySecret == null || bucketName == null) {
            throw new RuntimeException("oss配置不正确");
        }
    }

    /**
     * 获取访问url
     *
     * @return 访问url
     */
    public String getAccessUrl(boolean isSecurity) {
        return prefix + "://" + selectBucketName(isSecurity) + "." + endpoint + "/";
    }

    /**
     * 获取访问url
     *
     * @return 访问url
     */
    public String getAccessUrl(String prefix, boolean isSecurity) {
        return prefix + "://" + selectBucketName(isSecurity) + "." + endpoint + "/";
    }

    /**
     * 获取bucket的名字
     *
     * @param isSecurity 是不是安全域
     * @return bucket的名字
     */
    public String selectBucketName(boolean isSecurity) {
        return isSecurity ? getSecurityBucketName() : getBucketName();
    }

}

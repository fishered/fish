package cn.fisher.common.oss.acs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author fisher
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "ali.acs")
public class AliYunAcsConfig {

    private String accessKeyId;
    private String accessKeySecret;
    private Rule rule;
    /**
     * 凭证过期时间
     */
    private Long duration = 3600L;

    @Data
    public static class Rule{
        private String arn;
        private String sessionName;

        /**
         * 客户端角色检查
         */
        public void check(){
            if (arn == null || sessionName == null){
                throw new RuntimeException("acs配置role不正确！");
            }
        }
    }

    /**
     * acs配置检查
     */
    public void check(){
        if (accessKeyId == null || accessKeySecret == null || rule == null){
            throw new RuntimeException("acs配置不正确！");
        }
        rule.check();
    }

}

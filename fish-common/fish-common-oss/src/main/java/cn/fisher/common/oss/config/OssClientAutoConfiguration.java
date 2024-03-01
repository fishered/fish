package cn.fisher.common.oss.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fisher
 */
@Configuration
public class OssClientAutoConfiguration {

    @Autowired
    private AliYunOssConfig aliYunOssConfig;

    @Bean
    public OSS ossClient(){
        if (aliYunOssConfig == null){
            throw new RuntimeException("AliYun-OSS没有正确配置！");
        }

        aliYunOssConfig.check();
        return new OSSClientBuilder().build(
            aliYunOssConfig.getEndpoint(), aliYunOssConfig.getAccessKeyId(), aliYunOssConfig.getAccessKeySecret()
        );
    }

}

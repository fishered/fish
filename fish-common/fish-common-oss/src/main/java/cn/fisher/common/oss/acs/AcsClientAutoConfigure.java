package cn.fisher.common.oss.acs;

import cn.fisher.common.oss.config.AliYunOssConfig;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fisher
 */
@Configuration
public class AcsClientAutoConfigure {

    @Autowired
    private AliYunOssConfig aliYunOssConfig;

    /**
     * acs的客户端
     * @return
     */
    @Bean
    public IAcsClient acsClient(){
        if (aliYunOssConfig == null){
            throw new RuntimeException("AliYun-ACS客户端没有被正确配置！");
        }

        aliYunOssConfig.check();
        DefaultProfile profile = DefaultProfile.getProfile(
                "", aliYunOssConfig.getAccessKeyId(), aliYunOssConfig.getAccessKeySecret()
        );
        return new DefaultAcsClient(profile);
    }

}

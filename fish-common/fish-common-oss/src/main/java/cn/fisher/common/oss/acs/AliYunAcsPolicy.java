package cn.fisher.common.oss.acs;

import cn.fisher.common.oss.Group;
import cn.fisher.common.oss.config.AliYunOssConfig;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author fisher
 * 访问策略
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AliYunAcsPolicy {

    @JsonProperty("Version")
    private String version;
    @JsonProperty("Statement")
    private List<Statement> statement;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Statement{
        /**
         * 允许的动作 oss:PutObject
         */
        @JsonProperty("Action")
        private List<String> action;
        /**
         * 资源列表
         */
        @JsonProperty("Resource")
        private List<String> resources;
        /**
         * 权限
         */
        @JsonProperty("Effect")
        private String effect = "Allow";
    }

    /**
     * 创建安全策略
     */
    public static AliYunAcsPolicy createAcsPolicy(AliYunOssConfig config, AcsAction action, boolean isSecurity, Group... groups){
        if (action == null || groups.length == 0){
            throw new RuntimeException("没有指定策略！");
        }

        List<String> resources = new ArrayList<>();
        for (Group group : groups){
            resources.add("acs:oss:*:*" + config.selectBucketName(isSecurity) + "/" + group.getGroupName());
            resources.add("acs:oss:*:*" + config.selectBucketName(isSecurity) + "/" + group.getGroupName() + "/*");
        }
        return new AliYunAcsPolicy(
                "1", List.of(new Statement(List.of("oss:" + action.name()), resources, "Allow"))
        );
    }

    /**
     * 创建上传文件的安全策略
     */
    public static AliYunAcsPolicy createPutPolicy(AliYunOssConfig config, Group... groups){
        return createAcsPolicy(config, AcsAction.PutObject, true, groups);
    }


}

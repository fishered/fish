package cn.fisher.common.oss;

import cn.fisher.common.oss.config.AliYunOssConfig;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.Date;

/**
 * @author fisher
 */
@Slf4j
@Component
public class OssService {

    @Autowired
    private OSS oss;
    @Autowired
    private AliYunOssConfig config;

    /**
     * 文件默认路径
     */
    private GeneralPath generalPath = new DefaultGeneralPath();

    /**
     * 默认的文件分组
     */
    private Group SYSTEM = () -> "system";
    private Group TEMP = () -> "temp";

    /**
     * 上传至oss
     */
    public OssResult upload(File file, Group group){
        OssResult ossResult = new OssResult();
        String name = file.getName();

        try {
            if (StringUtils.isBlank(name) || group == null){
                return ossResult;
            }

            String filePath = group.getGroupName() + generalPath.path(name);
            PutObjectResult putObjectResult = oss.putObject(config.selectBucketName(false), filePath, file);

            ossResult.setSuccess(true);
            ossResult.setRequestId(putObjectResult.getRequestId());
            ossResult.setPath(filePath);
            ossResult.setAccessUrl(config.getAccessUrl(false));
            ossResult.setOriginalFileName(name);
            ossResult.setFileName(filePath.substring(name.lastIndexOf("/")));
        } catch (Exception e) {
            log.error("[OSS]文件上传异常：文件名：{}，异常信息：{}", name, e.getMessage());
        }
        return ossResult;
    }

    public OssResult uploadToTemp(File file){
        return upload(file, TEMP);
    }

    public OssResult uploadSystem(File file){
        return upload(file, SYSTEM);
    }

    /**
     * 获取安全的url路径
     */
    public String getOssPath(String url, boolean isSecurity){
        if (StringUtils.isBlank(url)){
            throw new RuntimeException("url是空的");
        }

        String accessUrl = config.getAccessUrl(isSecurity);
        return url.substring(url.indexOf(accessUrl) + accessUrl.length());
    }

    /**
     * 获取安全的url路径
     */
    public String getSecurityUrlPath(String urlPath){
        /**
         * 设置签名过期时间
         */
        Date expire = new Date(new Date().getTime() + 60 * 60);
        URL url = oss.generatePresignedUrl(
                config.getSecurityBucketName(), getOssPath(urlPath, true), expire
        );
        return url.toString();
    }



}

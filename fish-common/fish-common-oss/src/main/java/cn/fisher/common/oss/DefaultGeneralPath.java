package cn.fisher.common.oss;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author fisher
 * 默认的生成文件路径的方法
 */
public class DefaultGeneralPath extends AbstractGeneralPath{

    @Override
    public String doPath(String sourceFileName) throws Exception {
        if (StringUtils.isBlank(sourceFileName)){
            throw new RuntimeException("原文件是空的！");
        }

        LocalDateTime time = LocalDateTime.now();
        String timePath = time.getYear() + "/" + time.getMonthValue() + "/" + time.getDayOfMonth();
        String suffix = getSuffix(sourceFileName);
        if (StringUtils.isNotBlank(suffix)){
            timePath += "/" + suffix + "/" + UUID.randomUUID() + "." + suffix;
            return timePath;
        }
        return timePath + "/" + UUID.randomUUID();
    }

    /**
     * 获取文件后缀
     */
    private static String getSuffix(String sourceFileName){
        return StringUtils.substringAfterLast(sourceFileName, ".");
    }
}

package cn.fisher.common.oss;

import org.apache.commons.lang3.StringUtils;

/**
 * @author fisher
 */
public abstract class AbstractGeneralPath implements GeneralPath{

    /**
     * 生成文件路径
     * @param sourceFileName 文件信息
     * @return 文件路径
     * @throws Exception
     */
    @Override
    public String path(String sourceFileName) throws Exception {
        if (StringUtils.isBlank(sourceFileName)){
            throw new RuntimeException("文件名是空的！");
        }

        String path = doPath(sourceFileName);
        if (StringUtils.isBlank(path)){
            throw new RuntimeException("生成的文件路径是空的！");
        }
        if (!path.startsWith("/")){
            path = "/" + path;
        }
        return path;
    }

    /**
     * 具体执行生成文件路径的方法
     * @throws Exception
     */
    public abstract String doPath(String sourceFileName) throws Exception;
}

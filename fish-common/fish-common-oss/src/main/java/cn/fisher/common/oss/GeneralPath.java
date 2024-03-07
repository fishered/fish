package cn.fisher.common.oss;

/**
 * 生成文件的路径
 */
public interface GeneralPath {

    /**
     * 获取生成文件的路径
     * @param sourceFileName
     * @return
     */
    String path(String sourceFileName) throws Exception;

}

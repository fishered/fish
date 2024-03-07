package cn.fisher.common.oss;

import lombok.Data;

/**
 * @author fisher
 */
@Data
public class OssInfo {
    /**
     * 文件路径
     */
    private String path;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 原文件名
     */
    private String originalFileName;

}

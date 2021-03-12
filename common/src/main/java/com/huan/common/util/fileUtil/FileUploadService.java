package com.huan.common.util.fileUtil;

/**
 * 文件上传服务
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/1/29
 */
public interface FileUploadService {
    /**
     * 简单的文件上传
     *
     * @param localFilePath 需要上传的文件路径
     * @param filePrefix    上传到指定的oss目录
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/29
     * @since 1.0.0
     */
    String uploadFileStream(String localFilePath, String filePrefix);
}

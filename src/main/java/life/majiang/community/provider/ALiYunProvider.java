package life.majiang.community.provider;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.exception.CustomizeException;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@Service
public class ALiYunProvider {

    private static String accessKeyId = "LTAI4Fk7P1LZg7p6Jr5YpMtn";

    private static String accessKeySecret = "VH0VnIRlnhzEhlAeiLBLCyVFlhgrMw";

    String endpoint = "http://oss-cn-beijing.aliyuncs.com";


    public String upload(InputStream inputStream, String fileName){
        /* 重新命名，避免重名 */
        String generatedFileName;
        String[] filePaths = fileName.split("\\.");
        if(filePaths.length > 1){
            generatedFileName = UUID.randomUUID().toString() + "." + filePaths[filePaths.length-1];
        }else {
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        }
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // <yourObjectName>表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        PutObjectRequest putObjectRequest = new PutObjectRequest("tremp", generatedFileName, inputStream);
        PutObjectResult response = ossClient.putObject(putObjectRequest);
        ossClient.shutdown();
        if(response != null ){
            // 设置URL过期时间为10年  3600l* 1000*24*365*10
            Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
            // 生成URL
            String url = ossClient.generatePresignedUrl("tremp", generatedFileName, expiration).toString();
            return url;
        }else {
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        }
    }
}

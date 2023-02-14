package cjj.common;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class OSSCommon {
    // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
    String endpoint = "oss-cn-guangzhou.aliyuncs.com";
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    String accessKeyId = "LTAI5tM2K6V9xT8uTraFgDEk";
    String accessKeySecret = "t4sLk32lWoGDeRhfAhwlGuzilM0YNq";
    // 填写Bucket名称，例如examplebucket。
    String bucketName = "myvueblog-cjj";

    // 创建OSSClient实例。
    OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

    public boolean createBucket(String name){
        Bucket bucket = ossClient.createBucket(bucketName);
        return true;
    }

    /*public PutObjectResult addObj(Object o,String name){
        String fullname = "avator/" + name;
        return ossClient.putObject(bucketName, fullname, new ByteArrayInputStream(o.toString().getBytes()));
    }*/

    public String ossUpload(MultipartFile file,String url) throws IOException {
        InputStream inputStream = file.getInputStream();
        ossClient.putObject(bucketName,url,inputStream);
        return "https://" + bucketName + "." + endpoint + "/" + url;
    }


    public String download(String objectName) throws IOException {
        // 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
        // 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
        InputStream content = ossObject.getObjectContent();
        BufferedReader reader = null;
        String line = "";
        if (content != null) {
            reader = new BufferedReader(new InputStreamReader(content));
            while (true) {
                line = reader.readLine();
                if (line == null) break;
                System.out.println("\n" + line);
            }
            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            content.close();
        }
        return line;
    }


    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     */
    public String getContentType(String fileName) {
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(fileExtension)) {
            return "image/jpeg";
        }
        if (".jpg".equalsIgnoreCase(fileExtension)) {
            return "image/jpg";
        }
        if (".png".equalsIgnoreCase(fileExtension)) {
            return "image/png";
        }
        if (".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if (".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if (".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if (".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if (".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        if (".mp4".equalsIgnoreCase(fileExtension)) {
            return "video/mp4";
        }
        if (".mp3".equalsIgnoreCase(fileExtension)) {
            return "audio/mp3";
        }
        return "text/html";
    }


}

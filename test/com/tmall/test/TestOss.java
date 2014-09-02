//package com.tmall.test;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//
//import com.aliyun.openservices.oss.OSSClient;
//import com.aliyun.openservices.oss.model.ObjectMetadata;
//import com.aliyun.openservices.oss.model.PutObjectResult;
//
//public class TestOss {
//
//	static String accessKeyId = "i2fJWA1XVL0nfEVp";
//	static String accessKeySecret = "QWOvWIJ4qywLyfJt9FK9wlHzlv9zdT";
//	static String bucketName = "obwq" ;
//	
//	public static void main(String[] args) throws FileNotFoundException {
//
//		// 初始化一个OSSClient
//		OSSClient client = new OSSClient(accessKeyId, accessKeySecret);
//
//		// 获取指定文件的输入流
//		File file = new File("c:/abc.jpg");
//		InputStream content = new FileInputStream(file);
//
//		// 创建上传Object的Metadata
//		ObjectMetadata meta = new ObjectMetadata();
//
//		// 必须设置ContentLength
//		meta.setContentLength(file.length());
//
//		// 上传Object.
//		PutObjectResult result = client.putObject(bucketName, "sdfsdf", content,	meta);
//
//		// 打印ETag
//		System.out.println(result.getETag());
//	}
//}

package com.tmall.PhotoMap.oss;

import java.io.FileNotFoundException;

import com.aliyun.android.oss.OSSClient;
import com.tmall.PhotoMap.util.FileUtil;

public class AliYunOss {

	static String accessKeyId = "i2fJWA1XVL0nfEVp";
	static String accessKeySecret = "QWOvWIJ4qywLyfJt9FK9wlHzlv9zdT";
	static String bucketName = "obwq" ;
	
	private static OSSClient client = new OSSClient();
	
	static {
		client.setAccessId(accessKeyId);
		client.setAccessKey(accessKeySecret);
	}
	
	public static String writeOss(String filePath) throws FileNotFoundException {
		String fileName =  filePath.substring(filePath.lastIndexOf("/")+1) ;
		byte[] b = FileUtil.file2byte(filePath);
		String result =	client.uploadObject(bucketName, fileName, b ) ;
		return result ;
	}
	
}

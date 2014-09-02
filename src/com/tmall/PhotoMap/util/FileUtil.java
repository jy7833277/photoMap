package com.tmall.PhotoMap.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUtil {
	
	/**
	 * 文件转成数组
	 * @param path
	 * @return
	 */
	public static byte[] file2byte(String path) {
		byte[] data = null;
		FileInputStream input = null;
		ByteArrayOutputStream output = null ;
		try {
			input = new FileInputStream(new File(path));
			output = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int numBytesRead = 0;
			while ((numBytesRead = input.read(buf)) != -1) {
				output.write(buf, 0, numBytesRead);
			}
			data = output.toByteArray();
			output.close();
			input.close();
		} catch (Exception e) {
		}finally{
			if(output != null){
				try {
					output.close();
				} catch (IOException e) {
				}
				output = null;
			}
			if(input != null){
				try {
					input.close();
				} catch (IOException e) {
				}
				input = null;
			}
		}
		return data;
	}
}

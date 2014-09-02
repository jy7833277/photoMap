package com.tmall.PhotoMap.common.lang;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import com.tmall.PhotoMap.DemoApplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

public abstract class AbstractActivity extends Activity {

	private String screeTag = "screeTag" ;
	
	public void initScreeParam(){
		int screenWidth  = getWindowManager().getDefaultDisplay().getWidth();     // 屏幕宽（像素，如：480px）  
		int screenHeight = getWindowManager().getDefaultDisplay().getHeight();      // 屏幕高（像素，如：800p）  
		Log.e(screeTag + "  getDefaultDisplay", "screenWidth=" + screenWidth + "; screenHeight=" + screenHeight);
		
		((DemoApplication)getApplicationContext()).addAppData(PhotoMapConstant.SCREE_WIDTH, screenWidth+"");
		((DemoApplication)getApplicationContext()).addAppData(PhotoMapConstant.SCREE_WIDTH, screenHeight+"");
	}
	
	
	@SuppressLint("SdCardPath")
	protected Object[] svaePicture(Bundle bundle) {
		new DateFormat();
		String name = DateFormat.format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA)) + ".jpg";
		Toast.makeText(this, name, Toast.LENGTH_LONG).show();
		Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式

		FileOutputStream b = null;
		// 为什么不能直接保存在系统相册位置呢
		File file = new File("/sdcard/myImage/");
		file.mkdirs();// 创建文件夹
		String fileName = "/sdcard/myImage/" + name;

		try {
			b = new FileOutputStream(fileName);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, b);// 把数据写入文件
		} catch (FileNotFoundException e) {
		} finally {
			try {
				b.flush();
				b.close();
			} catch (IOException e) {
			}
		}
		return new Object[]{fileName,bitmap};
	}
	
}

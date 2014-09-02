package com.tmall.PhotoMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.tmall.PhotoMap.view.GridViewAdapter;

public class PhotoMapAddPhotoMap extends Activity {

	private GridViewAdapter grid  = null ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_picture);
		
		//得到图片数据
		Bundle bundle = getIntent().getExtras();
		Bitmap bitmap = svaePicture(bundle);
		
		grid = new GridViewAdapter(getBaseContext(), new Bitmap[]{bitmap,bitmap,bitmap,bitmap,bitmap,bitmap,bitmap}); 
		
		GridView gv = (GridView) findViewById(R.id.grid_view);
		gv.setAdapter(grid);
		
		
		gv.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> av, View v,int p, long id) {
				  if (grid.isShowDelete()) {
					  	   grid.setIsShowDelete(Boolean.FALSE);
					  } else {
						   grid.setIsShowDelete(Boolean.TRUE);
					  }
				return false;
			}
		});
//		ImageView imageView = ((ImageView) findViewById(R.id.imageView));
//		imageView.setOnLongClickListener(new OnLongClickListener() {
//			@Override
//			public boolean onLongClick(View v) {
//				DeleteDialog();
//				return false;
//			}
//		});
//		imageView.setImageBitmap(bitmap);// 将图片显示在ImageView里  
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	
	@SuppressLint("SdCardPath")
	private Bitmap svaePicture(Bundle bundle) {
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
		return bitmap;
	}
}

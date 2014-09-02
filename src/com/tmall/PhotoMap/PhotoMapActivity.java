package com.tmall.PhotoMap;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapDoubleClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMapLongClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.tmall.PhotoMap.common.dialog.CommonDialog;
import com.tmall.PhotoMap.common.lang.AbstractActivity;
import com.tmall.PhotoMap.common.listener.ExitSystemListener;
import com.tmall.PhotoMap.common.listener.LocationListenner;
import com.tmall.PhotoMap.common.listener.MapDoubleClientListener;
import com.tmall.PhotoMap.common.listener.MapLongClickListener;
import com.tmall.PhotoMap.o.DialogDto;
import com.tmall.PhotoMap.oss.AliYunOss;

public class PhotoMapActivity extends AbstractActivity {

	// 定位相关
	LocationClient mLocClient;
	BitmapDescriptor mCurrentMarker;

	MapView mMapView;
	BaiduMap mBaiduMap;

	// UI相关
	OnCheckedChangeListener radioButtonListener;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		initBaiduMap();
		// 初始屏幕
		initScreeParam();
	}

	
	// 地图初始化
	private void initBaiduMap(){
		mMapView = (MapView) findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();
		mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(14));
		mBaiduMap.setOnMapDoubleClickListener(new MapDoubleClientListener(this)); //双击
		mBaiduMap.setOnMapLongClickListener(new MapLongClickListener());//长按
		mBaiduMap.setMyLocationEnabled(true);// 开启定位图层
		mLocClient = new LocationClient(this);// 定位初始化
		mLocClient.registerLocationListener(new LocationListenner(mMapView));
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(30000);
		mLocClient.setLocOption(option);
		mLocClient.start();
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			String sdStatus = Environment.getExternalStorageState();
			if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
				Log.i("TestFile",
						"SD card is not avaiable/writeable right now.");
				return;
			}

			Object[] object = svaePicture(data.getExtras());
			final Bitmap bitmap = (Bitmap) object[1];
			final String filePath = (String) object[0];

			final Dialog dialog = new Dialog(PhotoMapActivity.this);

			dialog.setContentView(R.layout.custom_dialog);
			dialog.setTitle(R.string.custom_dialog_titile);

			ImageView image = (ImageView) dialog.findViewById(R.id.image_view);
			image.setImageBitmap(bitmap);

			Button buttonConfire = (Button) dialog.findViewById(R.id.button_confire);
			Button buttonCancle = (Button) dialog.findViewById(R.id.button_cancle);

			buttonConfire.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					try {
						Toast.makeText(PhotoMapActivity.this,
								"confire clicked", Toast.LENGTH_LONG).show();
						AliYunOss.writeOss(filePath);
						dialog.dismiss();
					} catch (FileNotFoundException e) {
					}
				}
			});

			buttonCancle.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(PhotoMapActivity.this, "calcle clicked",
							Toast.LENGTH_LONG).show();
					dialog.dismiss();
				}
			});

			// 得到参数
			WindowManager m = getWindowManager();
			Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
			WindowManager.LayoutParams p = getWindow().getAttributes(); // 获取对话框当前的参数值
			p.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.5
			p.width = (int) (d.getWidth() * 0.9); // 宽度设置为屏幕的0.8
			dialog.getWindow().setAttributes(p);
			// 显示
			dialog.show();

			try {
				AliYunOss.writeOss(filePath);
			} catch (FileNotFoundException e) {
			}

		}
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// 退出时销毁定位
		mLocClient.stop();
		// 关闭定位图层
		mBaiduMap.setMyLocationEnabled(false);
		mMapView.onDestroy();
		mMapView = null;
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			CommonDialog.alertDialog(PhotoMapActivity.this, new DialogDto("确认退出系统吗？", "提示"), new ExitSystemListener(this));
		}
		
		return false;
	}
}

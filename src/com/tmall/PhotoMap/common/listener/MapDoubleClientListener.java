package com.tmall.PhotoMap.common.listener;

import android.content.Intent;
import android.provider.MediaStore;

import com.baidu.mapapi.map.BaiduMap.OnMapDoubleClickListener;
import com.baidu.mapapi.model.LatLng;
import com.tmall.PhotoMap.common.lang.AbstractActivity;

/**
 * 地图双击的动作
 * @author guoxing.zgx
 *
 */
public class MapDoubleClientListener implements OnMapDoubleClickListener {
	
	private AbstractActivity activity;

	public MapDoubleClientListener(AbstractActivity activity) {
		this.activity = activity;
	}
	
	@Override
	public void onMapDoubleClick(LatLng arg0) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		activity.startActivityForResult(intent, 1);
	}
}

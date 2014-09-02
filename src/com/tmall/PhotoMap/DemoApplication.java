package com.tmall.PhotoMap;

import java.util.HashMap;
import java.util.Map;

import android.app.Application;

import com.alibaba.common.lang.StringUtil;
import com.baidu.mapapi.SDKInitializer;

public class DemoApplication extends Application {

	private Map<String, String> appData = new HashMap<String, String>() ;
	
	@Override
	public void onCreate() {
		super.onCreate();
		// 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
		SDKInitializer.initialize(this);
	}
	
	public void addAppData(String key ,String value){
		if(StringUtil.isBlank(key) || StringUtil.isBlank(value))
			throw new IllegalArgumentException("key or value 为空");
		
		appData.put(key, value);
	}

	public String getAppData(String key){
		if(StringUtil.isBlank(key)){
			throw new IllegalArgumentException("key为空");
		}
		return appData.get(key);
	}
	
}
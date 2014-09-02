package com.tmall.PhotoMap.common.dialog;

import com.tmall.PhotoMap.common.lang.AbstractActivity;

import android.app.Dialog;
import android.view.Display;
import android.view.WindowManager;

public abstract class AbstractDialog {

	protected Dialog dialog ;
	
	protected AbstractActivity activity ;
	
	public abstract  void customHandler() ;
	
	public void execute(){
		// 得到参数
		WindowManager m = activity.getWindowManager();
		Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
		WindowManager.LayoutParams p = activity.getWindow().getAttributes(); // 获取对话框当前的参数值
		p.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.5
		p.width = (int) (d.getWidth() * 0.9); // 宽度设置为屏幕的0.8
		dialog.getWindow().setAttributes(p);
		// 显示
		dialog.show();
	}
}
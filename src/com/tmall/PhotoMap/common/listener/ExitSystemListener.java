package com.tmall.PhotoMap.common.listener;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.tmall.PhotoMap.common.lang.AbstractActivity;

/**
 * 系统退出
 * @author guoxing.zgx
 *
 */
public class ExitSystemListener implements DialogInterface.OnClickListener {

	private AbstractActivity activity;

	public ExitSystemListener(AbstractActivity activity) {
		this.activity = activity;
	}

	public void onClick(DialogInterface dialog, int which) {
		switch (which) {
		case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
			activity.finish();
			break;
		case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
			break;
		default:
			break;
		}
	}

}

package com.tmall.PhotoMap.common.dialog;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;

import com.tmall.PhotoMap.o.DialogDto;

public class CommonDialog {

	/**
	 * 退出系统确认框
	 */
	public  static void alertDialog(Context context , DialogDto dialogDto , OnClickListener listener) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setMessage(dialogDto.getMessage());
		builder.setTitle(dialogDto.getTitle());
		builder.setPositiveButton(dialogDto.getConfireButtonStr(), listener);
		builder.setNegativeButton(dialogDto.getCancleButtonStr(), listener);
		builder.create().show();
	}
	
	
	public void ContentDialog(Context context){
		  Dialog dialog = new Dialog(context,1);
		  
		  dialog.show();
	}
}

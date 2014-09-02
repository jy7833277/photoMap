package com.tmall.PhotoMap.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.tmall.PhotoMap.R;

public class GridViewAdapter extends BaseAdapter {
	private Bitmap imageView[];
	private Context mContext;
	private ImageView img;
	private View deleteView;
	private boolean isShowDelete;// 根据这个变量来判断是否显示删除图标，true是显示，false是不显示

	public GridViewAdapter(Context mContext,Bitmap imageView[]) {
		this.mContext = mContext;
		this.imageView = imageView;
	}

	public void setIsShowDelete(boolean isShowDelete) {
		this.isShowDelete = isShowDelete;
		notifyDataSetChanged();
	}
	
	
	@Override
	public int getCount() {
		return imageView.length;
	}

	@Override
	public Object getItem(int position) {
		return imageView[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(mContext).inflate(R.layout.fragmet_grid_item, null);
		img = (ImageView) convertView.findViewById(R.id.img);
		deleteView = convertView.findViewById(R.id.delete_markView);

		deleteView.setVisibility(isShowDelete ? View.VISIBLE : View.GONE);// 设置删除按钮是否显示
		img.setImageBitmap(imageView[position]);
		return convertView;
	}

	public boolean isShowDelete() {
		return isShowDelete;
	}

}
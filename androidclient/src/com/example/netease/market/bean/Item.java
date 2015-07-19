package com.example.netease.market.bean;

import java.io.File;
import java.io.Serializable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.example.netease.market.common.LocalDataUtils;
import com.example.netease.market.common.UrlBitmapLoader;
import com.example.netease.market.common.UrlBitmapLoader.IBitmapHolder;

public class Item implements IBitmapHolder {
	public class TYPE_MRP extends Item {

	}

	public static final int TYPE_MRP = 1;
	public static final int TYPE_APK = 2;
	
	
	public static final int T_INSTALL = 0 ;
	public static final int T_DOWNLOADING = 1 ;
	public static final int T_INSTALLED = 2 ;
	public static final int T_DOWNLOADED = 3 ;
	public static final int T_CANCELING = 4 ;
	

	public int t_install;
	public int type;
	//下载链接
	public String downloadUrl;
	//应用名
	public String title;
	//图标连接
	public String iconUrl;
	//id
	public int id;
	//介绍
	public String des;
	//包名
	public String pkg;
	//类别
	public String fl;
	//版本
	public String bb;
	//大小
	public String size;
	//星级
	public String rjxj;
	
	public int progress ;
	
	public Bitmap iconBitmap; //图标位图缓存
//	public boolean isDownloading;
	public File downLoadApk ;


	public Item(){
		
	}
	
	public Item(int type, String title, String fl, String rjxj, String size, String bb, String pkg, String iconUrl) {
		this.type = type;
		this.title = title;
		this.fl = fl;
		this.rjxj = rjxj;
		this.size = size;
		this.bb= bb;
		this.pkg = pkg;
		this.iconUrl = iconUrl;
	}
	
	public Item(int type, String downloadUrl, String title, String fl, String rjxj, String size, String bb, String iconUrl, int id, String des, String pkg) {
		super();
		this.type = type;
		this.downloadUrl = downloadUrl;
		this.title = title;
		this.fl = fl;
		this.rjxj = rjxj;
		this.size = size;
		this.bb= bb;
		this.iconUrl = iconUrl;
		this.id = id;
		this.des = des;
		this.pkg = pkg;
	}
	
	@Override
	public void setBitmap(Bitmap bitmap) {
		iconBitmap = bitmap;
		//图片本地化
		LocalDataUtils.toLocal(iconUrl, iconBitmap);
	}

	public void loadIcon(Context context, ImageView imageView) {
		if(iconUrl.startsWith("apk://")){
			iconBitmap = BitmapFactory.decodeResource(context.getResources(), Integer.parseInt(iconUrl.substring(6)));
			imageView.setImageBitmap(iconBitmap);
		}else {
			iconBitmap = LocalDataUtils.getBitmap(iconUrl);
			
			if(iconBitmap == null)
				UrlBitmapLoader.loadBitmap(context, imageView, iconUrl, this);
			else {
				iconBitmap = UrlBitmapLoader.fitDpi(context.getResources(), iconBitmap);
				imageView.setImageBitmap(iconBitmap);
			}
		}
	}

	@Override
	public String toString() {
		return "title:"+title+":t_install:"+t_install+":progress:"+progress;
	}	
}

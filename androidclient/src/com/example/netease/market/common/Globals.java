package com.example.netease.market.common;


import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;

public final class Globals {
	/**
	 * apk 下载存储路径，以 / 结尾
	 */
	public static final String APK_STORE_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/mopodown/";
	
	/**
	 * 图标缓存路径
	 */
	public static final String ICON_STORE_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/mopoicon/";
	
	/**
	 * 首页地址
	 */
	public static final String HOME_PAGE = "http://dengxiaohua.hk127.wsdns.cc/apps/index.php/welcome/api";
	
	public static final String HOME_MES = "http://dengxiaohua.hk127.wsdns.cc/apps/index.php/welcome/apimes";
	
	public static Context context;
	public static Resources res;
	
	public static void init(Context context) {
		Globals.context = context;
		res = context.getResources();
	}
}

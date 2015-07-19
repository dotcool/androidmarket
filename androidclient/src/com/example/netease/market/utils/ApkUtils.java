package com.example.netease.market.utils;

import java.io.File;

import com.example.netease.market.bean.Item;
import com.example.netease.market.common.Globals;
import com.example.netease.market.common.LocalDataUtils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;


/**
 * APK 文件操作根据
 * 
 * @author Dengxiaohua 
 *
 */
public final class ApkUtils {

	/**
	 * 检查一个 apk 是否已安装
	 * 
	 * @param context
	 * @param pkg
	 * @return
	 */
	public static boolean isInstalled(Context context, String pkg) {
		PackageManager pm = context.getPackageManager();
		try {
			PackageInfo info = pm.getPackageInfo(pkg, 1);
			if (info != null && info.activities.length > 0) {
				return true;
			}
		} catch (NameNotFoundException e) {
		}

		return false;
	}

	public static void install(Context context, File path) {
		// 代码安装
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// intent.setDataAndType(Uri.parse("file://"+fileName),
		// "application/vnd.android.package-archive");
		intent.setDataAndType(Uri.fromFile(path), "application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	/**
	 * 卸载 apk
	 * 
	 * @param context
	 * @param pkg
	 *            包名
	 */
	public static void uninstall(Context context, String pkg) {
		context.startActivity(new Intent(Intent.ACTION_DELETE, Uri.parse("package:" + pkg)));
	}

	public static void runPackge(Context context, String pkg) {
		context.startActivity(context.getPackageManager().getLaunchIntentForPackage(pkg));
	}
	
	/**
	 * 获取存储卡上 apk安装包 包信息，不要求已安装
	 * 
	 * @param context
	 * @param path
	 * @return
	 */
	public static PackageInfo getPackageInfo(Context context, String path) {
		String archiveFilePath = path;// 安装包路径
		PackageManager pm = context.getPackageManager();
		return pm.getPackageArchiveInfo(archiveFilePath, PackageManager.GET_ACTIVITIES);
	}
	
	public static ApplicationInfo getApplicationInfo(Context context, String path) {
		PackageInfo info = getPackageInfo(context, path);
		if (info != null)
			return info.applicationInfo;
		return null;
	}

	public static String getPackgeName(Context context, String path) {
		ApplicationInfo info = getApplicationInfo(context, path);
		if (info != null) 
			return info.packageName;
		
//			ApplicationInfo appInfo = info.applicationInfo;
//			String packageName = appInfo.packageName; // 得到安装包名称
			// String appName = pm.getApplicationLabel(appInfo).toString();
			// String version=info.versionName; //得到版本信息
			// Drawable icon = pm.getApplicationIcon(appInfo);//得到图标信息

		return null;
	}
	
	public static int getAppIconResId(Context context) {
		try {
			PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return info.applicationInfo.icon;
		} catch (NameNotFoundException e) {
		}
		
		return 0;
	}
	
	public static File getDownloadFile(Item item){
		String keyName = LocalDataUtils.genName(item.downloadUrl);
		//检查apk文件是否存在
		return new File(Globals.APK_STORE_PATH, keyName + ".apk");
	}
}

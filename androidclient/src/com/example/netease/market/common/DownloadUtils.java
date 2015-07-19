package com.example.netease.market.common;

import java.io.File;

import android.content.Context;
import android.widget.Toast;

import com.example.netease.market.utils.FileUtils;

/**
 * 
 * @author Dengxiaohua 
 *
 */
public final class DownloadUtils {
	
	
	public static interface DownloadListener {
		public void onStart(int total);
		public void onProgress(int cur, float prog);
		public void onCancel();
		public void onFinish();
	}

	/**
	 * 启动一个下载（异步）
	 */
	public static Downloader start(Context context, File fileSavePath, String urlString, DownloadListener listener) {
		if (!FileUtils.isSDMounted()) {
			Toast.makeText(context, "SD卡未挂载！", Toast.LENGTH_SHORT).show();
			return null;
		}
		
		Downloader downloader = new Downloader(fileSavePath, urlString, listener);
		new Thread(downloader).start();
		
		return downloader;
	}
}
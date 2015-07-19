package com.example.netease.market.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.netease.market.common.DownloadUtils.DownloadListener;

public class Downloader implements Runnable {

	public static int RW_BUF_SIZE = 32*1024;
	
	File fileSavePath;
	String urlString;
	DownloadListener listener;
	boolean canceled = false;
	int mTotalLength, mCurLength;
	
	
	public Downloader(File fileSavePath, String urlString, DownloadListener listener) {
		this.fileSavePath = fileSavePath;
		this.urlString = urlString;
		this.listener = listener;
	}

	/**
	 * 取消下载
	 */
	public void cancel() {
		canceled = true;
	}
	
	@SuppressWarnings("resource")
	@Override
	public void run() {
		InputStream is = null;
		FileOutputStream fos = null;
		
		try {
			URL url = new URL(urlString);
			
			// 创建连接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(5 * 2000); //设置超时时间
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Charser", "GBK,utf-8;q=0.7,*;q=0.3");
			
			// 判断父目录是否存在
			File dir = fileSavePath.getParentFile();
			if(!dir.exists())
				dir.mkdirs();
			
			fos = new FileOutputStream(fileSavePath);
			int read = 0;
			byte buf[] = new byte[RW_BUF_SIZE];
			
			// 获取文件大小
			mTotalLength = conn.getContentLength();
			mCurLength = 0;
			is = conn.getInputStream();
			
			//开始回调
			if(listener != null) listener.onStart(mTotalLength);

			do {
				read = is.read(buf);
				if(read <= 0){ //下完了
					
					if(listener != null) listener.onFinish();
					
					return; //下完了，直接返回
				}
				
				fos.write(buf, 0, read);
				mCurLength += read;
				
				if(listener != null) listener.onProgress(mCurLength, (mCurLength/(float)mTotalLength)*100);
				} while (!canceled);// 点击取消就停止下载
			
			if(canceled){
				if(listener != null) listener.onCancel();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (Exception e1) {
			}
			
			try {
				is.close();
			} catch (Exception e) {
			}
		}
	}
}

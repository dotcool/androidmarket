package com.example.netease.market.common;

import java.util.HashMap;

/**
 * 下载管理器
 * 
 * @author Dengxiaohua 
 *
 */
public class DownloadManager {
	private static HashMap<String, Downloader> map;
	
	public static void init() {
		map = new HashMap<String, Downloader>();
	}
	
	public static int count() {
		return map.size();
	}
	
	public static boolean exist(String key) {
		return map.containsKey(key);
	}
	
	public static /*synchronized*/ void add(String key, Downloader downloader) {
		map.put(key, downloader);
	}
	
	public static void remove(String key) {
		map.remove(key);
	}
	
	public static Downloader getDownloader(String key){
		return map.get(key) ;
	}
}

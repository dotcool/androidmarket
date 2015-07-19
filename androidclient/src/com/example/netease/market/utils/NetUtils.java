package com.example.netease.market.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;


/**
 * 网络操作工具集合
 * 
 * @author Dengxiaohua 
 *
 */
public final class NetUtils {
	public static final int NETTYPE_WIFI=0,
		NETTYPE_WAP=1, //代理
		NETTYPE_NET=2, //直连
		NETTYPE_UNKNOW=3;
	
	public static final int NET_ID_MOBILE=0,                  //移动
	   NET_ID_CN=1,          // 联通gsm
	   NET_ID_CDMA=2,       //联通CDMA
	   NET_ID_NONE=3,       //未插卡
	   NET_ID_OTHER=4;     /*其他网络*/

	public static boolean isNetConnectivity(Context context) {
		ConnectivityManager connectManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);  
        return (connectManager.getActiveNetworkInfo() != null);
	}
	
	public static boolean isWIFI(Context context) {
		return NetUtils.getNetworkType(context) == NetUtils.NETTYPE_WIFI;
	}
	
	public static int getNetworkType(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		if (connectivity != null) { 
			// 获取网络连接管理的对象
			NetworkInfo info = connectivity.getActiveNetworkInfo();

			if (info != null && info.isConnected()) {
				// 判断当前网络是否已经连接
				if (info.getState() == NetworkInfo.State.CONNECTED) {
					if(info.getType() == ConnectivityManager.TYPE_WIFI){
						Log.d("", "getNetworkType is WIFI.");
						return NETTYPE_WIFI;
					}else if(info.getType() == ConnectivityManager.TYPE_MOBILE){
						String extInfo = info.getExtraInfo();
						if(extInfo != null && extInfo.toLowerCase().contains("wap")){
							Log.d("", "getNetworkType is WAP."); 
							return NETTYPE_WAP;
						}else {
							Log.d("", "getNetworkType is NET.");
							return NETTYPE_NET;
						}
					}
				}
			}
		}
		
		return NETTYPE_UNKNOW;
	}
	
	public static int getNetworkID(Context context) {
		String str = ((TelephonyManager) context.getSystemService("phone"))
				.getSubscriberId();

		if (str == null)
			return NET_ID_OTHER; //返回 NULL 会导致未插卡不能运行

		if ((str.regionMatches(0, "46000", 0, 5))
				|| (str.regionMatches(0, "46002", 0, 5))
				|| (str.regionMatches(0, "46007", 0, 5)))
			return NET_ID_MOBILE;
		else if (str.regionMatches(0, "46001", 0, 5))
			return NET_ID_CN;
		else if (str.regionMatches(0, "46003", 0, 5))
			return NET_ID_CDMA;
		else
			return NET_ID_MOBILE; //返回 NULL 会导致未插卡不能运行
	}

	public static boolean networkCanUse(Runnable runnable) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ((Context) runnable).getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = connectivityManager.getActiveNetworkInfo();
        if (netinfo != null && netinfo.isConnected()) {
            return true;
        }
        return false;
    }
	
	/**
	 * httpURLConnection的GET连接
	 * 
	 * @param path
	 * @param map
	 * @param enCode
	 * @throws UnsupportedEncodingException
	 */
	public static String connGet(String path, Map<String, String> map,
			String enCode) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		if (map != null && !map.isEmpty()) {
			sb.append("?");
			for (Map.Entry<String, String> entry : map.entrySet()) {
				sb.append(entry.getKey()).append('=').append(
						URLEncoder.encode(entry.getValue(), "UTF-8")).append(
						'&');
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		try {
			URL url = new URL(path + sb.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(10000);
			if (conn.getResponseCode() == 200) {
				InputStream is = conn.getInputStream();
				InputStreamReader isr = new InputStreamReader(is, enCode);
				BufferedReader bfr = new BufferedReader(isr);
				String line = null;
				StringBuilder sb_result = new StringBuilder();
				while ((line = bfr.readLine()) != null) {
					sb_result.append(line);
				}
				return sb_result.toString();
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

}

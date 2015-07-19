package com.example.netease.market;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;

import com.example.netease.market.bean.Item;
import com.example.netease.market.common.Globals;
import com.example.netease.market.common.LocalDataUtils;
import com.example.netease.market.utils.ApkUtils;
import com.example.netease.market.utils.HttpUtils;
import com.example.netease.market.utils.NetUtils;

public final class ItemLoader {
	static final String TAG ="mrp" ;
	
	Handler mHandler;
	Context mContext;
	List<Item> list ;
	
	
	public ItemLoader(Handler handler, Context context) {
		mHandler = handler;
		mContext = context;
		list = new ArrayList<Item>() ;
	}

	private void parseIndex(JSONObject jsonObject) throws Exception {
		JSONArray array = jsonObject.getJSONArray("apps");
		int l = array.length();
		for (int i = 0; i < l; i++) {
			JSONObject obj = array.getJSONObject(i);
			Item item = new Item(obj.getInt("type"),
					obj.getString("url"),
					obj.getString("title"),
					obj.getString("fl"),
					obj.getString("rjxj"),
					obj.getString("size"),
					obj.getString("bb"),
					obj.getString("icon"),
					obj.getInt("id"),
					obj.getString("des"),
					obj.getString("pkg"));
			Log.d(TAG ,"parseIndex:"+item.pkg) ;
			if(ApkUtils.isInstalled(mContext, item.pkg)){
				item.t_install = Item.T_INSTALLED ;
			}else if(ApkUtils.getDownloadFile(item).exists()){
				item.t_install = Item.T_DOWNLOADED ;
			}else{
				item.t_install = Item.T_INSTALL ;
			}
			Log.d(TAG ,"parseIndex:isInstalled:"+item.t_install) ;
			item.progress = 0 ;
			list.add(item);
		}
	}
	
	private void loadIndexReal() throws Exception {
		String jsonRet = HttpUtils.get(mContext, Globals.HOME_PAGE);
		if(jsonRet != null){
			JSONObject jsonObject = new JSONObject(jsonRet);
			int ret2 = jsonObject.getInt("code");

			if(ret2 == 200){
				LocalDataUtils.toPrivate("index", jsonRet);
				
				for (int i = 0; i < list.size(); /*删到只剩3个*/) {
					list.remove(i);
				}
				parseIndex(jsonObject);

				mHandler.sendMessage(mHandler.obtainMessage(1, list));
				return; //唯一一种成功情况
			}
		}

		mHandler.sendEmptyMessage(-1);
	}
	
	
	public void loadHomePage() {
		String jsonRet = LocalDataUtils.getStringFromPrivate("index");
		if(jsonRet != null){
			try {
				parseIndex(new JSONObject(jsonRet));
			} catch (Exception e) {
				Log.e(TAG, "load HomePage from local FAIL!");
				e.printStackTrace();
			}
		}
		
		if(!NetUtils.isNetConnectivity(mContext)) //网络不可用则不去联网更新了
			return;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					long t0 = SystemClock.uptimeMillis();
					loadIndexReal();
					Log.e(TAG, "load HomePage useTime=" + (SystemClock.uptimeMillis() - t0));
				} catch (Exception e) {
					e.printStackTrace();
					
					mHandler.sendEmptyMessage(-1);
				}
			}
		}).start();
	}
}

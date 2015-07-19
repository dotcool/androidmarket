package com.example.netease.market;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.netease.market.bean.Item;
import com.example.netease.market.common.DownloadManager;
import com.example.netease.market.common.Globals;
import com.example.netease.market.utils.Constants;
import com.example.netease.market.utils.NetUtils;
import com.example.netease.market.R;

import de.greenrobot.event.EventBus;
/**
 * 
 * @author Dengxiaohua 
 *
 */

public class MarketActivity extends Activity implements Callback, OnItemClickListener, OnClickListener {
	public static final String TAG = "mrp";
	
	static Handler mHandler;
	GridAdapter adapter;
	GridView gridview;
	public static ItemLoader loader;
	Button refresh ;	
	ProgressBar progressbar;
	SwipeRefreshLayout swipe;
	RelativeLayout layout;
	private TextView news;
	
	private long currentBackPressedTime = 0;

	private Button btn1;

	private Button btn2;
	private static final int BACK_PRESSED_INTERVAL = 2000;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case 1:
			List<Item> list = (List<Item>)msg.obj ;
			adapter.setList(list) ;
			adapter.notifyDataSetChanged();
			Toast.makeText(this, "已是最新列表！", Toast.LENGTH_SHORT).show();
			progressbar.setVisibility(View.GONE);
			break;
			
		case -1:
			Toast.makeText(this, "列表更新失败，请检查您的网络是否连接正常！", Toast.LENGTH_SHORT).show();
			progressbar.setVisibility(View.GONE);
			break;

		default:
			progressbar.setVisibility(View.GONE);
			return false;
		}
		return true;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Globals.init(this);
		DownloadManager.init();
		if(!EventBus.getDefault().isRegistered(this)){
			EventBus.getDefault().register(this) ;
		}
		super.onCreate(savedInstanceState);
		ExitApplication.getInstance().addActivity(this);
		setContentView(R.layout.gridview_main);	
				
        mHandler = new Handler(this);
        gridview = (GridView) findViewById(R.id.gridview);
		adapter = new GridAdapter(this);
		loader = new ItemLoader(mHandler, this);
		gridview.setAdapter(adapter);
		gridview.setOnItemClickListener(this);
		progressbar = (ProgressBar) findViewById(R.id.progressbar);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		layout = (RelativeLayout)findViewById(R.id.layout) ;
		layout.setVisibility(View.GONE);
		news = (TextView)findViewById(R.id.news) ;
		news.setOnClickListener(this) ;	
		mTask.execute(Globals.HOME_MES);
		refresh();
			
	}
		
	//刷新列表
	public void refresh() {
		loader.loadHomePage();	
		progressbar.setVisibility(View.VISIBLE);
    }	
	
	public void onEvent(AppEvent.OnApkStartEvent e){
		Log.d(TAG ,"onEvent:OnApkStartEvent:"+e.getItem().toString()) ;
		update() ;
	}
	private void update(){
		runOnUiThread(new Runnable(){
			@Override
			public void run() {
				adapter.notifyDataSetChanged() ;
			}}) ;
	}
	public void onEvent(AppEvent.OnPkgAddedEvent e){
		Log.d(TAG ,"onEvent:OnPkgAddedEvent:"+e.getPkg()) ;
		for(Item i: adapter.getList())
		{
			Log.d(TAG ,i.pkg+":"+e.getPkg()) ;
			if(i.pkg.equals(e.getPkg())){
				i.progress = 0 ;
				i.t_install = Item.T_INSTALLED ;
				break ;
			}
		}
		update() ;
	}
	public void onEvent(AppEvent.OnPkgRemovedEvent e){
		Log.d(TAG ,"onEvent:OnPkgRemovedEvent:"+e.getPkg()) ;
		for(Item i: adapter.getList())
		{
			if(i.pkg.equals(e.getPkg())){
				i.progress = 0 ;
				i.t_install = Item.T_INSTALL ;
				break ;
			}
		}
		update() ;
	}
	
	public void onEvent(AppEvent.OnDownloadStartEvent e){
		Log.d(TAG ,"onEvent:OnDownloadStartEvent:"+e.getItem().toString()) ;
		updateList(e.getItem()) ;
		update() ;
	}
	public void onEvent(AppEvent.OnDownloadingEvent e){
		updateList(e.getItem()) ;
		Log.d(TAG ,"onEvent:OnDownloadingEvent:"+e.getItem().toString()) ;
		update() ;
	}
	public void onEvent(AppEvent.OnDownloadFinishEvent e){
		Log.d(TAG ,"onEvent:OnDownloadFinishEvent:"+e.getItem().toString()) ;
		updateList(e.getItem()) ;
		update() ;
	}
	public void onEvent(AppEvent.OnDownloadCancelEvent e){
		Log.d(TAG ,"MarketActivity:onEvent:OnDownloadCancelEvent:"+e.getItem().toString()) ;
		updateList(e.getItem()) ;
		update() ;
	}
	
	
	private void updateList(Item item){
		for(Item i: adapter.getList())
		{
			if(i.downloadUrl.equals(item.downloadUrl)){
				i.progress = item.progress ;
				i.t_install = item.t_install ;
				break ;
			}
		}
	}
	
    @Override
	protected void onPause() {
		Log.i(TAG, "onPause");
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		Log.i(TAG, "onResume");
		super.onResume();
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this) ;
		Log.v(TAG, "onDestroy");
	}
	
	//自定义滚动消息
		public AsyncTask<String, Integer, String> mTask = new AsyncTask<String, Integer, String>() {

			@Override
			protected String doInBackground(String... arg0) {
				try {
					return NetUtils.connGet(arg0[0], null, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					return null;
				}
			}

			@SuppressWarnings("unused")
			@Override
			protected void onPostExecute(String result) {
				if (result == null)
					return;
				try {
					JSONObject jo = new JSONObject(result);
					JSONArray ja = new JSONArray(jo.getString("mes"));
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < ja.length(); i++) {
						JSONObject joo = ja.optJSONObject(i);
						int id = joo.getInt("id");
						String mes = joo.getString("mes");
						sb.append(mes);
					}
					String str=sb.toString();
					if (str == null || "".equals(str)) {
//						Toast.makeText(TestMyPictrueActivity.this, "没有消息",
//								Toast.LENGTH_LONG).show();
					} else {
						showMsgDialog(news);
						layout.setVisibility(View.VISIBLE);
						news.setText(str);
						showMsgDialog(news);
					}
				} catch (JSONException e) {
					// text.setText("获取失败");
//					Toast.makeText(TestMyPictrueActivity.this, "没有消息",
//							Toast.LENGTH_LONG).show();
				}
				super.onPostExecute(result);
			}
		};
		
		//点击消息文本弹出对话框详细显示
		private void showMsgDialog(View v) {
			String str = news.getText().toString().trim();
			if (str == null || "".equals(str)) {
				return;
			}
			final MyAlertDialog ab= MyAlertDialog.getInstance(this);		
			ab.Title("公告:");                                
	        ab.TitleColor("#000000");                                 
	        ab.DividerColor("#11000011");                              
	        ab.Message(str);
	        ab.MessageColor("#FF0000");
	        ab.Icon(getResources().getDrawable(R.drawable.ic_launcher));
	        ab.isCancelableOnTouchOutside(false);
	        ab.Duration(700);
	        ab.Button1Text("知道了");
	    //    ab.setCustomView(R.layout.custom_view,v.getContext())
	        ab.setButton1Click(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	Log.i("tip","确定");
	            	ab.dismiss();	
	            }
	        })
	        .show();	
		}
	
	private void preaseItem(final int position) {
		final Item item = (Item) adapter.getItem(position);

		switch (item.type) {
		case Item.TYPE_MRP:
		case Item.TYPE_APK:
			Intent intent = new Intent(this, AppDetails.class);
			intent.putExtra(Constants.KEY_APP_URL, item.downloadUrl);
			intent.putExtra(Constants.KEY_APP_NAME, item.title);
			intent.putExtra(Constants.KEY_APP_DESCRIPTION, item.des);
			intent.putExtra(Constants.KEY_APP_RATING, item.rjxj);
			intent.putExtra(Constants.KEY_APP_SIZE, item.size);
			intent.putExtra(Constants.KEY_APP_TYPE, item.fl);
			intent.putExtra(Constants.KEY_APP_AUTHOR, item.bb);
			intent.putExtra(Constants.KEY_APP_ICON, item.iconUrl);
			intent.putExtra(Constants.KEY_APP_PKG, item.pkg);
			intent.putExtra(Constants.KEY_APP_T_STATUS, item.t_install);
			startActivity(intent);
			break;
		}
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		preaseItem(position);
	}

	
	@Override
	public void onBackPressed() {
		// 判断时间间隔
		if (System.currentTimeMillis() - currentBackPressedTime > BACK_PRESSED_INTERVAL) {
			currentBackPressedTime = System.currentTimeMillis();
			Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
		} else {
            ExitApplication.getInstance().exit(this);
		}
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn1:
			refresh();
			break ;
		case R.id.btn2:
			startActivity(new Intent(MarketActivity.this,FileexActivity.class));
			break ;
		}
	}			

}
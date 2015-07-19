package com.example.netease.market;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.netease.market.bean.Item;
import com.example.netease.market.common.DownloadManager;
import com.example.netease.market.common.DownloadUtils;
import com.example.netease.market.common.Downloader;
import com.example.netease.market.utils.ApkUtils;
import com.example.netease.market.R;

import de.greenrobot.event.EventBus;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {
//	protected static final String holder = null;
//	protected static final String textView = null;
	protected static final String TAG = "mrp";
	Activity mActivity;
	List<Item> items;
	LayoutInflater inflater;
//	ListView listView;
	ImageView imageView;
	private final int MAX_LINES = 2;
//	Activity getActivity() {
//		return mActivity;
//	}
		
	public GridAdapter(Activity activity) {
		items = new ArrayList<Item>();
		
		mActivity = activity;
		inflater = LayoutInflater.from(activity);
//		this.listView = listView;
	
	}
	
	public void setList(List<Item> items){
		this.items = items ;
	}
	
	public List<Item> getList(){
		return this.items ;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return items.get(position).id;
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		Item item = items.get(position);
		Log.d(TAG ,"getView:"+item) ;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.gridview_item, parent ,false);
			holder = new ViewHolder();
			convertView.setTag(holder);
			holder.imageView = (ImageView) convertView.findViewById(R.id.itemImage_icon);
			holder.textname = (TextView) convertView.findViewById(R.id.app_name);
			holder.installBtn = (Button) convertView.findViewById(R.id.btns);
			holder.des = (TextView) convertView.findViewById(R.id.des);
			holder.progressBar= (ProgressBar) convertView.findViewById(R.id.progressBar1);
			holder.down = (ImageView) convertView.findViewById(R.id.down);
			holder.des.setMaxLines(MAX_LINES);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (item.iconBitmap == null){
			item.loadIcon(mActivity, holder.imageView);
		}else {
			holder.imageView.setImageBitmap(item.iconBitmap);
		}
		holder.textname.setText(item.title);
		holder.des.setText(item.des);

		switch(item.t_install){
			case Item.T_DOWNLOADING:
				holder.down.setVisibility(View.GONE);
				holder.progressBar.setProgress(item.progress) ;
				holder.progressBar.setVisibility(View.VISIBLE);
				holder.des.setVisibility(View.VISIBLE) ;
				holder.des.setText(item.progress+"%") ;
				holder.des.setTextColor(Color.RED); 
				holder.installBtn.setText("取消");
				break ;
			case Item.T_CANCELING:
				holder.down.setVisibility(View.VISIBLE) ;
				holder.progressBar.setVisibility(View.GONE);
				holder.des.setVisibility(View.VISIBLE);
			holder.des.setTextColor(Color.parseColor("#696969")); 
				holder.installBtn.setText("下载");
				break ;
			case Item.T_INSTALL:
				holder.down.setVisibility(View.VISIBLE) ;
				holder.progressBar.setVisibility(View.GONE);
				holder.des.setVisibility(View.VISIBLE);
				holder.des.setTextColor(Color.parseColor("#696969")); 
				holder.installBtn.setText("下载");
				break ;
			case Item.T_INSTALLED:
				holder.down.setVisibility(View.GONE);
				holder.progressBar.setVisibility(View.GONE);
				holder.des.setVisibility(View.VISIBLE);
				holder.des.setTextColor(Color.parseColor("#696969")); 
				holder.installBtn.setText("启动");
				break ;
			case Item.T_DOWNLOADED:
				holder.down.setVisibility(View.GONE);
				holder.progressBar.setVisibility(View.GONE);
				holder.des.setVisibility(View.VISIBLE);
				holder.des.setTextColor(Color.parseColor("#696969")); 
				holder.installBtn.setText("安装");
				break ;
		}
		holder.installBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {		
				switch(items.get(position).t_install){
				case Item.T_DOWNLOADING:
					// cancel downloading
					downloadCancel(items.get(position)) ;
					break ;
				case Item.T_CANCELING:
				case Item.T_INSTALL:
					// start download
					startDownload(items.get(position)) ;
					break ;
				case Item.T_INSTALLED:
					// start apk
					startApk(items.get(position)) ;
					break ;
				case Item.T_DOWNLOADED:
					// install apk
					installApk(items.get(position)) ;
					break ;
				}
			}	
		});

		return convertView;
	}
	
	private void installApk(Item item){
		item.t_install = Item.T_DOWNLOADED ;
		item.progress = 100 ;
		EventBus.getDefault().post(new AppEvent.OnApkInstallEvent(item)) ;
		ApkUtils.install(mActivity, ApkUtils.getDownloadFile(item)) ;
	}
	
	private void startApk(Item item){
		item.t_install = Item.T_INSTALLED ;
		item.progress = 0 ;
		ApkUtils.runPackge(mActivity, item.pkg) ;
		EventBus.getDefault().post(new AppEvent.OnApkStartEvent(item)) ;
	}
	
	private void startDownload(final Item item){
		item.t_install = Item.T_DOWNLOADING ;
		item.progress = 0 ;
		EventBus.getDefault().post(new AppEvent.OnDownloadStartEvent(item)) ;
//		String keyName = LocalDataUtils.genName(item.downloadUrl);
		//检查apk文件是否存在
		final File file_apk =  ApkUtils.getDownloadFile(item) ;
		//启动下载器
		if(DownloadManager.exist(item.downloadUrl))
			return;
		Downloader downloader = DownloadUtils.start(mActivity, file_apk, item.downloadUrl, new DownloadUtils.DownloadListener() {
			
			private long lastTime ;
			@Override
			public void onStart(int total) {
				Log.d(TAG ,"Downloader:onStart:"+total) ;
			}
			
			@Override
			public void onProgress(int cur, float prog) {
				if(item.t_install != Item.T_CANCELING){
					if(System.currentTimeMillis()-lastTime >300){
						Log.d(TAG ,"Downloader:onProgress:"+prog) ;
						item.t_install = Item.T_DOWNLOADING ;
						item.progress = (int)prog ;
						lastTime = System.currentTimeMillis() ;
						EventBus.getDefault().post(new AppEvent.OnDownloadingEvent(item)) ;
					}
				}
			}
			
			@Override
			public void onFinish() {
				Log.d(TAG ,"Downloader:onFinish") ;
				item.t_install = Item.T_DOWNLOADED ;
				item.progress = 100 ;
				EventBus.getDefault().post(new AppEvent.OnDownloadFinishEvent(item)) ;
				DownloadManager.remove(item.downloadUrl);
			}
			
			@Override
			public void onCancel() {
				Log.d(TAG ,"Downloader:onCancel") ;
				file_apk.delete() ;
			}
		});
		DownloadManager.add(item.downloadUrl, downloader);
	}
	
	private void downloadCancel(Item item){
		item.t_install = Item.T_CANCELING ;
		item.progress = 0 ;
		EventBus.getDefault().post(new AppEvent.OnDownloadCancelEvent(item)) ;
		DownloadManager.getDownloader(item.downloadUrl).cancel() ;
		DownloadManager.remove(item.downloadUrl);
	}
	
	final class ViewHolder {
		TextView textname,des;
		ImageView imageView,down;
		Button installBtn;
	//	RatingBar ratingBar;
		ProgressBar progressBar;
	}		
}

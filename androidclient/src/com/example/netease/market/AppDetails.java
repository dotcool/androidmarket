package com.example.netease.market;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.netease.market.bean.Item;
import com.example.netease.market.common.DownloadManager;
import com.example.netease.market.common.DownloadUtils;
import com.example.netease.market.common.Downloader;
import com.example.netease.market.utils.ApkUtils;
import com.example.netease.market.utils.BitmapCache;
import com.example.netease.market.utils.Constants;
import com.example.netease.market.R;
import de.greenrobot.event.EventBus;
/**
 * 
 * @author Dengxiaohua 
 *
 */
public class AppDetails extends Activity implements OnClickListener {

	protected static final String TAG = "mopo";
	// apk下载地址
	private String mAppUrl;
//	 图片
	 public Bitmap iconBitmap;
	// 图片下载地址
	private String mAppIcon;
	// 应用类别
	private String mAppCategory;
	// 应用名
	private String mAppName;
	// 应用描述
	private String mAppDescription;
	// 应用版本
	private String mAppVersion;
	// 应用大小
	private String mAppSize;
	// 应用评分等级
	private String mAppLevel;
	// 应用包名
	private String mAppPkg;
	private int mAppTStatus ;

	// 下载安装按钮
	private Button mBtnDownload;
	private TextView mDownloadInfo;
	// 图标
	private ImageView mImgIcon,down;
	// 应用名组件
	private TextView mTxtAppName;	
	// 应用类型组件
	private TextView type;
	// 应用大小组件
	private TextView size;
	// 应用版本组件
	private TextView author;
	// 应用描述组件
	private TextView mTxtAppDescription;
	// 应用详情加载组件
	private TextView tv_load_more;
	// 应用星级
	private RatingBar mRatingBar;
	
	ProgressBar progressBar;
	
	private Item item ;
	
	private final int MAX_LINES = 4;
	private boolean isClickable = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_info_detail);
		Intent intent = getIntent();
		if(!EventBus.getDefault().isRegistered(this)){
			EventBus.getDefault().register(this) ;
		}
		mAppUrl = intent.getStringExtra(Constants.KEY_APP_URL);
		mAppName = intent.getStringExtra(Constants.KEY_APP_NAME);
		mAppDescription = intent.getStringExtra(Constants.KEY_APP_DESCRIPTION);
		mAppLevel = intent.getStringExtra(Constants.KEY_APP_RATING);		
		mAppCategory = intent.getStringExtra(Constants.KEY_APP_TYPE);
		mAppSize = intent.getStringExtra(Constants.KEY_APP_SIZE);
		mAppVersion = intent.getStringExtra(Constants.KEY_APP_AUTHOR);
		mAppIcon = intent.getStringExtra(Constants.KEY_APP_ICON);
		mAppPkg = intent.getStringExtra(Constants.KEY_APP_PKG);
		mAppTStatus = intent.getIntExtra(Constants.KEY_APP_T_STATUS ,Item.T_INSTALL);

		item = new Item() ;
		item.downloadUrl = mAppUrl ;
		item.title = mAppName ;
		item.des = mAppDescription ;
		item.rjxj = mAppLevel ;		
		item.fl = mAppCategory ;
		item.size = mAppSize ;
		item.bb = mAppVersion ;
		item.iconUrl = mAppIcon ;
		item.pkg = mAppPkg ;
		item.t_install = mAppTStatus ;
		
		findViewById(R.id.backa).setOnClickListener(this);
		mBtnDownload = (Button) findViewById(R.id.imdownload);
		mBtnDownload.setOnClickListener(this);
		mImgIcon = (ImageView) findViewById(R.id.itemImage_logo);
		BitmapCache bc = new BitmapCache(item.iconUrl);
		if (bc.mBitmap == null)
			bc.loadIcon(this, mImgIcon);
		else {
			mImgIcon.setImageBitmap(bc.mBitmap);
		}
		mTxtAppName = (TextView) findViewById(R.id.itemText_name);
		mTxtAppName.setText(item.title);
		mDownloadInfo = (TextView)findViewById(R.id.download_info) ;		
		type = (TextView) findViewById(R.id.itemText_type_name);
		type.setText("类型:"+item.fl);
		size = (TextView) findViewById(R.id.tv_game_size);
		size.setText("大小:" + item.size);
		author = (TextView) findViewById(R.id.tv_author);
		author.setText("版本:" + item.bb);
		mTxtAppDescription = (TextView) findViewById(R.id.tv_games_desc);
		mTxtAppDescription.setText(item.des);
		mTxtAppDescription.setMaxLines(MAX_LINES);
		tv_load_more = (TextView) findViewById(R.id.tv_loadmore);
		tv_load_more.setOnClickListener(this);
		mRatingBar = (RatingBar) findViewById(R.id.ratingBar_score);
		mRatingBar.setRating(Float.valueOf(item.rjxj));
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		down = (ImageView) findViewById(R.id.down);
	}
	
	public void onEvent(AppEvent.OnPkgAddedEvent e){
		Log.d(TAG ,"onEvent:OnPkgAddedEvent:"+e.getPkg()) ;
		if(!e.getPkg().equals(mAppPkg)){
			return ;
		}
		item.t_install = Item.T_INSTALLED ;
		progressBar.setVisibility(View.GONE) ;
		mDownloadInfo.setVisibility(View.GONE) ;
		mBtnDownload.setText("启动") ;
	}

	public void onEvent(AppEvent.OnPkgRemovedEvent e){
		Log.d(TAG ,"onEvent:OnPkgRemovedEvent:"+e.getPkg()) ;
		if(!e.getPkg().equals(mAppPkg)){
			return ;
		}
		item.t_install = Item.T_INSTALL ;
		progressBar.setVisibility(View.GONE) ;
		mDownloadInfo.setVisibility(View.GONE) ;
		mBtnDownload.setText("安装") ;
	}

	public void onEvent(AppEvent.OnDownloadStartEvent e){
		Log.d(TAG ,"onEvent:OnDownloadStartEvent:"+e.getItem().toString()) ;
		if(!e.getItem().downloadUrl.equals(mAppUrl)){
			return ;
		}
		runOnUiThread(new Runnable(){
			@Override
			public void run() {
				progressBar.setVisibility(View.VISIBLE) ;
				mDownloadInfo.setVisibility(View.VISIBLE) ;
				mDownloadInfo.setText("0%") ;
				mBtnDownload.setText("取消") ;
			}}) ;
	}
	public void onEvent(AppEvent.OnDownloadingEvent e){
		Log.d(TAG ,"onEvent:OnDownloadingEvent:"+e.getItem().toString()) ;
		if(!e.getItem().downloadUrl.equals(mAppUrl)){
			return ;
		}
		final int progress = e.getItem().progress ;
		runOnUiThread(new Runnable(){
			@Override
			public void run() {				 
				progressBar.setVisibility(View.VISIBLE) ;
				mDownloadInfo.setVisibility(View.VISIBLE) ;
				mDownloadInfo.setText(progress+"%") ;
				mBtnDownload.setText("取消") ;}});
	}
	public void onEvent(AppEvent.OnDownloadFinishEvent e){
		Log.d(TAG ,"onEvent:OnDownloadFinishEvent:"+e.getItem().toString()) ;
		if(!e.getItem().downloadUrl.equals(mAppUrl)){
			return ;
		}
		runOnUiThread(new Runnable(){
			@Override
			public void run() {
				progressBar.setVisibility(View.GONE) ;
				mDownloadInfo.setVisibility(View.GONE) ;
				mBtnDownload.setText("安装") ;}});
	}
	public void onEvent(AppEvent.OnDownloadCancelEvent e){
		Log.d(TAG ,"AppDetails:onEvent:OnDownloadCancelEvent:"+e.getItem().toString()) ;
		if(!e.getItem().downloadUrl.equals(mAppUrl)){
			return ;
		}
		runOnUiThread(new Runnable(){
			@Override
			public void run() {
				progressBar.setVisibility(View.GONE) ;
				mDownloadInfo.setVisibility(View.GONE) ;
				mBtnDownload.setText("下载") ;}});
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		switch(item.t_install){
		case Item.T_CANCELING:
			down.setVisibility(View.GONE);
			progressBar.setVisibility(View.GONE) ;
			mDownloadInfo.setVisibility(View.GONE) ;
			mBtnDownload.setText("下载") ;
			break ;
		case Item.T_DOWNLOADED:
			down.setVisibility(View.GONE);
			progressBar.setVisibility(View.GONE) ;
			mDownloadInfo.setVisibility(View.GONE) ;
			mBtnDownload.setText("安装") ;
			break ;
		case Item.T_DOWNLOADING:
			down.setVisibility(View.VISIBLE) ;
			progressBar.setVisibility(View.VISIBLE) ;
			mDownloadInfo.setVisibility(View.VISIBLE) ;
			mBtnDownload.setText("取消") ;
			break ;
		case Item.T_INSTALL:
			down.setVisibility(View.VISIBLE) ;
			progressBar.setVisibility(View.GONE) ;
			mDownloadInfo.setVisibility(View.GONE) ;
			mBtnDownload.setText("下载") ;
			break ;
		case Item.T_INSTALLED:
			down.setVisibility(View.GONE);
			progressBar.setVisibility(View.GONE) ;
			mDownloadInfo.setVisibility(View.GONE) ;
			mBtnDownload.setText("启动") ;
			break ;
		}
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {		
		case R.id.imdownload:
			switch(item.t_install){
			case Item.T_DOWNLOADING:
				// cancel downloading
				downloadCancel(item) ;
				break ;
			case Item.T_CANCELING:
			case Item.T_INSTALL:
				// start download
					startDownload(item) ;			
				break ;
			case Item.T_INSTALLED:
				// start apk
				startApk(item) ;
				break ;
			case Item.T_DOWNLOADED:
				// install apk
				installApk(item) ;
				break ;
			}
			break;
		case R.id.tv_loadmore:
			if (!isClickable) {
				mTxtAppDescription.setMaxLines(mTxtAppDescription.getLineCount());
				tv_load_more.setText("收起");
				isClickable = true;
			} else {
				mTxtAppDescription.setMaxLines(MAX_LINES);
				tv_load_more.setText("详情>");
				isClickable = false;
			}
			break;
		case R.id.backa:
			finish();
			break;
		default:
			break;
		}
	}				
					
	private void installApk(Item item){						
		item.t_install = Item.T_DOWNLOADED ;
		item.progress = 100 ;
		EventBus.getDefault().post(new AppEvent.OnApkInstallEvent(item)) ;
		ApkUtils.install(this, ApkUtils.getDownloadFile(item)) ;
	}
	
	private void startApk(Item item){
		item.t_install = Item.T_INSTALLED ;
		item.progress = 0 ;
		ApkUtils.runPackge(this, item.pkg) ;
		EventBus.getDefault().post(new AppEvent.OnApkStartEvent(item)) ;
	}
	
	private void startDownload(final Item item) {
		item.t_install = Item.T_DOWNLOADING ;
		item.progress = 0 ;
		EventBus.getDefault().post(new AppEvent.OnDownloadStartEvent(item)) ;
//		String keyName = LocalDataUtils.genName(item.downloadUrl);
		//检查apk文件是否存在
		final File file_apk =  ApkUtils.getDownloadFile(item) ;
		//启动下载器
		if(DownloadManager.exist(item.downloadUrl))
			return;
		Downloader downloader = DownloadUtils.start(this, file_apk, item.downloadUrl, new DownloadUtils.DownloadListener() {
			
			private long lastTime ;
			@Override
			public void onStart(int total) {
				Log.d(TAG ,"Downloader:onStart:"+total) ;
			}
			
			@Override
			public void onProgress(int cur, float prog) {					
				if(item.t_install != Item.T_CANCELING){
					if(System.currentTimeMillis()-lastTime >300){
							progressBar.setMax(item.progress);
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

	@Override
	protected void onDestroy(){
		super.onDestroy() ;
		EventBus.getDefault().unregister(this) ;
	}
}

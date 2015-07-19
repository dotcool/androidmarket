package com.example.netease.market;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.netease.market.MarketActivity;
import com.example.netease.market.R;
import com.example.netease.market.common.Globals;
/**
 * 
 * @author Dengxiaohua 
 *
 */
public class FileexActivity extends Activity implements OnClickListener {
	
	private List<String> items = null;
	private List<String> paths = null;
	private String curPath = "/";
	TextView mPath;
	private ListView listView;
    List<PackageInfo> pakeageinfo;
	private TextView nofile;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fileex_main);
		listView = (ListView) findViewById(R.id.listView1);
		nofile = (TextView) findViewById(R.id.nofile);
		nofile.setVisibility(View.GONE);
		nofile.setOnClickListener(this);
		
		findViewById(R.id.btn1).setOnClickListener(this);
		findViewById(R.id.btn2).setOnClickListener(this);		
		
		pakeageinfo = getPackageManager().getInstalledPackages(0);
		refresh();// 进入系统根目录
			
		/*** 处理listV.Item点击事件，并根据不同情况更新文本框内容、显示与隐藏按键 ***/
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@SuppressWarnings("unused")
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if (paths.get(position).toString().equals("!.02un")) {
					listView.setAdapter(new MyAdapter(FileexActivity.this,
							items, paths));
				} else {
					File file = new File(paths.get(position));
					if (file.isDirectory()) {
						getResources().getDrawable(
								R.drawable.ic_launcher);
					//	imageView.setImageDrawable(apk_icon);
						curPath = paths.get(position);
						getFileDir(paths.get(position));
					} else {						
						curPath = paths.get(position);
						if (curPath.endsWith(".apk")) {
							ApplicationInfo applicationInfo = null;
							try {
								applicationInfo = (getPackageManager()
										.getPackageArchiveInfo(paths.get(position).toString(),
												PackageManager.GET_ACTIVITIES)).applicationInfo;
							if ("apk" != null) {
								Intent intent = new Intent(Intent.ACTION_VIEW);
								intent.setDataAndType(
										Uri.fromFile(new File(curPath)),
										"application/vnd.android.package-archive");
								startActivity(intent);
							}
							} catch (Exception e) {
								FileexActivity.this.deleteshow(file);
							}							
						}

					}
				}
			}

		});
	}

	protected void deleteshow(final File file) {
		final MyAlertDialog ab= MyAlertDialog.getInstance(this);		
		ab.Title("提示!");                                
        ab.TitleColor("#000000");                                 
        ab.DividerColor("#11000011");                              
        ab.Message("安装包不完整无法进行安装，原因可能是后台正在下载该应用"+ (file.isDirectory() ? "文件夹" : "文件")+"或安装包已损坏，"+"您可以选择以下操作...");
        ab.MessageColor("#ff0000");
        ab.Icon(getResources().getDrawable(R.drawable.ic_launcher));
        ab.isCancelableOnTouchOutside(false);
        ab.Duration(700);
        ab.Button1Text("删除文件"); 
        ab.Button2Text("继续等待");
    //    ab.setCustomView(R.layout.custom_view,v.getContext())
        ab.setButton1Click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Log.i("tip","返回应用程序");
            	ab.dismiss();
            	if (file.isFile()) {
					file.delete();// 是文件则直接删除
				} else {
					FileDelete.deleteFolder(file);// 是文件夹则用这个方法删除
				}
				refresh();				
            }
        });
        ab.setButton2Click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Log.i("tip","返回应用程序");
            	ab.dismiss();
            	refresh();
            }
        })
        .show();	
	}			

	public void refresh() {
		getFileDir(Globals.APK_STORE_PATH);
		
	}

	/*** 处理文件遍历，并根据class　MyAdapter定义格式显示到listV ***/
	private void getFileDir(String onItemClickListener) {
		items = new ArrayList<String>();
		paths = new ArrayList<String>();

		File f = new File(onItemClickListener);
		File[] files = f.listFiles();

		if (files != null) {
			Arrays.sort(files, new MyFileSorter());// 可行，字母降序，大小写敏感，但可自己加排序规则。
			// File [] dfiles=listSortedFiles(f);//排序，字母降序
			for (int i = 0; i < files.length; i++) {
				File file = files[i];

				if (file.getPath().endsWith(".apk")) {
					items.add(file.getName());
					paths.add(file.getPath());
				}
			}
	//	} else {
	//		Toast.makeText(this, "读取错误", Toast.LENGTH_LONG).show();
		}
		if (paths.size() == 0) {
			nofile.setVisibility(View.VISIBLE);
		}
		listView.setAdapter(new MyAdapter(this, items, paths));
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {		
		case R.id.btn1:			
			onBackPressed();			
			break;
		case R.id.btn2:	
			refresh();
			break;
		case R.id.nofile:	
			refresh();
			break;
		}		
	}			

	//退出应用
	@Override
	public void onBackPressed() {
		MarketActivity.loader.loadHomePage();
		finish();
	}
	
	private void initDeleteDialog(final File file) {
		final MyAlertDialog ab= MyAlertDialog.getInstance(this);		
		ab.Title("提示!");                                
        ab.TitleColor("#000000");                                 
        ab.DividerColor("#11000011");                              
        ab.Message("您确定要删除该" + (file.isDirectory() ? "文件夹" : "文件") + "吗?");
        ab.MessageColor("#000000");
        ab.Icon(getResources().getDrawable(R.drawable.ic_launcher));
        ab.isCancelableOnTouchOutside(false);
        ab.Duration(700);
        ab.Button1Text("确定"); 
        ab.Button2Text("取消");
    //    ab.setCustomView(R.layout.custom_view,v.getContext())
        ab.setButton1Click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Log.i("tip","返回应用程序");
            	ab.dismiss();
            	if (file.isFile()) {
					file.delete();// 是文件则直接删除
				} else {
					FileDelete.deleteFolder(file);// 是文件夹则用这个方法删除
				}
				refresh();				
            }
        });
        ab.setButton2Click(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	Log.i("tip","返回应用程序");
            	ab.dismiss();
            }
        })
        .show();	
	}			
	
	public class MyAdapter extends BaseAdapter {
		private LayoutInflater mInflater;

		public MyAdapter(Context context, List<String> it, List<String> pa) {
			mInflater = LayoutInflater.from(context);

		}

		private class ViewHolder {
			TextView text, text1, text2;
			ImageView myicon;
			Button btn;
		}

		public int getCount() {
			return items.size();
		}

		public Object getItem(int position) {
			return items.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ViewHolder holder;

			final File file = new File(paths.get(position).toString());
			
			holder = new ViewHolder();
			if (convertView == null) {
				
				convertView = mInflater.inflate(R.layout.fileex_item, null);				
				holder.text = (TextView) convertView
						.findViewById(R.id.textView1);
				holder.text1 = (TextView) convertView
						.findViewById(R.id.textView2);
				holder.text2 = (TextView) convertView
						.findViewById(R.id.textView3);
				holder.myicon = (ImageView) convertView
						.findViewById(R.id.imageView1);
				holder.btn = (Button) convertView
						.findViewById(R.id.btn_uninstall);
				holder.btn.setOnClickListener(new View.OnClickListener() {
					public void onClick(View paramAnonymousView) {
						FileexActivity.this.initDeleteDialog(file);
					}
				});
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.text1.setText(file.getName());
			holder.text2.setText(coverSize(file.length()));
			if (file.isDirectory()) {
			} else if (file.getName().endsWith(".apk")) {
				ApplicationInfo applicationInfo = null;
				try {
					applicationInfo = (getPackageManager()
							.getPackageArchiveInfo(paths.get(position).toString(),
									PackageManager.GET_ACTIVITIES)).applicationInfo;
				} catch (Exception e) {
					holder.text.setText("不完整的安装包");
					holder.text.setTextColor(Color.RED);
				}
				if (applicationInfo != null) {
					/** 获取apk的图标 */
					applicationInfo.sourceDir = paths.get(position).toString();
					applicationInfo.publicSourceDir = paths.get(position)
							.toString();
					holder.myicon.setImageDrawable(getPackageManager()
							.getApplicationIcon(applicationInfo));
					holder.text.setText((String) getPackageManager()
							.getApplicationLabel(applicationInfo));
				}
			}

			return convertView;
		}

	}

	@SuppressLint("DefaultLocale")
	private String coverSize(long size) {
		String s = "";

		if (size < 1024)
			s += size + "b";
		else if (size < 1024 * 1024) {
			s = String.format("%.2f K", size / 1024f);
		} else if (size < 1024 * 1024 * 1024) {
			s = String.format("%.2f M", size / 1024 / 1024f);
		} else {
			s = String.format("%.2f G", size / 1024 / 1024 / 1024f);
		}

		return s;
	}

}
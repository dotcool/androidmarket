package com.example.netease.market.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.example.netease.market.common.LocalDataUtils;
import com.example.netease.market.common.UrlBitmapLoader;
import com.example.netease.market.common.UrlBitmapLoader.IBitmapHolder;
import com.example.netease.market.R;

public class BitmapCache implements IBitmapHolder {

	private String mIconUrl;
	public Bitmap mBitmap;

	public BitmapCache(String url) {
		mIconUrl = url;
	}

	@Override
	public void setBitmap(Bitmap bitmap) {
		mBitmap = bitmap;
		LocalDataUtils.toLocal(mIconUrl, mBitmap);
	}

	public void loadIcon(Context context, ImageView imageView) {
		if (mIconUrl != null) {
			if (mIconUrl.startsWith("apk://")) {
				mBitmap = BitmapFactory.decodeResource(context.getResources(),
						Integer.parseInt(mIconUrl.substring(6)));
				imageView.setImageBitmap(mBitmap);
			} else {
				mBitmap = LocalDataUtils.getBitmap(mIconUrl);

				if (mBitmap == null)
					UrlBitmapLoader.loadBitmap(context, imageView, mIconUrl,
							this);
				else {
					mBitmap = UrlBitmapLoader.fitDpi(context.getResources(),
							mBitmap);
					imageView.setImageBitmap(mBitmap);
				}
			}
		} else {
			imageView.setImageResource(R.drawable.icon_empty);
		}
	}

}

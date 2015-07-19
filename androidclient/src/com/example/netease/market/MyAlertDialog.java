package com.example.netease.market;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * 
 * @author Dengxiaohua 
 *
 */
public class MyAlertDialog extends Dialog implements DialogInterface {

	private final String defTextColor = "#FFFFFFFF";

	private final String defDividerColor = "#11000000";

	private final String defMsgColor = "#FFFFFFFF";

	private final String defDialogColor = "#FFE74C3C";

	private static Context tmpContext;

	private LinearLayout mLinearLayoutView;

	private RelativeLayout mRelativeLayoutView;

	private LinearLayout mLinearLayoutMsgView;

	private LinearLayout mLinearLayoutTopView;

	private FrameLayout mFrameLayoutCustomView;

	private View mDialogView;

	private View mDivider;

	private TextView mTitle;

	private TextView mMessage;

	private ImageView mIcon;

	private Button mButton1;

	private Button mButton2;

	private boolean isCancelable = true;

	private static MyAlertDialog instance;

	public MyAlertDialog(Context context) {
		super(context);
		init(context);

	}

	public MyAlertDialog(Context context, int theme) {
		super(context, theme);
		init(context);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WindowManager.LayoutParams params = getWindow().getAttributes();
		params.height = ViewGroup.LayoutParams.MATCH_PARENT;
		params.width = ViewGroup.LayoutParams.MATCH_PARENT;
		getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

	}

	public static MyAlertDialog getInstance(Context context) {

		if (instance == null || !tmpContext.equals(context)) {
			synchronized (MyAlertDialog.class) {
				if (instance == null || !tmpContext.equals(context)) {
					instance = new MyAlertDialog(context, R.style.dialog_untran);
				}
			}
		}
		tmpContext = context;
		return instance;

	}

	private void init(Context context) {

		mDialogView = View.inflate(context, R.layout.custom_dialog, null);

		mLinearLayoutView = (LinearLayout) mDialogView.findViewById(R.id.parentPanel);
		mRelativeLayoutView = (RelativeLayout) mDialogView.findViewById(R.id.main);
		mLinearLayoutTopView = (LinearLayout) mDialogView.findViewById(R.id.topPanel);
		mLinearLayoutMsgView = (LinearLayout) mDialogView.findViewById(R.id.contentPanel);
		mFrameLayoutCustomView = (FrameLayout) mDialogView.findViewById(R.id.customPanel);

		mTitle = (TextView) mDialogView.findViewById(R.id.alertTitle);
		mMessage = (TextView) mDialogView.findViewById(R.id.message);
		mIcon = (ImageView) mDialogView.findViewById(R.id.icon);
		mDivider = mDialogView.findViewById(R.id.titleDivider);
		mButton1 = (Button) mDialogView.findViewById(R.id.button1);
		mButton2 = (Button) mDialogView.findViewById(R.id.button2);

		setContentView(mDialogView);
		mRelativeLayoutView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (isCancelable)
					dismiss();
			}
		});
	}

	public void toDefault() {
		mTitle.setTextColor(Color.parseColor(defTextColor));
		mDivider.setBackgroundColor(Color.parseColor(defDividerColor));
		mMessage.setTextColor(Color.parseColor(defMsgColor));
		mLinearLayoutView.setBackgroundColor(Color.parseColor(defDialogColor));
	}

	public MyAlertDialog DividerColor(String colorString) {
		mDivider.setBackgroundColor(Color.parseColor(colorString));
		return this;
	}

	public MyAlertDialog DividerColor(int color) {
		mDivider.setBackgroundColor(color);
		return this;
	}

	public MyAlertDialog Title(CharSequence title) {
		toggleView(mLinearLayoutTopView, title);
		mTitle.setText(title);
		return this;
	}

	public MyAlertDialog TitleColor(String colorString) {
		mTitle.setTextColor(Color.parseColor(colorString));
		return this;
	}

	public MyAlertDialog TitleColor(int color) {
		mTitle.setTextColor(color);
		return this;
	}

	public MyAlertDialog Message(int textResId) {
		toggleView(mLinearLayoutMsgView, textResId);
		mMessage.setText(textResId);
		return this;
	}

	public MyAlertDialog Message(CharSequence msg) {
		toggleView(mLinearLayoutMsgView, msg);
		mMessage.setText(msg);
		return this;
	}

	public MyAlertDialog MessageColor(String colorString) {
		mMessage.setTextColor(Color.parseColor(colorString));
		return this;
	}

	public MyAlertDialog MessageColor(int color) {
		mMessage.setTextColor(color);
		return this;
	}

	public MyAlertDialog DialogColor(String colorString) {
		mLinearLayoutView.getBackground().setColorFilter(ColorUtils.getColorFilter(Color.parseColor(colorString)));
		return this;
	}

	public MyAlertDialog DialogColor(int color) {
		mLinearLayoutView.getBackground().setColorFilter(ColorUtils.getColorFilter(color));
		return this;
	}

	public MyAlertDialog Icon(int drawableResId) {
		mIcon.setImageResource(drawableResId);
		return this;
	}

	public MyAlertDialog Icon(Drawable icon) {
		mIcon.setImageDrawable(icon);
		return this;
	}

	public MyAlertDialog Duration(int duration) {
		return this;
	}

	public MyAlertDialog ButtonDrawable(int resid) {
		mButton1.setBackgroundResource(resid);
		mButton2.setBackgroundResource(resid);
		return this;
	}

	public MyAlertDialog Button1Text(CharSequence text) {
		mButton1.setVisibility(View.VISIBLE);
		mButton1.setText(text);

		return this;
	}

	public MyAlertDialog Button2Text(CharSequence text) {
		mButton2.setVisibility(View.VISIBLE);
		mButton2.setText(text);
		return this;
	}

	public MyAlertDialog setButton1Click(View.OnClickListener click) {
		mButton1.setOnClickListener(click);
		return this;
	}

	public MyAlertDialog setButton2Click(View.OnClickListener click) {
		mButton2.setOnClickListener(click);
		return this;
	}

	public MyAlertDialog setCustomView(int resId, Context context) {
		View customView = View.inflate(context, resId, null);
		if (mFrameLayoutCustomView.getChildCount() > 0) {
			mFrameLayoutCustomView.removeAllViews();
		}
		mFrameLayoutCustomView.addView(customView);
		return this;
	}

	public MyAlertDialog setCustomView(View view, Context context) {
		if (mFrameLayoutCustomView.getChildCount() > 0) {
			mFrameLayoutCustomView.removeAllViews();
		}
		mFrameLayoutCustomView.addView(view);

		return this;
	}

	public MyAlertDialog isCancelableOnTouchOutside(boolean cancelable) {
		this.isCancelable = cancelable;
		this.setCanceledOnTouchOutside(cancelable);
		return this;
	}

	public MyAlertDialog isCancelable(boolean cancelable) {
		this.isCancelable = cancelable;
		this.setCancelable(cancelable);
		return this;
	}

	private void toggleView(View view, Object obj) {
		if (obj == null) {
			view.setVisibility(View.GONE);
		} else {
			view.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void show() {
		super.show();
	}

	@Override
	public void dismiss() {
		super.dismiss();
		mButton1.setVisibility(View.GONE);
		mButton2.setVisibility(View.GONE);
	}
	
	@Override    
	public boolean onKeyDown(int keyCode, KeyEvent event) {  
	if(keyCode == KeyEvent.KEYCODE_BACK){      
	return  true;
	}  
	return  super.onKeyDown(keyCode, event);     

	} 
}

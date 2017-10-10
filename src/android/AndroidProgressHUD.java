package org.apache.cordova.plugin;

import android.util.Log;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Typeface;


public class AndroidProgressHUD extends Dialog {

	private static final String TAG = "AndroidProgressHUD";

	Context context;
	public AndroidProgressHUD(Context context) {
		super(context);
		this.context=context;
	}

	public AndroidProgressHUD(Context context, int theme) {
		super(context, theme);
		this.context=context;
	}


	public void onWindowFocusChanged(boolean hasFocus){
	ImageView imageView = (ImageView) findViewById(context.getResources().getIdentifier("spinnerImageView", "id", context.getPackageName()));
        AnimationDrawable spinner = (AnimationDrawable) imageView.getBackground();
        spinner.start();
    }

	public void setMessage(CharSequence message) {
		if(message != null && message.length() > 0) {
			findViewById(context.getResources().getIdentifier("message", "id", context.getPackageName())).setVisibility(View.VISIBLE);
			TextView txt = (TextView)findViewById(context.getResources().getIdentifier("message", "id", context.getPackageName()));
			txt.setText(message);
			txt.invalidate();
		}
	}

	public static AndroidProgressHUD show(Context context, CharSequence message, CharSequence detailMessage, boolean cancelable, OnCancelListener cancelListener) {
					 //R.style.ProgressHUD
		AndroidProgressHUD dialog = new AndroidProgressHUD(context,context.getResources().getIdentifier("ProgressHUD", "style", context.getPackageName()) );
		dialog.setTitle("");
		dialog.setContentView(context.getResources().getIdentifier("progress_hud", "layout", context.getPackageName()));

		if(message == null || message.length() == 0) {
			message = "";
		}

		TextView txt = (TextView)dialog.findViewById(context.getResources().getIdentifier("message", "id", context.getPackageName()) );
		txt.setTypeface(null, Typeface.BOLD);
		txt.setText(message);

		if(detailMessage == null || detailMessage.length() == 0) {
			detailMessage = "";
		}

		TextView detailTxt = (TextView)dialog.findViewById(context.getResources().getIdentifier("detailMessage", "id", context.getPackageName()) );
		detailTxt.setText(detailMessage);

		dialog.setCancelable(cancelable);
		dialog.setOnCancelListener(cancelListener);
		dialog.getWindow().getAttributes().gravity=Gravity.CENTER;
		WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
		lp.dimAmount=0.2f;
		dialog.getWindow().setAttributes(lp);
		//dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

		try {
			dialog.show();
		} catch (Exception e) {
            Log.e(TAG, "ActivityIndicator dialog.show() failed", e);
        }

		return dialog;
	}
}

package org.apache.cordova.plugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.apache.cordova.plugin.AndroidProgressHUD;

public class ActivityIndicator extends CordovaPlugin {

	private AndroidProgressHUD activityIndicator = null;
	private String text = null;
	private String detailText = null;

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (action.equals("show")) {
			String text = args.getString(0);
			String detailText = args.getString(1);
			show(text, detailText);
			callbackContext.success();
			return true;
		} else if (action.equals("hide")) {
			hide();
			callbackContext.success();
			return true;
		}

		return false;
	}

	/**
	 * This show the ProgressDialog
	 * @param text - Message to display in the Progress Dialog
	 */
	public void show(String text, String detailText) {
		this.text = text;
		this.detailText = detailText;

		cordova.getActivity().runOnUiThread(new Runnable() {
			public void run() {
				activityIndicator = AndroidProgressHUD.show(ActivityIndicator.this.cordova.getActivity(), ActivityIndicator.this.text, ActivityIndicator.this.detailText, false, null);
			}
		});
	}

	/**
	 * This hide the ProgressDialog
	 */
	public void hide() {
		cordova.getActivity().runOnUiThread(new Runnable() {
			public void run() {
			if (activityIndicator != null) {
				activityIndicator.dismiss();
				activityIndicator = null;
			}
			}
		});
	}
}
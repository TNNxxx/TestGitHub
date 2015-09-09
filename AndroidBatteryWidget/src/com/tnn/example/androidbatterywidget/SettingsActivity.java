package com.tnn.example.androidbatterywidget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

public class SettingsActivity extends Activity{

	private RelativeLayout relativeLayoutVibrate;
	private CheckBox chkVibrate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_layout);
	
		// bind Widget/ view Matching
		relativeLayoutVibrate = (RelativeLayout) findViewById(R.id.relativeLayoutVibrate);
		chkVibrate = (CheckBox) findViewById(R.id.chkVibrate);
		
		// Event Listener
		setWidgetEventListener();

	}

	private void setWidgetEventListener() {
		// TODO Auto-generated method stub
		relativeLayoutVibrate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(chkVibrate.isChecked()) {
					chkVibrate.setChecked(false);
				} else {
					chkVibrate.setChecked(true);
				}
			}
		});
	}

	
}

package com.tnn.example.androidbatterywidget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class CustomDialogClass extends Dialog implements OnClickListener {

	public Activity c;
	public Dialog dlg;
	private Button btnOK, btnCancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.customdialogui);
		
		btnOK = (Button) findViewById(R.id.next_button);
		btnCancel = (Button) findViewById(R.id.button2);
		
		btnOK.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
	}

	public CustomDialogClass(Activity activity) {
		super(activity);
		this.c = activity;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.next_button:
				Toast.makeText(c, "Click OK", Toast.LENGTH_SHORT).show();
			break;
		case R.id.button2:
				Toast.makeText(c, "Click Cancel", Toast.LENGTH_SHORT).show();
				dismiss();
		default:
			break;
		}
		dismiss();
	}

}

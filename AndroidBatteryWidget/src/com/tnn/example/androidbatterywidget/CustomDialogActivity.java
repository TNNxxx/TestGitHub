package com.tnn.example.androidbatterywidget;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CustomDialogActivity extends Activity {

	final Context context = this;
	private Button btnOK, btnCancel;
	private OnClickListener varOnClickListener;
	private TextView txtTitle;
	private TextView txtMessage;
	private android.content.DialogInterface.OnClickListener varDialogOnClickListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		Intent intent = this.getIntent();
		
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.customdialogui);
		// Dialog with transparent background in Android
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		
//		setContentView(R.layout.customdialogui);
		txtTitle = (TextView) dialog.findViewById(R.id.title);
		txtMessage = (TextView) dialog.findViewById(R.id.message);
		
		txtTitle.setText(intent.getCharSequenceExtra("Title"));
		txtMessage.setText(intent.getCharSequenceExtra("Message"));

		btnOK = (Button) dialog.findViewById(R.id.next_button);
		btnCancel = (Button) dialog.findViewById(R.id.button2);
		btnCancel.setVisibility(View.INVISIBLE); // ซ่อนปุ่ม
		
		btnOK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Toast.makeText(getBaseContext(), " + Click OK + ", Toast.LENGTH_SHORT).show();
				dialog.dismiss();
			}
		});
/*// ใช้ไม่ได้
//		btnOK.setOnClickListener(varOnClickListener);
		btnCancel.setOnClickListener(varOnClickListener);
		
		varDialogOnClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Log.w("TagMIT", "::onclik::");
			}
		};
		varOnClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(v == btnCancel) { Log.d("TagMIT", "cCancel"); }
				Log.i("TagMIT", "onClick: " + v.getId() + ", " + R.id.button2);
				switch (v.getId()) {
				case R.id.next_button:
					Toast.makeText(getBaseContext(), "Click OK", Toast.LENGTH_SHORT).show();
				break;
				case R.id.button2:
					Toast.makeText(getBaseContext(), "Click Cancel", Toast.LENGTH_SHORT).show();
				default:
					Toast.makeText(getBaseContext(), "Default", Toast.LENGTH_SHORT).show();
				break;
				}
			}
		};
*/
		dialog.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				// TODO Auto-generated method stub
				((Activity) context).finish();
//				getApplicationContext.finish();
			}
		});
		dialog.show();

	}

}

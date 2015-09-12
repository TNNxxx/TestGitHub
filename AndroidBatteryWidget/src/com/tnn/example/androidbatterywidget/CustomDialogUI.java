package com.tnn.example.androidbatterywidget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Vibrator;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomDialogUI {

	private Dialog dialog;
	private RelativeLayout rl;
	private Vibrator vib;

	@SuppressWarnings("static-access")
	public void dialog(final Context context, String title, String message,
			final Runnable task) {
		dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.customdialogui);
		dialog.setCancelable(false);
		TextView m = (TextView) dialog.findViewById(R.id.message);
		TextView t = (TextView) dialog.findViewById(R.id.title);
		final Button n = (Button) dialog.findViewById(R.id.button2);
		final Button p = (Button) dialog.findViewById(R.id.next_button);
		rl = (RelativeLayout) dialog.findViewById(R.id.rlmain);
		t.setText(bold(title));
		m.setText(message);
		dialog.show();
		n.setText(bold("Close"));
		p.setText(bold("OK"));
		// color(context, rl);
		vib = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
		n.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				vib.vibrate(15);
				dialog.dismiss();
			}
		});
		p.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				vib.vibrate(20);
				dialog.dismiss();
//				task.run();
			}
		});
	}

	//customize text style bold italic....
	public SpannableString bold(String title) {
		SpannableString spanString = new SpannableString(title);
		spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
		spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), 0);
//		spanString.setSpan(new StyleSpan(Typeface.ITALIC), 0, spanString.length(), 0);
		return spanString;
	}
}

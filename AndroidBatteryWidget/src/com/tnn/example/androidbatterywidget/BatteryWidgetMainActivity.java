package com.tnn.example.androidbatterywidget;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class BatteryWidgetMainActivity extends Activity {

	private SeekBar seekBarBatteryLowAlarm;
	private TextView txtView;
	private TextView txtBatteryLowAlarm;
	private ToggleButton toggleButtonBatteryLowAlarm;
	
	SharedPreferences sharedPrefs;
	private ToggleButton toggleButtonAlarmTime;
	private Button btnMySettings;
	private Button btnSettingsWizard;
	private Button btnPlusVolt;
	private Button btnDoublePlusVolt;
	private Button btnMinusVolt;
	private Button btnDoubleMinusVolt;
	private TextView txtVoltage;
	private static final String widgetPrefsName = "BatteryWidgetPrefs";
	private static final String seekBarBatteryLowAlarmPrefsName = "seekBarBatteryLowAlarmPrefsName";
	private static final String txtVoltageBatteryLowAlarmPrefsName = "txtVoltageBatteryLowAlarmPrefsName";
	private static final String toggleButtonBatteryLowAlarmPrefsName = "toggleButtonBatteryLowAlarmPrefsName";
	private static final String toggleButtonAlarmTimePrefsName = "toggleButtonAlarmTimePrefsName";
	private static final String tagMIT = "TagMIT";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_battery_widget_main);
		setContentView(R.layout.main);
		
		// bind Widget/ view Matching
		seekBarBatteryLowAlarm = (SeekBar) findViewById(R.id.seekBarBatteryLowAlarm);
		txtBatteryLowAlarm = (TextView) findViewById(R.id.txtBatteryLowAlarm);
		txtView = (TextView) findViewById(R.id.txtView);
		toggleButtonBatteryLowAlarm = (ToggleButton) findViewById(R.id.toggleButtonBatteryLowAlarm);
		toggleButtonAlarmTime = (ToggleButton) findViewById(R.id.toggleButtonAlarmTime);
		btnMySettings = (Button) findViewById(R.id.btnMySettings);
		btnSettingsWizard = (Button) findViewById(R.id.btnSettingsWizard);
		btnPlusVolt = (Button) findViewById(R.id.btnPlusVolt);
		btnDoublePlusVolt = (Button) findViewById(R.id.btnDoublePlusVolt);
		btnMinusVolt = (Button) findViewById(R.id.btnMinusVolt);
		btnDoubleMinusVolt = (Button) findViewById(R.id.btnDoubleMinusVolt);
		txtVoltage = (TextView) findViewById(R.id.txtVoltage);
		
		initialSharedPreferences(); // ต้องเรียกใช้งานก่อนกำหนด Event Listener จะได้ไม่มีปัญหาเกิดเหตุการณ์ซ้ำ
		// Event Listener
		setWidgetEventListener();
		
		txtView.setText("Covered: " + seekBarBatteryLowAlarm.getProgress() + "/" + seekBarBatteryLowAlarm.getMax());
	}

	private void initialSharedPreferences() {
		// TODO Auto-generated method stub
		sharedPrefs = getSharedPreferences(widgetPrefsName, MODE_PRIVATE); 
//		setIntSharedPreferences(widgetPrefsName, (int)12);
//		setBooleanSharedPreferences(widgetPrefsName, true);
		toggleButtonBatteryLowAlarm.setChecked(sharedPrefs.getBoolean(toggleButtonBatteryLowAlarmPrefsName, false));
		
		toggleButtonAlarmTime.setChecked(sharedPrefs.getBoolean(toggleButtonAlarmTimePrefsName, false));
		
		int seekProgress = sharedPrefs.getInt(seekBarBatteryLowAlarmPrefsName, 0);
		seekBarBatteryLowAlarm.setProgress(seekProgress);
		txtBatteryLowAlarm.setText("Alarm Level: " + seekProgress + "%");
		txtView.setText("Covered: " + seekProgress + "/" + seekBarBatteryLowAlarm.getMax());
		txtVoltage.setText(String.format("%.3f", sharedPrefs.getFloat(txtVoltageBatteryLowAlarmPrefsName, 3.2f)));
	}

	private void setBooleanSharedPreferences(String preferencesName, boolean bValue) {
		Editor editor = sharedPrefs.edit();
		editor.putBoolean(preferencesName, bValue);
		editor.commit();
	}

	private void setIntSharedPreferences(String preferencesName, int iValue) {
		Editor editor = sharedPrefs.edit();
		editor.putInt(preferencesName, iValue);
		editor.commit();
	}
	
	private void setFloatSharedPreferences(String preferenceName, float fValue) {
		Editor editor = sharedPrefs.edit();
		editor.putFloat(preferenceName, fValue);
		editor.commit();
	}

	private void setWidgetEventListener() {
		// TODO Auto-generated method stub
		seekBarBatteryLowAlarm.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			int progress = 0;
			@Override
			public void onProgressChanged(SeekBar seekBar, int progresValue,
					boolean fromUser) {
				// TODO Auto-generated method stub
				progress = progresValue;
				txtBatteryLowAlarm.setText("Alarm Level: " + progresValue + "%");
//				Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				txtView.setText("Covered: " + progress + "/" + seekBar.getMax());
				setIntSharedPreferences(seekBarBatteryLowAlarmPrefsName, seekBar.getProgress());
				Toast.makeText(getApplicationContext(), "Stopped tracking seekbar: " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
			}
		});
		
		toggleButtonBatteryLowAlarm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setBooleanSharedPreferences(toggleButtonBatteryLowAlarmPrefsName, toggleButtonBatteryLowAlarm.isChecked());
				Toast.makeText(getApplicationContext(), "ToggleButton: " + 
						toggleButtonBatteryLowAlarm.isChecked() + ", " + 
						toggleButtonBatteryLowAlarm.getText(), Toast.LENGTH_SHORT).show();
			}
		});
		
		toggleButtonAlarmTime.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setBooleanSharedPreferences(toggleButtonAlarmTimePrefsName, toggleButtonAlarmTime.isChecked());
			}
		});
		
		btnMySettings.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
			}
		});
		
		btnSettingsWizard.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(BatteryWidgetMainActivity.this, SettingsActivityWizard.class));
			}
		});
		
		btnPlusVolt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					float numVolt = Float.valueOf(txtVoltage.getText().toString());
					numVolt += 0.010;
					txtVoltage.setText(String.format("%.3f", numVolt));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		btnDoublePlusVolt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					float numVolt = Float.valueOf(txtVoltage.getText().toString());
					numVolt += 0.100;
					txtVoltage.setText(String.format("%.3f", numVolt));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		btnMinusVolt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					float numVolt = Float.valueOf(txtVoltage.getText().toString());
					numVolt -= 0.010;
					txtVoltage.setText(String.format("%.3f", numVolt));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		btnDoubleMinusVolt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					float numVolt = Float.valueOf(txtVoltage.getText().toString());
					numVolt -= 0.100;
					txtVoltage.setText(String.format("%.3f", numVolt));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		txtVoltage.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				Log.e(tagMIT, s.toString());
				setFloatSharedPreferences(txtVoltageBatteryLowAlarmPrefsName, Float.valueOf(txtVoltage.getText().toString()));
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.battery_widget_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
//			setContentView(R.layout.settings_layout);
//			startActivity(new Intent(this, SettingsActivity.class));
			startActivity(new Intent(this, SettingsActivityWizard.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

package com.tnn.example.androidbatterywidget;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.nsd.NsdManager.RegistrationListener;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class BatteryWidget extends AppWidgetProvider {

	@Override
	public void onAppWidgetOptionsChanged(Context context,
			AppWidgetManager appWidgetManager, int appWidgetId,
			Bundle newOptions) {
		// TODO Auto-generated method stub
//		super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId,	newOptions);
		insertLogFile("", 0, "onAppWidgetOptionsChanged +>  " + getNowDateTime() + "\r\n");
	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
		insertLogFile("", 0, "onDeleted +>  " + getNowDateTime() + "\r\n");
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
		insertLogFile("", 0, "onEnabled +>  " + getNowDateTime() + "\r\n");
	}

	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		super.onDisabled(context);
		insertLogFile("", 0, "onDisabled +>  " + getNowDateTime() + "\r\n");
	}

	@Override
	public void onRestored(Context context, int[] oldWidgetIds,
			int[] newWidgetIds) {
		// TODO Auto-generated method stub
//		super.onRestored(context, oldWidgetIds, newWidgetIds);
		insertLogFile("", 0, "onRestored +>  " + getNowDateTime() + "\r\n");
	}

	private static final String tagMIT = "TagMIT";
//	AppWidgetManager appwidgetmanager;
//	SharedPreferences sharedPrefs;
	private static final String widgetPrefsName = "BatteryWidgetPrefs";
	private static final String seekBarBatteryLowAlarmPrefsName = "seekBarBatteryLowAlarmPrefsName";
	private static final String toggleButtonBatteryLowAlarmPrefsName = "toggleButtonBatteryLowAlarmPrefsName";
	private static final String toggleButtonAlarmTimePrefsName = "toggleButtonAlarmTimePrefsName";
	private static final String txtVoltageBatteryLowAlarmPrefsName = "txtVoltageBatteryLowAlarmPrefsName";
	
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
//		Log.i(tagMIT, "onUpdate");
		insertLogFile("", 0, "onUpdate +>  " + getNowDateTime() + "\r\n");
//		super.onUpdate(context, appWidgetManager, appWidgetIds);
//		Timer timer = new Timer();
//		timer.scheduleAtFixedRate(new MyTime(context, appWidgetManager), 1, 1000);
//		this.appwidgetmanager = appWidgetManager;
		context.getApplicationContext().startService(new Intent("com.tnn.example.androidbatterywidget.action.UPDATE"));
//		BatteryUpdateService(context, appWidgetManager);
		
		onClickWidgetActivityLaunch(context, appWidgetIds); // ทำให้สามารถคลิ๊กที่ Widget เพื่อเรียก Activity ได้
	
//		sharedPrefs = context.getSharedPreferences(widgetPrefsName, 0); 
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
//		Log.d(tagMIT, "onReceive");
		insertLogFile("", 0, "onReceive +>  " + getNowDateTime() + "\r\n");
		super.onReceive(context, intent);
	}

	private void onClickWidgetActivityLaunch(Context context, int[] appWidgetIds) {
		AppWidgetManager appWidgetManager1 = AppWidgetManager.getInstance(context);

	// หมายเหตุ โปรแกรมทั้งสองส่วนสามารถที่จะใช้งานได้เหมือนกัน
		
/*		// เลย์เอาท์ของวิตเจ็ต
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_battery_widget_main);
		// Activity ที่จะให้ทำงานเมื่อมีการกดที่ วิตเจ็ต
		Intent configIntent = new Intent(context, BatteryWidgetMainActivity.class);
		PendingIntent configPendingIntent = PendingIntent.getActivity(context,  0, configIntent, 0);
		// พื้นที่ ที่จะรับการกด ให้ใช้เลย์เอาท์ของ วิตเจ็ต
		remoteViews.setOnClickPendingIntent(R.id.linearLayoutWidget, configPendingIntent);
		appWidgetManager1.updateAppWidget(appWidgetIds, remoteViews);
*/		
		// How To Code an Android Widget
		//  http://www.sitepoint.com/how-to-code-an-android-widget/
		final int N = appWidgetIds.length;
//		Log.i(tagMIT, "Updating widgets " + Arrays.asList(appWidgetIds));
		// Perform this loop procedure for each App Widget that belongs to this
	    // provider
	    for (int i = 0; i < N; i++) {
	      int appWidgetId = appWidgetIds[i];

	      // Create an Intent to launch ExampleActivity
	      Intent intent = new Intent(context, BatteryWidgetMainActivity.class);
	      PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

	      // Get the layout for the App Widget and attach an on-click listener
	      // to the button
	      RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_battery_widget_main);
	      views.setOnClickPendingIntent(R.id.linearLayoutWidget, pendingIntent);

	      // To update a label
//	      views.setTextViewText(R.id.widget1label, df.format(new Date()));

	      // Tell the AppWidgetManager to perform an update on the current app
	      // widget
	      appWidgetManager1.updateAppWidget(appWidgetId, views);
	    }
	}

	private static String getNowDateTime() {
		// TODO Auto-generated method stub
		/*							Whereas you can have DateFormat patterns such as:
		"yyyy.MM.dd G 'at' HH:mm:ss z" ---- 2001.07.04 AD at 12:08:56 PDT
		"hh 'o''clock' a, zzzz" ----------- 12 o'clock PM, Pacific Daylight Time
		"EEE, d MMM yyyy HH:mm:ss Z"------- Wed, 4 Jul 2001 12:08:56 -0700
		"yyyy-MM-dd'T'HH:mm:ss.SSSZ"------- 2001-07-04T12:08:56.235-0700
		"yyMMddHHmmssZ"-------------------- 010704120856-0700
		"K:mm a, z" ----------------------- 0:08 PM, PDT
		"h:mm a" -------------------------- 12:08 PM
		"EEE, MMM d, ''yy" ---------------- Wed, Jul 4, '01
*/			
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, HH:mm:ss.SSS, ");
		String currentDateTime = sdf.format(new Date());
		return currentDateTime;
	}

	public static class BatteryUpdateService extends Service {
		int scale = -1;
		int level = -1;
		int voltage = -1;
		int temp = -1;
/*		RemoteViews remoteViews;
		AppWidgetManager appWidgetMager;
		ComponentName thisWidget;

		public BatteryUpdateService(Context context, AppWidgetManager appWidgetManager) {
			this.appWidgetMager = appWidgetManager;
			remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_battery_widget_main);
			thisWidget = new ComponentName(context, BatteryWidget.class);
		}
		public void updateViews() {
			// TODO Auto-generated method stub
			remoteViews.setTextViewText(R.id.widget_textview, "TIME = " );
			appWidgetMager.updateAppWidget(thisWidget, remoteViews);
		}
*/		
		private BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				String action = intent.getAction();
				if(action.equals(Intent.ACTION_BATTERY_CHANGED)) {
					level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
					scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
					temp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
					voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
					float fVoltage = (float) voltage/1000;
					batteryStatus(context, intent);
					// There is a Unicode symbol 
					//   for Celsius degrees that you can use in Java: \u2103. 
					//   For Fahrenheit you can use \u2109.
//					String tmpBattery = "Level: " + level + "/" + scale + 
//							", Temp: " + String.valueOf((float)temp/10) + " \u2103" +
//							", Volt: " + String.valueOf((float)voltage/1000) + " V";
					String tmpBattery = "Lv:" + level + "/" + scale + ", " + String.valueOf((float)temp/10) + " \u2103" +
							", " + String.valueOf((float)voltage/1000) + " V";
					Log.w(tagMIT, tmpBattery);
//					Log.i(tagMIT, String.valueOf(level) + "%");
//					if(level < 5) {
//						Toast.makeText(context, "Level: " + level + " \u2103", Toast.LENGTH_SHORT).show();
//					}
					
					SharedPreferences shared = context.getSharedPreferences(widgetPrefsName, MODE_PRIVATE);
					if(shared.getBoolean(toggleButtonBatteryLowAlarmPrefsName, false)) {
//						Toast.makeText(context, "Toggle: ON", Toast.LENGTH_SHORT).show();
						if((level < shared.getInt(seekBarBatteryLowAlarmPrefsName, 0)) ||
								(fVoltage < shared.getFloat(txtVoltageBatteryLowAlarmPrefsName, 3.2f))){

							int timeToast = Toast.LENGTH_SHORT;
							if(shared.getBoolean(toggleButtonAlarmTimePrefsName, false)) {
								timeToast = Toast.LENGTH_LONG;
							}
								
//							String tmpToast = "Level: " + level + "%" +
//									", Temp: " + String.valueOf((float)temp/10) + " \u2103" +
//									", Volt: " + String.valueOf((float)voltage/1000) + " V";
							String tmpToast = "Lv: " + level + "%" +
									", " + String.valueOf((float)temp/10) + " \u2103" +
									", " + String.valueOf((float)voltage/1000) + " V";

							Toast.makeText(context, tmpToast, timeToast).show();
							String currentDateTime = getNowDateTime();
							Log.i(tagMIT, currentDateTime + tmpToast);
							appendLog(currentDateTime + tmpToast);
//							appendTopLog(currentDateTime + tmpToast);
							insertLogFile("", 0, currentDateTime + tmpToast + "\r\n");
						}
					}
					updateViews(context, level, scale, temp, voltage);
//					updateViews();
				}
			}

		};
//		IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
//		registerReceiver(batteryReceiver, filter);

		@Override
		public int onStartCommand(Intent intent, int flags, int startId) {
			// TODO Auto-generated method stub
//			Log.e(tagMIT, "start");
			appendLog("onStart");
//			appendTopLog("onStart");
			insertLogFile("", 0, "onStart +>  " + getNowDateTime() + "\r\n");
			registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
			return super.onStartCommand(intent, flags, startId);
		}

		// [Android] BatteryManager
		//   https://akira-watson.com/android/batterymanager.html
		protected void batteryStatus(Context context, Intent batteryStatusIntent) {
			// TODO Auto-generated method stub
			String strStatus = "";
			int level = batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
			String batteryInfo = "Level: " + String.valueOf(level) + "\n\n";
			
			// battery health
			int bh = batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);
			String health = "?";
			if(bh == BatteryManager.BATTERY_HEALTH_GOOD) {
				health = "GOOD";
			}
			else if(bh == BatteryManager.BATTERY_HEALTH_DEAD) {
				health = "DEAD";
			}
			else if(bh == BatteryManager.BATTERY_HEALTH_COLD) {
				health = "COLD";
			}
			else if (bh == BatteryManager.BATTERY_HEALTH_OVERHEAT) {
				health = "OVERHEAT";
			}
			else if(bh == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE) {
				health = "OVER_VOLTAGE";
			}
			else if(bh == BatteryManager.BATTERY_HEALTH_UNKNOWN) {
				health = "UNKNOW";
			}
			else if(bh == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE) {
				health = "UNSPECIFIED_FAILURE";
			}
			batteryInfo += "Health: " + health + "\n\n";
			
			// icon ID
			int bi = batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL, -1);
			batteryInfo += "Icon: " + String.valueOf(bi) + "\n\n";
			
			// battery plugged
			int bpl = batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
			String plugged = "NOT PLUGGED";
			switch (bpl) {
			case BatteryManager.BATTERY_PLUGGED_AC:
				plugged = "PLUGGED AC";
				strStatus += "AC ";
				break;
			case BatteryManager.BATTERY_PLUGGED_USB:
				plugged = "PLUGGED USB";
				strStatus += "USB ";
				break;
			case BatteryManager.BATTERY_PLUGGED_WIRELESS:
				plugged = "PLUGGED WIRELESS";
				break;
			}
			batteryInfo += "Plugged: " + plugged + "\n\n";
			
			// battery present or not
			boolean bpr = batteryStatusIntent.getBooleanExtra(BatteryManager.EXTRA_PRESENT, false);
			String present = "?";
			if(bpr) {
				present = "PRESENT";
			} else {
				present = "NOT PRESENT";
			}
			batteryInfo += "Present: " + present + "\n\n";
			
			// scale
			int bsc = batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
			batteryInfo += "Scale: " + String.valueOf(bsc) + "\n\n";
			
			// battery status
			int bst = batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
			String status = "?";
//			boolean bCharging = false;
			switch (bst) {
				case BatteryManager.BATTERY_STATUS_CHARGING:
					status = "CHARGING";
					strStatus += "Charging ";
//					bCharging = true;
					break;
				case BatteryManager.BATTERY_STATUS_DISCHARGING:
					status = "DISCHARGING";
					strStatus += "Discharging ";
					break;
				case BatteryManager.BATTERY_STATUS_FULL:
					status = "FULL";
					strStatus += "Full ";
					break;
				case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
					status = "NOT CHARGING";
					strStatus += "Not Chargine ";
					break;
				case BatteryManager.BATTERY_STATUS_UNKNOWN:
					status = "UNKNOWN";
					break;
			}
			batteryInfo += "Status: " + status + "\n\n";
			
			// battery technology
			String bte = batteryStatusIntent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
			batteryInfo += "Technology: " + bte + "\n\n";
			
			// temperature
			int btp = batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
			batteryInfo += "Temperature: " + String.valueOf((float)btp/10) + " \u2103" + "\n\n";
			int bv = batteryStatusIntent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
			batteryInfo += "Voltage: " + String.valueOf((float)bv/1000) + " [V]" + "\n\n";
			
			// Later is the difference of support by the Lollipop and model
			if(Build.VERSION.SDK_INT >= 21) {
/*				BatteryManager bm = (BatteryManager) this.getSystemService(Context.BATTERY_SERVICE);
				
				int remainingCapacityRatio = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
				batteryInfo += "RemainingCapacityRatio: " + remainingCapacityRatio + " %" + "\n\n";
				
				int batteryCapacityMicroAh = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CHARGE_COUNTER);
				batteryInfo += "BatteryCapacityMicroAh: " + batteryCapacityMicroAh + " uAh" + "\n\n";
				
				int dischargingTimeSeconds = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE);
				batteryInfo += "DischargingTimeSeconds: " + dischargingTimeSeconds + " mSec" + "\n\n";
				
				int currentNow = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW);
				batteryInfo += "CurrentNow: " + currentNow + " uA" + "\n\n";
				
				long energyCounter = bm.getLongProperty(BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER);
				batteryInfo += "EnergyCounter: " + energyCounter + " nWh" + "\n\n";
				*/
			}
			
//			Log.w(tagMIT, batteryInfo);
			updateGraphicView(context, strStatus, bst);
		}

		public void updateGraphicView(Context context, String strStatus, int batteryStatus) {
			// TODO Auto-generated method stub
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_battery_widget_main);
			views.setTextViewText(R.id.widget_textview, strStatus);
			switch(batteryStatus) {
				case BatteryManager.BATTERY_STATUS_CHARGING:
					views.setImageViewResource(R.id.imgBattery, R.drawable.ic_charging);
					break;
				case BatteryManager.BATTERY_STATUS_DISCHARGING:
					views.setImageViewResource(R.id.imgBattery, R.drawable.ic_battery);
					break;
				case BatteryManager.BATTERY_STATUS_FULL:
					views.setImageViewResource(R.id.imgBattery, R.drawable.ic_battery_full);
					break;
			}
			ComponentName componentName = new ComponentName(context, BatteryWidget.class);
			AppWidgetManager appWidgetManaget = AppWidgetManager.getInstance(context);
			appWidgetManaget.updateAppWidget(componentName, views);
		}

		protected void updateViews(Context context, int level2, int scale2,
				int temp2, int voltage2) {
			// TODO Auto-generated method stub
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_battery_widget_main);
//			views.setTextViewText(R.id.widget_textview, "Volt: " + String.valueOf(level/scale));
//			views.setTextViewText(R.id.widget_textview, "Tempareture: " + String.valueOf((float)temp2/10) + " \u2103");
//			views.setTextViewText(R.id.txtTempareture, "Temp: " + String.valueOf((float)temp2/10) + " \u2103");
//			views.setTextViewText(R.id.txtVolt, "Volt: " + String.valueOf((float)voltage/1000 + " V"));
			views.setTextViewText(R.id.txtTempareture, " " + String.valueOf((float)temp2/10) + " \u2103");
			views.setTextViewText(R.id.txtVolt, " " + String.valueOf((float)voltage/1000 + " V"));
			views.setTextViewText(R.id.txtPercent, String.valueOf(level2) + "%");
			ComponentName componentName = new ComponentName(context, BatteryWidget.class);
			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
			appWidgetManager.updateAppWidget(componentName, views);
		}

		@Override
		public IBinder onBind(Intent intent) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	// Android Writing Logs to text File
	//   http://stackoverflow.com/questions/1756296/android-writing-logs-to-text-file
	public static void appendLog(String text) {
		File logFile = new File("sdcard/BatteryWidgetLog.txt");
		BufferedWriter buf = null;
		if(!logFile.exists()) {
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			//BufferedWriter for performance, true to set append to file flag
			buf = new BufferedWriter(new FileWriter(logFile, true));
			buf.append(text);
			buf.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				buf.flush();
				buf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// Java. How to append text to top of file.txt
	//   http://stackoverflow.com/questions/16665124/java-how-to-append-text-to-top-of-file-txt
	// ต้องการที่จะให้เพิ่มข้อมูลลงไฟล์ในบรรทัดแรก แต่ตัวอย่างนี้ใช้ไม่ได้
	public static void appendTopLog(String text) {
		try {
			RandomAccessFile file = new RandomAccessFile(new File("sdcard/BatteryWidgetTopLog.txt"), "rw");
//			file.seek(0);
			file.getChannel().position(0);
			file.writeBytes(text);
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Writing in the beginning of a text file Java
	//   http://stackoverflow.com/questions/6127648/writing-in-the-beginning-of-a-text-file-java
	//     InputStream inputStream = new SequenceInputStream(new ByteArrayInputStream("my line\n".getBytes()), new FileInputStream(new File("myfile.txt")));
	//  อันนี้ยังไม่ได้ทดลอง และไม่รู้ว่าจะทำอย่างไร ไม่เข้าใจ
	
	// Inserting text into an existing file via Java
	//   http://stackoverflow.com/questions/289965/inserting-text-into-an-existing-file-via-java
	// ต้องการที่จะให้เพิ่มข้อมูลลงไฟล์ในบรรทัดแรก แต่ตัวอย่างนี้ใช้ได้
	public static void insertLogFile(String filename, long offset, String content) {
		filename = "sdcard/BatteryWidgetInsertLog.txt";
		try {
			RandomAccessFile raf = new RandomAccessFile(new File(filename), "rw");
			RandomAccessFile rtemp = new RandomAccessFile(new File(filename + "~"), "rw");
			long fileSize = raf.length();
			FileChannel sourceChannel = raf.getChannel();
			FileChannel targetChannel = rtemp.getChannel();
			sourceChannel.transferTo(offset, (fileSize-offset), targetChannel);
			sourceChannel.truncate(offset);
			raf.seek(offset);
			raf.writeBytes(content);
			long newOffset = raf.getFilePointer();
			targetChannel.position(0L);
			sourceChannel.transferFrom(targetChannel, newOffset, (fileSize-offset));
			sourceChannel.close();
			targetChannel.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
/*	
	private class MyTime extends TimerTask {
		RemoteViews remoteViews;
		AppWidgetManager appWidgetMager;
		ComponentName thisWidget;
		DateFormat format = SimpleDateFormat.getTimeInstance(SimpleDateFormat.MEDIUM, Locale.getDefault());

		public MyTime(Context context, AppWidgetManager appWidgetManager) {
			this.appWidgetMager = appWidgetManager;
			remoteViews = new RemoteViews(context.getPackageName(), R.layout.activity_battery_widget_main);
			thisWidget = new ComponentName(context, BatteryWidget.class);
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			remoteViews.setTextViewText(R.id.widget_textview, "TIME = " + format.format(new Date()));
			appWidgetMager.updateAppWidget(thisWidget, remoteViews);
		}
	}
*/

}

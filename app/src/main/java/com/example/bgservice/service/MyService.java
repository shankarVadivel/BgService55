package com.example.bgservice.service;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
	SharedPreferences sharedpreferences;

	public static final String MyPREFERENCES = "MyPrefs" ;
	public static final String CountPref = "countKey";
	public int cout=0;
	private Timer mTimer;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mTimer = new Timer();
		mTimer.schedule(timerTask, 1000, 1 * 1000);
		sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		cout = sharedpreferences.getInt(CountPref, 0);


		Log.e("Log", "Runningmain"+cout);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.onStartCommand(intent, flags, startId);
	}


	TimerTask timerTask = new TimerTask() {

		@Override
		public void run() {
			cout++;
			SharedPreferences.Editor editor = sharedpreferences.edit();
			editor.putInt(CountPref, cout);
			editor.apply();
			Log.e("Log", "Running"+cout);
		}
	};

	public void onDestroy() {
		try {
			mTimer.cancel();
			timerTask.cancel();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Intent intent = new Intent("com.example.bgservice");
		sendBroadcast(intent);
	}
}

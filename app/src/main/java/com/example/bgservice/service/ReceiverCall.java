package com.example.bgservice.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ReceiverCall extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("Service Stops", "stopped");
		context.startService(new Intent(context, MyService.class));;
	}

}

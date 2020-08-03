package com.example.bgservice.viewmodel;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bgservice.service.MyService;

import static com.example.bgservice.service.MyService.CountPref;
import static com.example.bgservice.service.MyService.MyPREFERENCES;

public class MyActivityViewModel extends ViewModel {
    private final MutableLiveData<Integer> countObservable=new MutableLiveData<>();
    Handler taskHandler;
    TextView text_timer;
    SharedPreferences sharedpreferences;
    Context context;
    public MyActivityViewModel(Context context) {
        this.context=context;
        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        if(!isMyServiceRunning(MyService.class)){
            Intent i =new Intent(context, MyService.class);
            context.startService(i);
        }

        taskHandler = new Handler();
        taskHandler.postDelayed(myTask, 0);


    }
    private Runnable myTask = new Runnable(){
        public void run() {
            getPrefValue();
// repeat the task
            taskHandler.postDelayed(this, 1000);
        }
    };

    private void getPrefValue(){
        new Thread(new Runnable() {

            public void run() {
                final int count=sharedpreferences.getInt(CountPref, 0);
                countObservable.postValue(count);
           /*     context.runOnUiThread(new Runnable() {
                    public void run() {
                        text_timer.setText(count+"");
                    }
                });*/
            }
        }).start();
    }
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager)  context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    public LiveData<Integer> getCountObservable() {
        return countObservable;
    }
}

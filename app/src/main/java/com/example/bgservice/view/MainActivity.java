package com.example.bgservice.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bgservice.R;
import com.example.bgservice.viewmodel.MyActivityViewModel;
import com.example.bgservice.viewmodel.factory.MyActivityViewModelFactory;

public class MainActivity extends AppCompatActivity {
	Handler taskHandler;
	TextView text_timer;
	SharedPreferences sharedpreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text_timer=findViewById(R.id.txt_timer);

		MyActivityViewModel viewModel = new ViewModelProvider(this,new MyActivityViewModelFactory(this)).get(MyActivityViewModel.class);

		observeViewModel(viewModel);

	}

	private void observeViewModel(MyActivityViewModel viewModel) {
		// Update the ui when the data changes

		viewModel.getCountObservable().observe(this, new Observer<Integer>() {
			@Override
			public void onChanged(Integer count) {
				text_timer.setText(count+"");
			}
		});
	}
}

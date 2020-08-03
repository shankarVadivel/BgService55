package com.example.bgservice.viewmodel.factory;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.bgservice.viewmodel.MyActivityViewModel;


public class MyActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory
{
    private Context context;


    public MyActivityViewModelFactory(Context context)
    {
        this.context = context;
       }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass)
    {
        return (T) new MyActivityViewModel(context);
    }
}
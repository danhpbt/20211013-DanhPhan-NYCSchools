package com.example.a20211013_danhphan_nycschools.ui;

import com.example.a20211013_danhphan_nycschools.base.BaseViewModel;
import com.example.a20211013_danhphan_nycschools.data.remote.ApiService;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends BaseViewModel {

    @Inject
    public MainViewModel(ApiService apiService)
    {
        super(apiService);
    }
}

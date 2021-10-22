package com.example.a20211013_danhphan_nycschools.base;

import androidx.lifecycle.ViewModel;

import com.example.a20211013_danhphan_nycschools.data.remote.ApiService;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {
    private CompositeDisposable mCompositeDisposable;

    private final ApiService apiService;

    public BaseViewModel(ApiService apiService)
    {
        this.apiService = apiService;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public ApiService getApiService() {
        return apiService;
    }
    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }
}

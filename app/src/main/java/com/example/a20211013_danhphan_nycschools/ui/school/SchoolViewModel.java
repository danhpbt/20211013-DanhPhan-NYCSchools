package com.example.a20211013_danhphan_nycschools.ui.school;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableList;
import androidx.lifecycle.ViewModel;

import com.example.a20211013_danhphan_nycschools.base.BaseViewModel;
import com.example.a20211013_danhphan_nycschools.data.remote.ApiService;
import com.example.a20211013_danhphan_nycschools.data.remote.school.SchoolInfo;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class SchoolViewModel extends BaseViewModel {
    private final ObservableList<SchoolInfo> schoolInfoList = new ObservableArrayList<>();
    private final ObservableBoolean mIsLoading = new ObservableBoolean();
    SchoolViewModelListener mListener;

    @Inject
    public SchoolViewModel(ApiService apiService)
    {
        super(apiService);
        getSchoolsData();
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    public ObservableList<SchoolInfo> getSchoolInfoList()
    {
        return schoolInfoList;
    }

    public void getSchoolsData()
    {
        setIsLoading(true);

        getCompositeDisposable().add(getApiService()
                .getListSchoolInfo()
                //.delay(3, TimeUnit.SECONDS) //delay to view shimmer
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        schoolInfos -> {
                            if ((schoolInfos != null) && (schoolInfos.size() > 0)) {
                                schoolInfoList.clear();
                                schoolInfoList.addAll(schoolInfos);
                            }
                    setIsLoading(false);
                }, throwable -> {
                    //setIsLoading(false); still loading if have error
                    if (mListener != null)
                        mListener.onError(throwable.getLocalizedMessage());
                }));
    }

    public void setListener(SchoolViewModelListener listener)
    {
        mListener = listener;
    }


    interface SchoolViewModelListener{
        void onError(String msg);
    }
}

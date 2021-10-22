package com.example.a20211013_danhphan_nycschools.ui.sat;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import com.example.a20211013_danhphan_nycschools.base.BaseViewModel;
import com.example.a20211013_danhphan_nycschools.data.remote.ApiService;
import com.example.a20211013_danhphan_nycschools.data.remote.school.SchoolInfo;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class SatViewModel extends BaseViewModel {

    private final ObservableField<SchoolInfo> mSchoolInfo = new ObservableField<>();

    private final ObservableBoolean mIsLoading = new ObservableBoolean();
    SatViewModel.SatViewModelListener mListener;
    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    @Inject
    public SatViewModel(ApiService apiService)
    {
        super(apiService);
        mSchoolInfo.set(new SchoolInfo());
    }

    public void setSchoolInfo(SchoolInfo schoolInfo)
    {
        mSchoolInfo.set(schoolInfo);
    }

    public ObservableField<SchoolInfo> getSchoolInfo()
    {
        return mSchoolInfo;
    }

    public void getSatInfo(SchoolInfo schoolInfo)
    {
        setIsLoading(true);

        getCompositeDisposable().add(getApiService()
                .getSatInfo(schoolInfo.getDbn())
                //.delay(3, TimeUnit.SECONDS) //delay to view shimmer
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        satInfo -> {
                            if ((satInfo != null) && (satInfo.size() > 0)) {
                                schoolInfo.setSatInfo(satInfo.get(0));
                                mSchoolInfo.set(schoolInfo);

                                setIsLoading(false);
                            }
                            else
                                mListener.onError("Can not find SAT score info.");
                        }, throwable -> {
                            //setIsLoading(false); still loading if have error
                            if (mListener != null)
                                mListener.onError(throwable.getLocalizedMessage());
                        }));
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    public void setListener(SatViewModel.SatViewModelListener listener)
    {
        mListener = listener;
    }

    interface SatViewModelListener{
        void onError(String msg);
    }

}

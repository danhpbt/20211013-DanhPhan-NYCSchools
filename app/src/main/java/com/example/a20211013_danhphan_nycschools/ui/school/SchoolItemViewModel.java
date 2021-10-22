package com.example.a20211013_danhphan_nycschools.ui.school;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import com.example.a20211013_danhphan_nycschools.data.remote.school.SchoolInfo;

public class SchoolItemViewModel extends ViewModel {
    final ObservableInt mIndex = new ObservableInt();
    private final SchoolInfo schoolInfo;

    SchoolItemViewModelListener mListener;

    public SchoolItemViewModel(int index, SchoolInfo schoolInfo)
    {
        mIndex.set(index + 1);
        this.schoolInfo = schoolInfo;
    }

    public String getIndex()
    {
        return mIndex.get() + "";
    }

    public SchoolInfo getSchoolInfo()
    {
        return schoolInfo;
    }

    public String getSchoolName()
    {
        return schoolInfo.getSchoolName();
    }

    public String getAddress()
    {
        //11000 Garden Grove Blvd, Garden Grove, CA 92843
        String res = String.format("%s, %s, %s %s",
                schoolInfo.getPrimaryAddress(),
                schoolInfo.getCity(),
                schoolInfo.getStateCode(),
                schoolInfo.getZip());
        return res;
    }

    public void setListener(SchoolItemViewModelListener listener)
    {
        mListener = listener;
    }

    public void onItemClick()
    {
        if (mListener != null)
            mListener.onItemClick(schoolInfo);
    }

    public void onLocationClick()
    {
        if (mListener != null)
            mListener.onLocationClick(schoolInfo);
    }

    interface SchoolItemViewModelListener{
        void onItemClick(SchoolInfo schoolInfo);
        void onLocationClick(SchoolInfo schoolInfo);
    }
}


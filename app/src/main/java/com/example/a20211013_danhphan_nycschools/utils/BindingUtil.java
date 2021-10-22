package com.example.a20211013_danhphan_nycschools.utils;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a20211013_danhphan_nycschools.data.remote.school.SchoolInfo;
import com.example.a20211013_danhphan_nycschools.ui.school.SchoolAdapter;

import java.util.List;

public final class BindingUtil {
    @BindingAdapter({"adapterRV"})
    public static void addSchoolInfoItems(RecyclerView recyclerView, List<SchoolInfo> schoolInfos) {
        SchoolAdapter adapter = (SchoolAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(schoolInfos);
        }
    }
}

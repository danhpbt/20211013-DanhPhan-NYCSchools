package com.example.a20211013_danhphan_nycschools.ui.school;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a20211013_danhphan_nycschools.base.BaseViewHolder;
import com.example.a20211013_danhphan_nycschools.data.remote.school.SchoolInfo;
import com.example.a20211013_danhphan_nycschools.databinding.SchoolItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder> {

    List<SchoolInfo> schoolInfoList = new ArrayList<>();
    private SchoolAdapterListener mListener;

    public SchoolAdapter() {

    }

    @NonNull
    @Override
    public SchoolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SchoolItemBinding schoolItemBinding = SchoolItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        return new SchoolViewHolder(schoolItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public void onViewRecycled(@NonNull SchoolViewHolder holder) {
        super.onViewRecycled(holder);
        holder.recycle();
    }

    @Override
    public int getItemCount() {
        if (schoolInfoList != null && schoolInfoList.size() > 0) {
            return schoolInfoList.size();
        } else {
            return 0;
        }
    }

    public void clearItems() {
        schoolInfoList.clear();
    }

    public void addItems(List<SchoolInfo> schoolInfos) {
        if (schoolInfos != null) {
            schoolInfoList.addAll(schoolInfos);
            notifyDataSetChanged();
        }
    }


    public void setListener(SchoolAdapterListener listener)
    {
        mListener = listener;
    }

    //////////////////////////////////////////////////
    public interface SchoolAdapterListener
    {
        void onClickListener(SchoolInfo schoolInfo);
        void onLocationClick(SchoolInfo schoolInfo);
    }

    public class SchoolViewHolder extends BaseViewHolder implements
            SchoolItemViewModel.SchoolItemViewModelListener {

        SchoolItemBinding schoolItemBinding;
        SchoolItemViewModel schoolItemViewModel;

        public SchoolViewHolder(@NonNull SchoolItemBinding schoolItemBinding) {
            super(schoolItemBinding.getRoot());

            // init binding
            this.schoolItemBinding = schoolItemBinding;
        }

        public void onBind(int position)
        {
            final SchoolInfo schoolInfo = schoolInfoList.get(position);
            schoolItemViewModel = new SchoolItemViewModel(position, schoolInfo);
            schoolItemViewModel.setListener(this);
            schoolItemBinding.setViewModel(schoolItemViewModel);

            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            schoolItemBinding.executePendingBindings();
        }

        public void recycle()
        {

        }

        @Override
        public void onItemClick(SchoolInfo schoolInfo) {
            if (mListener != null)
                mListener.onClickListener(schoolInfo);
        }

        @Override
        public void onLocationClick(SchoolInfo schoolInfo) {
            if (mListener != null)
                mListener.onLocationClick(schoolInfo);

        }
    }
}

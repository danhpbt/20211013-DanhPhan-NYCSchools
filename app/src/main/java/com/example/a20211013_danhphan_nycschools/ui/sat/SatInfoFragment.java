package com.example.a20211013_danhphan_nycschools.ui.sat;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.a20211013_danhphan_nycschools.R;
import com.example.a20211013_danhphan_nycschools.base.BaseFragment;
import com.example.a20211013_danhphan_nycschools.data.remote.school.SchoolInfo;
import com.example.a20211013_danhphan_nycschools.databinding.FragmentSatInfoBinding;
import com.example.a20211013_danhphan_nycschools.ui.school.SchoolViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SatInfoFragment extends BaseFragment<FragmentSatInfoBinding, SatViewModel> implements
        SatViewModel.SatViewModelListener
{
    //private FragmentSatInfoBinding binding;
    private SatViewModel satViewModel;

    private SchoolInfo schoolInfo;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_sat_info;
    }

    @Override
    public int getBindingVariable() {
        return BR.satViewModel;
    }

    @Override
    public SatViewModel getViewModel() {
        satViewModel = new ViewModelProvider(this).get(SatViewModel.class);
        satViewModel.setListener(this);

        return satViewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        schoolInfo = SatInfoFragmentArgs.fromBundle(getArguments()).getSchoolInfo();
        satViewModel.getSatInfo(schoolInfo);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            satViewModel.getSatInfo(schoolInfo);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

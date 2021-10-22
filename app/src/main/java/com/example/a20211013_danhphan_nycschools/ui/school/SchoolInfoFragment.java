package com.example.a20211013_danhphan_nycschools.ui.school;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.a20211013_danhphan_nycschools.R;
import com.example.a20211013_danhphan_nycschools.base.BaseFragment;
import com.example.a20211013_danhphan_nycschools.data.remote.school.SchoolInfo;
import com.example.a20211013_danhphan_nycschools.databinding.FragmentSchoolListBinding;
import com.example.a20211013_danhphan_nycschools.ui.sat.SatInfoFragment;
import com.example.a20211013_danhphan_nycschools.ui.sat.SatInfoFragmentDirections;

import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SchoolInfoFragment extends BaseFragment<FragmentSchoolListBinding, SchoolViewModel>
    implements SchoolViewModel.SchoolViewModelListener,
        SchoolAdapter.SchoolAdapterListener
{

    private FragmentSchoolListBinding binding;
    private SchoolViewModel schoolViewModel;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_school_list;
    }

    @Override
    public int getBindingVariable() {
        return BR.schoolViewModel;
    }

    @Override
    public SchoolViewModel getViewModel() {
        schoolViewModel = new ViewModelProvider(this).get(SchoolViewModel.class);
        schoolViewModel.setListener(this);

        return schoolViewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        binding = getViewDataBinding();

        Context context = getContext();
        SchoolAdapter schoolAdapter;
        LinearLayoutManager linearLayoutManager;

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        linearLayoutManager = new LinearLayoutManager(context);

        schoolAdapter = new SchoolAdapter();
        schoolAdapter.setListener(this);

        binding.rvItems.setLayoutManager(linearLayoutManager);
        binding.rvItems.addItemDecoration(dividerItemDecoration);
        binding.rvItems.setAdapter(schoolAdapter);

        return view;
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClickListener(SchoolInfo schoolInfo) {
        //NavHostFragment.findNavController(this).navigate(R.id.action_schoolInfoFragment_to_satInfoFragment);
        NavHostFragment.findNavController(this).navigate(
                SchoolInfoFragmentDirections.actionSchoolInfoFragmentToSatInfoFragment(schoolInfo));
    }

    @Override
    public void onLocationClick(SchoolInfo schoolInfo) {
        String uri = String.format(Locale.ENGLISH, "geo:0,0?q=%s,%s(%s)&z=16",
                schoolInfo.getLatitude(), schoolInfo.getLongitude(),
                schoolInfo.getSchoolName());

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        // Make the Intent explicit by setting the Google Maps package
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
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
            schoolViewModel.getSchoolsData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

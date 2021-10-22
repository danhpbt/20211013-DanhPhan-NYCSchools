package com.example.a20211013_danhphan_nycschools.data.remote;

import com.example.a20211013_danhphan_nycschools.data.remote.sat.SatInfo;
import com.example.a20211013_danhphan_nycschools.data.remote.school.SchoolInfo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {
    @GET("s3k6-pzi2.json")
    //Single<SchoolInfoResponse> getListSchoolInfo();
    Single<List<SchoolInfo>> getListSchoolInfo();

    @GET("f9bf-2cp4.json")
    Single<List<SatInfo>> getSatInfo(@Query("dbn") String dbm);
}

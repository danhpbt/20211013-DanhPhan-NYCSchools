package com.example.a20211013_danhphan_nycschools.data.remote.sat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SatInfo {
    @Expose
    @SerializedName("num_of_sat_test_takers")
    private String num_of_sat_test_takers = "";

    @Expose
    @SerializedName("sat_critical_reading_avg_score")
    private String sat_critical_reading_avg_score = "";

    @Expose
    @SerializedName("sat_math_avg_score")
    private String sat_math_avg_score = "";

    @Expose
    @SerializedName("sat_writing_avg_score")
    private String sat_writing_avg_score = "";

    public String getNumTestTakers() {
        return num_of_sat_test_takers;
    }

    public String getReadingAvgScore() {
        return sat_critical_reading_avg_score;
    }

    public String getMathAvgScore() {
        return sat_math_avg_score;
    }

    public String getWritingAvgScore() {
        return sat_writing_avg_score;
    }
}

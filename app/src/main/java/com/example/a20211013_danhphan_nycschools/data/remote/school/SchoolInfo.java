package com.example.a20211013_danhphan_nycschools.data.remote.school;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.a20211013_danhphan_nycschools.data.remote.sat.SatInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SchoolInfo implements Parcelable {
    @Expose
    @SerializedName("dbn")
    private String dbn = "";

    @Expose
    @SerializedName("school_name")
    private String school_name = "";

    @Expose
    @SerializedName("primary_address_line_1")
    private String primary_address = "";

    @Expose
    @SerializedName("city")
    private String city = "";

    @Expose
    @SerializedName("zip")
    private String zip = "";

    @Expose
    @SerializedName("state_code")
    private String state_code = "";

    @Expose
    @SerializedName("latitude")
    private String latitude = "";

    @Expose
    @SerializedName("longitude")
    private String longitude = "";

    @Expose
    @SerializedName("phone_number")
    private String phone_number = "";

    @Expose
    @SerializedName("fax_number")
    private String fax_number = "";

    @Expose
    @SerializedName("school_email")
    private String school_email = "";

    @Expose
    @SerializedName("website")
    private String website = "";

    private SatInfo satInfo = new SatInfo();

    public String getDbn() {
        return dbn;
    }

    public String getSchoolName() {
        return school_name;
    }

    public String getPrimaryAddress() {
        return primary_address;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public String getStateCode() {
        return state_code;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public String getFaxNumber() {
        return fax_number;
    }

    public String getEmail() {
        return school_email;
    }

    public String getWebsite() {
        return website;
    }

    public SatInfo getSatInfo()
    {
        return satInfo;
    }

    public void setSatInfo(SatInfo satInfo)
    {
        this.satInfo = satInfo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(dbn);
        parcel.writeString(school_name);
        parcel.writeString(phone_number);
        parcel.writeString(fax_number);
        parcel.writeString(school_email);
        parcel.writeString(website);
    }

    public SchoolInfo()
    {

    }

    // Parcelling part
    public SchoolInfo(Parcel in){
        dbn = in.readString();
        school_name = in.readString();
        phone_number = in.readString();
        fax_number = in.readString();
        school_email = in.readString();
        website = in.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public SchoolInfo createFromParcel(Parcel in) {
            return new SchoolInfo(in);
        }

        public SchoolInfo[] newArray(int size) {
            return new SchoolInfo[size];
        }
    };
}

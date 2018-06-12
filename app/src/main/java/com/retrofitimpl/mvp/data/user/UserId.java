package com.retrofitimpl.mvp.data.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UserId implements Parcelable {

    @SerializedName("name")
    private String name;
    @SerializedName("value")
    private String value;

    public UserId(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    protected UserId(Parcel in) {
        name = in.readString();
        value = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(value);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UserId> CREATOR = new Parcelable.Creator<UserId>() {
        @Override
        public UserId createFromParcel(Parcel in) {
            return new UserId(in);
        }

        @Override
        public UserId[] newArray(int size) {
            return new UserId[size];
        }
    };
}

package com.retrofitimpl.mvp.data.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UserPictures implements Parcelable {

    @SerializedName("large")
    private String large;
    @SerializedName("medium")
    private String medium;
    @SerializedName("thumbnail")
    private String thumbnail;

    public UserPictures(String large, String medium, String thumbnail) {
        this.large = large;
        this.medium = medium;
        this.thumbnail = thumbnail;
    }

    public String getLarge() {
        return large;
    }

    public String getMedium() {
        return medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    protected UserPictures(Parcel in) {
        large = in.readString();
        medium = in.readString();
        thumbnail = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(large);
        dest.writeString(medium);
        dest.writeString(thumbnail);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UserPictures> CREATOR = new Parcelable.Creator<UserPictures>() {
        @Override
        public UserPictures createFromParcel(Parcel in) {
            return new UserPictures(in);
        }

        @Override
        public UserPictures[] newArray(int size) {
            return new UserPictures[size];
        }
    };
}
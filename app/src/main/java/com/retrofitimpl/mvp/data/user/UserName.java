package com.retrofitimpl.mvp.data.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UserName implements Parcelable {

    @SerializedName("title")
    private String title;
    @SerializedName("first")
    private String first;
    @SerializedName("last")
    private String last;

    public UserName(String title, String first, String last) {
        this.title = title;
        this.first = first;
        this.last = last;
    }

    public String getTitle() {
        return title;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setLast(String last) {
        this.last = last;
    }

    protected UserName(Parcel in) {
        title = in.readString();
        first = in.readString();
        last = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(first);
        dest.writeString(last);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UserName> CREATOR = new Parcelable.Creator<UserName>() {
        @Override
        public UserName createFromParcel(Parcel in) {
            return new UserName(in);
        }

        @Override
        public UserName[] newArray(int size) {
            return new UserName[size];
        }
    };
}

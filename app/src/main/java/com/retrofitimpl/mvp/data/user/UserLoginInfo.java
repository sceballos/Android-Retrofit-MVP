package com.retrofitimpl.mvp.data.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UserLoginInfo implements Parcelable {

    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("salt")
    private String salt;
    @SerializedName("md5")
    private String md5;
    @SerializedName("sha1")
    private String sha1;
    @SerializedName("sha256")
    private String sha256;

    public UserLoginInfo(String username, String password, String salt, String md5, String sha1, String sha256) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.md5 = md5;
        this.sha1 = sha1;
        this.sha256 = sha256;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getMd5() {
        return md5;
    }

    public String getSha1() {
        return sha1;
    }

    public String getSha256() {
        return sha256;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    protected UserLoginInfo(Parcel in) {
        username = in.readString();
        password = in.readString();
        salt = in.readString();
        md5 = in.readString();
        sha1 = in.readString();
        sha256 = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(salt);
        dest.writeString(md5);
        dest.writeString(sha1);
        dest.writeString(sha256);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UserLoginInfo> CREATOR = new Parcelable.Creator<UserLoginInfo>() {
        @Override
        public UserLoginInfo createFromParcel(Parcel in) {
            return new UserLoginInfo(in);
        }

        @Override
        public UserLoginInfo[] newArray(int size) {
            return new UserLoginInfo[size];
        }
    };
}
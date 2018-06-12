package com.retrofitimpl.mvp.data.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class RandomUser implements Parcelable {

    @SerializedName("id")
    private UserId userId;
    @SerializedName("name")
    private UserName userName;
    @SerializedName("location")
    private UserLocation userLocation;
    @SerializedName("login")
    private UserLoginInfo userLoginInfo;
    @SerializedName("picture")
    private UserPictures userPictures;
    @SerializedName("gender")
    private String gender;
    @SerializedName("email")
    private String email;
    @SerializedName("dob")
    private String dob;
    @SerializedName("registered")
    private String registeredDate;
    @SerializedName("phone")
    private String phone;
    @SerializedName("cell")
    private String cellPhone;
    @SerializedName("nat")
    private String nationality;

    public RandomUser(UserId userId, UserName userName, UserLocation userLocation,
                      UserLoginInfo userLoginInfo, UserPictures userPictures,
                      String gender, String email, String dob, String registeredDate,
                      String phone, String cellPhone, String nationality) {
        this.userId = userId;
        this.userName = userName;
        this.userLocation = userLocation;
        this.userLoginInfo = userLoginInfo;
        this.userPictures = userPictures;
        this.gender = gender;
        this.email = email;
        this.dob = dob;
        this.registeredDate = registeredDate;
        this.phone = phone;
        this.cellPhone = cellPhone;
        this.nationality = nationality;
    }

    public UserId getUserId() {
        return userId;
    }

    public UserName getUserName() {
        return userName;
    }

    public UserLocation getUserLocation() {
        return userLocation;
    }

    public UserLoginInfo getUserLoginInfo() {
        return userLoginInfo;
    }

    public UserPictures getUserPictures() {
        return userPictures;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getDob() {
        return dob;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public String getPhone() {
        return phone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public String getNationality() {
        return nationality;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public void setUserName(UserName userName) {
        this.userName = userName;
    }

    public void setUserLocation(UserLocation userLocation) {
        this.userLocation = userLocation;
    }

    public void setUserLoginInfo(UserLoginInfo userLoginInfo) {
        this.userLoginInfo = userLoginInfo;
    }

    public void setUserPictures(UserPictures userPictures) {
        this.userPictures = userPictures;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    protected RandomUser(Parcel in) {
        userId = (UserId) in.readValue(UserId.class.getClassLoader());
        userName = (UserName) in.readValue(UserName.class.getClassLoader());
        userLocation = (UserLocation) in.readValue(UserLocation.class.getClassLoader());
        userLoginInfo = (UserLoginInfo) in.readValue(UserLoginInfo.class.getClassLoader());
        userPictures = (UserPictures) in.readValue(UserPictures.class.getClassLoader());
        gender = in.readString();
        email = in.readString();
        dob = in.readString();
        registeredDate = in.readString();
        phone = in.readString();
        cellPhone = in.readString();
        nationality = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(userId);
        dest.writeValue(userName);
        dest.writeValue(userLocation);
        dest.writeValue(userLoginInfo);
        dest.writeValue(userPictures);
        dest.writeString(gender);
        dest.writeString(email);
        dest.writeString(dob);
        dest.writeString(registeredDate);
        dest.writeString(phone);
        dest.writeString(cellPhone);
        dest.writeString(nationality);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<RandomUser> CREATOR = new Parcelable.Creator<RandomUser>() {
        @Override
        public RandomUser createFromParcel(Parcel in) {
            return new RandomUser(in);
        }

        @Override
        public RandomUser[] newArray(int size) {
            return new RandomUser[size];
        }
    };
}

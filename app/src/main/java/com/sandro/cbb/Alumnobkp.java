package com.sandro.cbb;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Alumnobkp implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("birthday")
    @Expose
    private Date birthday;
    @SerializedName("hobby")
    @Expose
    private String hobby;
    @SerializedName("profession")
    @Expose
    private String profession;
    @SerializedName("about_you")
    @Expose
    private String aboutYou;
    @SerializedName("photo_small")
    @Expose
    private String photoSmall;
    @SerializedName("photo_large")
    @Expose
    private String photoLarge;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAboutYou() {
        return aboutYou;
    }

    public void setAboutYou(String aboutYou) {
        this.aboutYou = aboutYou;
    }

    public String getPhotoSmall() {
        return photoSmall;
    }

    public void setPhotoSmall(String photoSmall) {
        this.photoSmall = photoSmall;
    }

    public String getPhotoLarge() {
        return photoLarge;
    }

    public void setPhotoLarge(String photoLarge) {
        this.photoLarge = photoLarge;
    }


    protected Alumnobkp(Parcel in) {
        name = in.readString();
        lastName = in.readString();
        long tmpBirthday = in.readLong();
        birthday = tmpBirthday != -1 ? new Date(tmpBirthday) : null;
        hobby = in.readString();
        profession = in.readString();
        aboutYou = in.readString();
        photoSmall = in.readString();
        photoLarge = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(lastName);
        dest.writeLong(birthday != null ? birthday.getTime() : -1L);
        dest.writeString(hobby);
        dest.writeString(profession);
        dest.writeString(aboutYou);
        dest.writeString(photoSmall);
        dest.writeString(photoLarge);
    }

}
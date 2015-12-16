package com.renovavision.androidimageloading.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Alexandr Golovach on 21.11.2015.
 */
public class Image implements Parcelable {

    @SerializedName("title")
    private String title;

    @SerializedName("width")
    private int width;

    @SerializedName("height")
    private int height;

    @SerializedName("type")
    private String type;

    @SerializedName("link")
    private String link;

    public Image() {
    }

    protected Image(Parcel in) {
        title = in.readString();
        width = in.readInt();
        height = in.readInt();
        type = in.readString();
        link = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getType() {
        return type;
    }

    public String getLink() {
        return link;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(width);
        dest.writeInt(height);
        dest.writeString(type);
        dest.writeString(link);
    }
}

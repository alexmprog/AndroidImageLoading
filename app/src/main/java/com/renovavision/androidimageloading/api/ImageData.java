package com.renovavision.androidimageloading.api;

import com.google.gson.annotations.SerializedName;
import com.renovavision.androidimageloading.model.Image;

import java.util.List;

/**
 * Created by Alexandr Golovach on 21.11.2015.
 */
public class ImageData {

    @SerializedName("data")
    private List<Image> images;

    public ImageData() {
    }

    public List<Image> getImages() {
        return images;
    }
}

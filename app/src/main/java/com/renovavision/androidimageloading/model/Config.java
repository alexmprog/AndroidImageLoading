package com.renovavision.androidimageloading.model;

/**
 * Created by Alexandr Golovach on 24.11.2015.
 */

public class Config {

    public enum ConfigType {
        PICASSO, UIL, FRESCO, GLIDE, VOLLEY;
    }

    public enum ConfigFormat {
        ARGB_8888, RGB_565;
    }

    private ConfigType mType;
    private String mName;

    public Config(ConfigType type, String name) {
        this.mType = type;
        this.mName = name;
    }

    public ConfigType getType() {
        return mType;
    }

    public String getName() {
        return mName;
    }

}

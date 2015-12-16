package com.renovavision.androidimageloading.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.renovavision.androidimageloading.model.Config;

/**
 * Created by Alexandr Golovach on 24.11.2015.
 */
public class ConfigManager {

    private static final String CONFIG_STORE = "Config_Store";
    private static final String CONFIG_TYPE = "Config_Type";
    private static final String CONFIG_FORMAT = "Config_Format";

    private static volatile ConfigManager sInstance;

    public static ConfigManager getInstance() {
        if (sInstance == null) {
            synchronized (ConfigManager.class) {
                if (sInstance == null) {
                    sInstance = new ConfigManager();
                }
            }
        }
        return sInstance;
    }

    private Config.ConfigFormat mConfigFormat;
    private Config.ConfigType mConfigType;

    private SharedPreferences mSharedPreferences;

    public void setUp(Context context, Config.ConfigFormat mConfigFormat, Config.ConfigType mConfigType) {
        this.mConfigFormat = mConfigFormat;
        this.mConfigType = mConfigType;
    }

    private void storeConfigSettings(Context context) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(CONFIG_STORE, 0);
            final SharedPreferences.Editor editor = mSharedPreferences.edit();


        }
    }

    private void applyConfig(Context context) {
        // TODO: configure library
        if (mConfigType == Config.ConfigType.PICASSO) {
        }
    }

    public Config.ConfigType getConfigType() {
        return mConfigType;
    }
}

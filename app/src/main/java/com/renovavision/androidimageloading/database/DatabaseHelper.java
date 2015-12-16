package com.renovavision.androidimageloading.database;

import android.content.Context;
import android.util.Log;

import com.renovavision.androidimageloading.model.Image;
import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Alexandr Golovach on 21.11.2015.
 */
public class DatabaseHelper {

    private static final String TAG = DatabaseHelper.class.getName();
    private static final String DB_NAME = "images";
    private static final String IMAGE_KEY = "image_key";

    private static DB openDatabase(Context context) {
        try {
            return DBFactory.open(context, DB_NAME);
        } catch (SnappydbException e) {
            Log.e("Can not initialize db", TAG, e);
            return null;
        }
    }

    public static List<Image> getImages(Context context) {
        DB snappyDb = openDatabase(context);
        if (snappyDb != null) {
            try {
                return Arrays.asList(snappyDb.getObjectArray(IMAGE_KEY, Image.class));
            } catch (SnappydbException e) {
                Log.e("Can not load images", TAG, e);
            }
        }
        return null;
    }

    public static void setImages(Context context, List<Image> images) {
        DB snappyDb = openDatabase(context);
        if (snappyDb != null) {
            try {
                snappyDb.put(IMAGE_KEY, images);
            } catch (SnappydbException e) {
                Log.e("Can not insert images", TAG, e);
            }
        }
    }
}

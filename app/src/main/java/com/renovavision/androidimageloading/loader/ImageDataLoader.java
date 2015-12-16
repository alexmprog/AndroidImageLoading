package com.renovavision.androidimageloading.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.renovavision.androidimageloading.api.ImgurApi;
import com.renovavision.androidimageloading.database.DatabaseHelper;
import com.renovavision.androidimageloading.model.Image;

import java.util.List;

/**
 * Created by Alexandr Golovach on 21.11.2015.
 */
public class ImageDataLoader extends AsyncTaskLoader<List<Image>> {

    public ImageDataLoader(Context context) {
        super(context);
    }

    @Override
    public List<Image> loadInBackground() {
        final List<Image> images = DatabaseHelper.getImages(getContext());
        if (images == null || images.isEmpty()) {
            final List<Image> imageList = ImgurApi.getInstance().getImages(1);
            DatabaseHelper.setImages(getContext(), imageList);
            return imageList;
        }
        return images;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }
}

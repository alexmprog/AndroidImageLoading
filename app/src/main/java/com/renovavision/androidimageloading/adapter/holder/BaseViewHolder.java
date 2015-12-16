package com.renovavision.androidimageloading.adapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.renovavision.androidimageloading.model.Image;

/**
 * Created by Alexandr Golovach on 23.11.2015.
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    protected int mImageSize;

    public BaseViewHolder(View itemView, int imageSize) {
        super(itemView);
        this.mImageSize = imageSize;
    }

    public abstract void bindData(Context context, Image image);
}

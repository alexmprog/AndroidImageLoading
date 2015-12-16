package com.renovavision.androidimageloading.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.renovavision.androidimageloading.R;
import com.renovavision.androidimageloading.model.Image;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Alexandr Golovach on 23.11.2015.
 */
public class PicassoViewHolder extends BaseViewHolder {

    @Bind(R.id.img_thumbnail)
    public ImageView imgThumbnail;

    public PicassoViewHolder(View itemView, int imageSize) {
        super(itemView, imageSize);
        ButterKnife.bind(this, itemView);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(mImageSize, mImageSize);
        imgThumbnail.setLayoutParams(params);
    }

    @Override
    public void bindData(Context context, Image image) {
        Picasso.with(context).load(image.getLink()).placeholder(R.mipmap.ic_launcher).resize(mImageSize, mImageSize).centerCrop().
                into(imgThumbnail);
    }
}

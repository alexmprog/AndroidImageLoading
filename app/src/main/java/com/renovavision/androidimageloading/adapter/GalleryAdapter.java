package com.renovavision.androidimageloading.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.renovavision.androidimageloading.R;
import com.renovavision.androidimageloading.adapter.holder.BaseViewHolder;
import com.renovavision.androidimageloading.adapter.holder.PicassoViewHolder;
import com.renovavision.androidimageloading.model.Config;
import com.renovavision.androidimageloading.model.Image;
import com.renovavision.androidimageloading.utils.ConfigManager;

import java.util.List;

/**
 * Created by Alexandr Golovach on 22.11.2015.
 */
public class GalleryAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Image> mItems;
    private Context mContext;
    private int mImageSize;

    private Config.ConfigType mConfigType;

    public GalleryAdapter(Context context, List<Image> images, int imageSize) {
        super();
        mItems = images;
        mContext = context;
        mImageSize = imageSize;
        this.mConfigType = ConfigManager.getInstance().getConfigType();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grid_item, viewGroup, false);
        BaseViewHolder viewHolder = null;
        switch (mConfigType) {
            case PICASSO:
                viewHolder = new PicassoViewHolder(view, mImageSize);
                break;
            default:
                viewHolder = new PicassoViewHolder(view, mImageSize);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, int i) {
        Image image = mItems.get(i);
        if (image != null) {
            viewHolder.bindData(mContext, image);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setImages(List<Image> images) {
        mItems = images;
        notifyDataSetChanged();
    }

    public Image getImageByPosition(int position) {
        if (mItems != null && !mItems.isEmpty()) {
            return mItems.get(position);
        }
        return null;
    }
}

package com.renovavision.androidimageloading.ui;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Alexandr Golovach on 22.11.2015.
 */
public class RectangleRecyclerItemDecoration extends RecyclerView.ItemDecoration {

    private final int mSpace;

    public RectangleRecyclerItemDecoration(int space) {
        mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = mSpace / 2;
        outRect.right = mSpace / 2;
        outRect.top = mSpace / 2;
        outRect.bottom = mSpace / 2;
    }
}

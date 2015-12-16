package com.renovavision.androidimageloading.activity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.renovavision.androidimageloading.R;
import com.renovavision.androidimageloading.adapter.GalleryAdapter;
import com.renovavision.androidimageloading.loader.ImageDataLoader;
import com.renovavision.androidimageloading.model.Image;
import com.renovavision.androidimageloading.ui.RecyclerItemClickListener;
import com.renovavision.androidimageloading.ui.RectangleRecyclerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GalleryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Image>> {

    private static final int LOADER_ID = 1;

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Bind(R.id.recycler_view)
    protected RecyclerView mRecyclerView;

    private GalleryAdapter mGridAdapter;

    // should be rectangle
    private int imageSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galery);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new RectangleRecyclerItemDecoration(getResources().getDimensionPixelSize(R.dimen.grid_item_margin)));

        imageSize = calculateGridImageSize();

        // The number of Columns
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                final Image image = mGridAdapter.getImageByPosition(position);
                if (image != null) {
                    Intent intent = new Intent(GalleryActivity.this, InfoActivity.class);
                    intent.putExtra(InfoActivity.ARG_IMAGE, image);
                    startActivity(intent);
                }
            }
        }));

        mGridAdapter = new GalleryAdapter(this, new ArrayList<Image>(), imageSize);
        mRecyclerView.setAdapter(mGridAdapter);

        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<List<Image>> onCreateLoader(int id, Bundle args) {
        return new ImageDataLoader(getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<List<Image>> loader, List<Image> data) {
        if (data != null && !data.isEmpty()) {
            mGridAdapter.setImages(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Image>> loader) {

    }

    private int calculateGridImageSize() {
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display defaultDisplay = windowManager.getDefaultDisplay();
        Point p = new Point();
        defaultDisplay.getSize(p);
        int itemMargin = getResources().getDimensionPixelSize(R.dimen.grid_item_margin);
        return (p.x - 4 * itemMargin) / 3;
    }
}

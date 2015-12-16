package com.renovavision.androidimageloading.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.renovavision.androidimageloading.R;
import com.renovavision.androidimageloading.model.Image;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Alexandr Golovach on 23.11.2015.
 */
public class InfoActivity extends AppCompatActivity {

    public static final String ARG_IMAGE = "Image_Arg";

    @Bind(R.id.image_title_text)
    protected TextView mTitleText;

    @Bind(R.id.image_type_text)
    protected TextView mTypeText;

    @Bind(R.id.image_size_text)
    protected TextView mSizeText;

    @Bind(R.id.image_view)
    protected ImageView mImageView;

    private Image mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);

        final Intent intent = getIntent();
        if (intent != null) {
            mImage = intent.getParcelableExtra(ARG_IMAGE);
            setImageData();
        }
    }

    private void setImageData() {
        if (mImage != null) {
            Picasso.with(this).load(mImage.getLink()).placeholder(R.mipmap.ic_launcher).
                    into(mImageView);

            mTitleText.setText(String.format(getString(R.string.image_title), mImage.getTitle()));
            mTypeText.setText(String.format(getString(R.string.image_type), mImage.getType()));
            mSizeText.setText(String.format(getString(R.string.image_size), mImage.getWidth(), mImage.getHeight()));
        }
    }
}

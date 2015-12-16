package com.renovavision.androidimageloading.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;

import com.renovavision.androidimageloading.R;
import com.renovavision.androidimageloading.adapter.ConfigAdapter;
import com.renovavision.androidimageloading.model.Config;
import com.renovavision.androidimageloading.ui.RecyclerItemClickListener;
import com.renovavision.androidimageloading.utils.ConfigManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Alexandr Golovach on 24.11.2015.
 */
public class ConfigActivity extends AppCompatActivity {

    @Bind(R.id.recycler_view)
    protected RecyclerView mRecyclerView;

    @Bind(R.id.radio)
    protected RadioGroup mRadioGroup;

    private ConfigAdapter mAdapter;

    private Config.ConfigFormat mFormat = Config.ConfigFormat.ARGB_8888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        ButterKnife.bind(this);

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_argb:
                        mFormat = Config.ConfigFormat.ARGB_8888;
                        break;
                    case R.id.radio_rgb:
                        mFormat = Config.ConfigFormat.RGB_565;
                        break;
                }
            }
        });

        // The number of Columns
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                final Config config = mAdapter.getConfigByPosition(position);
                if (config != null) {
                    ConfigManager.getInstance().setUp(ConfigActivity.this, mFormat, config.getType());
                    Intent intent = new Intent(ConfigActivity.this, GalleryActivity.class);
                    startActivity(intent);
                }
            }
        }));

        mAdapter = new ConfigAdapter(this, generateConfigList());
        mRecyclerView.setAdapter(mAdapter);

    }

    private List<Config> generateConfigList() {
        List<Config> configList = new ArrayList<>();
        configList.add(new Config(Config.ConfigType.FRESCO, "Fresco"));
        configList.add(new Config(Config.ConfigType.GLIDE, "Glide"));
        configList.add(new Config(Config.ConfigType.PICASSO, "Picasso"));
        configList.add(new Config(Config.ConfigType.VOLLEY, "Volley"));
        configList.add(new Config(Config.ConfigType.VOLLEY, "UIL"));
        return configList;
    }
}

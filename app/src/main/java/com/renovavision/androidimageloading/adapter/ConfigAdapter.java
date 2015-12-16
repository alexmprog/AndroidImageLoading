package com.renovavision.androidimageloading.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.renovavision.androidimageloading.R;
import com.renovavision.androidimageloading.model.Config;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Alexandr Golovach on 24.11.2015.
 */
public class ConfigAdapter extends RecyclerView.Adapter<ConfigAdapter.ConfigViewHolder> {

    private Context mContext;
    private List<Config> mConfigList;

    public ConfigAdapter(Context context, List<Config> configList) {
        super();
        this.mContext = context;
        this.mConfigList = configList;
    }

    @Override
    public ConfigViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.config_item, parent, false);
        return new ConfigViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ConfigViewHolder holder, int position) {
        final Config config = mConfigList.get(position);
        if (config != null) {
            holder.mTextName.setText(config.getName());
        }
    }

    @Override
    public int getItemCount() {
        return mConfigList.size();
    }

    public Config getConfigByPosition(int position) {
        if (mConfigList != null && !mConfigList.isEmpty()) {
            return mConfigList.get(position);
        }
        return null;
    }

    public static class ConfigViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.text_name)
        protected TextView mTextName;

        public ConfigViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

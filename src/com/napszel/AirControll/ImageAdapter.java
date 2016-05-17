package com.napszel.AirControll;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ImageAdapter extends BaseAdapter {
    private final Model model;

    private Context mContext;

    public ImageAdapter(Context c, Model m) {
        mContext = c;
        this.model = m;
        this.model.registerAdapter(this);
    }

    public int getCount() {
        return model.getCount();
    }

    @Override
    public Model.Elem getItem(int position) {
        return model.getElem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup _parent) {
        return model.getImageView(mContext, position);
    }

}

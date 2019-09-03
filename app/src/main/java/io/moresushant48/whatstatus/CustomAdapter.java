package io.moresushant48.whatstatus;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class CustomAdapter extends BaseAdapter {

    private Uri[] images;
    private Context context;
    private LayoutInflater layoutInflater;
    private ImageView playSign;

    public CustomAdapter(Context context, Uri[] images) {
        this.context = context;
        this.images = images;
        this.layoutInflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.activity_gridview, null); // inflate the layout
        playSign = convertView.findViewById(R.id.playSign);
        ImageView icon = (ImageView) convertView.findViewById(R.id.icon); // get the reference of ImageView

        Glide
                .with(context)
                .load(images[position].toString())
                .transition(DrawableTransitionOptions.withCrossFade(1000))
                .into(icon);

        if(images[position].toString().endsWith(".mp4")){
            playSign.setVisibility(View.VISIBLE);
        }

        icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return convertView;
    }
}
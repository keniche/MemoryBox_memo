package com.tokunaga.kensun.memory;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class GalleryAdapter extends ArrayAdapter<Gallery> {
    List<Gallery> mGallery;

    public GalleryAdapter(Context context, int layoutResourceID, List<Gallery> objects){
        super(context, layoutResourceID, objects);

        mGallery =objects;
    }
}

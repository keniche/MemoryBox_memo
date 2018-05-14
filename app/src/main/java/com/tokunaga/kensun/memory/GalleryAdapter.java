package com.tokunaga.kensun.memory;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

import io.realm.Realm;

public class GalleryAdapter extends ArrayAdapter<Gallery> {
    List<Gallery> mGallery;
    public Realm realm;

    public GalleryAdapter(Context context, int layoutResourceID, List<Gallery> objects) {
        super(context, layoutResourceID, objects);

        mGallery = objects;
    }

    @Override
    public int getCount() {
        return mGallery.size();
    }

    @Override
    public Gallery getItem(int position) {
        return mGallery.get(position);
    }

    public static class ViewHolder {
        ImageView imageView1;
        ImageView imageView2;
        ImageView imageView3;

        public ViewHolder(View view) {
            imageView1 = (ImageView) view.findViewById(R.id.image_view_1);
            imageView2 = (ImageView) view.findViewById(R.id.image_view_2);
            imageView3 = (ImageView) view.findViewById(R.id.image_view_3);

        }
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        //TODO:Realmをどこで開いてどこで閉じればいいのか？
        realm = Realm.getDefaultInstance();

        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.gallery, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Gallery item = getItem(position);

        if (item != null) {

            //set data
            viewHolder.imageView1.setImageView(item.memo1.pictures1);
            viewHolder.imageView2.setImageView(item.memo2.pictures1);
            viewHolder.imageView3.setImageView(item.memo3.pictures1);
            //TODO:byteをbitmapに直す処理が必要


            viewHolder.imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(, AddActivity.class);
                    startActivity(intent);
                    //TODO:ここの第一引数が分からない
                }
            });
        }

        return convertView;
        realm.close();
    }
}


//中身が空じゃない時だけ、反映する。if（ != null){}

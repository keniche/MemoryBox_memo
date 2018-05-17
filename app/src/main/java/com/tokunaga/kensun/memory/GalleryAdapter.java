package com.tokunaga.kensun.memory;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class GalleryAdapter extends ArrayAdapter<Gallery> {
    List<Gallery> mGallery;
    //public Realm realm;

    //第三引数は、mainActivityのadpArray。コンストラクタ内でこのclassのmGalleryに入れられる。
    public GalleryAdapter(Context context, int layoutResourceID, List<Gallery> object) {
        super(context, layoutResourceID, object);

        mGallery = object;
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

        //realm = Realm.getDefaultInstance();

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
            if (item.memo1.pictures1.length != 0) {
                //byte[]をbitmapに変更、setする。
                Bitmap bitmap = BitmapFactory.decodeByteArray(item.memo1.pictures1, 0, item.memo1.pictures1.length);
                viewHolder.imageView1.setImageBitmap(bitmap);
            }
            if (item.memo2.pictures1.length != 0) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(item.memo2.pictures1, 0, item.memo2.pictures1.length);
                viewHolder.imageView2.setImageBitmap(bitmap);
            }
            if (item.memo3.pictures1.length != 0) {
                //byte[]をbitmapに変更、setする。
                Bitmap bitmap = BitmapFactory.decodeByteArray(item.memo3.pictures1, 0, item.memo3.pictures1.length);
                viewHolder.imageView3.setImageBitmap(bitmap);
            }


//            //再度編集するためのonClick。とりあえずコメントアウト。
//            viewHolder.imageView1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(, AddActivity.class);
            //TODO:ここの第一引数が分からない
//                    startActivity(intent);
//                }
//            });
        }
        return convertView;
        //realm.close();
    }

}



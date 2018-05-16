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

    //TODO:Realmから何をどうやって取り出して、どこにいれておけばいい？→この処理自体不必要かも
//    //→memo型のGalleryクラスを
//    //→picture1を取り出して、配列にでも入れておけばいい？
//    //下の方でitem.memo1.picture1とかで認識されてるのを見ると、そもそもRealmから取り出すことは不要？
//    public void set() {
//        memoArray.clear();
//        RealmResults<Memo> results = realm.where(Memo.class).findAll();
//        List<Memo> item = realm.copyFromRealm(results);
//        memoArray.addAll(item);
//    }


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
            //TODO:下の条件分岐に自信がない。づめさんが言ってた、mainActivityに空の画像が表示される可能性を考えた条件分岐を作りたい。
            if (item.memo1.pictures1 != null) {

                //byte[]をbitmapに変更
                //TODO：なんで、ここでpicture1が認識されてるの？よくよく考えたら、Realmから取り出してないのに。いや、行けて当たり前？それなら、上の方でRealmから取り出すなんて処理いらない？
                //TODO:ここの処理が間違えている可能性？
                Bitmap bitmap1 = BitmapFactory.decodeByteArray(item.memo1.pictures1, 0, item.memo1.pictures1.length);
                Bitmap bitmap2 = BitmapFactory.decodeByteArray(item.memo2.pictures1, 0, item.memo2.pictures1.length);
                Bitmap bitmap3 = BitmapFactory.decodeByteArray(item.memo3.pictures1, 0, item.memo3.pictures1.length);

                //TODO:ここのsetImageBitmapは合ってる？
                viewHolder.imageView1.setImageBitmap(bitmap1);
                viewHolder.imageView2.setImageBitmap(bitmap2);
                viewHolder.imageView3.setImageBitmap(bitmap3);


//            //再度編集するためのonClick。とりあえずコメントアウト。
//            viewHolder.imageView1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(, AddActivity.class);
                      //TODO:ここの第一引数が分からない
//                    startActivity(intent);
//
//                }
//            });
            }
        }
        return convertView;
        //realm.close();
    }
}


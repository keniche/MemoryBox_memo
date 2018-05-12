package android.lifeistech.com.memo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import io.realm.Realm;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class MemoryFragment extends Fragment {
    int position;
    static final int REQUEST_CODE_GALLERY = 1;
    //static final int REQUEST_CODE_CAMERA = 2;

    ImageView imageView;
    public Realm realm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //realm = Realm.getDefaultInstance();

        View view = inflater.inflate(
                R.layout.fragment_memory, container, false);
        imageView = (ImageView) view.findViewById(R.id.imageView);

        Bundle args = getArguments();
        if (args != null) {
            imageView.setImageResource(args.getInt("image_id"));
            position = args.getInt("position");
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


        ImageView imageView = (ImageView) getActivity().findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
        });

        //カメラの起動をLongClickで実装してみようとした
//        imageView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public void onLongClick(View v) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, REQUEST_CODE_CAMERA);
//            }
//        });
    }

    //Realmのsaveメソッド
//    public void save(final byte[] pictures) {
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                Memo memo = realm.createObject(Memo.class);
//                memo.pictures = pictures;
//            }
//        });
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK) {
            try {
                //bitmapを取得？
                InputStream inputStream = getContext().getContentResolver().openInputStream(intent.getData());
                Bitmap bmp = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                imageView.setImageBitmap(bmp);

                //bitmapをbyteに変える
//                ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
//                byte[] pictures = bos.toByteArray();

                //byteをRealmに保存
                //save(pictures);


            } catch (Exception e) {
                Toast.makeText(getActivity(), "エラー", Toast.LENGTH_LONG).show();
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getActivity(), "CANCELED", Toast.LENGTH_LONG).show();
        }
    }

//    //Realmを閉じる
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//        realm.close();
//    }
}

package com.tokunaga.kensun.memory;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tokunaga.kensun.memory.R;

import android.os.Bundle;
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

    MemoryFragmentListener mListener;


    //インターフェイスの定義
    public interface MemoryFragmentListener {
        public void dataDeliver(byte[] picture);
    }

    //データ渡す準備
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mListener = (MemoryFragmentListener) activity;
    }

    //Realmを開く
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        realm = Realm.getDefaultInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_memory, container, false);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

        Bundle args = getArguments();
        if (args != null) {
            imageView.setImageResource(args.getInt("image_id"));
            position = args.getInt("position");
        }
        return view;
    }

    //ギャラリー起動後の処理
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK) {
            try {
                //bitmapを取得
                InputStream inputStream = getContext().getContentResolver().openInputStream(intent.getData());
                Bitmap bmp = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                imageView.setImageBitmap(bmp);

                //bitmapをbyteに変える
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                byte[] pictures = bos.toByteArray();

                mListener.dataDeliver(pictures);

                //Realmには保存しない

            } catch (Exception e) {
                Toast.makeText(getActivity(), "エラー", Toast.LENGTH_LONG).show();
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getActivity(), "CANCELED", Toast.LENGTH_LONG).show();
        }
    }


    //Realmを閉じる
    @Override
    public void onDestroy() {
        super.onDestroy();

        realm.close();
    }
}

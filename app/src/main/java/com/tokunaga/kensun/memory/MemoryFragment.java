package com.tokunaga.kensun.memory;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.tokunaga.kensun.memory.R;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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


public class MemoryFragment extends Fragment implements View.OnClickListener {
    int position;
    static final int REQUEST_CODE_GALLERY = 1;

    ImageView imageView;
    public Realm realm;

    byte[] pictures = new byte[0];

    MemoryFragmentListener memoryFragmentListener;

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
        enableListener(true);


        Bundle args = getArguments();
        if (args != null) {
            imageView.setImageResource(args.getInt("image_id"));
            position = args.getInt("position");


            Resources res = getContext().getResources();
            Drawable drawable = res.getDrawable(args.getInt("image_id"));
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            pictures = stream.toByteArray();

        }

        memoryFragmentListener.onReady(position);
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
                pictures = bos.toByteArray();


                //Realmには保存しない

            } catch (Exception e) {
                Toast.makeText(getActivity(), "エラー", Toast.LENGTH_LONG).show();
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getActivity(), "CANCELED", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        memoryFragmentListener = (MemoryFragmentListener) activity;

    }

    //Realmを閉じる
    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    public byte[] getImage() {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        //bitmapをbyteに変える
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        pictures = bos.toByteArray();
        return pictures;

    }

    public void setImage(Bitmap bitmap) {
        Log.e("TAG", imageView.toString());
        imageView.setImageBitmap(bitmap);
    }

    public void enableListener(Boolean enable) {
        if (enable) {
            imageView.setOnClickListener(this);
        } else {
            imageView.setOnClickListener(null);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_GALLERY);
    }
}

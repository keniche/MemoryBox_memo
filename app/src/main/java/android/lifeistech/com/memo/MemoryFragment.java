package android.lifeistech.com.memo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class MemoryFragment extends Fragment {
    int position;
    static final int REQUEST_CODE_GALLERY = 1;
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

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
    }

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

                //bitmapをbyteに変えて、realmに保存？

            } catch (Exception e) {
                Toast.makeText(getActivity(), "エラー", Toast.LENGTH_LONG).show();
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getActivity(), "CANCELED", Toast.LENGTH_LONG).show();
        }
    }
}

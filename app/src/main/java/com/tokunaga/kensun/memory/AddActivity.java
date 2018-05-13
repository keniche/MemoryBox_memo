package com.tokunaga.kensun.memory;


import com.tokunaga.kensun.memory.R;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;

public class AddActivity extends AppCompatActivity {
    static final int REQUEST_CODE_GALLERY = 1;
    //static final int REQUEST_CODE_CAMERA = 2;

    EditText titleEditText;
    EditText timeEditText;
    EditText folderEditText;
    EditText episodeEditText;

    public Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        realm = Realm.getDefaultInstance();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        timeEditText = (EditText) findViewById(R.id.timeEditText);
        folderEditText = (EditText) findViewById(R.id.folderEditText);
        episodeEditText = (EditText) findViewById(R.id.episodeEditText);


        int[] imageList = {R.drawable.icon_grey_1,
                R.drawable.icon_grey_2,
                R.drawable.icon_grey_3,
                R.drawable.icon_grey_4,
                R.drawable.icon_grey_5};

        FragmentAdapter pagerAdapter = new FragmentAdapter(getSupportFragmentManager(), imageList);
        viewPager.setAdapter(pagerAdapter);

    }

    //Realmのsaveメソッド
    public void save(final byte picture1, final byte picture2, final byte picture3, final byte picture4, final byte picture5, final String updateDate, final String title,final String time, final String folder, final String episode) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Memo memo = realm.createObject(Memo.class);
                memo.picture1 = picture1;
                memo.picture2 = picture2;
                memo.picture3 = picture3;
                memo.picture4 = picture4;
                memo.picture5 = picture5;

                memo.updateDate = updateDate;

                memo.title = title;
                memo.time =  time;
                memo.folder = folder;
                memo.episode = episode;
            }
        });
    }

    public void add(View v) {
        String title = titleEditText.getText().toString();
        String time = timeEditText.getText().toString();
        String folder = folderEditText.getText().toString();
        String episode = episodeEditText.getText().toString();

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE);
        String updateDate = sdf.format(date);

        //save(picture1, );

        //TODO:saveメソッドを整える、変数の被りについて処理する。


        finish();
    }

    //Realmを閉じる
    @Override
    public void onDestroy() {
        super.onDestroy();

        realm.close();
    }
}


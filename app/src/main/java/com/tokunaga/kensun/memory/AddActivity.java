package com.tokunaga.kensun.memory;


import com.tokunaga.kensun.memory.R;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

    FragmentAdapter pagerAdapter;


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

        pagerAdapter = new FragmentAdapter(getSupportFragmentManager(), imageList);
        viewPager.setAdapter(pagerAdapter);

        //ViewPagerの両側の情報
        viewPager.setOffscreenPageLimit(4);
    }


//    //Fragmentからpictureの情報をActivityに情報を渡すメソッド
//    @Override
//    public void dataDeliver(byte[] picture1) {
//        this.pictures1 = picture1;
//    }


    //Realmのsaveメソッド
    public void save(final byte[] pictures1, final byte[] pictures2, final byte[] pictures3, final byte[] pictures4, final byte[] pictures5, final String updateDate, final String title, final String time, final String folder, final String episode) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Memo memo = realm.createObject(Memo.class);
                memo.pictures1 = pictures1;
                memo.pictures2 = pictures2;
                memo.pictures3 = pictures3;
                memo.pictures4 = pictures4;
                memo.pictures5 = pictures5;

                memo.updateDate = updateDate;

                memo.title = title;
                memo.time = time;
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

        byte[] pictures1 = ((MemoryFragment) pagerAdapter.getFragment(0)).getImage();
        byte[] pictures2 = ((MemoryFragment) pagerAdapter.getFragment(1)).getImage();
        byte[] pictures3 = ((MemoryFragment) pagerAdapter.getFragment(2)).getImage();
        byte[] pictures4 = ((MemoryFragment) pagerAdapter.getFragment(3)).getImage();
        byte[] pictures5 = ((MemoryFragment) pagerAdapter.getFragment(4)).getImage();

        save(pictures1, pictures2, pictures3, pictures4, pictures5, updateDate, title, time, folder, episode);

        finish();
    }


    //Realmを閉じる
    @Override
    public void onDestroy() {
        super.onDestroy();

        realm.close();
    }

}










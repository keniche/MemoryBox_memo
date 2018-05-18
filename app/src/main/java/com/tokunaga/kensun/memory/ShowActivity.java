package com.tokunaga.kensun.memory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

public class ShowActivity extends AppCompatActivity implements MemoryFragmentListener {

    public Realm realm;

    public String updateDate;
    FragmentAdapter pagerAdapter;

    public TextView titleText;
    public TextView timeText;
    public TextView folderText;
    public TextView episodeText;

    public EditText editTitleText;
    public EditText editTimeText;
    public EditText editFolderText;
    public EditText editEpisodeText;

    LinearLayout showLinearLayout;
    LinearLayout editLinearLayout;
    LinearLayout editButtonLayout;
    LinearLayout showButtonLayout;

    MemoryFragment fragment1;
    MemoryFragment fragment2;
    MemoryFragment fragment3;
    MemoryFragment fragment4;
    MemoryFragment fragment5;


    Memo memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        realm = Realm.getDefaultInstance();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        titleText = (TextView) findViewById(R.id.titleText);
        timeText = (TextView) findViewById(R.id.timeText);
        folderText = (TextView) findViewById(R.id.folderText);
        episodeText = (TextView) findViewById(R.id.episodeText);

        editTitleText = (EditText) findViewById(R.id.editTitleText);
        editTimeText = (EditText) findViewById(R.id.editTimeText);
        editFolderText = (EditText) findViewById(R.id.editFolderText);
        editEpisodeText = (EditText) findViewById(R.id.editEpisodeText);

        editLinearLayout = (LinearLayout) findViewById(R.id.editLinearLayout);
        showLinearLayout = (LinearLayout) findViewById(R.id.showLinearLayout);
        editButtonLayout = (LinearLayout) findViewById(R.id.editButtonLayout);
        showButtonLayout = (LinearLayout) findViewById(R.id.showButtonLayout);

        editLinearLayout.setVisibility(View.GONE);
        showLinearLayout.setVisibility(View.VISIBLE);
        editButtonLayout.setVisibility(View.GONE);
        showButtonLayout.setVisibility(View.VISIBLE);


        Intent intent = getIntent();
        updateDate = intent.getStringExtra("updateDate");
        Log.e("TAG", "updateDate=" + String.valueOf(updateDate));


        int[] imageList = {R.drawable.icon_image_1,
                R.drawable.icon_image_2,
                R.drawable.icon_image_3,
                R.drawable.icon_image_4,
                R.drawable.icon_image_5};

        pagerAdapter = new FragmentAdapter(getSupportFragmentManager(), imageList);
        viewPager.setAdapter(pagerAdapter);

        //ViewPagerの両側の情報
        viewPager.setOffscreenPageLimit(4);

        showData();

    }

    public void showData() {
        memo = realm.where(Memo.class).equalTo("updateDate",
                getIntent().getStringExtra("updateDate")).findFirst();

        Log.e("TAG", memo.toString());
        titleText.setText(memo.title);
        timeText.setText(memo.time);
        folderText.setText(memo.folder);
        episodeText.setText(memo.episode);

//        editTitleText.setText(memo.title);
//        editTimeText.setText(memo.time);
//        editFolderText.setText(memo.folder);
//        editEpisodeText.setText(memo.episode);


       /* Bitmap bitmap1 = BitmapFactory.decodeByteArray(memo.pictures1, 0, memo.pictures1.length);
        Bitmap bitmap2 = BitmapFactory.decodeByteArray(memo.pictures2, 0, memo.pictures2.length);
        Bitmap bitmap3 = BitmapFactory.decodeByteArray(memo.pictures3, 0, memo.pictures3.length);
        Bitmap bitmap4 = BitmapFactory.decodeByteArray(memo.pictures4, 0, memo.pictures4.length);
        Bitmap bitmap5 = BitmapFactory.decodeByteArray(memo.pictures5, 0, memo.pictures5.length);

        MemoryFragment fragment1 = ((MemoryFragment) pagerAdapter.getFragment(0));
        MemoryFragment fragment2 = ((MemoryFragment) pagerAdapter.getFragment(1));
        MemoryFragment fragment3 = ((MemoryFragment) pagerAdapter.getFragment(2));
        MemoryFragment fragment4 = ((MemoryFragment) pagerAdapter.getFragment(3));
        MemoryFragment fragment5 = ((MemoryFragment) pagerAdapter.getFragment(4));


        fragment1.setImage(bitmap1);
        fragment2.setImage(bitmap2);
        fragment3.setImage(bitmap3);
        fragment4.setImage(bitmap4);
        fragment5.setImage(bitmap5);*/

    }

//    //Realmから情報を削除
//    public void delete(View v){
//
//
//        final RealmResults<Memo> results = realm.where(Memo.class).equalTo("updateDate",
//                getIntent().getStringExtra("updateDate")).findFirst();
//
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//
//                // 特定のオブジェクトのみを削除
//                Dog dog = results.get(5);
//                dog.deleteFromRealm();
//
//                // すべてのオブジェクトを削除
//                results.deleteFromRealm();
//            }
//        });
//        finish();
//    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        realm.close();
    }

    public void edit(View v) {
        showLinearLayout.setVisibility(View.GONE);
        editLinearLayout.setVisibility(View.VISIBLE);
        showButtonLayout.setVisibility(View.GONE);
        editButtonLayout.setVisibility(View.VISIBLE);

        editTitleText.setText(memo.title);
        editTimeText.setText(memo.time);
        editFolderText.setText(memo.folder);
        editEpisodeText.setText(memo.episode);

        fragment1.enableListener(true);
        fragment2.enableListener(true);
        fragment3.enableListener(true);
        fragment4.enableListener(true);
        fragment5.enableListener(true);
    }

    public void cancel(View v) {
        showLinearLayout.setVisibility(View.VISIBLE);
        editLinearLayout.setVisibility(View.GONE);
        showButtonLayout.setVisibility(View.VISIBLE);
        editButtonLayout.setVisibility(View.GONE);

        titleText.setText(memo.title);
        timeText.setText(memo.time);
        folderText.setText(memo.folder);
        episodeText.setText(memo.episode);
    }

    public void delete(View v) {
        memo = realm.where(Memo.class).equalTo("updateDate",
                getIntent().getStringExtra("updateDate")).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                memo.deleteFromRealm();
            }
        });
        finish();
    }


    public void save(View v) {
        memo = realm.where(Memo.class).equalTo("updateDate",
                getIntent().getStringExtra("updateDate")).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Log.e("image1", String.valueOf(fragment1.getImage().length));
                Log.e("image2", String.valueOf(fragment2.getImage().length));
                Log.e("image3", String.valueOf(fragment3.getImage().length));
                Log.e("image4", String.valueOf(fragment4.getImage().length));
                Log.e("image5", String.valueOf(fragment5.getImage().length));
                memo.pictures1 = fragment1.getImage();
                memo.pictures2 = fragment2.getImage();
                memo.pictures3 = fragment3.getImage();
                memo.pictures4 = fragment4.getImage();
                memo.pictures5 = fragment5.getImage();
                memo.title = editTitleText.getText().toString();
                memo.time = editTimeText.getText().toString();
                memo.folder = editFolderText.getText().toString();
                memo.episode = editEpisodeText.getText().toString();
            }
        });
        finish();
    }


    @Override
    public void onReady(int position) {

        switch (position) {
            case 0:
                fragment1 = ((MemoryFragment) pagerAdapter.getFragment(0));
                Bitmap bitmap1 = BitmapFactory.decodeByteArray(memo.pictures1, 0, memo.pictures1.length);
                Log.e("TAG", fragment1.toString());
                fragment1.setImage(bitmap1);
                fragment1.enableListener(false);
                break;
            case 1:
                fragment2 = ((MemoryFragment) pagerAdapter.getFragment(1));
                Bitmap bitmap2 = BitmapFactory.decodeByteArray(memo.pictures2, 0, memo.pictures2.length);
                Log.e("TAG", fragment2.toString());
                fragment2.setImage(bitmap2);
                fragment2.enableListener(false);
                break;
            case 2:
                fragment3 = ((MemoryFragment) pagerAdapter.getFragment(2));
                Bitmap bitmap3 = BitmapFactory.decodeByteArray(memo.pictures3, 0, memo.pictures3.length);
                Log.e("TAG", fragment3.toString());
                fragment3.setImage(bitmap3);
                fragment3.enableListener(false);
                break;
            case 3:
                fragment4 = ((MemoryFragment) pagerAdapter.getFragment(3));
                Bitmap bitmap4 = BitmapFactory.decodeByteArray(memo.pictures4, 0, memo.pictures4.length);
                Log.e("TAG", fragment4.toString());
                fragment4.setImage(bitmap4);
                fragment4.enableListener(false);
                break;
            case 4:
                fragment5 = ((MemoryFragment) pagerAdapter.getFragment(4));
                Bitmap bitmap5 = BitmapFactory.decodeByteArray(memo.pictures5, 0, memo.pictures5.length);
                Log.e("TAG", fragment5.toString());
                fragment5.setImage(bitmap5);
                fragment5.enableListener(false);
                break;

        }

    }
}





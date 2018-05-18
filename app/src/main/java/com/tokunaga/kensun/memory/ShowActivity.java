package com.tokunaga.kensun.memory;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

public class ShowActivity extends AppCompatActivity {

    public Realm realm;

    public String updateDate;
    FragmentAdapter pagerAdapter;

    public TextView titleText;
    public TextView timeText;
    public TextView folderText;
    public TextView episodeText;

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


        //showData();
    }

//    public void showData() {
//        final Memo memo = realm.where(Memo.class).equalTo("updateDate",
//                getIntent().getStringExtra("updateDate")).findFirst();
//
//        titleText.setText(memo.title);
//        timeText.setText(memo.time);
//        folderText.setText(memo.folder);
//        episodeText.setText(memo.episode);
//    }

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
}





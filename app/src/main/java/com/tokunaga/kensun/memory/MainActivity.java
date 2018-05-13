package com.tokunaga.kensun.memory;

import android.content.Intent;
import com.tokunaga.kensun.memory.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    public Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.tokunaga.kensun.memory.R.layout.activity_main);

        realm = Realm.getDefaultInstance();
    }


    public void add(View v) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    public void setMemoList(){
        //
        RealmResults<Memo> results = realm.where(Memo.class).findAll();
        List<Memo> items = realm.copyFromRealm(results);

        //byteをbitmapに変換

    }







    //Realmを閉じる
    @Override
    public void onDestroy() {
        super.onDestroy();

        realm.close();
    }
}

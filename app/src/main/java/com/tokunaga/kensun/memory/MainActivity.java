package com.tokunaga.kensun.memory;

import android.content.Intent;

import com.tokunaga.kensun.memory.R;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    public Realm realm;
    ArrayList<Memo> memoArray;
    ArrayList<Gallery> adpArray;
    ArrayList<Memo> tmp;
    ArrayAdapter galleryAdapter;

    //空のarray
    ArrayList<Gallery> transArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.tokunaga.kensun.memory.R.layout.activity_main);

        realm = Realm.getDefaultInstance();

        memoArray = new ArrayList<Memo>();
        adpArray = new ArrayList<Gallery>();
        tmp = new ArrayList<Memo>();

        transArray = new ArrayList<Gallery>();

        ListView listView = (ListView) findViewById(R.id.listView);

        //TODO:Adapterの第三引数をなしにしたものをつくって、表示しとく（byづめさん）
        //TODO:adpArrayをせっかくつくったのに、customAdapterのコンストラクタの第三引数のobjectとして、adpArrayを渡さないの?
        //TODO:とりあえず、空のtransArrayを渡してみた。
        galleryAdapter = new GalleryAdapter(this, R.layout.gallery, transArray);
        listView.setAdapter(galleryAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        set();
        galleryCreate();

        //Adapter内のデータが変更されたことを通知し、もう一度構成し直す。
        galleryAdapter.notifyDataSetChanged();
    }


    //Realmから情報を取り出してArrayListに入れる
    public void set() {
        memoArray.clear();
        RealmResults<Memo> results = realm.where(Memo.class).findAll();
        List<Memo> item = realm.copyFromRealm(results);
        memoArray.addAll(item);
    }


    //AdapterModelを作り、adpArrayにつっこむ
    public void galleryCreate() {
        Memo trans = new Memo();
        tmp.clear();
        adpArray.clear();

        for (int i = 0; i < memoArray.size(); i++) {
            tmp.add(memoArray.get(i));

            if (i % 3 == 2) {
                adpArray.add(new Gallery(tmp.get(0), tmp.get(1), tmp.get(2)));
                tmp.clear();
            }
            if (i == memoArray.size() - 1) {
                if (tmp.size() == 1) {
                    adpArray.add(new Gallery(tmp.get(0), trans, trans));
                    tmp.clear();
                } else if (tmp.size() == 2) {
                    adpArray.add(new Gallery(tmp.get(0), tmp.get(1), trans));
                    tmp.clear();
                }
            }
        }
    }


    public void add(View v) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }


    //Realmを閉じる
    @Override
    public void onDestroy() {
        super.onDestroy();

        realm.close();
    }
}

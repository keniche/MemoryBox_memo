package android.lifeistech.com.memo;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;

public class AddActivity extends AppCompatActivity {
    static final int REQUEST_CODE_GALLERY = 1;
    //static final int REQUEST_CODE_CAMERA = 2;

    EditText titleEditText;
    EditText dateEditText;
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
        dateEditText = (EditText) findViewById(R.id.dateEditText);
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
    public void save(final String title,final String date, final String folder, final String episode) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Memo memo = realm.createObject(Memo.class);
                memo.title = title;
                memo.date =  date;
                memo.folder = folder;
                memo.episode = episode;
            }
        });
    }


    public void add(View v) {
        String title = titleEditText.getText().toString();
        String date = dateEditText.getText().toString();
        String folder = folderEditText.getText().toString();
        String episode = episodeEditText.getText().toString();

        save(title, date, folder, episode);
        finish();
    }

    //Realmを閉じる
    @Override
    public void onDestroy() {
        super.onDestroy();

        realm.close();
    }
}


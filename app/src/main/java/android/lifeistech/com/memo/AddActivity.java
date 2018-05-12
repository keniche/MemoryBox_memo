package android.lifeistech.com.memo;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    static final int REQUEST_CODE_GALLERY = 1;
    static final int REQUEST_CODE_CAMERA = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);



        int[] imageList ={R.drawable.icon_grey_1,
                R.drawable.icon_grey_2,
                R.drawable.icon_grey_3,
                R.drawable.icon_grey_4,
                R.drawable.icon_grey_5};

        FragmentAdapter pagerAdapter = new FragmentAdapter(getSupportFragmentManager(), imageList);
        viewPager.setAdapter(pagerAdapter);

    }

    //ギャラリーとカメラから情報を取ってくるメソッド




}
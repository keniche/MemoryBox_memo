package com.tokunaga.kensun.memory;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {
    private int[] mImageList;

    public FragmentAdapter(FragmentManager fm, int[] imageList) {
        super(fm);
        mImageList = imageList;
    }

    @Override
    public Fragment getItem(int position) {
        MemoryFragment fragment = new MemoryFragment();
        Bundle args = new Bundle();
        args.putInt("image_id", mImageList[position]);
        args.putInt("position", position);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount() {
        return mImageList.length;
    }
}

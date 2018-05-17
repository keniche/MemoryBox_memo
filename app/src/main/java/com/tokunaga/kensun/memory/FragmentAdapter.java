package com.tokunaga.kensun.memory;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;


public class FragmentAdapter extends FragmentPagerAdapter {
    private int[] mImageList;
    private Map<Integer, String> mFragmentTags;
    private FragmentManager mFragmentManager;


    public FragmentAdapter(FragmentManager fm, int[] imageList) {
        super(fm);
        mFragmentManager = fm;
        mFragmentTags = new HashMap<Integer, String>();
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

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj = super.instantiateItem(container, position);
        if (obj instanceof Fragment) {
            // record the fragment tag here.
            Fragment f = (Fragment) obj;
            String tag = f.getTag();
            mFragmentTags.put(position, tag);
        }
        return obj;
    }

    public Fragment getFragment(int position) {
        String tag = mFragmentTags.get(position);
        if (tag == null)
            return null;
        return mFragmentManager.findFragmentByTag(tag);
    }

}

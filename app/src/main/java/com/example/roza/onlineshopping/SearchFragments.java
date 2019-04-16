package com.example.roza.onlineshopping;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.roza.onlineshopping.CameraFragment;
import com.example.roza.onlineshopping.TextFragment;
import com.example.roza.onlineshopping.VoiceFragment;

public class SearchFragments extends FragmentPagerAdapter
{
    public SearchFragments(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
                return new TextFragment();
            case 1:
                return new VoiceFragment();
            case 2:
                return new CameraFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Text";
            case 1:
                return "Voice";
            case 2:
                return "Barcode";
        }
        return super.getPageTitle(position);
    }
}

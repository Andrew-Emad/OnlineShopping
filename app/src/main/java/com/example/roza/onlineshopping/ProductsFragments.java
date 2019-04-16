package com.example.roza.onlineshopping;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ProductsFragments extends FragmentPagerAdapter {
    public ProductsFragments(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
                return new MobileFragment();
            case 1:
                return new LapFragment();
            case 2:
                return new FoodFragment();
            case 3:
                return new ClothesFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Mobiles";
            case 1:
                return "Laptops";
            case 2:
                return "Food";
            case 3:
                return "Clothes";
        }
        return super.getPageTitle(position);
    }
}

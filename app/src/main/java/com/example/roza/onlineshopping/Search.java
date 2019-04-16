package com.example.roza.onlineshopping;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;

public class Search extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ViewPager pager=(ViewPager)findViewById(R.id.viewPager2);
        pager.setAdapter(new SearchFragments(getSupportFragmentManager()));
        TabLayout tabs=(TabLayout)findViewById(R.id.tabLayout2);
        tabs.setupWithViewPager(pager);
    }
}

package com.paulphoku.ems;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem tab1, tab2, tab3;
    public PagerAdapter pagerAdapter;

    private Toolbar toolbar;



    //Exit
    public void exit() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
    //go to Login
    public void launchOtpActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabLayout = findViewById(R.id.tabLayout);
        tab1 = findViewById(R.id.Tab1);
        tab2 = findViewById(R.id.Tab2);
        tab3 = findViewById(R.id.Tab3);
        viewPager = findViewById(R.id.viewpager);

        toolbar = findViewById(R.id.my_toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Main Page");
        }
        toolbar.inflateMenu(R.menu.main_menu);

        pagerAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount() );
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0){
                    pagerAdapter.notifyDataSetChanged();
                }else if(tab.getPosition() == 1) {
                    pagerAdapter.notifyDataSetChanged();
                }else if(tab.getPosition() == 2) {
                    pagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });

       viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    //options menu method
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
//        return super.onCreateOptionsMenu(menu);
    }


    //options menu on select TODO
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_exit:
                // User chose the "Favorite" action, mark the current item
                Toast.makeText(HomeActivity.this, "Exit", Toast.LENGTH_SHORT).show();
                exit();
                return true;

            case R.id.action_log:
                // User chose the "Favorite" action, mark the current item
                Toast.makeText(HomeActivity.this, "Login", Toast.LENGTH_SHORT).show();
                exit();
                return true;

            case R.id.action_update_profile:
                // User chose the "Favorite" action, mark the current item
                Toast.makeText(HomeActivity.this, "update profile", Toast.LENGTH_SHORT).show();

                exit();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }

    }
}

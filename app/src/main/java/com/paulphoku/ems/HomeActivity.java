package com.paulphoku.ems;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


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

    //check n Navigate session
    public boolean isLoggedIn(){
        boolean user = false;
        if (user){
//            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
//            startActivity(intent);
            user = true;
        }else {
            user = false;
        }
        return user;
    }

    //go to Login
    public void launchOtpActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    //Cards+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //chat
    public void card_chat(View view) {
        Toast.makeText(this, "chat", Toast.LENGTH_SHORT).show();
    }
    //about
    public void card_about(View view) {
        Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
    }
    //schedule
    public void card_schedule(View view) {
        Toast.makeText(this, "schedule", Toast.LENGTH_SHORT).show();
    }
    //terms
    public void card_terms(View view) {
        Toast.makeText(this, "terms", Toast.LENGTH_SHORT).show();
    }
    //report
    public void card_report(View view) {
        Toast.makeText(this, "report", Toast.LENGTH_SHORT).show();
    }
    //buy
    public void card_buy(View view) {
        Toast.makeText(this, "buy", Toast.LENGTH_SHORT).show();
    }
    //add
    public void card_add(View view) {
        Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
    }
    //news
    public void card_news(View view) {
        Toast.makeText(this, "news", Toast.LENGTH_SHORT).show();
    }
    //Cards end+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabLayout = findViewById(R.id.tabLayout);
        tab1 = findViewById(R.id.Tab1);
        tab2 = findViewById(R.id.Tab2);
        tab3 = findViewById(R.id.Tab3);
        viewPager = findViewById(R.id.viewpager);

        tabLayout.getTabAt(0).getIcon().setTint(getResources().getColor(R.color.white,getTheme()));
        tabLayout.getTabAt(1).getIcon().setTint(getResources().getColor(R.color.primaryDarkColor,getTheme()));
        tabLayout.getTabAt(2).getIcon().setTint(getResources().getColor(R.color.primaryDarkColor,getTheme()));

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
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
                    //isLoggedIn();
                }else if(tab.getPosition() == 2) {
                    pagerAdapter.notifyDataSetChanged();
                }
                tab.getIcon().setTint(getResources().getColor(R.color.white,getTheme()));

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //for removing the color of first icon when switched to next tab
                tab.getIcon().setTint(getResources().getColor(R.color.primaryDarkColor,getTheme()));
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

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_log);
        if (isLoggedIn()){
            item.setTitle("Logout");
        }else{
            item.setTitle("Login");
        }
        return super.onPrepareOptionsMenu(menu);
    }

    //options menu on select TODO
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean result = true;

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

                if (isLoggedIn()){
                    //sign out
                    Toast.makeText(HomeActivity.this, "sign out", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(HomeActivity.this, "Login", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                return true;

            case R.id.action_update_profile:
                // User chose the "Favorite" action, mark the current item
                Toast.makeText(HomeActivity.this, "update profile", Toast.LENGTH_SHORT).show();

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                result = super.onOptionsItemSelected(item);
        }

        return result;
    }
}

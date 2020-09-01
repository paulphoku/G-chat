package com.paulphoku.ems;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.paulphoku.ems.Retrofit.Api;
import com.paulphoku.ems.Retrofit.Balance;
import com.paulphoku.ems.Retrofit.INodeJS;
import com.paulphoku.ems.Retrofit.RetrofitClient;
import com.paulphoku.ems.Retrofit.Transactions;
import com.paulphoku.ems.Retrofit.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem tab1, tab2, tab3;
    public PagerAdapter pagerAdapter;
    private Toolbar toolbar;
    //public  TextView txtBal = (TextView) findViewById(R.id.txtABalance);
    INodeJS api;





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

    //Cards++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //chat
    public void card_chat(View view) {
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);    }
    //about
    public void card_about(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
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
        Intent intent = new Intent(this, ReportActivity.class);
        startActivity(intent);    }
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
    //Cards end+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

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

        // toolbar is defined in the layout file
        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.main_menu);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

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

        //init Api
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ems-b.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Define api
        INodeJS jsonPlaceHolderApi = retrofit.create(INodeJS.class);
        User user = new User();
        String user_id = user.getUsrUniqueId();//"cf0dbec8-6407-4adf-84c8-65b9e95f5ea3";

        //Get Transactions
        Call<List<Transactions>> listCall = jsonPlaceHolderApi.getTransactions(user_id);
        listCall.enqueue(new Callback<List<Transactions>>() {
            @Override
            public void onResponse(Call<List<Transactions>> call, Response<List<Transactions>> response) {
                if (!response.isSuccessful()) {
                    //textView.setText("Code " + response.code());
                    Toast.makeText(HomeActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Transactions> transactions = response.body();

                for (Transactions transaction : transactions) {
                    Log.d("myTag", "id : " + transaction.getTId());
                    Log.d("myTag", "user id : " + transaction.getUsrId());
                    Log.d("myTag", "date : " + transaction.getTDate());
                    Log.d("myTag", "Desc : " + transaction.getTDesc());
                    Log.d("myTag", "Type : " + transaction.getTType());
                    Log.d("myTag", "Amt : " + transaction.getTAmt());
                }
            }
            @Override
            public void onFailure(Call<List<Transactions>> call, Throwable t) {
                //textView.setText(t.getMessage());
                Toast.makeText(HomeActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        //Get Bal
        Call<List<Balance>> balCall = jsonPlaceHolderApi.getBalance(user_id);
        balCall.enqueue(new Callback<List<Balance>>() {
            @Override
            public void onResponse(Call<List<Balance>> call, Response<List<Balance>> response) {
                if (!response.isSuccessful()) {
                    //textView.setText("Code " + response.code());
                    Toast.makeText(HomeActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Balance> balances = response.body();

                String b = "0";

                for (Balance balance : balances) {
                    Log.d("myTag", "Balance : " + balance.getBal());
                    b = balance.getBal().toString();
                }

                //txtBal.append(b);


            }
            @Override
            public void onFailure(Call<List<Balance>> call, Throwable t) {
                //textView.setText(t.getMessage());
                Toast.makeText(HomeActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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
                Intent setting = new Intent(this, SettingsActivity.class);
                startActivity(setting);
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

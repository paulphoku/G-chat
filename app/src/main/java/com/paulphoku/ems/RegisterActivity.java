package com.paulphoku.ems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.paulphoku.ems.Retrofit.INodeJS;
import com.paulphoku.ems.Retrofit.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    INodeJS myApi;
    CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //init Api
        Retrofit retrofit = RetrofitClient.getInstance();
        myApi = retrofit.create(INodeJS.class);

        //View
        final EditText fname = findViewById(R.id.txtRegUsrName);
        final EditText lname = findViewById(R.id.txtRegUsrName);
        final EditText password = findViewById(R.id.txtRegPass2);
        final EditText email = findViewById(R.id.txtRegEmail);

        //Event
        Button btnRegister = findViewById(R.id.btnReg);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(email.getText().toString(), fname.getText().toString() ,lname.getText().toString(), password.getText().toString());
            }
        });
    }


    //Register Apis
    private void registerUser(String email,String fname,String lname, String password) {
        compositeDisposable.add(myApi.registerUser(email, fname , lname, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Toast.makeText(RegisterActivity.this, ""+s, Toast.LENGTH_SHORT).show();
                    }
                })
        );
    }

    //go to register
    public void launchLoginActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    //go to OTP
    public void launchOtpActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, OtpActivity.class);
        startActivity(intent);
    }
}

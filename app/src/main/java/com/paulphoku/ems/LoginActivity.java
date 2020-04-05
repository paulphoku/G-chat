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

public class LoginActivity extends AppCompatActivity {
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
        setContentView(R.layout.login);

        //init Api
        Retrofit retrofit = RetrofitClient.getInstance();
        myApi = retrofit.create(INodeJS.class);

        //View
        final EditText password = findViewById(R.id.txtLogPassword);
        final EditText email = findViewById(R.id.txtLogEmail);

        //Event
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(email.getText().toString(), password.getText().toString());
            }
        });
    }
    //login Api
    private void loginUser(String email, String password){
        compositeDisposable.add(myApi.login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        if (s.contains("usr_encrypted_password"))
                            Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(LoginActivity.this, ""+s, Toast.LENGTH_SHORT).show();

                    }
                })
        );

    }
    //go to register
    public void launchLoginActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    //go to OTP
    public void launchOtpActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, OtpActivity.class);
        startActivity(intent);
    }
}

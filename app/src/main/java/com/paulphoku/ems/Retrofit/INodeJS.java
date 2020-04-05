package com.paulphoku.ems.Retrofit;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface INodeJS {
    //Register API
    @POST("register")
    @FormUrlEncoded
    Observable<String> registerUser(
            @Field("email") String email,
            @Field("fname") String fname,
            @Field("lname") String lname,
            @Field("password") String password
    );

    //Login API
    @POST("login")
    @FormUrlEncoded
    Observable<String> login(
            @Field("email") String email,
            @Field("password") String password
    );
}

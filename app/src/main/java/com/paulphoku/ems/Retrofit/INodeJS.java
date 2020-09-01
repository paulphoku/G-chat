package com.paulphoku.ems.Retrofit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface INodeJS {
    /* Register API */
    @POST("register")
    @FormUrlEncoded
    Observable<String> registerUser(
            @Field("email") String email,
            @Field("fname") String fname,
            @Field("lname") String lname,
            @Field("password") String password
    );

    /* Login API */
    @POST("login")
    @FormUrlEncoded
    Observable<String> login(
            @Field("email") String email,
            @Field("password") String password
    );

    /* Get users */
    @GET("getUser/{user_id}")
    Call<List<User>> getUser(
            @Path("user_id") String user_id
    );

    /* Get Transactions */
    @GET("getTrans/{user_id}")
    Call<List<Transactions>> getTransactions(
            @Path("user_id") String user_id
    );

    /* Get Transactions */
    @GET("getBal/{user_id}")
    Call<List<Balance>> getBalance(
            @Path("user_id") String user_id
    );
}
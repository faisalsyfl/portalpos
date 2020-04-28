package com.ibnuputra.hitapiretrofit.service;

import com.ibnuputra.hitapiretrofit.model.RetroPhoto;
import com.ibnuputra.hitapiretrofit.model.Berita;
import com.ibnuputra.hitapiretrofit.model.LoginCredentials;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface GetService {
    @GET("berita.php")
    Call<List<RetroPhoto>> getAllPhotos();
    @GET("berita.php")
    Call<List<Berita>> getBerita();
    @GET("detail_berita.php")
    Call<ResponseBody> getDetail(@Query("id_berita") String ids);

    @POST("login.php")
    @FormUrlEncoded
    Call<ResponseBody> validate(@Field("username") String username, @Field("password") String password);

}


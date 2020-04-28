package com.ibnuputra.hitapiretrofit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ibnuputra.hitapiretrofit.service.ApiClient;
import com.ibnuputra.hitapiretrofit.service.GetService;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private TextView title;
    private TextView desc;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        title = (TextView)findViewById(R.id.title);
        desc = (TextView)findViewById(R.id.content);


        String ids  =  intent.getStringExtra("id_berita");
        android.util.Log.i("IDS", "onCreate:"+ids);
        GetService service2 = ApiClient.getRetrofitInstance().create(GetService.class);
        Call<ResponseBody> call2 = service2.getDetail(ids);
        call2.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject json = new JSONObject(response.body().string());
                    String judul = json.getString("judul");
                    String isi_berita = json.getString("isi_berita");
                    String gambar = json.getString("gambar");
                    ImageView ivBasicImage = (ImageView) findViewById(R.id.main);
                    Picasso.with(DetailActivity.this).load(gambar).into(ivBasicImage);
                    title.setText(judul);
                    desc.setText(isi_berita);

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}

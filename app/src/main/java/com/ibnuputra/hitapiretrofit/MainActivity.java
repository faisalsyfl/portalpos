package com.ibnuputra.hitapiretrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ibnuputra.hitapiretrofit.model.RetroPhoto;
import com.ibnuputra.hitapiretrofit.model.Berita;
import com.ibnuputra.hitapiretrofit.service.ApiClient;
import com.ibnuputra.hitapiretrofit.service.GetService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements BeritaAdapter.OnCl {

    private CustomAdapter adapter;
    private BeritaAdapter adapter2;

    private RecyclerView recyclerView;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        /**Buat Handler Retrofit*/
        GetService service2 = ApiClient.getRetrofitInstance().create(GetService.class);
        Call<List<Berita>> call2 = service2.getBerita();
        Log.i("DATA", "onCreate:"+call2);
        call2.enqueue(new Callback<List<Berita>>() {
            @Override
            public void onResponse(Call<List<Berita>> call, Response<List<Berita>> response) {
                progressDialog.dismiss();
                generateBeritaList(response.body());
            }

            @Override
            public void onFailure(Call<List<Berita>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Gagal Memuat", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void generateBeritaList(List<Berita> beritaList){
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter2 = new BeritaAdapter(this, beritaList,this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter2);
    }

    @Override
    public void onClick(String id_berita) {
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra("id_berita",id_berita);
        startActivity(i);
    }

    class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int itemPosition = recyclerView.indexOfChild(v);
            Log.e("Clicked", String.valueOf(itemPosition));
        }
    }
}

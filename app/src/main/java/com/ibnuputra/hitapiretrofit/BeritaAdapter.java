package com.ibnuputra.hitapiretrofit;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibnuputra.hitapiretrofit.model.RetroPhoto;
import com.ibnuputra.hitapiretrofit.model.Berita;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.CustomViewHolder> {
    private List<Berita> dataList;
    private Context context;
    private OnCl clickCallback;


    public BeritaAdapter(Context context, List<Berita> dataList,OnCl listener){
        this.context = context;
        this.dataList = dataList;
        this.clickCallback = listener;
    }


    class CustomViewHolder extends RecyclerView.ViewHolder{

        public final View mView;

        private TextView judul;
        private ImageView gambar;

        CustomViewHolder(View itemView){
            super(itemView);
            mView = itemView;

            judul = mView.findViewById(R.id.t_judul);
            gambar = mView.findViewById(R.id.coverImage);
        }
    }


    @Override
    public BeritaAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.berita_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BeritaAdapter.CustomViewHolder holder, final int position) {
        holder.judul.setText(dataList.get(position).getJudul());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getGambar())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.gambar);
        holder.gambar.setOnClickListener(new View.OnClickListener(){
        final String id_berita = dataList.get(position).getId_berita().toString();
            @Override
            public void onClick(View view) {
                goToDetail(id_berita);
            }
        });
        holder.judul.setOnClickListener(new View.OnClickListener(){
            final String id_berita = dataList.get(position).getId_berita().toString();
            @Override
            public void onClick(View view) {
                goToDetail(id_berita);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public interface OnCl{
        void onClick(String id_berita);
    }
    public void goToDetail(String id_berita){
        clickCallback.onClick(id_berita);
    }

}

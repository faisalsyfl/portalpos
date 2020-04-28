package com.ibnuputra.hitapiretrofit.model;

import com.google.gson.annotations.SerializedName;

public class Berita {
    @SerializedName("id_berita")
    private Integer id_berita;
    @SerializedName("judul")
    private String judul;
    @SerializedName("isi_berita")
    private String isi_berita;
    @SerializedName("gambar")
    private String gambar;

    public Berita(Integer id_berita, String judul, String isi_berita, String gambar) {
        this.id_berita = id_berita;
        this.judul = judul;
        this.isi_berita = isi_berita;
        this.gambar = gambar;

    }
    public Integer getId_berita() {
        return id_berita;
    }

    public void setId_berita(Integer id_berita) {
        this.id_berita = id_berita;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi_berita() {
        return isi_berita;
    }

    public void setIsi_berita(String isi_berita) {
        this.isi_berita = isi_berita;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

}

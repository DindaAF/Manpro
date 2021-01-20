package com.tubes.Entity;

public class Film {
    private String IdFilm;
    private String Judul;
    private String Rilis;
    private String Deskripsi;

    public String getIdFilm() {
        return IdFilm;
    }

    public void setIdFilm(String idFilm) {
        IdFilm = idFilm;
    }

    public String getJudul() {
        return Judul;
    }

    public void setJudul(String judul) {
        Judul = judul;
    }

    public String getRilis() {
        return Rilis;
    }

    public void setRilis(String rilis) {
        Rilis = rilis;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    @Override
    public String toString() {
        return  Judul;
    }
}

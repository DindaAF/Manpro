package com.tubes.Entity;

public class Studio {
    private int IdStudio;
    private String JamTayang;
    private int Harga;
    private String NamaStudio;
    private Film Film_idFilm;


    public int getIdStudio() {
        return IdStudio;
    }

    public void setIdStudio(int idStudio) {
        IdStudio = idStudio;
    }

    public String getJamTayang() {
        return JamTayang;
    }

    public void setJamTayang(String jamTayang) {
        JamTayang = jamTayang;
    }

    public int getHarga() {
        return Harga;
    }

    public void setHarga(Integer harga) {
        Harga = harga;
    }

    public String getNamaStudio() {
        return NamaStudio;
    }

    public void setNamaStudio(String namaStudio) {
        NamaStudio = namaStudio;
    }

    public Film getFilm_idFilm() {
        return Film_idFilm;
    }

    public void setFilm_idFilm(Film film_idFilm) {
        Film_idFilm = film_idFilm;
    }

    @Override
    public String toString() {
        return  NamaStudio;
    }
}

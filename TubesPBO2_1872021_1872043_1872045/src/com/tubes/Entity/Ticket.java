package com.tubes.Entity;

public class Ticket {
    private int IdTicket;
    private Member User_IdUser;
    private String Jam;
    private int totalbayar;
    private int jumlahpesan;
    private int harga;
    private Studio Studio_IdStudio;
    private Studio Studio_Film_IdFilm;

    public Studio getStudio_IdStudio() {
        return Studio_IdStudio;
    }

    public void setStudio_IdStudio(Studio studio_IdStudio) {
        Studio_IdStudio = studio_IdStudio;
    }

    public Studio getStudio_Film_IdFilm() {
        return Studio_Film_IdFilm;
    }

    public void setStudio_Film_IdFilm(Studio studio_Film_IdFilm) {
        Studio_Film_IdFilm = studio_Film_IdFilm;
    }



    public String getJam() {
        return Jam;
    }

    public void setJam(String jam) {
        this.Jam = jam;
    }

    public int getTotalbayar() {
        return totalbayar;
    }

    public void setTotalbayar(int totalbayar) {
        this.totalbayar = totalbayar;
    }

    public int getJumlahpesan() {
        return jumlahpesan;
    }

    public void setJumlahpesan(int jumlahpesan) {
        this.jumlahpesan = jumlahpesan;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }



    public int getIdTicket() {
        return IdTicket;
    }

    public void setIdTicket(int idTicket) {
        IdTicket = idTicket;
    }

    public Member getUser_IdUser() {
        return User_IdUser;
    }

    public void setUser_IdUser(Member user_IdUser) {
        User_IdUser = user_IdUser;
    }




}

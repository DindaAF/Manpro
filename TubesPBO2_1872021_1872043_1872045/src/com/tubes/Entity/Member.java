package com.tubes.Entity;

public class Member {
    private String IdUser;
    private String NamaUser;
    private String Username;
    private String Password;
    private int Saldo;
    private String Role;

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String idUser) {
        IdUser = idUser;
    }

    public String getNamaUser() {
        return NamaUser;
    }

    public void setNamaUser(String namaUser) {
        NamaUser = namaUser;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getSaldo() {
        return Saldo;
    }

    public void setSaldo(int saldo) {
        Saldo = saldo;
    }

    @Override
    public String toString() {
        return IdUser + " - " + NamaUser ;
    }
    public void tambahSaldo(int sesuatu){
        this.Saldo+=sesuatu;
    }
    public void kurangSaldo(int sesuatu){
        this.Saldo-=sesuatu;
    }


    public void BeliTicket(int sesuatu){
        this.Saldo-=sesuatu;
    }

}

package com.tubes.Entity;

public class TopUp {
    private int IdTopUp;
    private String Tanggal;
    private int TopUp;
    private Member User_IdUser;
    private Admin Admin_IdAdmin;

    public int getIdTopUp() {
        return IdTopUp;
    }

    public void setIdTopUp(int idTopUp) {
        IdTopUp = idTopUp;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }

    public int getTopUp() {
        return TopUp;
    }

    public void setTopUp(int topUp) {
        TopUp = topUp;
    }

    public Member getUser_IdUser() {
        return User_IdUser;
    }

    public void setUser_IdUser(Member user_IdUser) {
        User_IdUser = user_IdUser;
    }

    public Admin getAdmin_IdAdmin() {
        return Admin_IdAdmin;
    }

    public void setAdmin_IdAdmin(Admin admin_IdAdmin) {
        Admin_IdAdmin = admin_IdAdmin;
    }
}

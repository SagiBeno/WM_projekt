package com.example.fuggohidak;

public class Fuggohid {
    int helyezes;
    String hid;
    String hely;
    String orszag;
    int hossz;
    int ev;

    public int getHelyezes() {
        return helyezes;
    }

    public String getHid() {
        return hid;
    }

    public String getHely() {
        return hely;
    }

    public String getOrszag() {
        return orszag;
    }

    public int getHossz() {
        return hossz;
    }

    public int getEv() {
        return ev;
    }

    public void setHelyezes(int helyezes) {
        this.helyezes = helyezes;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public void setHely(String hely) {
        this.hely = hely;
    }

    public void setOrszag(String orszag) {
        this.orszag = orszag;
    }

    public void setHossz(int hossz) {
        this.hossz = hossz;
    }

    public void setEv(int ev) {
        this.ev = ev;
    }

    public Fuggohid ( int helyezes, String hid, String hely, String orszag, int hossz, int ev) {
        this.setHelyezes(helyezes);
        this.setHid(hid);
        this.setHely(hely);
        this.setOrszag(orszag);
        this.setHossz(hossz);
        this.setEv(ev);
    }
}

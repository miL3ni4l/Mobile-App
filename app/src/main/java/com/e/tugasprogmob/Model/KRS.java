package com.e.tugasprogmob.Model;

public class KRS {
    private String kode;
    private String hari;
    private String sesi;
    private String sks;
    private String dosen;
    private String jumlah;

    public KRS(String kode, String hari, String sesi, String sks, String dosen, String jumlah) {
        this.kode = kode;
        this.hari = hari;
        this.sesi = sesi;
        this.sks = sks;
        this.dosen = dosen;
        this.jumlah = jumlah;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getSesi() {
        return sesi;
    }

    public void setSesi(String sesi) {
        this.sesi = sesi;
    }

    public String getSks() {
        return sks;
    }

    public void setSks(String sks) {
        this.sks = sks;
    }

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }
}
package com.e.tugasprogmob.Model;

public class Mahasiswa {
    private String NIDN;
    private String nama;
    private String gelar;
    private String email;
    public Mahasiswa(String NIDN,String nama,String gelar,String email){
        this.setNIDN(NIDN);
        this.setNama(nama);
        this.setGelar(gelar);
        this.setEmail(email);
    }

    public String getNIDN() {
        return NIDN;
    }

    public void setNIDN(String NIDN) {
        this.NIDN = NIDN;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGelar() {
        return gelar;
    }

    public void setGelar(String gelar) {
        this.gelar = gelar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package com.example.tugas;

public class Mahasiswa {
    private String item_id;
    private String nim;
    private String nama;
    private String phone;

    public Mahasiswa(){

    }

    public Mahasiswa(String nim, String nama, String phone) {
        this.nim = nim;
        this.nama = nama;
        this.phone = phone;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}

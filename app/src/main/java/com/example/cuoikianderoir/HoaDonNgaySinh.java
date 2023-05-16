package com.example.cuoikianderoir;

public class HoaDonNgaySinh {
    private int id;
    private String hoTen;
    private int soPhong;
    private double tien;

    public HoaDonNgaySinh( String hoTen, int soPhong, double tien) {
        this.id = id;
        this.hoTen = hoTen;
        this.soPhong = soPhong;
        this.tien = tien;
    }


    public HoaDonNgaySinh(int id, String hoTen, int soPhong, double tien) {
        this.id = id;
        this.hoTen = hoTen;
        this.soPhong = soPhong;
        this.tien = tien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }

    public double getTien() {
        return tien;
    }

    public void setTien(double tien) {
        this.tien = tien;
    }
}

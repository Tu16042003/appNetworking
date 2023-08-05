package com.example.appchu.model;

public class Comic {
    private String anh;
    private String tieude;
    private String theloai;
    private int luotdoc;

    public Comic(String anh, String tieude, String theloai, int luotdoc) {
        this.anh = anh;
        this.tieude = tieude;
        this.theloai = theloai;
        this.luotdoc = luotdoc;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public int getLuotdoc() {
        return luotdoc;
    }

    public void setLuotdoc(int luotdoc) {
        this.luotdoc = luotdoc;
    }
}

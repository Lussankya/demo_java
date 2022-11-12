/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkapt.com.model;

/**
 *
 * @author Admin
 */
public class SachPhieuMuon {
    public int ma;
    public String maPhieuMuon;
    public String maSach;

    public SachPhieuMuon() {
    }

    public SachPhieuMuon(int ma,String maPhieuMuon, String maSach) {
        this.ma = ma;
        this.maPhieuMuon = maPhieuMuon;
        this.maSach = maSach;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }
    
}

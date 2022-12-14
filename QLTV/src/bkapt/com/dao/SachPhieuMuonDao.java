/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkapt.com.dao;

import bkapt.com.helper.Connect;
import bkapt.com.model.SachPhieuMuon;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class SachPhieuMuonDao extends Connect{
    public List<SachPhieuMuon> findId(String id){
        List<SachPhieuMuon> list = new ArrayList<>();
        CallableStatement cs;
        try {
            cs = con.prepareCall("{call sp_MaSachPM(?)}");
             cs.setString(1, id);
             ResultSet rs = cs.executeQuery();
             while (rs.next()) { 
                SachPhieuMuon muon = new SachPhieuMuon();
                muon.setMa(rs.getInt(1));
                muon.setMaPhieuMuon(rs.getString(2));
                muon.setMaSach(rs.getString(3));
                list.add(muon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SachPhieuMuonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return list;
    }
    public List<SachPhieuMuon> findByMaPhieu(String maPhieu){
        List<SachPhieuMuon> list = new ArrayList<>();
        CallableStatement cs;
        try {
            cs = con.prepareCall("SELECT * from SachPhieuMuon WHERE MaPhieuMuon =?");
             cs.setString(1, maPhieu);
             ResultSet rs = cs.executeQuery();
             
             while (rs.next()) { 
                SachPhieuMuon muon = new SachPhieuMuon();
                muon.setMaSach(rs.getString(1));
                list.add(muon);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SachPhieuMuonDao.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return list;
    }
    //public int insert(int ma, String maPhieu,String maSach){
        public int insert( String maPhieu){
        String sql = "INSERT INTO SachPhieuMuon VALUES(?,?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(sql);
            //ps.setInt(1, ma);
            ps.setString(1, maPhieu);
            //ps.setString(3, maSach);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Loi: "+ex.getMessage());
            return 0;
        }
    }
    public int delete(String maPhieu){
        String sql = "DELETE FROM SachPhieuMuon WHERE MaPhieu = "+ maPhieu;
        PreparedStatement ps;
        try {
            ps = con.prepareCall(sql);
            ps.executeUpdate();
            return ps.executeUpdate();
        } catch (SQLException ex) {
            return 0;
        }
    }
    

    public int insert(String maPhieuMuon, String maSach) {
        String sql = "INSERT INTO SachPhieuMuon VALUES(?,?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(sql);
            //ps.setInt(1, 1);
            ps.setString(1, maPhieuMuon);
            ps.setString(2, maSach);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Loi: "+ex.getMessage());
            return 0;
        }
    }
}

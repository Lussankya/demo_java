/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkapt.com.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import bkapt.com.dao.PhieuMuonDAO;
import bkapt.com.dao.SachDAO;
import bkapt.com.dao.SachPhieuMuonDao;
import bkapt.com.dao.SinhVienDAO;
import bkapt.com.dao.TheLoaiSachDAO;
import bkapt.com.dao.ThongKeDAO;
import bkapt.com.model.PhieuMuon;
import bkapt.com.model.Sach;
import bkapt.com.model.SachPhieuMuon;
import bkapt.com.model.SinhVien;
import bkapt.com.model.TheLoaiSach;
import javax.swing.DefaultListModel;

/**
 *
 * @author PC
 */
public class SinhVienUI extends javax.swing.JFrame {
    ArrayList<SinhVien> listsv;
    ArrayList<Sach> lists;
    ArrayList<TheLoaiSach> listtls;
    ArrayList<PhieuMuon> listpm;
    LoginUI lg = new LoginUI();
    String us = lg.getUser();
    int row;
    ThongKeDAO tkdao = new ThongKeDAO();
    public SinhVienUI() {
        initComponents();
        setLocationRelativeTo(null);
        loadsv();
        lblWelcome.setText("Welcome: " + txtHoTen.getText());
        loads();
        loadtls();
        
        loadpm();
        
    }
    public void loadsv(){
        SinhVienDAO svdao =new SinhVienDAO() ;
        listsv = svdao.LoadMa(us);
            
            for(SinhVien sv: listsv){
                 Object[] row = new Object[]{
                    sv.getMaSV(),
                    sv.getPassword(),
                    sv.getHoTen(),
                    sv.getNgaySinh(),
                    sv.isGioiTinh(),
                    sv.getDiaChi(),
                    sv.getSdt(),
                    sv.getEmail()
                };
            txtMaSV.setText(sv.getMaSV());
            txtPass.setText(sv.getPassword());
            txtHoTen.setText(sv.getHoTen());
            String ngaySinh = sv.getNgaySinh();
            try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(ngaySinh);
            jDateChooser1.setDate(date);
            } catch (ParseException ex) {   
            }
            if(sv.isGioiTinh()==true){
            rdoNam.setSelected(true);
            }
            if(sv.isGioiTinh()==false){
                rdoNu.setSelected(true);
            }
            txtDiaChi.setText(sv.getDiaChi());
            txtSDT.setText(sv.getSdt());
            txtEmail.setText(sv.getEmail());
            }
            
    }
    public void updatesv(){
        SinhVien sv =  new SinhVien();
        sv.setMaSV(txtMaSV.getText());
        sv.setPassword(txtPass.getText());
        sv.setHoTen(txtHoTen.getText());
        Date date = jDateChooser1.getDate();
        String df = new SimpleDateFormat("yyyy-MM-dd").format(date);
        sv.setNgaySinh(df);
        boolean gt;
        if(rdoNam.isSelected())
            gt=true;
        else 
            gt=false;
        sv.setGioiTinh(gt);
        sv.setDiaChi(txtDiaChi.getText());
        sv.setSdt(txtSDT.getText());
        sv.setEmail(txtEmail.getText());
        SinhVienDAO svdao =new SinhVienDAO() ;
        if (svdao.update(sv)>0 )
        {    
            JOptionPane.showMessageDialog(null, "c???p nh???t th??nh c??ng");
        }
        else{
            JOptionPane.showMessageDialog(null, "c???p nh???t th???t b???i");
        }     
    }
    public void loads(){
        SachDAO sdao = new SachDAO();
        lists = sdao.load();
            DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
            model.setRowCount(0);
            for(Sach s: lists){
                Object[] row = new Object[]{
                    s.getMaSach(),s.getTenSach(),s.getMaTheLoai(),s.getTacGia(),s.getSoLuong(),s.getNxb(),s.getNgayNhap(),s.getNdtt(),s.getHinh()
                };
                model.addRow(row);
            }
    }
        public void loadTen(){
        SachDAO sdao = new SachDAO();
        lists = sdao.SearchTen(txtSearchS.getText());
            DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
            model.setRowCount(0);
            for(Sach s: lists){
                Object[] row = new Object[]{
                    s.getMaSach(),s.getTenSach(),s.getMaTheLoai(),s.getTacGia(),s.getSoLuong(),s.getNxb(),s.getNgayNhap(),s.getNdtt(),s.getHinh()
                };
                model.addRow(row);
            }
    }

    public void loadtls(){
    TheLoaiSachDAO tlsdao = new TheLoaiSachDAO();
        listtls = tlsdao.load();
        DefaultTableModel model = (DefaultTableModel) tblTheLoaiSach.getModel();
        model.setRowCount(0);
        for(TheLoaiSach tls:listtls){
            Object[] row = new Object[]{
                tls.getMaTheLoai(),
                tls.getTenTheLoai(),
                tls.getViTri()
            };
            model.addRow(row);
        }
    }
    public void loadTenTheLoai(){
        TheLoaiSachDAO tlsdao =new TheLoaiSachDAO() ;
        listtls = tlsdao.SearchTen(txtSearchTLS.getText());
            DefaultTableModel model = (DefaultTableModel) tblTheLoaiSach.getModel();
            model.setRowCount(0);
            for(TheLoaiSach tls: listtls){
                Object[] row = new Object[]{
                    tls.getMaTheLoai(),tls.getTenTheLoai(),tls.getViTri()
                };
                model.addRow(row);
            }
    }
    public void loadpm(){
        PhieuMuonDAO pmdao = new PhieuMuonDAO();
        SachPhieuMuonDao spmdao = new SachPhieuMuonDao();

        listpm = pmdao.SearchMaSVMaPhieu(us);
            DefaultTableModel model = (DefaultTableModel) tblPhieuMuon.getModel();
            model.setRowCount(0);
            for(PhieuMuon pm: listpm){
                List<SachPhieuMuon> listspm = spmdao.findByMaPhieu(pm.getMaPhieuMuon());               
                Object[] row = new Object[]{
                    pm.getMaPhieuMuon(),pm.getSoLuong(),pm.getNgayMuon(),pm.getNgayHenTra()
                };
                model.addRow(row);
            }
    }
    public void insertpm(PhieuMuon pm,DefaultListModel modelList){
//       PhieuMuon pm =  new PhieuMuon();
        PhieuMuonDAO pmdao =new PhieuMuonDAO() ;
        SachDAO sdao = new SachDAO();
        SachPhieuMuonDao spmdao = new SachPhieuMuonDao();
         ArrayList<Sach> listSach = sdao.load();
//         DefaultListModel modelList = new DefaultListModel();
         
        pmdao.insert(pm);
        if(modelList.getSize()>0){
            //Duy???t s??ch
             for (int i = 0; i < modelList.getSize(); i++) {
             for (Sach s : listSach) {
                 if (s.equals(modelList.getElementAt(i).toString())) {
                      if (spmdao.insert(pm.getMaPhieuMuon(),s.getMaSach())>0 )
                {    
                    JOptionPane.showMessageDialog(null, "Th??m Phi???u M?????n th??nh c??ng");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Vui l??ng nh???p ????ng th??ng tin trong Phi???u M?????n");
                }      
                 }
             }
              
         }
        }
    }
    public void inphieumuon(){
        PhieuMuonDAO pmdao = new PhieuMuonDAO();
        String phieu = (String) tblPhieuMuon.getValueAt(row, 0);
        pmdao.inphieumuon(phieu);
        
        }
    public void loadTableSachMuonNhieuNhatPM(){
        DefaultTableModel model = (DefaultTableModel) tblSach2.getModel();
        model.setRowCount(0);
        List<Object[]> list = tkdao.TKSachMuonNhieuNhatPM();
        for(Object[] row : list){
        model.addRow(row);
        }
    }
    public void loadTableSachMuonItNhatPM(){
        DefaultTableModel model = (DefaultTableModel) tblSach2.getModel();
        model.setRowCount(0);
        List<Object[]> list = tkdao.TKSachMuonItNhatPM();
        for(Object[] row : list){
        model.addRow(row);
        }
    }
    public boolean valiformSV() {
        if (txtPass.getText().equals("")) {
            txtPass.requestFocus();
            lblPassword1.setText("Ch??a nh???p Password");
            return false;
        }else if (!(txtPass.getText()).matches("\\w{3,50}")) {
            txtPass.requestFocus();
            lblPassword1.setText("M???t kh???u ??t nh???t 3 k?? t???");
            return false;
        }else if (txtHoTen.getText().equals("")) {
            txtHoTen.requestFocus();
            lblHoTen1.setText("Ch??a nh???p H??? T??n");
            return false;
        }else if (!(txtHoTen.getText().matches("\\D*"))) {
            txtHoTen.requestFocus();
            lblHoTen1.setText("H??? T??n ph???i l?? ch???");
            return false;
        }else if (txtDiaChi.getText().equals("")) {
            txtDiaChi.requestFocus();
            lblDiaChi1.setText("Ch??a nh???p ?????a ch???");
            return false;
        }else if (txtSDT.getText().equals("")) {
            lblSDT1.setText("Ch??a nh???p S??T");
            txtSDT.requestFocus();     
            return false;
        }else if (!(txtSDT.getText().matches("\\d{10,11}"))) {
            txtSDT.requestFocus();
            lblSDT1.setText("S??T ph???i l?? s???, 10 - 11 s???");
            return false;
        }else if (txtEmail.getText().equals("")) {
            txtEmail.requestFocus(); 
            lblEmail1.setText("Ch??a nh???p Email");
            return false;
        }else if (!(txtEmail.getText().matches("\\w+@\\w+\\.\\w{1,3}"))) {
            txtEmail.requestFocus(); 
            lblEmail1.setText("Nh???p Email ????ng ?????nh d???ng");
            return false;
        }else 
        return true;
    };
   
    public void display(){
        Sach s = lists.get(row);
        DefaultTableModel model = (DefaultTableModel) tblSach.getModel();
        ImageIcon image = new ImageIcon(getClass().getResource("/bkapt/com/image/"+s.getHinh()));
        ImageIcon resizedImage = resize(image, 96, 102); 
        lblHinh.setIcon(resizedImage);
    }

        public static ImageIcon resize(ImageIcon imageIcon, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(imageIcon.getImage(), 0, 0, width, height, null);
        g2d.dispose();
        return new ImageIcon(bi);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        pnlForm = new javax.swing.JPanel();
        lblAvt = new javax.swing.JLabel();
        lblWelcome = new javax.swing.JLabel();
        btnDangXuat = new javax.swing.JButton();
        pnl1 = new javax.swing.JPanel();
        tab = new javax.swing.JTabbedPane();
        tabSV = new javax.swing.JTabbedPane();
        pnl2 = new javax.swing.JPanel();
        lblTitleSV = new javax.swing.JLabel();
        lblMaSV = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtPass = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtMaSV = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        btnCapNhat = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        lblPassword1 = new javax.swing.JLabel();
        lblHoTen1 = new javax.swing.JLabel();
        lblDiaChi1 = new javax.swing.JLabel();
        lblSDT1 = new javax.swing.JLabel();
        lblEmail1 = new javax.swing.JLabel();
        tabSach = new javax.swing.JTabbedPane();
        pnl3 = new javax.swing.JPanel();
        lblTitleSach = new javax.swing.JLabel();
        pnl4 = new javax.swing.JPanel();
        txtSearchS = new javax.swing.JTextField();
        lblIconSearch = new javax.swing.JLabel();
        pnl5 = new javax.swing.JPanel();
        pnl7 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblSach = new javax.swing.JTable();
        pnl8 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblSach2 = new javax.swing.JTable();
        lblSapXep = new javax.swing.JLabel();
        cboSach = new javax.swing.JComboBox();
        pnl6 = new javax.swing.JPanel();
        lblHinh = new javax.swing.JLabel();
        pnl9 = new javax.swing.JPanel();
        lblTitleTLS = new javax.swing.JLabel();
        pnl10 = new javax.swing.JPanel();
        txtSearchTLS = new javax.swing.JTextField();
        lblIconSearchTLS = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTheLoaiSach = new javax.swing.JTable();
        tabPM = new javax.swing.JTabbedPane();
        pnl11 = new javax.swing.JPanel();
        lblTitlePM = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblPhieuMuon = new javax.swing.JTable();
        btnPrint = new javax.swing.JButton();
        lblDanhSachPM = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setSize(new java.awt.Dimension(1000, 600));

        pnlForm.setBackground(new java.awt.Color(197, 197, 197));
        pnlForm.setMinimumSize(new java.awt.Dimension(1000, 600));
        pnlForm.setPreferredSize(new java.awt.Dimension(1000, 600));

        lblAvt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkapt/com/icon/hinh4-removebg-preview.png"))); // NOI18N
        lblAvt.setText("jLabel2");

        lblWelcome.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblWelcome.setText("Welcome: ");

        btnDangXuat.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnDangXuat.setForeground(new java.awt.Color(255, 0, 0));
        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkapt/com/icon/Exit.png"))); // NOI18N
        btnDangXuat.setText("????ng xu???t");
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });

        pnl1.setBackground(new java.awt.Color(197, 197, 197));

        tab.setBackground(new java.awt.Color(197, 197, 197));
        tab.setForeground(new java.awt.Color(0, 51, 255));
        tab.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        tab.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tab.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        tabSV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tabSV.setPreferredSize(new java.awt.Dimension(890, 420));

        pnl2.setBackground(new java.awt.Color(197, 197, 197));

        lblTitleSV.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTitleSV.setForeground(new java.awt.Color(255, 255, 0));
        lblTitleSV.setText("TH??NG TIN SINH VI??N");

        lblMaSV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblMaSV.setText("M?? SV");

        lblPassword.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblPassword.setText("Password");

        lblHoTen.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblHoTen.setText("H??? t??n");

        lblNgaySinh.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNgaySinh.setText("Ng??y sinh");

        lblGioiTinh.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblGioiTinh.setText("Gi???i t??nh");

        lblEmail.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblEmail.setText("Email");

        lblDiaChi.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDiaChi.setText("?????a ch???");

        lblSDT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSDT.setText("S??T");

        txtHoTen.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtHoTen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHoTenKeyReleased(evt);
            }
        });

        txtEmail.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });

        txtPass.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPassKeyReleased(evt);
            }
        });

        txtDiaChi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtDiaChi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiaChiKeyReleased(evt);
            }
        });

        txtSDT.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKeyReleased(evt);
            }
        });

        txtMaSV.setEditable(false);
        txtMaSV.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rdoNu.setText("N???");

        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkapt/com/icon/Save as.png"))); // NOI18N
        btnCapNhat.setText("C???p nh???t");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        jDateChooser1.setDateFormatString("dd-MM-yyyy");

        lblPassword1.setForeground(new java.awt.Color(255, 0, 0));

        lblHoTen1.setForeground(new java.awt.Color(255, 0, 0));

        lblDiaChi1.setForeground(new java.awt.Color(255, 0, 0));

        lblSDT1.setForeground(new java.awt.Color(255, 0, 0));

        lblEmail1.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout pnl2Layout = new javax.swing.GroupLayout(pnl2);
        pnl2.setLayout(pnl2Layout);
        pnl2Layout.setHorizontalGroup(
            pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitleSV, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(221, 221, 221))
            .addGroup(pnl2Layout.createSequentialGroup()
                .addGap(303, 303, 303)
                .addComponent(btnCapNhat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnl2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl2Layout.createSequentialGroup()
                        .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl2Layout.createSequentialGroup()
                                .addComponent(lblMaSV)
                                .addGap(33, 33, 33)
                                .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl2Layout.createSequentialGroup()
                                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPassword)
                                    .addComponent(lblHoTen, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHoTen)
                                    .addComponent(lblHoTen1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnl2Layout.createSequentialGroup()
                                .addComponent(lblNgaySinh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 305, Short.MAX_VALUE)
                        .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl2Layout.createSequentialGroup()
                                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDiaChi)
                                    .addComponent(lblSDT))
                                .addGap(18, 18, 18)
                                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDiaChi1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblSDT1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnl2Layout.createSequentialGroup()
                                .addComponent(lblEmail)
                                .addGap(26, 26, 26)
                                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(105, 105, 105))
                    .addGroup(pnl2Layout.createSequentialGroup()
                        .addComponent(lblGioiTinh)
                        .addGap(18, 18, 18)
                        .addComponent(rdoNam)
                        .addGap(18, 18, 18)
                        .addComponent(rdoNu)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pnl2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtDiaChi, txtEmail, txtHoTen, txtMaSV, txtSDT});

        pnl2Layout.setVerticalGroup(
            pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl2Layout.createSequentialGroup()
                .addComponent(lblTitleSV, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaSV)
                    .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiaChi))
                .addGap(1, 1, 1)
                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiaChi1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHoTen)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSDT)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHoTen1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSDT1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl2Layout.createSequentialGroup()
                        .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblNgaySinh))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGioiTinh)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu)))
                    .addGroup(pnl2Layout.createSequentialGroup()
                        .addGroup(pnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54)
                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        pnl2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtDiaChi, txtEmail, txtHoTen, txtMaSV, txtPass, txtSDT});

        tabSV.addTab("", pnl2);

        tab.addTab(" C???p nh???t th??ng tin", new javax.swing.ImageIcon(getClass().getResource("/bkapt/com/icon/svhinh1.png")), tabSV); // NOI18N
        tabSV.getAccessibleContext().setAccessibleName("Th??ng tin");

        tabSach.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tabSach.setPreferredSize(new java.awt.Dimension(890, 420));

        pnl3.setBackground(new java.awt.Color(197, 197, 197));
        pnl3.setPreferredSize(new java.awt.Dimension(890, 420));

        lblTitleSach.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTitleSach.setForeground(new java.awt.Color(255, 255, 0));
        lblTitleSach.setText("S??CH");

        pnl4.setBackground(new java.awt.Color(204, 204, 255));
        pnl4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "T??m Ki???m theo M?? S??ch, T??n S??ch, M?? TL", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        txtSearchS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSearchS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchSActionPerformed(evt);
            }
        });
        txtSearchS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchSKeyReleased(evt);
            }
        });

        lblIconSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkapt/com/icon/Search.png"))); // NOI18N

        javax.swing.GroupLayout pnl4Layout = new javax.swing.GroupLayout(pnl4);
        pnl4.setLayout(pnl4Layout);
        pnl4Layout.setHorizontalGroup(
            pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl4Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblIconSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(txtSearchS, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        pnl4Layout.setVerticalGroup(
            pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl4Layout.createSequentialGroup()
                .addGroup(pnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIconSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearchS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnl5.setBackground(new java.awt.Color(197, 197, 197));
        pnl5.setLayout(new java.awt.CardLayout());

        pnl7.setBackground(new java.awt.Color(197, 197, 197));

        tblSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "M?? S??ch", "T??n S??ch", "M?? Th??? Lo???i", "T??c Gi???", "S??? L?????ng", "NXB", "Ng??y Nh???p", "NDTT", "H??nh"
            }
        ));
        tblSach.setRowHeight(30);
        tblSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSachMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblSach);

        javax.swing.GroupLayout pnl7Layout = new javax.swing.GroupLayout(pnl7);
        pnl7.setLayout(pnl7Layout);
        pnl7Layout.setHorizontalGroup(
            pnl7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 965, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl7Layout.setVerticalGroup(
            pnl7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnl5.add(pnl7, "card2");

        pnl8.setBackground(new java.awt.Color(197, 197, 197));

        tblSach2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "M?? S??ch", "T??n S??ch", "S??? L?????ng"
            }
        ));
        tblSach2.setRowHeight(30);
        jScrollPane7.setViewportView(tblSach2);

        javax.swing.GroupLayout pnl8Layout = new javax.swing.GroupLayout(pnl8);
        pnl8.setLayout(pnl8Layout);
        pnl8Layout.setHorizontalGroup(
            pnl8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE)
                .addGap(32, 32, 32))
        );
        pnl8Layout.setVerticalGroup(
            pnl8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pnl5.add(pnl8, "card3");

        lblSapXep.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSapXep.setText("S???p x???p theo: ");

        cboSach.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cboSach.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "T???t c???", "S??ch m?????n nhi???u nh???t", "S??ch m?????n ??t nh???t" }));
        cboSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSachActionPerformed(evt);
            }
        });

        pnl6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnl6.setPreferredSize(new java.awt.Dimension(150, 184));

        javax.swing.GroupLayout pnl6Layout = new javax.swing.GroupLayout(pnl6);
        pnl6.setLayout(pnl6Layout);
        pnl6Layout.setHorizontalGroup(
            pnl6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHinh, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnl6Layout.setVerticalGroup(
            pnl6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHinh, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnl3Layout = new javax.swing.GroupLayout(pnl3);
        pnl3.setLayout(pnl3Layout);
        pnl3Layout.setHorizontalGroup(
            pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnl3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTitleSach)
                    .addComponent(pnl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lblSapXep)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboSach, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnl6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        pnl3Layout.setVerticalGroup(
            pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl3Layout.createSequentialGroup()
                .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl3Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(lblTitleSach)
                        .addGap(18, 18, 18)
                        .addComponent(pnl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboSach, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSapXep))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnl6, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(pnl5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnl4.getAccessibleContext().setAccessibleName("T??m Ki???m theo M?? S??ch, T??n S??ch");

        tabSach.addTab("S??ch", pnl3);

        pnl9.setBackground(new java.awt.Color(197, 197, 197));

        lblTitleTLS.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTitleTLS.setForeground(new java.awt.Color(255, 255, 0));
        lblTitleTLS.setText("TH??? LO???I S??CH");

        pnl10.setBackground(new java.awt.Color(204, 204, 255));
        pnl10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "T??m ki???m M?? TL, T??n TL ho???c V??? tr??", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        txtSearchTLS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSearchTLS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchTLSKeyReleased(evt);
            }
        });

        lblIconSearchTLS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkapt/com/icon/Search.png"))); // NOI18N

        javax.swing.GroupLayout pnl10Layout = new javax.swing.GroupLayout(pnl10);
        pnl10.setLayout(pnl10Layout);
        pnl10Layout.setHorizontalGroup(
            pnl10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl10Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblIconSearchTLS, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtSearchTLS, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(264, 264, 264))
        );
        pnl10Layout.setVerticalGroup(
            pnl10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl10Layout.createSequentialGroup()
                .addGroup(pnl10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearchTLS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIconSearchTLS, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblTheLoaiSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? Th??? Lo???i", "T??n Th??? Lo???i S??ch", "V??? Tr??"
            }
        ));
        tblTheLoaiSach.setRowHeight(30);
        jScrollPane2.setViewportView(tblTheLoaiSach);

        javax.swing.GroupLayout pnl9Layout = new javax.swing.GroupLayout(pnl9);
        pnl9.setLayout(pnl9Layout);
        pnl9Layout.setHorizontalGroup(
            pnl9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl9Layout.createSequentialGroup()
                .addGroup(pnl9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnl9Layout.createSequentialGroup()
                            .addGap(246, 246, 246)
                            .addComponent(lblTitleTLS))
                        .addGroup(pnl9Layout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 613, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnl9Layout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(pnl10, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(341, Short.MAX_VALUE))
        );
        pnl9Layout.setVerticalGroup(
            pnl9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitleTLS)
                .addGap(42, 42, 42)
                .addComponent(pnl10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        tabSach.addTab("Th??? Lo???i S??ch", pnl9);

        tab.addTab("          S??ch           ", new javax.swing.ImageIcon(getClass().getResource("/bkapt/com/icon/svhinh2.png")), tabSach, ""); // NOI18N

        tabPM.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tabPM.setPreferredSize(new java.awt.Dimension(890, 420));

        pnl11.setBackground(new java.awt.Color(197, 197, 197));

        lblTitlePM.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTitlePM.setForeground(new java.awt.Color(255, 255, 0));
        lblTitlePM.setText("PHI???U M?????N S??CH");

        tblPhieuMuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "M?? Phi???u M?????n", "S??? L?????ng M?????n", "Ng??y M?????n", "Ng??y H???n Tr???"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhieuMuon.setRowHeight(30);
        tblPhieuMuon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuMuonMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblPhieuMuon);

        btnPrint.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bkapt/com/icon/Print.png"))); // NOI18N
        btnPrint.setText("In");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        lblDanhSachPM.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblDanhSachPM.setText("Danh s??ch phi???u m?????n");

        javax.swing.GroupLayout pnl11Layout = new javax.swing.GroupLayout(pnl11);
        pnl11.setLayout(pnl11Layout);
        pnl11Layout.setHorizontalGroup(
            pnl11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl11Layout.createSequentialGroup()
                .addGroup(pnl11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl11Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl11Layout.createSequentialGroup()
                        .addGap(307, 307, 307)
                        .addComponent(lblDanhSachPM))
                    .addGroup(pnl11Layout.createSequentialGroup()
                        .addGap(339, 339, 339)
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl11Layout.createSequentialGroup()
                        .addGap(299, 299, 299)
                        .addComponent(lblTitlePM)))
                .addContainerGap(296, Short.MAX_VALUE))
        );
        pnl11Layout.setVerticalGroup(
            pnl11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitlePM)
                .addGap(18, 18, 18)
                .addComponent(lblDanhSachPM)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
        );

        tabPM.addTab("L???p phi???u M?????n", pnl11);

        tab.addTab("     Phi???u M?????n    ", new javax.swing.ImageIcon(getClass().getResource("/bkapt/com/icon/svhinh3.png")), tabPM, ""); // NOI18N

        javax.swing.GroupLayout pnl1Layout = new javax.swing.GroupLayout(pnl1);
        pnl1.setLayout(pnl1Layout);
        pnl1Layout.setHorizontalGroup(
            pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl1Layout.createSequentialGroup()
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 967, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
        );
        pnl1Layout.setVerticalGroup(
            pnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(lblAvt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(lblWelcome)
                        .addGap(133, 133, 133)
                        .addComponent(btnDangXuat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 479, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWelcome)
                    .addComponent(btnDangXuat)
                    .addComponent(lblAvt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pnl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        lg.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        if(valiformSV()==true){
            updatesv();
            loadsv();
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void txtSearchTLSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchTLSKeyReleased
        loadTenTheLoai();
    }//GEN-LAST:event_txtSearchTLSKeyReleased

    private void txtSearchSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchSKeyReleased
            pnl8.setVisible(false);
            loadTen();

    }//GEN-LAST:event_txtSearchSKeyReleased

    private void cboSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSachActionPerformed
        if(cboSach.getSelectedItem().equals("S??ch m?????n nhi???u nh???t")){
            pnl7.setVisible(false);
            pnl8.setVisible(true);
            loadTableSachMuonNhieuNhatPM();
        }else if(cboSach.getSelectedItem().equals("S??ch m?????n ??t nh???t")){
            pnl7.setVisible(false);
            pnl8.setVisible(true);
            loadTableSachMuonItNhatPM();
        }else{
            pnl8.setVisible(false);
            pnl9.setVisible(true);
            loads();
        }
    }//GEN-LAST:event_cboSachActionPerformed

    private void tblSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSachMouseClicked
        row = tblSach.getSelectedRow();
        display();
    }//GEN-LAST:event_tblSachMouseClicked

    private void txtPassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyReleased
        if(!txtPass.getText().equals("")){
            lblPassword1.setText(null);
        }
    }//GEN-LAST:event_txtPassKeyReleased

    private void txtHoTenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHoTenKeyReleased
        if(!txtHoTen.getText().equals("")){
            lblHoTen1.setText(null);
        }
    }//GEN-LAST:event_txtHoTenKeyReleased

    private void txtDiaChiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiaChiKeyReleased
        if(!txtDiaChi.getText().equals("")){
            lblDiaChi1.setText(null);
        }
    }//GEN-LAST:event_txtDiaChiKeyReleased

    private void txtSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyReleased
        if(!txtSDT.getText().equals("")){
            lblSDT1.setText(null);
        }
    }//GEN-LAST:event_txtSDTKeyReleased

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        if(!txtEmail.getText().equals("")){
            lblEmail1.setText(null);
        }
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtSearchSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchSActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        int index = tblPhieuMuon.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui l??ng ch???n 1 Phi???u M?????n trong b???ng ????? In", "Th??ng B??o", 1);
            return;
        }
        else{
            inphieumuon();
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void tblPhieuMuonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuMuonMouseClicked
        row = tblPhieuMuon.getSelectedRow();

    }//GEN-LAST:event_tblPhieuMuonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SinhVienUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SinhVienUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SinhVienUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SinhVienUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SinhVienUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnPrint;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox cboSach;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblAvt;
    private javax.swing.JLabel lblDanhSachPM;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblDiaChi1;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmail1;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblHoTen1;
    private javax.swing.JLabel lblIconSearch;
    private javax.swing.JLabel lblIconSearchTLS;
    private javax.swing.JLabel lblMaSV;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPassword1;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblSDT1;
    private javax.swing.JLabel lblSapXep;
    private javax.swing.JLabel lblTitlePM;
    private javax.swing.JLabel lblTitleSV;
    private javax.swing.JLabel lblTitleSach;
    private javax.swing.JLabel lblTitleTLS;
    private javax.swing.JLabel lblWelcome;
    private javax.swing.JPanel pnl1;
    private javax.swing.JPanel pnl10;
    private javax.swing.JPanel pnl11;
    private javax.swing.JPanel pnl2;
    private javax.swing.JPanel pnl3;
    private javax.swing.JPanel pnl4;
    private javax.swing.JPanel pnl5;
    private javax.swing.JPanel pnl6;
    private javax.swing.JPanel pnl7;
    private javax.swing.JPanel pnl8;
    private javax.swing.JPanel pnl9;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JTabbedPane tabPM;
    private javax.swing.JTabbedPane tabSV;
    private javax.swing.JTabbedPane tabSach;
    private javax.swing.JTable tblPhieuMuon;
    private javax.swing.JTable tblSach;
    private javax.swing.JTable tblSach2;
    private javax.swing.JTable tblTheLoaiSach;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaSV;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSearchS;
    private javax.swing.JTextField txtSearchTLS;
    // End of variables declaration//GEN-END:variables
}

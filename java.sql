-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.18-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for qltv2
DROP DATABASE IF EXISTS `qltv2`;
CREATE DATABASE IF NOT EXISTS `qltv2` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `qltv2`;

-- Dumping structure for table qltv2.admin
DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) DEFAULT NULL,
  `Ten` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table qltv2.admin: ~1 rows (approximately)
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`Username`, `Password`, `Ten`) VALUES
	('admin', 'admin', 'ADMIN');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;

-- Dumping structure for table qltv2.lop
DROP TABLE IF EXISTS `lop`;
CREATE TABLE IF NOT EXISTS `lop` (
  `MaLop` varchar(10) NOT NULL,
  `Ten` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`MaLop`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table qltv2.lop: ~11 rows (approximately)
/*!40000 ALTER TABLE `lop` DISABLE KEYS */;
INSERT INTO `lop` (`MaLop`, `Ten`) VALUES
	('ML01', 'C1903C'),
	('ML02', 'C1906I'),
	('ML03', 'C1904I'),
	('ML04', 'C1908I'),
	('ML05', 'C1902I'),
	('ML06', 'C1907I'),
	('ML07', 'C1908I'),
	('ML08', 'C1900I'),
	('ML09', 'C1923I'),
	('ML10', 'C1922I'),
	('ML11', 'C2009L');
/*!40000 ALTER TABLE `lop` ENABLE KEYS */;

-- Dumping structure for table qltv2.nxb
DROP TABLE IF EXISTS `nxb`;
CREATE TABLE IF NOT EXISTS `nxb` (
  `MaNXB` varchar(10) NOT NULL,
  `Ten` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `DiaChi` varchar(30) CHARACTER SET utf8mb4 DEFAULT NULL,
  `Email` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`MaNXB`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table qltv2.nxb: ~10 rows (approximately)
/*!40000 ALTER TABLE `nxb` DISABLE KEYS */;
INSERT INTO `nxb` (`MaNXB`, `Ten`, `DiaChi`, `Email`) VALUES
	('NXB01', 'Mat Trang', 'Quận 1', 'Qasdb89@fpt.edu.vn'),
	('NXB02', 'Hoa Ngoc', 'Quận 6', 'kimasdhoa@yahoo.com.vn'),
	('NXB03', 'Thien Vuong', 'Quận Tân Bình', 'nguyenxuan@gmail.com'),
	('NXB04', 'Bac Dau', 'Quận 12', 'asdsdasddd@gmail.com'),
	('NXB05', 'Diem Vuong', 'Quận 9', 'suswwan0175@gmail.com'),
	('NXB06', 'Moc Tinh', 'Quận 4', 'vanasdphu@yahoo.com.vn'),
	('NXB07', 'Kim Tinh', 'Quận 3', 'asdasda@yahoo.com.vn'),
	('NXB08', 'Sao Hoa', 'Quận 2', 'HdaKim@yahoo.com.vn'),
	('NXB09', 'Persider', 'Quận Thủ Đức', 'viwwwenbui@gmail.com'),
	('NXB10', 'GUT CHOP', 'Quận 5', 'xuanetbui@gmail.com');
/*!40000 ALTER TABLE `nxb` ENABLE KEYS */;

-- Dumping structure for table qltv2.phieumuon
DROP TABLE IF EXISTS `phieumuon`;
CREATE TABLE IF NOT EXISTS `phieumuon` (
  `MaPhieuMuon` varchar(10) NOT NULL,
  `MaSV` varchar(10) DEFAULT NULL,
  `MaSach` varchar(10) DEFAULT NULL,
  `SoLuong` int(11) DEFAULT NULL,
  `NgayMuon` date DEFAULT NULL,
  `NgayHenTra` date DEFAULT NULL,
  PRIMARY KEY (`MaPhieuMuon`),
  KEY `FK_PM_MaSvPhieuMuon` (`MaSV`),
  KEY `FK_PM_MaSach` (`MaSach`),
  CONSTRAINT `FK_PM_MaSach` FOREIGN KEY (`MaSach`) REFERENCES `sach` (`MaSach`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PM_MaSvPhieuMuon` FOREIGN KEY (`MaSV`) REFERENCES `sinhvien` (`MaSV`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table qltv2.phieumuon: ~10 rows (approximately)
/*!40000 ALTER TABLE `phieumuon` DISABLE KEYS */;
INSERT INTO `phieumuon` (`MaPhieuMuon`, `MaSV`, `MaSach`, `SoLuong`, `NgayMuon`, `NgayHenTra`) VALUES
	('PM01', 'SV01', 'MS1', 1, '2019-12-01', '2019-12-11'),
	('PM02', 'SV07', 'MS7', 4, '2019-12-12', '2019-12-22'),
	('PM03', 'SV05', 'MS5', 2, '2019-12-14', '2019-12-24'),
	('PM04', 'SV06', 'MS6', 1, '2019-12-15', '2019-12-25'),
	('PM05', 'SV04', 'MS4', 1, '2019-12-11', '2019-12-21'),
	('PM06', 'SV02', 'MS2', 2, '2019-12-05', '2019-12-15'),
	('PM07', 'SV03', 'MS3', 1, '2019-12-06', '2019-12-16'),
	('PM08', 'SV08', 'MS8', 2, '2019-12-02', '2019-12-12'),
	('PM09', 'SV09', 'MS9', 3, '2019-12-08', '2019-12-18'),
	('PM10', 'SV10', 'MS10', 5, '2019-12-10', '2019-12-20');
/*!40000 ALTER TABLE `phieumuon` ENABLE KEYS */;

-- Dumping structure for table qltv2.sach
DROP TABLE IF EXISTS `sach`;
CREATE TABLE IF NOT EXISTS `sach` (
  `MaSach` varchar(10) NOT NULL,
  `TenSach` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `MaTheLoai` varchar(10) DEFAULT NULL,
  `TacGia` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `SoLuong` int(11) DEFAULT NULL,
  `MaNXB` varchar(10) DEFAULT NULL,
  `NgayNhap` date DEFAULT NULL,
  `NDTT` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `Hinh` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`MaSach`),
  KEY `FK_Sach_TheLoai` (`MaTheLoai`),
  KEY `FK_PM_MaSachNXB` (`MaNXB`),
  CONSTRAINT `FK_PM_MaSachNXB` FOREIGN KEY (`MaNXB`) REFERENCES `nxb` (`MaNXB`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Sach_TheLoai` FOREIGN KEY (`MaTheLoai`) REFERENCES `theloaisach` (`MaTheLoai`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table qltv2.sach: ~11 rows (approximately)
/*!40000 ALTER TABLE `sach` DISABLE KEYS */;
INSERT INTO `sach` (`MaSach`, `TenSach`, `MaTheLoai`, `TacGia`, `SoLuong`, `MaNXB`, `NgayNhap`, `NDTT`, `Hinh`) VALUES
	('MS1', 'Kingdom Vương giả thiên hạ', 'MTL1', 'Hara Yasuhisa', 3, 'NXB01', '2019-02-15', 'abc', 'ms1.jpg'),
	('MS10', 'Khám Phá Thế Giới Diệu Kì', 'MTL10', 'Marfé Ferguson Delano', 7, 'NXB06', '2019-02-27', 'abc', 'ms10.jpg'),
	('MS11', '3242342', 'MTL3', 'TG01', 2, 'NXB06', '2022-11-12', 'hfu ggfhwied', '897134704145342554.webp'),
	('MS2', 'Những món cơm ngon', 'MTL2', 'Tiêu Quỳnh', 1, 'NXB02', '2019-02-20', 'abc', 'ms2.jpg'),
	('MS3', 'Truyện kể tây tạng', 'MTL3', 'Nhiều tác giả', 1, 'NXB03', '2019-02-22', 'abc', 'ms3.jpg'),
	('MS4', 'Giải thuật và lập trình', 'MTL4', 'Lê Minh Hoàng', 7, 'NXB04', '2019-02-24', 'abc', 'ms4.jpg'),
	('MS5', 'Biết hài lòng', 'MTL5', 'Leo Babauta', 4, 'NXB05', '2019-02-27', 'abc', 'ms5.png'),
	('MS6', 'Điểm mù', 'MTL6', 'Max H', 2, 'NXB01', '2019-02-27', 'abc', 'ms6.jpg'),
	('MS7', 'Kinh Tế Học', 'MTL6', 'Nhiều Tác Giả', 6, 'NXB07', '2019-02-27', 'abc', 'ms7.jpg'),
	('MS8', 'Đắc Nhân Tâm', 'MTL5', 'Dale Carnegie', 6, 'NXB08', '2019-02-27', 'abc', 'ms8.jpg'),
	('MS9', 'SherLock Holmes', 'MTL9', 'Arthur Conan Doyle', 6, 'NXB01', '2019-02-27', 'abc', 'ms9.jpg');
/*!40000 ALTER TABLE `sach` ENABLE KEYS */;

-- Dumping structure for table qltv2.sachphieumuon
DROP TABLE IF EXISTS `sachphieumuon`;
CREATE TABLE IF NOT EXISTS `sachphieumuon` (
  `Ma` int(11) NOT NULL,
  `MaSach` varchar(10) DEFAULT NULL,
  `MaPhieuMuon` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`Ma`),
  KEY `FK_PM_MaSachMuon` (`MaSach`),
  KEY `FK_PM_MaPhieuMuon` (`MaPhieuMuon`),
  CONSTRAINT `FK_PM_MaPhieuMuon` FOREIGN KEY (`MaPhieuMuon`) REFERENCES `phieumuon` (`MaPhieuMuon`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PM_MaSachMuon` FOREIGN KEY (`MaSach`) REFERENCES `sach` (`MaSach`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table qltv2.sachphieumuon: ~0 rows (approximately)
/*!40000 ALTER TABLE `sachphieumuon` DISABLE KEYS */;
/*!40000 ALTER TABLE `sachphieumuon` ENABLE KEYS */;

-- Dumping structure for table qltv2.sachtg
DROP TABLE IF EXISTS `sachtg`;
CREATE TABLE IF NOT EXISTS `sachtg` (
  `Ma` int(11) NOT NULL,
  `MaTG` varchar(10) DEFAULT NULL,
  `MaSach` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`Ma`),
  KEY `FK_PM_MaTG` (`MaTG`),
  KEY `FK_PM_MaSachTG` (`MaSach`),
  CONSTRAINT `FK_PM_MaSachTG` FOREIGN KEY (`MaSach`) REFERENCES `sach` (`MaSach`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PM_MaTG` FOREIGN KEY (`MaTG`) REFERENCES `tacgia` (`MaTG`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table qltv2.sachtg: ~0 rows (approximately)
/*!40000 ALTER TABLE `sachtg` DISABLE KEYS */;
/*!40000 ALTER TABLE `sachtg` ENABLE KEYS */;

-- Dumping structure for table qltv2.sinhvien
DROP TABLE IF EXISTS `sinhvien`;
CREATE TABLE IF NOT EXISTS `sinhvien` (
  `MaSV` varchar(10) NOT NULL,
  `Password` varchar(50) DEFAULT NULL,
  `MaLop` varchar(10) DEFAULT NULL,
  `HoTen` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `GioiTinh` tinyint(1) DEFAULT NULL,
  `DiaChi` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `SDT` varchar(11) DEFAULT NULL,
  `Email` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`MaSV`),
  KEY `FK_PM_MaLop` (`MaLop`),
  CONSTRAINT `FK_PM_MaLop` FOREIGN KEY (`MaLop`) REFERENCES `lop` (`MaLop`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table qltv2.sinhvien: ~11 rows (approximately)
/*!40000 ALTER TABLE `sinhvien` DISABLE KEYS */;
INSERT INTO `sinhvien` (`MaSV`, `Password`, `MaLop`, `HoTen`, `NgaySinh`, `GioiTinh`, `DiaChi`, `SDT`, `Email`) VALUES
	('SV01', '123', 'ML01', 'Đỗ Bùi Quý', '2000-02-22', 0, 'Quận 1', '0933662633', 'Quydbps00389@fpt.edu.vn'),
	('SV02', '123', 'ML02', 'Bùi Thị Kim Hà', '2000-02-02', 1, 'Quận 2', '0930000000', 'HaKim@yahoo.com.vn'),
	('SV03', '123', 'ML10', 'Đỗ Huyền Trân', '2000-05-22', 1, 'Quận 3', '0931111111', 'huyentran@yahoo.com.vn'),
	('SV04', '123', 'ML09', 'Đỗ Văn Phú', '2000-06-25', 0, 'Quận 4', '0932222222', 'vanphu@yahoo.com.vn'),
	('SV05', '123', 'ML08', 'Bùi Thị Kim Hoa', '2000-01-12', 0, 'Quận 6', '0933334444', 'kimhoa@yahoo.com.vn'),
	('SV06', '123', 'ML07', 'Nguyễn Thị Xuân', '2000-02-29', 0, 'Quận Tân Bình', '0932156482', 'nguyenxuan@gmail.com'),
	('SV07', '123', 'ML06', 'Trương Mỹ Châu', '2000-04-15', 0, 'Quận 12', '0935811169', 'chautruong@gmail.com'),
	('SV08', '123', 'ML03', 'Phan Tấn Trung', '2000-07-30', 0, 'Quận 9', '0969169169', 'susan0175@gmail.com'),
	('SV09', '123', 'ML05', 'Bùi Viện', '2000-09-02', 0, 'Quận Thủ Đức', '0938149159', 'vienbui@gmail.com'),
	('SV10', '123', 'ML04', 'Bùi Xuân Viết', '2000-03-28', 0, 'Quận 5', '0989189189', 'xuanvietbui@gmail.com'),
	('SV11', '123', 'ML01', 'fffdfwe', '2022-11-11', 1, 'fdfsfwe', '6747567000', 'A@gmail.com');
/*!40000 ALTER TABLE `sinhvien` ENABLE KEYS */;

-- Dumping structure for procedure qltv2.sp_GioiTinhSV
DROP PROCEDURE IF EXISTS `sp_GioiTinhSV`;
DELIMITER //
CREATE PROCEDURE `sp_GioiTinhSV`(p_GioiTinh tinyint)
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select * from SinhVien where GioiTinh = p_GioiTinh;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_MaSachPM
DROP PROCEDURE IF EXISTS `sp_MaSachPM`;
DELIMITER //
CREATE PROCEDURE `sp_MaSachPM`(p_MaSach varchar(10))
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select MaPhieuMuon, pm.MaSV, sv.HoTen, pm.MaSach, s.TenSach, pm.SoLuong, NgayMuon, NgayHenTra
	from PhieuMuon pm join SinhVien sv on pm.MaSV = sv.MaSV
	join Sach s on pm.MaSach = s.MaSach
	where s.MaSach = p_MaSach;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_MaSVPM
DROP PROCEDURE IF EXISTS `sp_MaSVPM`;
DELIMITER //
CREATE PROCEDURE `sp_MaSVPM`(p_MaSV varchar(50))
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select MaPhieuMuon, pm.MaSV, sv.HoTen, pm.MaSach, s.TenSach, pm.SoLuong, NgayMuon, NgayHenTra
	from PhieuMuon pm join SinhVien sv on pm.MaSV = sv.MaSV
	join Sach s on pm.MaSach = s.MaSach
	where sv.MaSV = p_MaSV;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_MaTheLoaiSach
DROP PROCEDURE IF EXISTS `sp_MaTheLoaiSach`;
DELIMITER //
CREATE PROCEDURE `sp_MaTheLoaiSach`(p_MaTheLoai varchar(10))
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select Tls.MaTheLoai, TenTheLoai, ViTri, MaSach, TenSach, SoLuong
	from TheLoaiSach tls join Sach s on tls.MaTheLoai = s.MaTheLoai 
	where tls.MaTheLoai = p_MaTheLoai
	order by SoLuong desc;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_NgaySachGiam
DROP PROCEDURE IF EXISTS `sp_NgaySachGiam`;
DELIMITER //
CREATE PROCEDURE `sp_NgaySachGiam`(p_ngayBD varchar(50), p_ngayKT varchar(50))
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select * from Sach
	where ngaynhap >= p_ngayBD and ngaynhap <= p_ngayKT
	order by SoLuong desc;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_NgaySachTang
DROP PROCEDURE IF EXISTS `sp_NgaySachTang`;
DELIMITER //
CREATE PROCEDURE `sp_NgaySachTang`(p_ngayBD varchar(50), p_ngayKT varchar(50))
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select * from Sach
	where ngaynhap >= p_ngayBD and ngaynhap <= p_ngayKT
	order by SoLuong asc;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_PhieuMuon
DROP PROCEDURE IF EXISTS `sp_PhieuMuon`;
DELIMITER //
CREATE PROCEDURE `sp_PhieuMuon`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select MaPhieuMuon, pm.MaSV, sv.HoTen, pm.MaSach, s.TenSach, pm.SoLuong, NgayMuon, NgayHenTra
	from PhieuMuon pm join SinhVien sv on pm.MaSV = sv.MaSV
	join Sach s on pm.MaSach = s.MaSach;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_SachMuonItNhat
DROP PROCEDURE IF EXISTS `sp_SachMuonItNhat`;
DELIMITER //
CREATE PROCEDURE `sp_SachMuonItNhat`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select  pm.MaSach, s.TenSach, sum(pm.SoLuong) SachMuonItNhat
	from PhieuMuon pm join Sach s on pm.MaSach = s.MaSach
	group by pm.MaSach,  s.TenSach
	order by sum(pm.SoLuong) ASC;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_SachMuonItNhatSVUI
DROP PROCEDURE IF EXISTS `sp_SachMuonItNhatSVUI`;
DELIMITER //
CREATE PROCEDURE `sp_SachMuonItNhatSVUI`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select  pm.MaSach, s.TenSach, sum(pm.SoLuong) SachMuonItNhatSVUI
	from PhieuMuon pm join Sach s on pm.MaSach = s.MaSach
	group by pm.MaSach,  s.TenSach
	order by sum(pm.SoLuong) asc;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_SachMuonNhieuNhat
DROP PROCEDURE IF EXISTS `sp_SachMuonNhieuNhat`;
DELIMITER //
CREATE PROCEDURE `sp_SachMuonNhieuNhat`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select  pm.MaSach, s.TenSach, sum(pm.SoLuong) SachMuonNhieuNhat
	from PhieuMuon pm join Sach s on pm.MaSach = s.MaSach
	group by pm.MaSach,  s.TenSach
	order by sum(pm.SoLuong) desc;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_SachMuonNhieuNhatSVUI
DROP PROCEDURE IF EXISTS `sp_SachMuonNhieuNhatSVUI`;
DELIMITER //
CREATE PROCEDURE `sp_SachMuonNhieuNhatSVUI`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select  pm.MaSach, s.TenSach, sum(pm.SoLuong) SachMuonNhieuNhatSVUI
	from PhieuMuon pm join Sach s on pm.MaSach = s.MaSach
	group by pm.MaSach,  s.TenSach
	order by sum(pm.SoLuong) desc;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_soSachSVPM
DROP PROCEDURE IF EXISTS `sp_soSachSVPM`;
DELIMITER //
CREATE PROCEDURE `sp_soSachSVPM`(p_MaSV varchar(50))
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select sum(SoLuong) soSachSVPM, MaSV from PhieuMuon
	where MaSV = p_MaSV
	group by MaSV;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_soSachTLS
DROP PROCEDURE IF EXISTS `sp_soSachTLS`;
DELIMITER //
CREATE PROCEDURE `sp_soSachTLS`(p_MaTheLoai varchar(10))
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select SUM(SoLuong) tongSachTLS
	from Sach
	where MaTheLoai = p_MaTheLoai;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_soSachViTriTLS
DROP PROCEDURE IF EXISTS `sp_soSachViTriTLS`;
DELIMITER //
CREATE PROCEDURE `sp_soSachViTriTLS`(p_ViTri nvarchar(50))
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select SUM(SoLuong) tongSachViTriTLS
from TheLoaiSach tls join Sach s on tls.MaTheLoai = s.MaTheLoai 
	where ViTri = p_ViTri;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_soSVMuonSachPM
DROP PROCEDURE IF EXISTS `sp_soSVMuonSachPM`;
DELIMITER //
CREATE PROCEDURE `sp_soSVMuonSachPM`(p_MaSach varchar(10))
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select COUNT(MaSV) tongSVMuonSach
	from PhieuMuon
	where MaSach = p_MaSach;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_soTheLoaiTLS
DROP PROCEDURE IF EXISTS `sp_soTheLoaiTLS`;
DELIMITER //
CREATE PROCEDURE `sp_soTheLoaiTLS`(p_MaTheLoai varchar(10))
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select COUNT(tls.MaTheLoai) soTheLoaiTLS
from TheLoaiSach tls join Sach s on tls.MaTheLoai = s.MaTheLoai 
	where tls.MaTheLoai = p_MaTheLoai;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_soTheLoaiViTriTLS
DROP PROCEDURE IF EXISTS `sp_soTheLoaiViTriTLS`;
DELIMITER //
CREATE PROCEDURE `sp_soTheLoaiViTriTLS`(p_ViTri nvarchar(50))
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select count(MaTheLoai) soTheLoaiViTriTLS
	from TheLoaiSach 
	where ViTri = p_ViTri;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_SVChuaMuonSach
DROP PROCEDURE IF EXISTS `sp_SVChuaMuonSach`;
DELIMITER //
CREATE PROCEDURE `sp_SVChuaMuonSach`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select sv.MaSV, Password, HoTen, NgaySinh, GioiTinh, DiaChi, SDT, Email
	from SinhVien sv join PhieuMuon pm on sv.MaSV = pm.MaSV
	where pm.MaSV IS NULL;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_SVConHanTraSach
DROP PROCEDURE IF EXISTS `sp_SVConHanTraSach`;
DELIMITER //
CREATE PROCEDURE `sp_SVConHanTraSach`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select MaPhieuMuon, pm.MaSV, sv.HoTen, pm.MaSach, s.TenSach, pm.SoLuong, NgayMuon, NgayHenTra
	from PhieuMuon pm join SinhVien sv on pm.MaSV = sv.MaSV
	join Sach s on pm.MaSach = s.MaSach
	where NgayHenTra > (select NOW(3));
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_SVDaMuonSach
DROP PROCEDURE IF EXISTS `sp_SVDaMuonSach`;
DELIMITER //
CREATE PROCEDURE `sp_SVDaMuonSach`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select DISTINCT pm.MaSV, Password, HoTen, NgaySinh, GioiTinh, DiaChi, SDT, Email
	from SinhVien sv join PhieuMuon pm on sv.MaSV = pm.MaSV;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_SVMuonItSachNhat
DROP PROCEDURE IF EXISTS `sp_SVMuonItSachNhat`;
DELIMITER //
CREATE PROCEDURE `sp_SVMuonItSachNhat`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select  pm.MaSV, sv.HoTen, sum(pm.SoLuong) SVMuonItSachNhat
	from PhieuMuon pm join SinhVien sv on pm.MaSV = sv.MaSV
	join Sach s on pm.MaSach = s.MaSach
	group by pm.MaSV, sv.HoTen
	order by sum(pm.SoLuong) asc;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_SVMuonNhieuSachNhat
DROP PROCEDURE IF EXISTS `sp_SVMuonNhieuSachNhat`;
DELIMITER //
CREATE PROCEDURE `sp_SVMuonNhieuSachNhat`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select  pm.MaSV, sv.HoTen, sum(pm.SoLuong) SVMuonNhieuSachNhat
	from PhieuMuon pm join SinhVien sv on pm.MaSV = sv.MaSV
	join Sach s on pm.MaSach = s.MaSach
	group by pm.MaSV, sv.HoTen
	order by sum(pm.SoLuong) desc;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_SVQuaHanTraSach
DROP PROCEDURE IF EXISTS `sp_SVQuaHanTraSach`;
DELIMITER //
CREATE PROCEDURE `sp_SVQuaHanTraSach`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select MaPhieuMuon, pm.MaSV, sv.HoTen, pm.MaSach, s.TenSach, pm.SoLuong, NgayMuon, NgayHenTra
	from PhieuMuon pm join SinhVien sv on pm.MaSV = sv.MaSV
	join Sach s on pm.MaSach = s.MaSach
	where NgayHenTra < (select NOW(3));
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_TheLoaiSach
DROP PROCEDURE IF EXISTS `sp_TheLoaiSach`;
DELIMITER //
CREATE PROCEDURE `sp_TheLoaiSach`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select Tls.MaTheLoai, TenTheLoai, ViTri, MaSach, TenSach, SoLuong
	from TheLoaiSach tls join Sach s on tls.MaTheLoai = s.MaTheLoai 
	order by SoLuong desc;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_tongGioiTinhSV
DROP PROCEDURE IF EXISTS `sp_tongGioiTinhSV`;
DELIMITER //
CREATE PROCEDURE `sp_tongGioiTinhSV`(p_GioiTinh tinyint)
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select COUNT(GioiTinh) tongGioiTinhSV
	from SinhVien where GioiTinh = p_GioiTinh;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_TongPM
DROP PROCEDURE IF EXISTS `sp_TongPM`;
DELIMITER //
CREATE PROCEDURE `sp_TongPM`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select COUNT(MaPhieuMuon) TongPM
	from PhieuMuon;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_TongSach
DROP PROCEDURE IF EXISTS `sp_TongSach`;
DELIMITER //
CREATE PROCEDURE `sp_TongSach`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select sum(SoLuong) TongSach from Sach;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_TongSachNgaySach
DROP PROCEDURE IF EXISTS `sp_TongSachNgaySach`;
DELIMITER //
CREATE PROCEDURE `sp_TongSachNgaySach`(p_ngayBD varchar(50), p_ngayKT varchar(50))
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select sum(SoLuong) TongNgaySach
	from Sach
	where ngaynhap >= p_ngayBD and ngaynhap <= p_ngayKT;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_TongSachSVMuon
DROP PROCEDURE IF EXISTS `sp_TongSachSVMuon`;
DELIMITER //
CREATE PROCEDURE `sp_TongSachSVMuon`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select sum(SoLuong) TongSachSVMuon from PhieuMuon;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_TongSoPhieuConHanTraSach
DROP PROCEDURE IF EXISTS `sp_TongSoPhieuConHanTraSach`;
DELIMITER //
CREATE PROCEDURE `sp_TongSoPhieuConHanTraSach`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select COUNT(MaPhieuMuon) TongSoPhieuConHanTraSach
	from PhieuMuon
	where NgayHenTra > (select NOW(3));
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_TongSoPhieuQuaHanTraSach
DROP PROCEDURE IF EXISTS `sp_TongSoPhieuQuaHanTraSach`;
DELIMITER //
CREATE PROCEDURE `sp_TongSoPhieuQuaHanTraSach`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select COUNT(MaPhieuMuon) TongSoPhieuQuaHanTraSach
	from PhieuMuon
	where NgayHenTra < (select NOW(3));
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_tongSV
DROP PROCEDURE IF EXISTS `sp_tongSV`;
DELIMITER //
CREATE PROCEDURE `sp_tongSV`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select COUNT(MaSV) tongSV
	from SinhVien; 
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_TongSVChuaMuonSach
DROP PROCEDURE IF EXISTS `sp_TongSVChuaMuonSach`;
DELIMITER //
CREATE PROCEDURE `sp_TongSVChuaMuonSach`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select COUNT(sv.MaSV) TongSVChuaMuonSach
	from SinhVien sv join PhieuMuon pm on sv.MaSV = pm.MaSV
	where pm.MaSV IS NULL;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_TongSVConHanTraSach
DROP PROCEDURE IF EXISTS `sp_TongSVConHanTraSach`;
DELIMITER //
CREATE PROCEDURE `sp_TongSVConHanTraSach`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select COUNT(DISTINCT MaSV) TongSVConHanTraSach 
	from PhieuMuon
	where NgayHenTra > (select NOW(3));
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_TongSVDaMuonSach
DROP PROCEDURE IF EXISTS `sp_TongSVDaMuonSach`;
DELIMITER //
CREATE PROCEDURE `sp_TongSVDaMuonSach`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select COUNT(DISTINCT pm.MaSV) TongSVDaMuonSach
	from SinhVien sv join PhieuMuon pm on sv.MaSV = pm.MaSV;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_TongSVQuaHanTraSach
DROP PROCEDURE IF EXISTS `sp_TongSVQuaHanTraSach`;
DELIMITER //
CREATE PROCEDURE `sp_TongSVQuaHanTraSach`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select COUNT(DISTINCT MaSV) TongSVQuaHanTraSach
	from PhieuMuon
	where NgayHenTra < (select NOW(3));
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_TongTheLoaiTLS
DROP PROCEDURE IF EXISTS `sp_TongTheLoaiTLS`;
DELIMITER //
CREATE PROCEDURE `sp_TongTheLoaiTLS`()
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select count(MaTheLoai) TongTheLoaiTLS from TheLoaiSach;
END//
DELIMITER ;

-- Dumping structure for procedure qltv2.sp_ViTriTheLoaiSach
DROP PROCEDURE IF EXISTS `sp_ViTriTheLoaiSach`;
DELIMITER //
CREATE PROCEDURE `sp_ViTriTheLoaiSach`(p_ViTri nvarchar(50))
BEGIN 
 	-- SQLINES LICENSE FOR EVALUATION USE ONLY
	 Select Tls.MaTheLoai, TenTheLoai, ViTri, MaSach, TenSach, SoLuong
	from TheLoaiSach tls join Sach s on tls.MaTheLoai = s.MaTheLoai 
	where ViTri = p_ViTri
	order by SoLuong desc;
END//
DELIMITER ;

-- Dumping structure for table qltv2.tacgia
DROP TABLE IF EXISTS `tacgia`;
CREATE TABLE IF NOT EXISTS `tacgia` (
  `MaTG` varchar(10) NOT NULL,
  `Ten` varchar(30) CHARACTER SET utf8mb4 DEFAULT NULL,
  `NgaySinh` date DEFAULT NULL,
  `DiaChi` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `Email` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`MaTG`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table qltv2.tacgia: ~10 rows (approximately)
/*!40000 ALTER TABLE `tacgia` DISABLE KEYS */;
INSERT INTO `tacgia` (`MaTG`, `Ten`, `NgaySinh`, `DiaChi`, `Email`) VALUES
	('TG01', 'Nam Cao', '1970-02-22', 'Quận 1', 'Quyps9@fpt.edu.vn'),
	('TG02', 'Trương Mỹ Tho', '1879-04-15', 'Quận 12', 'chautruong@gmail.com'),
	('TG03', 'To Huu', '1890-02-02', 'Quận 2', 'HaKim@yahoo.com.vn'),
	('TG04', 'Chau Tinh Tri', '1879-05-22', 'Quận 3', 'huntran@yahoo.com.vn'),
	('TG05', 'Ton Ngo Khong', '1879-06-25', 'Quận 4', 'vanphu@yahoo.com.vn'),
	('TG06', 'Nguyễn Thị Chat', '1879-02-26', 'Quận Tân Bình', 'nguyenxuan@gmail.com'),
	('TG07', 'Phan Trung Kien', '1879-07-30', 'Quận 9', 'susan0175@gmail.com'),
	('TG08', 'Bùi Kim Hoa', '1879-01-12', 'Quận 6', 'kimhoa@yahoo.com.vn'),
	('TG09', 'La Van Viện', '1879-09-02', 'Quận Thủ Đức', 'vienbui@gmail.com'),
	('TG10', 'Bùi Viết Ngo', '1879-03-28', 'Quận 5', 'xuanvibui@gmail.com');
/*!40000 ALTER TABLE `tacgia` ENABLE KEYS */;

-- Dumping structure for table qltv2.theloaisach
DROP TABLE IF EXISTS `theloaisach`;
CREATE TABLE IF NOT EXISTS `theloaisach` (
  `MaTheLoai` varchar(10) NOT NULL,
  `TenTheLoai` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `ViTri` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`MaTheLoai`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table qltv2.theloaisach: ~11 rows (approximately)
/*!40000 ALTER TABLE `theloaisach` DISABLE KEYS */;
INSERT INTO `theloaisach` (`MaTheLoai`, `TenTheLoai`, `ViTri`) VALUES
	('MTL1', 'Truyện Tranh', 'Tủ số 1'),
	('MTL10', 'Khám Phá Thế Giới', 'Tủ số 7'),
	('MTL11', 'fbubaf', '11'),
	('MTL2', 'Ẩm thực', 'Tủ số 2'),
	('MTL3', 'Cổ tích', 'Tủ số 3'),
	('MTL4', 'Công nghệ thông tin', 'Tủ số 4'),
	('MTL5', 'Kỹ năng sống', 'Tủ số 5'),
	('MTL6', 'Kinh Tế', 'Tủ số 5'),
	('MTL7', 'Trẻ Em', 'Tủ số 6'),
	('MTL8', 'Trinh Thám', 'Tủ số 8'),
	('MTL9', 'Kinh dị', 'Tủ số 8');
/*!40000 ALTER TABLE `theloaisach` ENABLE KEYS */;

-- Dumping structure for table qltv2.vipham
DROP TABLE IF EXISTS `vipham`;
CREATE TABLE IF NOT EXISTS `vipham` (
  `MaViPham` varchar(10) NOT NULL,
  `MaSV` varchar(10) DEFAULT NULL,
  `Ten` varchar(30) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`MaViPham`),
  KEY `FK_PM_MaSV` (`MaSV`),
  CONSTRAINT `FK_PM_MaSV` FOREIGN KEY (`MaSV`) REFERENCES `sinhvien` (`MaSV`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table qltv2.vipham: ~10 rows (approximately)
/*!40000 ALTER TABLE `vipham` DISABLE KEYS */;
INSERT INTO `vipham` (`MaViPham`, `MaSV`, `Ten`) VALUES
	('VP01', 'SV01', 'hen khong tra'),
	('VP02', 'SV04', 'co giu mat dung tim'),
	('VP03', 'SV02', 'Khong cho fb'),
	('VP04', 'SV06', 'Xin so khong cho'),
	('VP05', 'SV03', 'Lam rach sach'),
	('VP06', 'SV02', 'Mat nhieu mun'),
	('VP07', 'SV05', 'Deo co nguoi yeu'),
	('VP08', 'SV06', 'deo tam'),
	('VP09', 'SV07', 'o ban'),
	('VP10', 'SV01', 'met');
/*!40000 ALTER TABLE `vipham` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

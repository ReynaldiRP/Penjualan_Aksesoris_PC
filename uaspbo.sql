-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 21, 2023 at 04:25 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `uaspbo`
--

DELIMITER $$
--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `generateID` (`tbl` VARCHAR(100)) RETURNS VARCHAR(6) CHARSET utf8mb4 BEGIN
    DECLARE v_ID VARCHAR(6);
    DECLARE v_No INT(6);
    
    SET v_ID = '0';
    SET v_No = 0;
    
    CASE
    	WHEN tbl = 'pegawai' THEN
        SELECT MAX(substr(id_pegawai, 2)) INTO v_ID FROM pegawai;
		IF v_ID IS NULL THEN SET v_No = v_No + 1;
        ELSE SET v_No = CONVERT(v_ID, BINARY) + 1;
		END IF;
		SET v_ID = CONCAT('A', lpad(v_No, 5, 0));

        WHEN tbl = 'pelanggan' THEN
        SELECT MAX(substr(id_pelanggan, 2)) INTO v_ID FROM pelanggan;
		IF v_ID IS NULL THEN SET v_No = v_No + 1;
        ELSE SET v_No = CONVERT(v_ID, BINARY) + 1;
		END IF;
		SET v_ID = CONCAT('B', lpad(v_No, 5, 0));

        WHEN tbl = 'barang' THEN
        SELECT MAX(substr(id_barang, 2)) INTO v_ID FROM barang;
		IF v_ID IS NULL THEN SET v_No = v_No + 1;
        ELSE SET v_No = CONVERT(v_ID, BINARY) + 1;
		END IF;
		SET v_ID = CONCAT('C', lpad(v_No, 5, 0));

        WHEN tbl = 'transaksi' THEN
        SELECT MAX(substr(id_transaksi, 2)) INTO v_ID FROM transaksi;
		IF v_ID IS NULL THEN SET v_No = v_No + 1;
        ELSE SET v_No = CONVERT(v_ID, BINARY) + 1;
		END IF;
		SET v_ID = CONCAT('D', lpad(v_No, 5, 0));
        WHEN tbl = 'transaksi2' THEN
        SELECT MAX(substr(id_transaksi, 2)) INTO v_ID FROM detail_transaksi;
		IF v_ID IS NULL THEN SET v_No = v_No + 1;
        ELSE SET v_No = CONVERT(v_ID, BINARY) + 1;
		END IF;
		SET v_ID = CONCAT('D', lpad(v_No, 5, 0));
        WHEN tbl = 'pembayaran' THEN
        SELECT MAX(substr(id_transaksi, 2)) INTO v_ID FROM pembayaran;
		IF v_ID IS NULL THEN SET v_No = v_No + 1;
        ELSE SET v_No = CONVERT(v_ID, BINARY) + 1;
		END IF;
		SET v_ID = CONCAT('E', lpad(v_No, 5, 0));
	END CASE;
    RETURN v_ID;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `totalHarga` (`nama` VARCHAR(256), `kuan` INT(100)) RETURNS INT(100) BEGIN
    DECLARE jml INT(100);
    DECLARE hrg INT(100);
    DECLARE Total INT(100);

	SELECT harga_jual INTO hrg FROM barang
    WHERE Nama_Barang = nama;
    
    SET jml = hrg * kuan;
    IF (jml > 200000) THEN SET Total = (jml * 80) / 100;
    ELSEIF (jml < 200000) THEN SET Total = jml;
    END IF;
    RETURN Total;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `uangKembalian` (`uang` INT(100), `total` INT(100)) RETURNS INT(100) BEGIN
    DECLARE kembalian INT(100);
    
    SET kembalian = uang - total;

    RETURN kembalian;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `ID_Barang` varchar(6) NOT NULL,
  `Jenis_Barang` varchar(256) NOT NULL,
  `Nama_Barang` varchar(256) NOT NULL,
  `Ukuran_Barang` varchar(256) NOT NULL,
  `Harga_Jual` int(100) NOT NULL,
  `Harga_Beli` int(100) NOT NULL,
  `Stok` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`ID_Barang`, `Jenis_Barang`, `Nama_Barang`, `Ukuran_Barang`, `Harga_Jual`, `Harga_Beli`, `Stok`) VALUES
('C00001', 'Mechanical', 'Vortex VX5', '60%', 420000, 320000, 10),
('C00002', 'Mechanical', 'Vortex VX7 Pro', 'TKL', 550000, 450000, 15),
('C00003', 'Mechanical', 'Rexus MX3.2', 'Full Size', 320000, 250000, 20),
('C00004', 'Membrane', 'HP K100', 'Full Size', 80000, 130000, 30),
('C00005', 'Mechanical', 'Venom', '60%', 2000000, 3500000, 5);

-- --------------------------------------------------------

--
-- Table structure for table `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `ID_Transaksi` varchar(6) NOT NULL,
  `Jenis_Barang` varchar(256) NOT NULL,
  `Nama_Barang` varchar(256) NOT NULL,
  `Ukuran_Barang` varchar(256) NOT NULL,
  `Kuantitas` int(100) NOT NULL,
  `Total_Harga` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`ID_Transaksi`, `Jenis_Barang`, `Nama_Barang`, `Ukuran_Barang`, `Kuantitas`, `Total_Harga`) VALUES
('D00001', 'Mechanical', 'Vortex VX5', '60%', 2, 840000),
('D00002', 'Mechanical', 'Rexus MX3.2', 'Full Size', 3, 960000),
('D00003', 'Mechanical', 'Vortex VX7 Pro', 'TKL', 1, 550000),
('D00004', 'Membrane', 'HP K100', 'Full Size', 5, 400000),
('D00005', 'Mechanical', 'Vortex VX5', '60%', 1, 420000);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `ID` int(100) NOT NULL,
  `Username` varchar(256) NOT NULL,
  `Password` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`ID`, `Username`, `Password`) VALUES
(1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `pegawai`
--

CREATE TABLE `pegawai` (
  `ID_Pegawai` varchar(6) NOT NULL,
  `Nama_Pegawai` varchar(256) NOT NULL,
  `Tanggal_Lahir_Pegawai` date NOT NULL,
  `Alamat_Pegawai` varchar(256) NOT NULL,
  `Telepon_Pegawai` varchar(256) NOT NULL,
  `JK_Pegawai` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`ID_Pegawai`, `Nama_Pegawai`, `Tanggal_Lahir_Pegawai`, `Alamat_Pegawai`, `Telepon_Pegawai`, `JK_Pegawai`) VALUES
('A00001', 'Anggi', '2001-01-01', 'DSA', '081', 'P'),
('A00002', 'Caca', '2003-03-03', 'DSC', '083', 'P'),
('A00003', 'Bima', '2002-02-02', 'DSB', '082', 'L'),
('A00004', 'Dinda', '2005-05-05', 'DSD', '085', 'P'),
('A00005', 'Gege', '2022-01-08', 'Waru', '0812323443', 'L');

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `ID_Pelanggan` varchar(6) NOT NULL,
  `Nama_Pelanggan` varchar(256) NOT NULL,
  `Tanggal_Lahir_Pelanggan` varchar(256) NOT NULL,
  `Alamat_Pelanggan` varchar(256) NOT NULL,
  `Telepon_Pelanggan` varchar(256) NOT NULL,
  `JK_Pelanggan` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`ID_Pelanggan`, `Nama_Pelanggan`, `Tanggal_Lahir_Pelanggan`, `Alamat_Pelanggan`, `Telepon_Pelanggan`, `JK_Pelanggan`) VALUES
('B00001', 'Agus', '2001-01-01', 'QWA', '091', 'L'),
('B00002', 'Bela', '2002-02-02', 'QWB', '092', 'P'),
('B00003', 'Cahyo', '2003-03-03', 'QWC', '093', 'L'),
('B00004', 'Delima', '2005-05-05', 'QWD', '095', 'P'),
('B00005', 'Gaga', '2015-01-15', 'Wage', '08434334', 'L');

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran`
--

CREATE TABLE `pembayaran` (
  `ID_Pembayaran` varchar(6) NOT NULL,
  `ID_Transaksi` varchar(6) NOT NULL,
  `Nama_Pelanggan` varchar(256) NOT NULL,
  `Nama_Barang` varchar(256) NOT NULL,
  `Kuantitas` int(100) NOT NULL,
  `Total_Harga` int(100) NOT NULL,
  `Uang_Pembayaran` int(100) NOT NULL,
  `Kembalian` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pembayaran`
--

INSERT INTO `pembayaran` (`ID_Pembayaran`, `ID_Transaksi`, `Nama_Pelanggan`, `Nama_Barang`, `Kuantitas`, `Total_Harga`, `Uang_Pembayaran`, `Kembalian`) VALUES
('E00001', 'D00001', 'Agus', 'Vortex VX5', 2, 840000, 1000000, 160000),
('E00002', 'D00002', 'Bela', 'Rexus MX3.2', 3, 960000, 1000000, 40000),
('E00003', 'D00003', 'Cahyo', 'Vortex VX7 Pro', 1, 550000, 1000000, 450000),
('E00004', 'D00004', 'Delima', 'HP K100', 5, 400000, 1000000, 600000),
('E00005', 'D00005', 'Delima', 'Vortex VX5', 1, 420000, 1000000, 580000);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `ID_Transaksi` varchar(6) NOT NULL,
  `Nama_Pegawai` varchar(256) NOT NULL,
  `Nama_Pelanggan` varchar(256) NOT NULL,
  `Tanggal_Transaksi` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`ID_Transaksi`, `Nama_Pegawai`, `Nama_Pelanggan`, `Tanggal_Transaksi`) VALUES
('D00001', 'Anggi', 'Agus', '2021-12-26'),
('D00002', 'Bima', 'Bela', '2021-12-27'),
('D00003', 'Caca', 'Cahyo', '2021-12-28'),
('D00004', 'Dinda', 'Delima', '2021-12-29'),
('D00005', 'Anggi', 'Delima', '2022-01-13');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`ID_Barang`),
  ADD KEY `Jenis_Barang` (`Jenis_Barang`),
  ADD KEY `Nama_Barang` (`Nama_Barang`),
  ADD KEY `Ukuran_Barang` (`Ukuran_Barang`);

--
-- Indexes for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`ID_Transaksi`),
  ADD KEY `Total_Harga` (`Total_Harga`),
  ADD KEY `Kuantitas` (`Kuantitas`) USING BTREE;

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`ID_Pegawai`),
  ADD KEY `Nama_Pegawai` (`Nama_Pegawai`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`ID_Pelanggan`),
  ADD KEY `Nama_Pelanggan` (`Nama_Pelanggan`);

--
-- Indexes for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD PRIMARY KEY (`ID_Pembayaran`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`ID_Transaksi`),
  ADD KEY `ID_Transaksi` (`ID_Transaksi`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `login`
--
ALTER TABLE `login`
  MODIFY `ID` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

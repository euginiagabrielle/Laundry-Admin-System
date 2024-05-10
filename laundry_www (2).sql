-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 26, 2023 at 12:20 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `laundry_www`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `detail_transaksi_insert` (IN `_kondisi_item` VARCHAR(50), IN `_jenis_layanan` VARCHAR(12), IN `_tanggal_pengambilan` DATE, IN `_id_transaksi` VARCHAR(5), IN `_id_item` VARCHAR(8))   begin
	declare nomor int;
	select id_detail_transaksi+1 into nomor from detail_transaksi order by id_detail_transaksi desc limit 1;
	insert into detail_transaksi 
		(id_detail_transaksi, kondisi_item, jenis_layanan, tanggal_pengambilan, id_transaksi, id_item)
		values
		(nomor, _kondisi_item, _jenis_layanan, _tanggal_pengambilan, _id_transaksi, _id_item);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `transaksi_insert` (IN `_tanggal_transaksi` DATE, IN `_nama_customer` VARCHAR(50), IN `_ongkir_pickup` INT, IN `_ongkir_deliver` INT, IN `_nama_driver` VARCHAR(50), IN `_id_metode` VARCHAR(8), IN `_dp` INT, INOUT `nomor` VARCHAR(5))   begin
	select lpad(cast(id_transaksi as int)+1, 5, '0') into nomor from transaksi order by id_transaksi desc limit 1;
	select @id_customer:= id_customer from customers where nama_customer=_nama_customer;
	insert into transaksi 
		(id_transaksi, tanggal_transaksi, id_customer, ongkir_pickup, ongkir_deliver, nama_driver, id_metode, dp)
		values
		(nomor, _tanggal_transaksi, @id_customer, _ongkir_pickup, _ongkir_deliver, _nama_driver, _id_metode, _dp);
	select nomor;
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id_customer` int(5) NOT NULL,
  `nama_customer` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `no_telp` varchar(13) NOT NULL,
  `id_radius` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id_customer`, `nama_customer`, `alamat`, `no_telp`, `id_radius`) VALUES
(1, 'Griffin Sanjaya', 'Jl. Sumatra V/8', '081388292377', 2),
(2, 'Ferdie Halim', 'Jl. HR Muhammad 8/15', '089920911199', 1),
(3, 'Kayla Cortney', 'Jl. Royal Residence, Cluster Oakwood, L15/4', '085822192836', 2),
(4, 'Kely Gunawan', 'Jl. Tunjungan Barat XI/20', '081183847484', 3),
(5, 'Steven Chen', 'Jl. Graha Natura E4/6', '089927374803', 1),
(6, 'Donny Tan', 'Jl. Darmo Baru III/11', '081918283800', 3),
(7, 'Nikki Tjahjadi', 'Jl. Darmo Indah Barat VIII/30', '089683740019', 2),
(8, 'Jolie Chandra', 'Citraland Northwest Lake NE/17', '089633502502', 2),
(9, 'Adeline Salim', 'Bukit Darmo Golf B3/2', '081045570399', 1),
(10, 'Edith Alecia', 'Jl. Tunjungan Barat VIII/18', '085627388195', 3),
(11, 'Chloe Manuella', 'Jl. Darmo Permai Selatan VI/12', '089655183183', 1),
(12, 'Nancy Agatha Wijaya', 'Darmo Indah Selatan HH/64', '085866140140', 2),
(13, 'Jovian Faustino ', 'Jl. Darmo Permai Selatan XI/3', '081044031588', 1);

-- --------------------------------------------------------

--
-- Table structure for table `detail_promo`
--

CREATE TABLE `detail_promo` (
  `id_detail_promo` int(5) NOT NULL,
  `diskon` float NOT NULL,
  `id_kategori` varchar(5) NOT NULL,
  `id_promo` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detail_promo`
--

INSERT INTO `detail_promo` (`id_detail_promo`, `diskon`, `id_kategori`, `id_promo`) VALUES
(1, 0.2, '2OT', 'SB623'),
(2, 0.5, '1LB', 'JS623'),
(3, 0.5, '2OT', 'JS623'),
(4, 0.5, '3PT', 'JS623');

-- --------------------------------------------------------

--
-- Table structure for table `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_detail_transaksi` int(5) NOT NULL,
  `kondisi_item` varchar(50) NOT NULL,
  `jenis_layanan` varchar(12) NOT NULL,
  `diskon` float DEFAULT NULL,
  `tanggal_pengambilan` date NOT NULL,
  `id_transaksi` varchar(5) NOT NULL,
  `id_item` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`id_detail_transaksi`, `kondisi_item`, `jenis_layanan`, `diskon`, `tanggal_pengambilan`, `id_transaksi`, `id_item`) VALUES
(1, 'Baik', 'Laundry', 0.5, '2023-06-03', '00001', '3BCM'),
(2, 'Baik', 'Laundry', 0.5, '2023-06-05', '00002', '1SP'),
(3, 'Baik', 'Laundry', 0.5, '2023-06-05', '00003', '2BCL'),
(4, 'Baik', 'Dry Clean', 0.5, '2023-06-09', '00004', '6GA'),
(5, 'Baik', 'Dry Clean', NULL, '2023-06-10', '00005', '2BCL'),
(6, 'Baik', 'Laundry', NULL, '2023-06-21', '00006', '4JJ'),
(7, 'Baik', 'Laundry', NULL, '2023-06-21', '00007', '6GA'),
(8, 'Baik', 'Dry Clean', NULL, '2023-06-22', '00008', '6GA'),
(9, 'Sobek pada bagian kerah', 'Laundry', NULL, '2023-06-22', '00009', '5SM'),
(10, 'Warna luntur', 'Laundry', NULL, '2023-06-25', '00010', '2BCL'),
(11, 'Baik', 'Dry Clean', 0.2, '2023-06-26', '00011', '4JJ'),
(12, 'Warna luntur', 'Dry Clean', 0.2, '2023-06-28', '00012', '5SM'),
(13, 'Kain tipis', 'Dry Clean', NULL, '2023-06-28', '00013', '6GA'),
(14, 'Baik', 'Laundry', NULL, '2023-06-28', '00014', '4JJ'),
(15, 'Kain mudah sobek', 'Dry Clean', 0.2, '2023-06-28', '00015', '5SM'),
(16, 'Warna luntur', 'Laundry', NULL, '2023-07-01', '00016', '1SP');

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `id_item` varchar(8) NOT NULL,
  `nama_item` varchar(50) NOT NULL,
  `harga` int(6) NOT NULL,
  `lama_penyelesaian` int(2) NOT NULL,
  `id_kategori` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id_item`, `nama_item`, `harga`, `lama_penyelesaian`, `id_kategori`) VALUES
('1SP', 'Sprei', 29000, 1, '1LB'),
('2BCL', 'Bed Cover (L)', 60000, 2, '1LB'),
('3BCM', 'Bed Cover (M)', 58000, 2, '1LB'),
('4JJ', 'Jaket/Jas', 43000, 2, '2OT'),
('5SM', 'Sweater/Mantel', 37000, 2, '2OT'),
('6GA', 'Gaun', 283000, 2, '3PT');

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `id_kategori` varchar(5) NOT NULL,
  `nama_kategori` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id_kategori`, `nama_kategori`) VALUES
('1LB', 'Linen & Bed'),
('2OT', 'Outer'),
('3PT', 'Party');

-- --------------------------------------------------------

--
-- Table structure for table `metode_pembayaran`
--

CREATE TABLE `metode_pembayaran` (
  `id_metode` varchar(10) NOT NULL,
  `nama_metode` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `metode_pembayaran`
--

INSERT INTO `metode_pembayaran` (`id_metode`, `nama_metode`) VALUES
('1-TN', 'Tunai'),
('2-DB', 'Debit'),
('3-QR', 'QRIS'),
('4-KK', 'Kartu kredit');

-- --------------------------------------------------------

--
-- Table structure for table `promo`
--

CREATE TABLE `promo` (
  `id_promo` varchar(10) NOT NULL,
  `nama_promo` varchar(50) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `promo`
--

INSERT INTO `promo` (`id_promo`, `nama_promo`, `start_date`, `end_date`) VALUES
('JS623', 'Juni Santai', '2023-06-01', '2023-06-07'),
('SB623', 'Semester Break', '2023-06-21', '2023-06-28');

-- --------------------------------------------------------

--
-- Table structure for table `radius`
--

CREATE TABLE `radius` (
  `id_radius` int(2) NOT NULL,
  `min_radius` int(5) NOT NULL,
  `max_radius` int(5) DEFAULT NULL,
  `harga` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `radius`
--

INSERT INTO `radius` (`id_radius`, `min_radius`, `max_radius`, `harga`) VALUES
(1, 0, 5, 10000),
(2, 6, 10, 20000),
(3, 11, NULL, 30000);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` varchar(5) NOT NULL,
  `tanggal_transaksi` date NOT NULL,
  `DP` int(6) DEFAULT NULL,
  `pelunasan` int(6) DEFAULT NULL,
  `ongkir_pickup` int(6) DEFAULT NULL,
  `ongkir_deliver` int(6) DEFAULT NULL,
  `id_customer` int(5) NOT NULL,
  `id_promo` varchar(10) DEFAULT NULL,
  `nama_driver` varchar(50) DEFAULT NULL,
  `id_metode` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `tanggal_transaksi`, `DP`, `pelunasan`, `ongkir_pickup`, `ongkir_deliver`, `id_customer`, `id_promo`, `nama_driver`, `id_metode`) VALUES
('00001', '2023-06-01', 24500, NULL, NULL, 20000, 8, 'JS623', 'Agung Jusuf', '4-KK'),
('00002', '2023-06-03', NULL, NULL, NULL, NULL, 1, 'JS623', NULL, '1-TN'),
('00003', '2023-06-03', NULL, NULL, NULL, NULL, 1, 'JS623', NULL, '1-TN'),
('00004', '2023-06-07', NULL, NULL, NULL, 30000, 10, 'JS623', 'Agung Jusuf', '3-QR'),
('00005', '2023-06-08', 30000, NULL, NULL, NULL, 2, NULL, NULL, '3-QR'),
('00006', '2023-06-19', NULL, NULL, NULL, NULL, 1, NULL, NULL, '1-TN'),
('00007', '2023-06-19', NULL, NULL, NULL, NULL, 7, NULL, NULL, '1-TN'),
('00008', '2023-06-20', NULL, NULL, NULL, NULL, 9, NULL, NULL, '3-QR'),
('00009', '2023-06-20', NULL, NULL, NULL, 10000, 5, NULL, 'Agung Jusuf', '2-DB'),
('00010', '2023-06-23', NULL, NULL, 30000, NULL, 6, NULL, 'Agung Jusuf', '3-QR'),
('00011', '2023-06-24', NULL, NULL, NULL, NULL, 7, 'SB623', NULL, '1-TN'),
('00012', '2023-06-26', NULL, NULL, NULL, 10000, 2, 'SB623', 'Bima Hadi', '2-DB'),
('00013', '2023-06-26', 171500, NULL, 30000, 30000, 4, NULL, 'Bima Hadi', '1-TN'),
('00014', '2023-06-26', NULL, NULL, NULL, NULL, 6, 'SB623', NULL, '4-KK'),
('00015', '2023-06-26', NULL, NULL, NULL, NULL, 6, 'SB623', NULL, '4-KK'),
('00016', '2023-06-30', NULL, NULL, 20000, NULL, 3, NULL, 'Agung Jusuf', '2-DB');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id_customer`),
  ADD KEY `fk_id_radius` (`id_radius`);

--
-- Indexes for table `detail_promo`
--
ALTER TABLE `detail_promo`
  ADD PRIMARY KEY (`id_detail_promo`),
  ADD KEY `fk_id_kategori` (`id_kategori`),
  ADD KEY `fk_id_promo` (`id_promo`);

--
-- Indexes for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`id_detail_transaksi`),
  ADD KEY `fk_id_item` (`id_item`),
  ADD KEY `fk_id_transaksi` (`id_transaksi`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id_item`),
  ADD KEY `fk_id_kategori_item` (`id_kategori`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indexes for table `metode_pembayaran`
--
ALTER TABLE `metode_pembayaran`
  ADD PRIMARY KEY (`id_metode`);

--
-- Indexes for table `promo`
--
ALTER TABLE `promo`
  ADD PRIMARY KEY (`id_promo`);

--
-- Indexes for table `radius`
--
ALTER TABLE `radius`
  ADD PRIMARY KEY (`id_radius`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `id_metode` (`id_metode`),
  ADD KEY `fk_id_promo_transaksi` (`id_promo`),
  ADD KEY `fk_id_customer` (`id_customer`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_promo`
--
ALTER TABLE `detail_promo`
  MODIFY `id_detail_promo` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `radius`
--
ALTER TABLE `radius`
  MODIFY `id_radius` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customers`
--
ALTER TABLE `customers`
  ADD CONSTRAINT `fk_id_radius` FOREIGN KEY (`id_radius`) REFERENCES `radius` (`id_radius`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `detail_promo`
--
ALTER TABLE `detail_promo`
  ADD CONSTRAINT `fk_id_kategori` FOREIGN KEY (`id_kategori`) REFERENCES `kategori` (`id_kategori`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_id_promo` FOREIGN KEY (`id_promo`) REFERENCES `promo` (`id_promo`);

--
-- Constraints for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD CONSTRAINT `fk_id_item` FOREIGN KEY (`id_item`) REFERENCES `items` (`id_item`),
  ADD CONSTRAINT `fk_id_transaksi` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `items`
--
ALTER TABLE `items`
  ADD CONSTRAINT `fk_id_kategori_item` FOREIGN KEY (`id_kategori`) REFERENCES `kategori` (`id_kategori`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `fk_id_customer` FOREIGN KEY (`id_customer`) REFERENCES `customers` (`id_customer`),
  ADD CONSTRAINT `fk_id_promo_transaksi` FOREIGN KEY (`id_promo`) REFERENCES `promo` (`id_promo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `id_metode` FOREIGN KEY (`id_metode`) REFERENCES `metode_pembayaran` (`id_metode`) ON DELETE CASCADE ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

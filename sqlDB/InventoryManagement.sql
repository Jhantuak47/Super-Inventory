-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 17, 2018 at 01:56 PM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `InventoryManagement`
--

-- --------------------------------------------------------

--
-- Table structure for table `brand`
--

CREATE TABLE `brand` (
  `id` int(20) NOT NULL,
  `b_name` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` tinyint(4) NOT NULL DEFAULT '1',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `brand`
--

INSERT INTO `brand` (`id`, `b_name`, `created_at`, `updated_at`, `status`, `is_deleted`) VALUES
(1, 'reliance', '2018-05-04 11:18:05', '2018-04-28 18:05:35', 0, 0),
(2, 'puma', '2018-05-03 12:29:46', '2018-04-28 18:08:48', 1, 1),
(3, 'nokia', '2018-05-03 09:20:41', '2018-04-28 18:13:35', 1, 0),
(4, 'samsung', '2018-05-03 09:20:41', '2018-04-28 18:14:06', 1, 0),
(5, 'lg', '2018-05-03 09:20:41', '2018-04-28 19:00:31', 1, 0),
(6, 'mayur', '2018-05-03 09:20:41', '2018-04-28 19:31:30', 1, 0),
(7, 'xiaomi', '2018-05-03 09:20:41', '2018-05-03 05:28:26', 1, 0),
(8, 'levis', '2018-05-03 09:20:41', '2018-05-03 05:46:27', 1, 0),
(9, 'fastrack', '2018-05-03 09:20:41', '2018-05-03 05:49:27', 1, 0),
(10, 'lenovo', '2018-05-03 09:20:41', '2018-05-03 05:52:07', 1, 0),
(11, 'hp', '2018-05-03 09:20:41', '2018-05-03 05:52:41', 1, 0),
(12, 'intel', '2018-05-03 09:20:41', '2018-05-03 05:56:24', 1, 0),
(13, 'asus', '2018-05-03 09:20:41', '2018-05-03 05:57:08', 1, 0),
(14, 'usv', '2018-05-03 09:20:41', '2018-05-03 05:58:15', 1, 0),
(15, 'lupin', '2018-05-03 09:20:41', '2018-05-03 06:00:44', 1, 0),
(16, 'dabur', '2018-05-04 08:20:06', '2018-05-03 06:06:36', 1, 0),
(17, 'surfexel', '2018-05-03 09:20:41', '2018-05-03 06:08:13', 1, 0),
(18, 'shreeleather', '2018-05-03 09:20:41', '2018-05-03 06:09:56', 1, 0),
(19, 'itc', '2018-05-04 08:20:11', '2018-05-03 07:22:05', 1, 0),
(20, 'amul', '2018-05-14 05:49:05', '2018-05-03 07:24:04', 1, 0),
(21, 'motorola', '2018-05-14 06:29:06', '2018-05-03 07:29:12', 1, 1),
(22, 'zeeonee', '2018-05-14 06:24:07', '2018-05-05 07:42:41', 1, 1),
(23, 'patanjali', '2018-05-14 06:24:07', '2018-05-14 04:33:55', 1, 1),
(24, 'tata', '2018-05-14 06:40:16', '2018-05-14 06:40:16', 1, 0),
(25, 'cello', '2018-05-17 02:31:46', '2018-05-17 02:31:46', 1, 0),
(26, 'detol', '2018-05-17 02:39:35', '2018-05-17 02:39:35', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `category_master`
--

CREATE TABLE `category_master` (
  `id` int(10) NOT NULL,
  `c_name` varchar(225) NOT NULL,
  `parrent_category` int(10) DEFAULT NULL,
  `c_status` tinyint(1) DEFAULT NULL,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0',
  `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category_master`
--

INSERT INTO `category_master` (`id`, `c_name`, `parrent_category`, `c_status`, `is_deleted`, `updated_at`, `created_at`) VALUES
(1, 'electronics', 0, 1, 0, '2018-05-02 11:39:18', '2018-05-02 11:39:18'),
(2, 'softwaresdemo', 0, 1, 0, '2018-05-02 11:39:18', '2018-05-02 11:39:18'),
(3, 'clothsupdate', 1, 0, 0, '2018-05-02 11:39:18', '2018-05-02 11:39:18'),
(6, 'gadgets', 1, 1, 0, '2018-05-02 11:39:18', '2018-05-02 11:39:18'),
(7, 'mobile phone', 1, 0, 0, '2018-05-02 11:39:18', '2018-05-02 11:39:18'),
(9, 'antivirus', 2, 0, 0, '2018-05-02 11:39:18', '2018-05-02 11:39:18'),
(10, 'laptops', 1, 1, 0, '2018-05-02 11:39:18', '2018-05-02 11:39:18'),
(11, 'watchesd', 1, 1, 1, '2018-05-05 07:11:32', '2018-05-02 11:39:18'),
(15, 'MySoftwares', 0, 1, 0, '2018-05-02 11:39:18', '2018-05-02 11:39:18'),
(16, 'DemoSoftwares', 2, 1, 0, '2018-05-02 11:39:18', '2018-05-02 11:39:18'),
(17, 'Softwaresdemo', 2, 1, 0, '2018-05-02 13:46:25', '2018-05-02 11:39:18'),
(18, 'foods', 0, 1, 0, '2018-05-04 08:21:02', '2018-05-02 11:39:18'),
(21, 'Beauty Product', 0, 1, 0, '2018-05-02 13:46:16', '2018-05-02 11:39:18'),
(22, 'brauty cream', 21, 1, 0, '2018-05-02 11:39:18', '2018-05-02 11:39:18'),
(23, 'hardware', 0, 1, 0, '2018-05-02 14:05:56', '2018-05-02 13:43:26'),
(24, 'Medicined', 0, 1, 0, '2018-05-14 10:34:02', '2018-05-02 13:46:00'),
(25, 'pda', 23, 1, 0, '2018-05-14 10:34:07', '2018-05-05 07:42:06'),
(26, 'medicine', 0, 1, 1, '2018-05-14 10:36:44', '2018-05-05 07:49:02'),
(27, 'care product', 0, 1, 0, '2018-05-14 10:42:43', '2018-05-14 10:42:43'),
(28, 'shooes', 0, 1, 0, '2018-05-17 02:30:25', '2018-05-17 02:30:25'),
(29, 'hand wash', 27, 1, 0, '2018-05-17 02:39:24', '2018-05-17 02:39:24');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `invoice_no` int(20) NOT NULL,
  `cust_name` varchar(100) NOT NULL,
  `order_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sub_total` double NOT NULL,
  `gst_tax` double NOT NULL,
  `discount` double NOT NULL DEFAULT '0',
  `net_total` double NOT NULL,
  `paid_amount` double NOT NULL,
  `due` double NOT NULL DEFAULT '0',
  `payment_method` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`invoice_no`, `cust_name`, `order_date`, `sub_total`, `gst_tax`, `discount`, `net_total`, `paid_amount`, `due`, `payment_method`) VALUES
(1, 'Jhantu Nandi', '2018-05-08 10:24:23', 10423, 1876.1399999999999, 100, 12199.14, 12000, 200, 'Cash'),
(2, 'Jhantu Nandi', '2018-05-08 11:33:51', 28000, 5040, 500, 32540, 32000, 540, 'Cash'),
(3, 'sumit dutta', '2018-05-09 03:09:44', 28010, 5041.8, 0, 33051.8, 33052, 0, 'Cash'),
(4, 'rintu', '2018-05-09 03:11:50', 3777, 679.86, 0, 4456.86, 3456, 1001, 'Cash'),
(5, 'djkjjsjf', '2018-05-09 03:27:48', 3423, 616.14, 0, 4039.14, 4000, 40, 'Cash'),
(6, 'jlkjflsjsd', '2018-05-09 03:30:46', 3463, 623.34, 623, 3463.34, 3464, 0, 'Cash'),
(7, 'priya kumari', '2018-05-09 05:19:01', 35405, 6372.9, 0, 41777.9, 40000, 1778, 'Cash'),
(8, 'ruchi jha', '2018-05-09 05:21:35', 224354, 40383.72, 100, 264637.72, 260000, 4638, 'Cash'),
(9, 'klsjlkfj', '2018-05-09 05:25:16', 100, 18, 0, 118, 118, 0, 'Cash'),
(10, 'rakhit', '2018-05-16 17:39:49', 30708, 5527.44, 0, 36235.44, 36235, 1, 'Cash'),
(11, 'piyush', '2018-05-16 17:40:07', 28000, 5040, 0, 33040, 33040, 0, 'Cash'),
(12, 'Jhantu Nandi', '2018-05-09 06:25:06', 5, 0.8999999999999999, 0, 5.9, 6, 0, 'Cash'),
(13, 'Jhantu Nandi', '2018-05-16 17:45:02', 7010, 1261.8, 0, 8271.8, 100, 8172, 'Cash'),
(14, 'xxxxx', '2018-05-09 06:29:55', 7100, 1278, 0, 8378, 1000, 7378, 'Cash'),
(15, 'sumit', '2018-05-16 17:40:20', 240, 43.199999999999996, 0, 283.2, 284, 0, 'Cash'),
(16, 'arnab', '2018-05-16 17:40:24', 11300, 2034, 0, 13334, 3455, 9879, 'Cash'),
(17, 'xxx', '2018-05-09 07:07:44', 14159, 2548.62, 0, 16707.62, 16000, 708, 'Cash'),
(18, 'xxxxxx', '2018-05-09 07:12:36', 557, 100.25999999999999, 100, 557.26, 558, 0, 'Cash'),
(22, 'mahesh', '2018-05-16 17:40:37', 28000, 5040, 0, 33040, 567, 32473, 'Cash'),
(23, 'jafjksdljfsd', '2018-05-09 07:56:34', 2000, 360, 0, 2360, 588, 1772, 'Cash'),
(28, 'satya', '2018-05-16 17:40:42', 354, 63.72, 0, 417.72, 418, 0, 'Cash'),
(29, 'Jhantu Nandi', '2018-05-17 05:40:56', 960, 172.79999999999998, 133, 1000, 1000, 0, 'Cash'),
(30, 'Jhantu Nandi', '2018-05-17 08:32:53', 34280, 6170.4, 0, 40451, 4000, 36451, 'Cash');

-- --------------------------------------------------------

--
-- Table structure for table `invoice_details`
--

CREATE TABLE `invoice_details` (
  `id` int(20) NOT NULL,
  `invoice_no` int(20) NOT NULL,
  `product_id` int(20) NOT NULL,
  `product_price` double NOT NULL,
  `qty` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invoice_details`
--

INSERT INTO `invoice_details` (`id`, `invoice_no`, `product_id`, `product_price`, `qty`) VALUES
(1, 1, 10, 3423, 1),
(2, 1, 1, 7000, 1),
(3, 2, 8, 28000, 1),
(4, 3, 8, 28000, 1),
(5, 3, 2, 5, 2),
(6, 4, 10, 3423, 1),
(7, 4, 9, 354, 1),
(8, 5, 10, 3423, 1),
(9, 6, 10, 3423, 1),
(10, 6, 2, 5, 8),
(11, 7, 2, 5, 1),
(12, 7, 9, 354, 100),
(13, 8, 8, 28000, 8),
(14, 8, 9, 354, 1),
(15, 9, 6, 100, 1),
(16, 10, 8, 28000, 1),
(17, 10, 9, 354, 2),
(18, 10, 7, 2000, 1),
(19, 11, 8, 28000, 1),
(20, 12, 2, 5, 1),
(21, 13, 1, 7000, 1),
(22, 13, 5, 10, 1),
(23, 14, 6, 100, 1),
(25, 12, 3, 400, 8),
(26, 12, 4, 400, 4);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(20) NOT NULL,
  `p_name` varchar(255) NOT NULL,
  `brand_id` int(20) NOT NULL,
  `category_master_id` int(10) NOT NULL,
  `purchase_id` int(20) NOT NULL,
  `cost_price` double NOT NULL,
  `price` double NOT NULL,
  `avl_stock` int(20) NOT NULL,
  `added_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `type` varchar(255) DEFAULT NULL,
  `wt` double DEFAULT NULL,
  `exp_date` varchar(255) DEFAULT NULL,
  `batch_no` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `p_name`, `brand_id`, `category_master_id`, `purchase_id`, `cost_price`, `price`, `avl_stock`, `added_date`, `status`, `type`, `wt`, `exp_date`, `batch_no`, `Description`, `is_deleted`) VALUES
(1, 'redmi', 3, 6, 13, 6000, 7000, 90, '2018-05-13 10:57:55', 1, 'mobile', 0.2, '', '34rrs', 'jsdkljlkjf', 0),
(2, 'lux', 6, 18, 17, 5, 6, 28, '2018-05-17 05:32:08', 1, 'soap', 0.01, '', '345sjkfls', 'beauty soap', 0),
(3, 'xyz', 16, 18, 1, 29, 30, 20, '2018-05-16 20:22:25', 1, 'eatable', 1, '2020-05-20', '34-ths-98', 'dabur chayawanprash is a product to eat .', 0),
(4, 'x-deo', 19, 21, 1, 90, 99, 10, '2018-05-16 20:23:17', 1, 'Deodorant', 0.7, '2020-05-13', 'fs89', 'x-deo is a deodorant belongs to itc', 0),
(5, 'talcom', 19, 21, 1, 9, 10, 33, '2018-05-16 20:23:26', 1, 'talcum powder', 0.3, '2020-05-18', 'jgh-34', 'sdklsdlkjfsfjsdlkfjsdklj', 0),
(6, 'levis-t', 8, 3, 1, 99, 100, 48, '2018-05-16 20:23:32', 1, 't-shirt', 0.1, '', '678ef', 'levis-t is T-shirt belongs to levis brand', 0),
(7, 'skylite', 11, 17, 1, 1900, 2000, 22, '2018-05-16 20:23:46', 1, 'software', 0, '', 'ds45', 'skylite is a software use to solve all your cloud problem', 0),
(8, 'hp-pavilian', 11, 10, 1, 22000, 30000, 58, '2018-05-13 10:54:23', 1, 'hardware', 3, '', 'sjs341', 'hp-pavilian is a bran of hp which is a laptop', 0),
(9, 'shirt', 6, 7, 1, 300, 354, 354, '2018-05-16 20:23:52', 1, 'jlkdjlkdj', 3, '', 'fsdsf24', 'jfisjflf', 0),
(10, 'g4', 5, 1, 1, 3000, 3423, 10, '2018-05-16 20:26:58', 1, 'ldjkfl', 2, '2021-05-11', 'jsjs345', 'lsflkjkl', 0),
(11, 'kur-kure', 19, 18, 16, 4.5, 5, 20, '2018-05-17 03:17:16', 1, 'snacks', 0, '2020-06-10', '3456fdfdf', 'kurure is a food product', 0),
(12, 'kesh-king', 14, 21, 15, 75, 80, 10, '2018-05-16 20:21:17', 1, 'shampoo', 0, '2019-09-17', 'uye234', 'care product', 0),
(13, 'detol+', 26, 29, 15, 70, 80, 0, '2018-05-17 02:48:13', 1, 'klfklklskslk', 0, '2018-05-10', '345RGS', 'dslkjskfjkfjkls', 0),
(14, 'milton ', 6, 3, 17, 5, 8, 10, '2018-05-17 05:32:08', 1, 'cloaths', NULL, '', 'ssdf2345', NULL, 0);

-- --------------------------------------------------------

--
-- Table structure for table `purchase_details`
--

CREATE TABLE `purchase_details` (
  `id` int(20) NOT NULL,
  `purchased_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `purchesed_from` text NOT NULL,
  `purchesed_bill_no` varchar(20) NOT NULL,
  `total_purchesed_amount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchase_details`
--

INSERT INTO `purchase_details` (`id`, `purchased_at`, `purchesed_from`, `purchesed_bill_no`, `total_purchesed_amount`) VALUES
(1, '2018-05-08 18:30:00', 'dilip tradors', '12345', 60000),
(2, '2018-05-10 07:40:25', 'xxxxxxx', '2345', 345),
(4, '2018-05-10 10:47:16', 'samsondar singh', '3456', 27070),
(10, '2018-05-10 11:48:06', 'Jhantu Nandi', '34566', 28075),
(11, '2018-05-11 14:16:30', 'seth ji', '345345', 28075),
(12, '2018-05-11 14:22:16', 'jeth part two', '2343', 6000),
(13, '2018-05-11 14:36:08', 'demosub', '1234', 6000),
(15, '2018-05-16 20:27:46', 'silap', '23456', 75),
(16, '2018-05-17 03:17:16', 'laxmi enterprise', '34545', 4.5),
(17, '2018-05-17 05:32:08', 'dilip tradors', '7654', 150);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(10) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `u_email` varchar(255) DEFAULT NULL,
  `u_password` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `is_admin` tinyint(1) NOT NULL DEFAULT '0',
  `last_login` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `address` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `u_email`, `u_password`, `mobile`, `is_admin`, `last_login`, `created_at`, `address`) VALUES
(1, 'jhantu', 'admin@gm.com', 'admin', '2147483647', 1, '2018-05-17 08:21:28', '2018-04-23 18:30:00', NULL),
(2, 'krishna', 'krish@gm.com', '12345', '7979982081', 0, '2018-04-26 18:09:37', '2018-04-26 18:09:37', 'Near Exchange Office, Manbad, P.o-Jharia, Dis Dhanbad'),
(3, 'piyush kumar', 'pi@gm.com', 'xyz', '12345678', 0, '2018-04-27 01:32:46', '2018-04-27 01:32:46', 'Darji para, garikhana'),
(4, 'sumit', 'sum@gm.com', '123', '2345678', 0, '2018-04-27 01:40:26', '2018-04-27 01:40:26', 'dhanbad'),
(5, 'Arnab', 'ar@gm.com', '123', '3456783457', 0, '2018-04-27 01:45:27', '2018-04-27 01:45:27', 'jaharia'),
(6, 'sasu', 'sa@gm.com', 'abc', '23456', 0, '2018-04-27 01:48:27', '2018-04-27 01:48:27', 'fghfnfdzxfcbvhf'),
(7, 'kdlf', 'ag@gm.com', 'sdfdgf', '123456', 0, '2018-04-27 01:58:28', '2018-04-27 01:58:28', 'fjlkfsdlk'),
(8, 'Jhantu Nandi', 'jhantunandi4@gmail.com', '98353jha', '7979982081', 0, '2018-05-17 02:19:52', '2018-05-17 02:19:52', 'Near Exchange Office, Manbad, P.o-Jharia, Dis Dhanbad');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category_master`
--
ALTER TABLE `category_master`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`invoice_no`);

--
-- Indexes for table `invoice_details`
--
ALTER TABLE `invoice_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `invoice_no` (`invoice_no`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `p_name` (`p_name`),
  ADD KEY `brand_id` (`brand_id`,`category_master_id`),
  ADD KEY `category_master_id` (`category_master_id`),
  ADD KEY `purchase_id` (`purchase_id`);

--
-- Indexes for table `purchase_details`
--
ALTER TABLE `purchase_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_email` (`u_email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `brand`
--
ALTER TABLE `brand`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `category_master`
--
ALTER TABLE `category_master`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `invoice_no` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `invoice_details`
--
ALTER TABLE `invoice_details`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `purchase_details`
--
ALTER TABLE `purchase_details`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `invoice_details`
--
ALTER TABLE `invoice_details`
  ADD CONSTRAINT `invoice_details_ibfk_1` FOREIGN KEY (`invoice_no`) REFERENCES `invoice` (`invoice_no`),
  ADD CONSTRAINT `invoice_details_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
  ADD CONSTRAINT `products_ibfk_2` FOREIGN KEY (`category_master_id`) REFERENCES `category_master` (`id`),
  ADD CONSTRAINT `products_ibfk_3` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
  ADD CONSTRAINT `products_ibfk_4` FOREIGN KEY (`purchase_id`) REFERENCES `purchase_details` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

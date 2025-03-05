-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 05, 2025 at 10:44 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tracnghiem`
--

-- --------------------------------------------------------

--
-- Table structure for table `answers`
--

CREATE TABLE `answers` (
  `awID` int(11) NOT NULL,
  `qID` int(11) NOT NULL COMMENT 'id câu hỏi',
  `awContent` text NOT NULL,
  `awPictures` text NOT NULL COMMENT 'url ảnh',
  `isRight` tinyint(4) NOT NULL COMMENT '1: đúng; 0: Sai',
  `awStatus` tinyint(4) NOT NULL COMMENT '1: active; 0: hidden'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `answers`
--

INSERT INTO `answers` (`awID`, `qID`, `awContent`, `awPictures`, `isRight`, `awStatus`) VALUES
(4, 2, '299,792,458 m/s', '', 1, 1),
(5, 2, '150,000,000 m/s', '', 0, 1),
(6, 3, 'NaOH + H2', '', 1, 1),
(7, 3, 'NaCl', '', 0, 1),
(8, 4, '12', '', 0, 1),
(9, 4, '13', '', 1, 1),
(10, 4, '33', '', 0, 1),
(11, 4, '43', '', 0, 1),
(15, 1, '1', '', 0, 1),
(16, 1, '2', '', 1, 1),
(17, 1, '3', '', 0, 1),
(18, 5, 'a', '', 0, 1),
(19, 5, 'b', '', 1, 1),
(20, 5, 'c', '', 0, 1),
(21, 5, 'd', '', 0, 1),
(22, 6, 'a', '', 1, 1),
(23, 6, 'a', '', 0, 1),
(24, 6, 'a', '', 0, 1),
(25, 7, 'a', '', 1, 1),
(26, 7, '2', '', 0, 1),
(27, 7, '1', '', 0, 1),
(36, 8, 'Python', '', 0, 1),
(37, 8, 'C++', '', 0, 1),
(38, 8, 'Java', '', 0, 1),
(39, 8, 'HTML', '', 1, 1),
(100, 22, 'Array', '', 1, 1),
(101, 22, 'VARCHAR', '', 0, 1),
(102, 22, 'INT', '', 0, 1),
(103, 22, 'DATE', '', 0, 1),
(104, 23, 'FOREIGN KEY', '', 1, 1),
(105, 23, 'PRIMARY KEY', '', 0, 1),
(106, 23, 'UNIQUE', '', 0, 1),
(107, 23, 'INDEX', '', 0, 1),
(108, 21, 'Tìm kiếm nhị phân', '', 1, 1),
(109, 21, 'Tìm kiếm tuyến tính', '', 0, 1),
(110, 21, 'Tìm kiếm đệ quy', '', 0, 1),
(111, 21, 'Tìm kiếm tham lam', '', 0, 1),
(152, 20, 'SMTP', '', 1, 1),
(153, 20, 'HTTP', '', 0, 1),
(154, 20, 'DNS', '', 0, 1),
(155, 20, 'FTP', '', 0, 1),
(156, 19, 'Windows', '', 1, 1),
(157, 19, 'Linux', '', 0, 1),
(158, 19, 'macOS', '', 0, 1),
(159, 19, 'Ubuntu', '', 0, 1),
(160, 18, 'Lưu trữ tạm thời dữ liệu', '', 1, 1),
(161, 18, 'Lưu trữ vĩnh viễn dữ liệu', '', 0, 1),
(162, 18, 'Xử lý đồ họa', '', 0, 1),
(163, 18, 'Kết nối internet', '', 0, 1),
(164, 17, 'Chuyển đổi tên miền thành địa chỉ IP', '', 1, 1),
(165, 17, 'Lưu trữ dữ liệu trên máy chủ', '', 0, 1),
(166, 17, 'Quản lý người dùng trên hệ thống', '', 0, 1),
(167, 17, 'Mã hóa dữ liệu khi truyền tải', '', 0, 1),
(168, 16, 'SELECT', '', 1, 1),
(169, 16, 'UPDATE', '', 0, 1),
(170, 16, 'DELETE', '', 0, 1),
(171, 16, 'INSERT', '', 0, 1),
(172, 15, 'Structured Query Language', '', 1, 1),
(173, 15, 'Simple Query Language', '', 0, 1),
(174, 15, 'Standard Query Language', '', 0, 1),
(175, 15, 'System Query Logic', '', 0, 1),
(176, 14, 'HTTP', '', 1, 1),
(177, 14, 'FTP', '', 0, 1),
(178, 14, 'SMTP', '', 0, 1),
(179, 14, 'POP3', '', 0, 1),
(180, 13, 'Xác định duy nhất một bản ghi', '', 1, 1),
(181, 13, 'Chứa dữ liệu trùng lặp', '', 0, 1),
(182, 13, 'Tăng tốc độ xử lý', '', 0, 1),
(183, 13, 'Liên kết giữa các bảng', '', 0, 1),
(184, 12, 'Linux', '', 1, 1),
(185, 12, 'Windows', '', 0, 1),
(186, 12, 'macOS', '', 0, 1),
(187, 12, 'Android', '', 0, 1),
(188, 11, 'Xác định duy nhất một bản ghi', '', 1, 1),
(189, 11, 'Tầng Vật lý', '', 0, 1),
(190, 11, 'Tầng Giao vận', '', 0, 1),
(191, 11, 'Tầng Liên kết dữ liệu', '', 0, 1),
(192, 10, 'c++', '', 0, 1),
(193, 10, 'python', '', 0, 1),
(194, 10, 'html', '', 1, 1),
(195, 10, 'java', '', 0, 1),
(196, 9, 'Tầng Mạng', '', 1, 1),
(197, 9, 'Tầng Vật lý', '', 0, 1),
(198, 9, 'Tầng Giao vận', '', 0, 1),
(199, 9, 'Tầng Liên kết dữ liệu', '', 0, 1),
(200, 26, 'Central Processing Unit', '', 1, 1),
(201, 26, 'Computer Personal Unit', '', 0, 1),
(202, 26, 'Control Processing Unit', '', 0, 1),
(203, 26, 'Core Processor Utility', '', 0, 1),
(204, 27, 'Ổ cứng (HDD/SSD)', '', 1, 1),
(205, 27, 'RAM', '', 0, 1),
(206, 27, 'CPU', '', 0, 1),
(207, 27, 'PSU', '', 0, 1),
(208, 28, 'USB', '', 1, 1),
(209, 28, 'HDMI', '', 0, 1),
(210, 28, 'VGA', '', 0, 1),
(211, 28, 'LAN', '', 0, 1),
(212, 29, 'Android và iOS', '', 1, 1),
(213, 29, 'Windows', '', 0, 1),
(214, 29, 'Linux', '', 0, 1),
(215, 29, 'MacOS', '', 0, 1),
(216, 30, 'int', '', 1, 1),
(217, 30, 'float', '', 0, 1),
(218, 30, 'char', '', 0, 1),
(219, 30, 'double', '', 0, 1),
(220, 31, 'SMTP', '', 1, 1),
(221, 31, 'HTTP', '', 0, 1),
(222, 31, 'DNS', '', 0, 1),
(223, 31, 'FTP', '', 0, 1),
(224, 32, 'Kế thừa (Inheritance)', '', 1, 1),
(225, 32, 'Đóng gói (Encapsulation)', '', 0, 1),
(226, 32, 'Đa hình (Polymorphism)', '', 0, 1),
(227, 32, 'Trừu tượng (Abstraction)', '', 0, 1),
(228, 33, 'Stack', '', 1, 1),
(229, 33, 'Queue', '', 0, 1),
(230, 33, 'Linked List', '', 0, 1),
(231, 33, 'Array', '', 0, 1),
(232, 34, 'Merge Sort', '', 1, 1),
(233, 34, 'Bubble Sort', '', 0, 1),
(234, 34, 'Insertion Sort', '', 0, 1),
(235, 34, 'Selection Sort', '', 0, 1),
(236, 35, 'Linux', '', 1, 1),
(237, 35, 'Windows', '', 0, 1),
(238, 35, 'MacOS', '', 0, 1),
(239, 35, 'FreeBSD', '', 0, 1),
(240, 40, '3', '', 0, 1),
(241, 40, '4', '', 1, 1),
(242, 40, '5', '', 0, 1),
(243, 40, '6', '', 0, 1),
(244, 41, '3', '', 0, 1),
(245, 41, '4', '', 1, 1),
(246, 41, '5', '', 0, 1),
(247, 41, '6', '', 0, 1),
(248, 42, '3', '', 0, 1),
(249, 42, '4', '', 1, 1),
(250, 42, '5', '', 0, 1),
(251, 42, '6', '', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `exams`
--

CREATE TABLE `exams` (
  `testCode` varchar(20) NOT NULL,
  `exOrder` varchar(1) NOT NULL COMMENT 'A;B;C;D;E;F',
  `exCode` varchar(20) NOT NULL COMMENT '=testCode + exOrder',
  `ex_quesIDs` text NOT NULL COMMENT 'mảng các id câu hỏi'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `exams`
--

INSERT INTO `exams` (`testCode`, `exOrder`, `exCode`, `ex_quesIDs`) VALUES
('tes1', '1', 'tes1_1', '4,5,6'),
('tes1', '2', 'tes1_2', '4,5,6'),
('tes1', '3', 'tes1_3', '4,5,6'),
('tes1', '4', 'tes1_4', '4,5,6'),
('tes1', '5', 'tes1_5', '4,5,6'),
('tes1', '6', 'tes1_6', '4,5,6'),
('TESTCNTT', '1', 'TESTCNTT_1', '8,9,10,11,12,13,14,15,16,17,18,19,20,21'),
('TESTCNTT', '2', 'TESTCNTT_2', '8,9,10,11,12,13,14,15,16,17,18,19,20,21'),
('TESTCNTT', '3', 'TESTCNTT_3', '8,9,10,11,12,13,14,15,16,17,18,19,20,21'),
('TESTTOAN', '1', 'TESTTOAN_1', '4,5,6,7'),
('TESTTOAN', '2', 'TESTTOAN_2', '4,5,6,7'),
('TESTTOAN', '3', 'TESTTOAN_3', '4,5,6,7'),
('TESTTOAN', '4', 'TESTTOAN_4', '4,5,6,7'),
('TESTTOAN', '5', 'TESTTOAN_5', '4,5,6,7'),
('TESTTOAN', '6', 'TESTTOAN_6', '4,5,6,7'),
('TESTTOAN', '7', 'TESTTOAN_7', '4,5,6,7'),
('TESTTOAN', '8', 'TESTTOAN_8', '4,5,6,7'),
('TESTTOAN', '9', 'TESTTOAN_9', '4,5,6,7');

-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE `logs` (
  `logID` int(11) NOT NULL,
  `logContent` text NOT NULL,
  `logUserID` int(11) NOT NULL,
  `logExCode` varchar(20) NOT NULL,
  `logDate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `logs`
--

INSERT INTO `logs` (`logID`, `logContent`, `logUserID`, `logExCode`, `logDate`) VALUES
(233, '8:39', 1, 'TESTCNTT_1', '2025-03-05 10:02:25'),
(234, '8:39, 9:197', 1, 'TESTCNTT_1', '2025-03-05 10:02:26'),
(235, '8:39, 9:197, 10:193', 1, 'TESTCNTT_1', '2025-03-05 10:02:26'),
(236, '8:39, 9:197, 10:192', 1, 'TESTCNTT_1', '2025-03-05 10:02:27'),
(237, '8:39, 9:197, 10:192', 1, 'TESTCNTT_1', '2025-03-05 10:02:27'),
(238, '8:37', 2, 'TESTCNTT_1', '2025-03-05 10:02:39'),
(239, '8:37, 9:197', 2, 'TESTCNTT_1', '2025-03-05 10:02:40'),
(240, '8:37, 9:197, 11:191', 2, 'TESTCNTT_1', '2025-03-05 10:02:41'),
(241, '8:37, 9:197, 11:191, 12:185', 2, 'TESTCNTT_1', '2025-03-05 10:02:42'),
(242, '8:37, 9:197, 11:191, 12:185, 13:181', 2, 'TESTCNTT_1', '2025-03-05 10:02:42'),
(243, '8:37, 9:197, 11:191, 12:185, 13:181, 15:173', 2, 'TESTCNTT_1', '2025-03-05 10:02:44'),
(244, '8:37, 9:197, 11:191, 12:185, 13:181, 15:173, 16:169', 2, 'TESTCNTT_1', '2025-03-05 10:02:44'),
(245, '8:37, 9:197, 11:191, 12:185, 13:181, 15:173, 16:169, 17:165', 2, 'TESTCNTT_1', '2025-03-05 10:02:45'),
(246, '8:37, 9:197, 11:191, 12:185, 13:181, 15:173, 16:169, 17:165, 20:153', 2, 'TESTCNTT_1', '2025-03-05 10:02:46'),
(247, '8:37, 9:197, 11:191, 12:185, 13:181, 15:173, 16:169, 17:165, 20:153, 21:109', 2, 'TESTCNTT_1', '2025-03-05 10:02:47'),
(248, '8:37, 9:197, 11:191, 12:185, 13:181, 15:173, 16:169, 17:165, 20:153, 21:109, 19:157', 2, 'TESTCNTT_1', '2025-03-05 10:02:48'),
(249, '8:37, 9:197, 11:191, 12:185, 13:181, 15:173, 16:169, 17:165, 20:153, 21:109, 19:157, 14:177', 2, 'TESTCNTT_1', '2025-03-05 10:02:51'),
(250, '8:37, 9:197, 11:191, 12:185, 13:181, 15:173, 16:169, 17:165, 20:153, 21:109, 19:157, 14:177, 10:193', 2, 'TESTCNTT_1', '2025-03-05 10:02:52'),
(251, 'confirm', 2, 'TESTCNTT_1', '2025-03-05 10:02:54'),
(252, '4:9', 2, 'tes1_1', '2025-03-05 10:03:01'),
(253, '4:9, 5:19', 2, 'tes1_1', '2025-03-05 10:03:01'),
(254, 'confirm', 2, 'tes1_1', '2025-03-05 10:03:02'),
(255, '8:39, 9:197, 10:192, 17:165', 1, 'TESTCNTT_1', '2025-03-05 10:03:20'),
(256, 'confirm', 1, 'TESTCNTT_1', '2025-03-05 10:03:21'),
(257, '4:9', 1, 'tes1_1', '2025-03-05 10:09:21'),
(258, 'confirm', 1, 'tes1_1', '2025-03-05 10:09:21'),
(259, '8:36', 1, 'TESTCNTT_1', '2025-03-05 10:31:50'),
(260, '8:36, 9:197', 1, 'TESTCNTT_1', '2025-03-05 10:31:52'),
(261, '8:36, 9:197, 10:193', 1, 'TESTCNTT_1', '2025-03-05 10:31:53'),
(262, '8:36, 9:197, 10:193, 11:189', 1, 'TESTCNTT_1', '2025-03-05 10:31:59'),
(263, '8:36, 9:197, 10:193, 11:189, 12:185', 1, 'TESTCNTT_1', '2025-03-05 10:32:01'),
(264, 'confirm', 1, 'TESTCNTT_1', '2025-03-05 10:32:04'),
(265, '4:11', 1, 'TESTTOAN_1', '2025-03-05 16:42:44'),
(266, 'confirm', 1, 'TESTTOAN_1', '2025-03-05 16:42:46');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `qID` int(11) NOT NULL,
  `qContent` text NOT NULL COMMENT 'nội dung câu hỏi',
  `qPictures` text NOT NULL COMMENT 'url hình đính kèm',
  `qTopicID` int(11) NOT NULL,
  `qLevel` varchar(10) NOT NULL COMMENT 'easy, meidum, diff',
  `qStatus` tinyint(4) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`qID`, `qContent`, `qPictures`, `qTopicID`, `qLevel`, `qStatus`) VALUES
(1, '1 + 1 bằng bao nhiêu?', '', 1, 'easy', 1),
(2, 'Tốc độ ánh sáng là bao nhiêu?', '', 2, 'medium', 1),
(3, 'Phản ứng hóa học giữa Na và H2O tạo ra chất gì?', '', 3, 'diff', 1),
(4, 'tester', '', 1, 'Dễ', 1),
(5, 'abc', '', 1, 'Dễ', 1),
(6, 'aaa', '', 1, 'Dễ', 1),
(7, 'abc', '', 1, 'Dễ', 1),
(8, 'Ngôn ngữ lập trình nào sau đây được sử dụng phổ biến để phát triển ứng dụng web?', '', 4, 'Dễ', 1),
(9, 'Trong mô hình mạng OSI, tầng nào chịu trách nhiệm định tuyến dữ liệu giữa các thiết bị mạng?', '', 4, 'Dễ', 1),
(10, 'Ngôn ngữ lập trình nào phổ biến trong phát triển web?', '', 4, 'Dễ', 1),
(11, 'Tầng nào trong mô hình OSI chịu trách nhiệm định tuyến?', '', 4, 'Dễ', 1),
(12, 'Phần mềm nào là hệ điều hành mã nguồn mở?', '', 4, 'Dễ', 1),
(13, 'Trong cơ sở dữ liệu, khóa chính có vai trò gì?', '', 4, 'Dễ', 1),
(14, 'Giao thức phổ biến nhất để truyền dữ liệu trên web là gì?', '', 4, 'Dễ', 1),
(15, 'SQL là viết tắt của cụm từ nào?', '', 4, 'Dễ', 1),
(16, 'Câu lệnh nào dùng để lấy dữ liệu trong SQL?', '', 4, 'Dễ', 1),
(17, 'Chức năng chính của DNS là gì?', '', 4, 'Dễ', 1),
(18, 'RAM có chức năng gì trong máy tính?', '', 4, 'Dễ', 1),
(19, 'Hệ điều hành nào được phát triển bởi Microsoft?', '', 4, 'Dễ', 1),
(20, 'Giao thức nào được sử dụng để gửi email?', '', 4, 'Dễ', 1),
(21, 'Thuật toán nào dùng để tìm kiếm nhị phân?', '', 4, 'Dễ', 1),
(22, 'Kiểu dữ liệu nào sau đây không có trong SQL?', '', 4, 'vừa', 1),
(23, 'Ký hiệu nào đại diện cho khóa ngoại trong SQL?', '', 4, 'vừa', 1),
(24, 'Công cụ nào hỗ trợ lập trình viên kiểm soát phiên bản?', '', 4, 'vừa', 1),
(25, 'Hệ thống quản lý cơ sở dữ liệu quan hệ nào phổ biến nhất?', '', 4, 'vừa', 1),
(26, 'Phát biểu nào sau đây về lập trình hướng đối tượng là đúng?', '', 4, 'vừa', 1),
(27, 'Bộ nhớ nào có tốc độ nhanh nhất?', '', 4, 'vừa', 1),
(28, 'Lệnh nào dùng để cập nhật dữ liệu trong SQL?', '', 4, 'vừa', 1),
(29, 'Phần cứng nào đóng vai trò xử lý chính trong máy tính?', '', 4, 'dễ', 1),
(30, 'CPU viết tắt của từ gì?', '', 5, 'Dễ', 1),
(31, 'Thiết bị nào được sử dụng để lưu trữ dữ liệu lâu dài?', '', 5, 'Dễ', 1),
(32, 'Cổng giao tiếp phổ biến để kết nối chuột và bàn phím là gì?', '', 5, 'Dễ', 1),
(33, 'Hệ điều hành nào phổ biến trên smartphone?', '', 5, 'Dễ', 1),
(34, 'Trong lập trình, kiểu dữ liệu nào dùng để lưu trữ số nguyên?', '', 5, 'Dễ', 1),
(35, 'Giao thức nào được sử dụng để truyền tải email?', '', 5, 'Vừa', 1),
(36, 'Trong lập trình hướng đối tượng, tính chất nào cho phép một lớp kế thừa từ lớp khác?', '', 5, 'Vừa', 1),
(37, 'Cấu trúc dữ liệu nào phù hợp để thực hiện LIFO?', '', 5, 'Vừa', 1),
(38, 'Giải thuật nào có độ phức tạp tốt nhất là O(n log n)?', '', 5, 'Khó', 1),
(39, 'Hệ điều hành nào được sử dụng phổ biến nhất trên máy chủ?', '', 5, 'Khó', 1),
(40, '232323232 + 3332 = ?', '', 1, 'Dễ', 1),
(41, '2 + 3332 = ?', '', 1, 'Dễ', 1),
(42, '99 + 90', '', 1, 'Khó', 1);

-- --------------------------------------------------------

--
-- Table structure for table `result`
--

CREATE TABLE `result` (
  `rs_num` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `exCode` varchar(20) NOT NULL,
  `rs_anwsers` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'các đáp án đã chọn',
  `rs_mark` decimal(10,2) DEFAULT NULL,
  `rs_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `result`
--

INSERT INTO `result` (`rs_num`, `userID`, `exCode`, `rs_anwsers`, `rs_mark`, `rs_date`) VALUES
(28, 2, 'TESTCNTT_1', '8:37, 9:197, 11:191, 12:185, 13:181, 15:173, 16:169, 17:165, 20:153, 21:109, 19:157, 14:177, 10:193, 18:161', 0.00, '2025-03-05 10:02:56'),
(29, 2, 'tes1_1', '4:9, 5:19, 6:23', 3.33, '2025-03-05 10:03:04'),
(30, 1, 'TESTCNTT_1', '8:39, 9:197, 10:192, 17:165, 18:161', 0.50, '2025-03-05 10:28:45'),
(31, 1, 'TESTCNTT_1', '8:36, 9:197, 10:193, 11:189, 12:185, 13:181', 0.00, '2025-03-05 10:32:19'),
(32, 1, 'tes1_1', '4:9, 5:19', 3.33, '2025-03-05 11:09:03'),
(33, 1, 'TESTTOAN_1', '4:11, 5:18', 0.00, '2025-03-05 16:42:53');

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE `test` (
  `testID` int(11) NOT NULL,
  `testCode` varchar(20) NOT NULL COMMENT 'mã bài thi',
  `testTitle` text NOT NULL,
  `testTime` int(11) NOT NULL COMMENT 'thời gian làm bài (phút)',
  `tpID` int(11) NOT NULL COMMENT 'id của chủ đề/bài học',
  `num_easy` int(11) NOT NULL COMMENT 'số lượng câu dễ',
  `num_medium` int(11) NOT NULL COMMENT 'số lượng câu trung bình',
  `num_diff` int(11) NOT NULL COMMENT 'số lượng câu khó',
  `testLimit` tinyint(4) NOT NULL COMMENT 'số lần thi',
  `testDate` date NOT NULL,
  `testStatus` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`testID`, `testCode`, `testTitle`, `testTime`, `tpID`, `num_easy`, `num_medium`, `num_diff`, `testLimit`, `testDate`, `testStatus`) VALUES
(8, 'TESTCNTT', 'bài thi công nghệ thông tin', 60, 4, 15, 5, 0, 2, '2025-03-02', 1),
(9, 'TESTTOAN', 'bài thi môn toán', 90, 1, 5, 4, 2, 5, '2025-03-02', 1),
(10, 'tes1', 'asd', 3, 1, 2, 2, 2, 1, '2025-03-03', 1);

-- --------------------------------------------------------

--
-- Table structure for table `topics`
--

CREATE TABLE `topics` (
  `tpID` int(11) NOT NULL,
  `tpTitle` text NOT NULL COMMENT 'tên topic',
  `tpParent` int(11) NOT NULL COMMENT 'id của topic cha',
  `tpStatus` tinyint(4) NOT NULL COMMENT '1: active; 0: hidden'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `topics`
--

INSERT INTO `topics` (`tpID`, `tpTitle`, `tpParent`, `tpStatus`) VALUES
(1, 'Toán', 0, 1),
(2, 'Lý', 0, 1),
(3, 'Hóa', 0, 0),
(4, 'CNTT', 0, 1),
(5, 'Kỹ thuật', 4, 1),
(6, 'Giải thuật', 4, 1),
(7, 'a', 0, 1),
(8, 'b', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userID` int(11) NOT NULL,
  `userName` varchar(40) NOT NULL COMMENT 'login = userName',
  `userEmail` varchar(20) NOT NULL,
  `userPassword` varchar(40) NOT NULL COMMENT 'mã hóa dùng md5',
  `userFullName` varchar(40) NOT NULL,
  `isAdmin` tinyint(4) NOT NULL COMMENT '1: admin; 0: user',
  `trangThai` tinyint(1) NOT NULL DEFAULT 1 COMMENT '1: active, 0: inactive'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `userName`, `userEmail`, `userPassword`, `userFullName`, `isAdmin`, `trangThai`) VALUES
(1, 'admin', 'admin@gmail.com', '123456', 'Administrator', 1, 1),
(2, 'user1', 'user1@example.com', '11111111', 'User One', 0, 1),
(3, 'user2', 'user2@example.com', '11111111', 'assdasd', 0, 1),
(101, 'Nguyen Van A', 'a@example.com', '123123', 'aaaa', 0, 1),
(102, 'Tran Thi B', 'b@example.com', '123123', 'bbbb', 0, 1),
(103, 'Le Van C', 'c@example.com', '123123', 'cccc', 0, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `answers`
--
ALTER TABLE `answers`
  ADD PRIMARY KEY (`awID`),
  ADD KEY `qID` (`qID`);

--
-- Indexes for table `exams`
--
ALTER TABLE `exams`
  ADD PRIMARY KEY (`exCode`) USING BTREE,
  ADD KEY `testCode` (`testCode`),
  ADD KEY `exCode` (`exCode`);

--
-- Indexes for table `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`logID`),
  ADD KEY `logUserID` (`logUserID`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`qID`),
  ADD KEY `qTopicID` (`qTopicID`);

--
-- Indexes for table `result`
--
ALTER TABLE `result`
  ADD PRIMARY KEY (`rs_num`) USING BTREE,
  ADD KEY `userID` (`userID`),
  ADD KEY `exID` (`exCode`),
  ADD KEY `exCode` (`exCode`);

--
-- Indexes for table `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`testID`,`tpID`,`testCode`) USING BTREE,
  ADD KEY `tpID` (`tpID`);

--
-- Indexes for table `topics`
--
ALTER TABLE `topics`
  ADD PRIMARY KEY (`tpID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `answers`
--
ALTER TABLE `answers`
  MODIFY `awID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=252;

--
-- AUTO_INCREMENT for table `logs`
--
ALTER TABLE `logs`
  MODIFY `logID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=267;

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `qID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `result`
--
ALTER TABLE `result`
  MODIFY `rs_num` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `test`
--
ALTER TABLE `test`
  MODIFY `testID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `topics`
--
ALTER TABLE `topics`
  MODIFY `tpID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=104;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answers`
--
ALTER TABLE `answers`
  ADD CONSTRAINT `answers_ibfk_1` FOREIGN KEY (`qID`) REFERENCES `questions` (`qID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `logs`
--
ALTER TABLE `logs`
  ADD CONSTRAINT `logs_ibfk_1` FOREIGN KEY (`logUserID`) REFERENCES `users` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`qTopicID`) REFERENCES `topics` (`tpID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `result`
--
ALTER TABLE `result`
  ADD CONSTRAINT `result_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `test`
--
ALTER TABLE `test`
  ADD CONSTRAINT `test_ibfk_1` FOREIGN KEY (`tpID`) REFERENCES `topics` (`tpID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

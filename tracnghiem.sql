-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 28, 2025 at 11:24 AM
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
(8, 4, '4', '', 1, 1),
(9, 4, '5', '', 0, 1),
(10, 4, '6', '', 0, 1),
(11, 5, '3.14159', '', 1, 1),
(12, 5, '3.5', '', 0, 1),
(13, 5, '2.718', '', 0, 1),
(14, 6, '7', '', 1, 1),
(15, 6, '6', '', 0, 1),
(16, 6, '8', '', 0, 1),
(17, 7, '180 độ', '', 1, 1),
(18, 7, '90 độ', '', 0, 1),
(19, 7, '360 độ', '', 0, 1),
(20, 8, '120', '', 1, 1),
(21, 8, '60', '', 0, 1),
(22, 8, '24', '', 0, 1),
(23, 9, '19', '', 1, 1),
(24, 9, '17', '', 0, 1),
(25, 9, '15', '', 0, 1),
(26, 10, '27', '', 1, 1),
(27, 10, '9', '', 0, 1),
(28, 10, '18', '', 0, 1),
(29, 11, '2πr', '', 1, 1),
(30, 11, 'πr^2', '', 0, 1),
(31, 11, 'd^2', '', 0, 1),
(32, 12, '0', '', 1, 1),
(33, 12, '1', '', 0, 1),
(34, 12, 'Vô hạn', '', 0, 1),
(35, 13, '55', '', 1, 1),
(36, 13, '34', '', 0, 1),
(37, 13, '89', '', 0, 1),
(38, 14, 'x = 2 hoặc x = 3', '', 1, 1),
(39, 14, 'x = 1 hoặc x = 6', '', 0, 1),
(40, 14, 'x = 3 hoặc x = 4', '', 0, 1),
(41, 15, 'Hình tròn', '', 1, 1),
(42, 15, 'Hình vuông', '', 0, 1),
(43, 15, 'Hình tam giác đều', '', 0, 1),
(44, 1, '2', '', 1, 1),
(45, 1, '1', '', 0, 1),
(46, 1, '3', '', 0, 1),
(47, 1, '4', '', 0, 1),
(48, 2, '299,792 km/s', '', 0, 1),
(49, 2, '300,000 km/s', '', 0, 1),
(50, 3, 'H2O', '', 0, 1),
(51, 3, 'NaOH', '', 0, 1),
(52, 4, '3', '', 0, 1),
(53, 5, '4.14', '', 0, 1),
(54, 6, '9', '', 0, 1),
(55, 7, '270 độ', '', 0, 1),
(56, 8, '720', '', 0, 1),
(57, 9, '13', '', 0, 1),
(58, 10, '36', '', 0, 1),
(59, 11, 'πd', '', 0, 1),
(60, 12, 'Không xác định', '', 0, 1),
(61, 13, '21', '', 0, 1),
(62, 14, 'x = 1 hoặc x = 2', '', 0, 1),
(63, 15, 'Hình chữ nhật', '', 0, 1);

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
('TST001', 'A', 'TST001A', '[1,2,3,4,5,6,7,8,9,10]'),
('TST001', 'B', 'TST001B', '[11,12,13,14,15,16,17,18,19,20]'),
('TST002', 'A', 'TST002A', '[21,22,23,24,25,26,27,28,29,30]');

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
(4, '2 + 2 bằng bao nhiêu?', '', 1, 'easy', 1),
(5, 'Giá trị của π (pi) xấp xỉ bằng bao nhiêu?', '', 1, 'easy', 1),
(6, 'Căn bậc hai của 49 là bao nhiêu?', '', 1, 'easy', 1),
(7, 'Tổng các góc trong tam giác bằng bao nhiêu độ?', '', 1, 'easy', 1),
(8, '5! (5 giai thừa) bằng bao nhiêu?', '', 1, 'medium', 1),
(9, 'Số nguyên tố lớn nhất nhỏ hơn 20 là số nào?', '', 1, 'medium', 1),
(10, 'Giá trị của biểu thức 3^3 là bao nhiêu?', '', 1, 'medium', 1),
(11, 'Chu vi của hình tròn có bán kính r được tính bằng công thức nào?', '', 1, 'medium', 1),
(12, 'Tích vô hướng của hai vectơ vuông góc bằng bao nhiêu?', '', 1, 'diff', 1),
(13, 'Số Fibonacci thứ 10 là số nào?', '', 1, 'diff', 1),
(14, 'Giải phương trình x^2 - 5x + 6 = 0', '', 1, 'diff', 1),
(15, 'Hình nào có diện tích lớn nhất với cùng chu vi: hình vuông, hình tròn, hình tam giác đều?', '', 1, 'diff', 1);

-- --------------------------------------------------------

--
-- Table structure for table `result`
--

CREATE TABLE `result` (
  `rs_num` tinyint(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `exCode` varchar(20) NOT NULL,
  `rs_anwsers` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'các đáp án đã chọn' CHECK (json_valid(`rs_anwsers`)),
  `rs_mark` decimal(10,0) NOT NULL,
  `rs_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `result`
--

INSERT INTO `result` (`rs_num`, `userID`, `exCode`, `rs_anwsers`, `rs_mark`, `rs_date`) VALUES
(1, 101, 'TST001A', '[1,0,1,1,0,1,1,1,0,1]', 8, '2025-02-13 10:30:00'),
(2, 102, 'TST001B', '[1,1,1,1,1,0,0,1,1,0]', 7, '2025-02-13 11:00:00'),
(3, 103, 'TST002A', '[0,1,1,1,1,1,0,1,0,1]', 9, '2025-02-14 09:00:00');

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
(1, 'TST001', 'Bài kiểm tra Toán', 60, 1, 5, 3, 2, 3, '2025-02-13', 1),
(2, 'TST002', 'Bài kiểm tra Lý', 45, 2, 4, 4, 2, 2, '2025-02-14', 1);

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
(3, 'Hóa', 0, 1);

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
(1, 'admin', 'asd@gmail.com', '123456', 'Administrator', 1, 1),
(2, 'user1', 'user1@example.com', '11111111', 'User One', 0, 0),
(3, 'user2', 'user2@example.com', '11111111', 'assdasd', 0, 0),
(101, 'Nguyen Van A', 'a@example.com', '123123', 'aaaa', 0, 1),
(102, 'Tran Thi B', 'b@example.com', '123123', 'bbbb', 0, 1),
(103, 'Le Van C', 'c@example.com', '123123', 'cccc', 0, 1),
(104, 'hello1', 'a1@gmail.com', '123456', 'đây là 1', 0, 1),
(105, 'hello2', 'a2@gmail.com', '123457', '', 0, 1),
(106, '123', 'a3@gmail.com', '123458', 'đây là 3', 0, 1),
(107, 'hello4', 'a4@gmail.com', '123459', 'đây là 4', 0, 1),
(108, 'hello7', 'a9@gmail.com', '123457', 'đây là 7', 0, 1);

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
  ADD PRIMARY KEY (`testCode`,`exOrder`),
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
  ADD PRIMARY KEY (`rs_num`,`userID`,`exCode`),
  ADD KEY `userID` (`userID`),
  ADD KEY `exID` (`exCode`),
  ADD KEY `exCode` (`exCode`);

--
-- Indexes for table `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`testID`,`tpID`) USING BTREE,
  ADD KEY `tpID` (`tpID`),
  ADD KEY `testCode` (`testCode`);

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
  MODIFY `awID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT for table `logs`
--
ALTER TABLE `logs`
  MODIFY `logID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `qID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `test`
--
ALTER TABLE `test`
  MODIFY `testID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `topics`
--
ALTER TABLE `topics`
  MODIFY `tpID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=109;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `answers`
--
ALTER TABLE `answers`
  ADD CONSTRAINT `answers_ibfk_1` FOREIGN KEY (`qID`) REFERENCES `questions` (`qID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `exams`
--
ALTER TABLE `exams`
  ADD CONSTRAINT `exams_ibfk_1` FOREIGN KEY (`exCode`) REFERENCES `result` (`exCode`) ON DELETE NO ACTION ON UPDATE NO ACTION;

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
  ADD CONSTRAINT `test_ibfk_1` FOREIGN KEY (`tpID`) REFERENCES `topics` (`tpID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `test_ibfk_2` FOREIGN KEY (`testCode`) REFERENCES `exams` (`testCode`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

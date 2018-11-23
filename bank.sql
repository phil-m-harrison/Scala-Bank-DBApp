-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 22, 2018 at 05:24 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bank`
--

-- --------------------------------------------------------

--
-- Table structure for table `deposit`
--

CREATE TABLE `deposit` (
  `RegNo` int(11) NOT NULL,
  `Amount` int(11) NOT NULL,
  `Date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `deposit`
--

INSERT INTO `deposit` (`RegNo`, `Amount`, `Date`) VALUES
(4, 100, '2018-11-21'),
(6, 300, '2018-11-21'),
(3, 2400, '2018-11-21'),
(4, 400, '2018-11-21'),
(1, 2000, '2018-11-21'),
(1, 100, '2018-11-21'),
(1, 1, '2018-11-21'),
(1, 1, '2018-11-21'),
(6, 100, '2018-11-21'),
(6, 50, '2018-11-21'),
(6, 50, '2018-11-21'),
(14, 500, '2018-11-21'),
(14, 200, '2018-11-21'),
(14, 200, '2018-11-21'),
(16, 200, '2018-11-22'),
(1, 100, '2018-11-22'),
(1, 100, '2018-11-22'),
(1, 300, '2018-11-22'),
(5, 600, '2018-11-22');

-- --------------------------------------------------------

--
-- Table structure for table `personal`
--

CREATE TABLE `personal` (
  `RegNo` int(11) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `Address` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `personal`
--

INSERT INTO `personal` (`RegNo`, `Name`, `Address`) VALUES
(1, 'Phil', 'Preston'),
(3, 'John', 'Manchester'),
(4, 'Debbie', 'London'),
(5, 'Tracy', 'Derby'),
(6, 'Greg', 'Formby'),
(7, 'Yuri', 'Norwich'),
(8, 'Tom', 'Lancaster'),
(14, 'Dan', 'Wirral'),
(15, 'Hal', 'Blackburn'),
(16, 'Harry', 'Leyland'),
(17, 'Ryan', 'Preston'),
(18, 'Tim', 'Slough'),
(19, 'June', 'Doncaster');

-- --------------------------------------------------------

--
-- Table structure for table `withdraw`
--

CREATE TABLE `withdraw` (
  `RegNo` int(11) NOT NULL,
  `Amount` int(11) NOT NULL,
  `Date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `withdraw`
--

INSERT INTO `withdraw` (`RegNo`, `Amount`, `Date`) VALUES
(1, 500, '2018-11-21'),
(3, 500, '2018-11-21'),
(4, 200, '2018-11-21'),
(5, 200, '2018-11-21'),
(3, 200, '2018-11-21'),
(3, 500, '2018-11-21'),
(3, 400, '2018-11-21'),
(3, 100, '2018-11-21'),
(6, 350, '2018-11-21'),
(6, 120, '2018-11-21'),
(14, 300, '2018-11-21'),
(1, 100, '2018-11-22'),
(1, 1500, '2018-11-22'),
(1, 150, '2018-11-22');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `personal`
--
ALTER TABLE `personal`
  ADD PRIMARY KEY (`RegNo`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `personal`
--
ALTER TABLE `personal`
  MODIFY `RegNo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `deposit`
--
ALTER TABLE `deposit`
  ADD CONSTRAINT `deposit_ibfk_1` FOREIGN KEY (`RegNo`) REFERENCES `personal` (`RegNo`);

--
-- Constraints for table `withdraw`
--
ALTER TABLE `withdraw`
  ADD CONSTRAINT `withdraw_ibfk_1` FOREIGN KEY (`RegNo`) REFERENCES `personal` (`RegNo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

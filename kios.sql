-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Feb 14, 2017 at 07:09 PM
-- Server version: 5.7.15
-- PHP Version: 5.6.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `new`
--

-- --------------------------------------------------------

--
-- Table structure for table `contract`
--

CREATE TABLE `contract` (
  `ID_contract` int(10) NOT NULL,
  `fk_ID_researcher` varchar(10) NOT NULL,
  `fk_ID_project` int(10) NOT NULL,
  `Position` varchar(15) NOT NULL,
  `Details` varchar(40) DEFAULT NULL,
  `Date_From` date NOT NULL,
  `Date_To` date NOT NULL,
  `Salary` float(10,2) NOT NULL,
  `Monthly_Hours` float(10,2) DEFAULT NULL,
  `Hourly_Rate` float(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `contract`
--

INSERT INTO `contract` (`ID_contract`, `fk_ID_researcher`, `fk_ID_project`, `Position`, `Details`, `Date_From`, `Date_To`, `Salary`, `Monthly_Hours`, `Hourly_Rate`) VALUES
(1, '1', 1, 'Electrician', '', '2018-01-23', '2018-01-23', 90000.00, 150.00, 5.00),
(2, '1', 1, 'Developer', '', '2017-01-01', '2017-01-01', 9000.00, 150.00, 5.00),
(3, '1', 1, 'Developer', '', '2017-01-09', '2017-01-21', 9000.00, 150.00, 5.00),
(4, '5', 1, 'Electrician', '', '2019-01-29', '2020-03-29', 5000.00, 150.00, 5.00);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `UserName` varchar(10) NOT NULL,
  `Password` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`UserName`, `Password`) VALUES
('chris', '123456'),
('christos', '123456'),
('kakos', 'MTIzNDU2');

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `Project_ID` int(10) NOT NULL,
  `Project_Name` varchar(45) NOT NULL,
  `Project_super` int(3) NOT NULL,
  `Details` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`Project_ID`, `Project_Name`, `Project_super`, `Details`) VALUES
(1, 'Dynamic Systems', 1, NULL),
(2, 'Android Systems', 1, 'NA'),
(3, 'Networks', 1, NULL),
(4, 'Computer Security', 1, NULL),
(5, 'Signals', 1, NULL),
(6, 'Security', 1, 'NA');

-- --------------------------------------------------------

--
-- Table structure for table `researchers`
--

CREATE TABLE `researchers` (
  `ID` varchar(10) NOT NULL,
  `FullName` varchar(45) NOT NULL,
  `fk_Supervisor` int(3) NOT NULL,
  `Address` varchar(45) DEFAULT NULL,
  `Telephone` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `AccessToKios` tinyint(1) DEFAULT NULL,
  `OfficeNumber` varchar(45) DEFAULT NULL,
  `Active` tinyint(1) DEFAULT NULL,
  `Details` varchar(40) DEFAULT NULL,
  `supervisor2` int(3) DEFAULT NULL,
  `Equipment` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `researchers`
--

INSERT INTO `researchers` (`ID`, `FullName`, `fk_Supervisor`, `Address`, `Telephone`, `Email`, `AccessToKios`, `OfficeNumber`, `Active`, `Details`, `supervisor2`, `Equipment`) VALUES
('1', 'Andrew', 1, 'Evripidi 5', '', '', NULL, NULL, 1, NULL, NULL, 'iPad'),
('10', 'Kar', 4, NULL, NULL, NULL, 0, NULL, 0, NULL, NULL, NULL),
('2', 'Rafail Rafail', 1, 'Anrew', NULL, NULL, NULL, NULL, 1, NULL, NULL, 'Computer'),
('3', 'John Cena', 1, 'Kostas', NULL, NULL, NULL, NULL, 0, NULL, NULL, 'Circuits'),
('5', 'Andreas Andreou', 1, 'Andrew', NULL, NULL, NULL, NULL, 1, NULL, NULL, 'iMac'),
('6', 'Christos Christou', 1, 'Andrew', NULL, NULL, NULL, NULL, 1, NULL, 3, 'Macbook'),
('7', 'African', 4, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `supervisors`
--

CREATE TABLE `supervisors` (
  `ID` int(3) NOT NULL,
  `FullName` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `supervisors`
--

INSERT INTO `supervisors` (`ID`, `FullName`) VALUES
(1, 'Marios Polycarpou'),
(2, 'Christos Panayiotou'),
(3, 'George Ellinas'),
(4, 'Elias Kyriakides'),
(5, 'Maria Michael'),
(6, 'Constantinos Pitris'),
(7, 'Theocharis Theocharidis'),
(8, 'Georgios Mitsis');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(8) NOT NULL,
  `name` varchar(30) NOT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(40) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`) VALUES
(1, 'Maria Ioannou', 'Ioannou@hotmail.com', '25d55ad283aa400af464c76d713c07ad'),
(2, 'Christos', 'Christosvevo@gmail.com', '3462fa234800ce2deb9514c5c4ddfec9');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `contract`
--
ALTER TABLE `contract`
  ADD PRIMARY KEY (`ID_contract`),
  ADD KEY `ID_idx` (`fk_ID_researcher`),
  ADD KEY `ID_Project_idx` (`fk_ID_project`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`UserName`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`Project_ID`),
  ADD UNIQUE KEY `Project_ID_UNIQUE` (`Project_ID`),
  ADD KEY `super_idx` (`Project_super`);

--
-- Indexes for table `researchers`
--
ALTER TABLE `researchers`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `super2_idx` (`supervisor2`),
  ADD KEY `supervisors_idx` (`fk_Supervisor`);

--
-- Indexes for table `supervisors`
--
ALTER TABLE `supervisors`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `contract`
--
ALTER TABLE `contract`
  MODIFY `ID_contract` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `Project_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `supervisors`
--
ALTER TABLE `supervisors`
  MODIFY `ID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `contract`
--
ALTER TABLE `contract`
  ADD CONSTRAINT `ID` FOREIGN KEY (`fk_ID_researcher`) REFERENCES `researchers` (`ID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `ID_Project` FOREIGN KEY (`fk_ID_project`) REFERENCES `project` (`Project_ID`) ON UPDATE CASCADE;

--
-- Constraints for table `project`
--
ALTER TABLE `project`
  ADD CONSTRAINT `super` FOREIGN KEY (`Project_super`) REFERENCES `supervisors` (`ID`) ON UPDATE CASCADE;

--
-- Constraints for table `researchers`
--
ALTER TABLE `researchers`
  ADD CONSTRAINT `super2` FOREIGN KEY (`supervisor2`) REFERENCES `supervisors` (`ID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `supervisors` FOREIGN KEY (`fk_Supervisor`) REFERENCES `supervisors` (`ID`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

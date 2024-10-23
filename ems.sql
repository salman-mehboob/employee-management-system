-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 12, 2021 at 10:43 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ems`
--

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` bigint(20) NOT NULL,
  `date_created` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `parent` bigint(20) DEFAULT NULL,
  `text` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL,
  `module_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`id`, `name`) VALUES
(1, 'JAVA TEAM'),
(2, 'IT');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL,
  `dob` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `address` varchar(200) COLLATE utf8mb4_bin NOT NULL,
  `city` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `cnic` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `date_created` varchar(250) COLLATE utf8mb4_bin DEFAULT NULL,
  `designation` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `first_name` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `gender` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `last_name` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `line_2` varchar(200) COLLATE utf8mb4_bin NOT NULL,
  `linkedin` varchar(250) COLLATE utf8mb4_bin DEFAULT NULL,
  `middle_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `personal_email` varchar(250) COLLATE utf8mb4_bin DEFAULT NULL,
  `photo` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `primary_contact_number` varchar(15) COLLATE utf8mb4_bin NOT NULL,
  `secondry_contact_number` varchar(15) COLLATE utf8mb4_bin NOT NULL,
  `skype` varchar(250) COLLATE utf8mb4_bin DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `work_email` varchar(250) COLLATE utf8mb4_bin DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `dob`, `address`, `city`, `cnic`, `date_created`, `designation`, `first_name`, `gender`, `last_name`, `line_2`, `linkedin`, `middle_name`, `personal_email`, `photo`, `primary_contact_number`, `secondry_contact_number`, `skype`, `status`, `work_email`, `department_id`) VALUES
(1, '1999-12-14', '111 silverdale ave L13 7EZ', 'Liverpool', '3230414560863', '18/02/21 17:21:20', 'Java Develop', 'Salman', 'Male', 'Mehboob ', '', 'salman01', '', 'Salmanmehboob60@gmail.com', NULL, '07447273134', '07447273134', 'salman01', 0, 'Salmanmehboob60@gmail.com', 1);

-- --------------------------------------------------------

--
-- Table structure for table `employee_bills_claim`
--

CREATE TABLE `employee_bills_claim` (
  `id` bigint(20) NOT NULL,
  `amount` double DEFAULT NULL,
  `approved_amount` double DEFAULT NULL,
  `approved_by` bigint(20) DEFAULT NULL,
  `bill_frequency` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `bill_type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `date_created` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Table structure for table `employee_dependants`
--

CREATE TABLE `employee_dependants` (
  `id` bigint(20) NOT NULL,
  `dob` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `date_created` varchar(250) COLLATE utf8mb4_bin DEFAULT NULL,
  `relation` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `first_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `last_name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Table structure for table `employee_leaves`
--

CREATE TABLE `employee_leaves` (
  `id` bigint(20) NOT NULL,
  `approved_by` bigint(20) DEFAULT NULL,
  `date_created` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `date_from` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `date_to` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL,
  `leaves_categories_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Table structure for table `employee_tasks`
--

CREATE TABLE `employee_tasks` (
  `id` bigint(20) NOT NULL,
  `date_created` date DEFAULT NULL,
  `date_due` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `date_submitted` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `description` varchar(300) COLLATE utf8mb4_bin DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Table structure for table `employee_web_histroy`
--

CREATE TABLE `employee_web_histroy` (
  `id` bigint(20) NOT NULL,
  `date_created` varchar(250) COLLATE utf8mb4_bin DEFAULT NULL,
  `title` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `employee_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Table structure for table `files`
--

CREATE TABLE `files` (
  `id` bigint(20) NOT NULL,
  `file` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `parent` bigint(20) DEFAULT NULL,
  `employee_id` bigint(20) DEFAULT NULL,
  `module_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- --------------------------------------------------------

--
-- Table structure for table `leaves_categories`
--

CREATE TABLE `leaves_categories` (
  `id` bigint(20) NOT NULL,
  `leave_type` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `quota` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `leaves_categories`
--

INSERT INTO `leaves_categories` (`id`, `leave_type`, `quota`) VALUES
(1, 'Sick Leave', 'two days'),
(2, 'Vacation', 'One Week'),
(3, 'Monthly', 'two days'),
(4, 'Yearly', 'Three Weeks');

-- --------------------------------------------------------

--
-- Table structure for table `module`
--

CREATE TABLE `module` (
  `id` bigint(20) NOT NULL,
  `parent` bigint(20) DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `module`
--

INSERT INTO `module` (`id`, `parent`, `title`) VALUES
(1, NULL, 'leaveClaim'),
(2, NULL, 'billClaim'),
(3, NULL, 'taskAssign');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `role` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `role`) VALUES
(1, 'ADMIN'),
(2, 'USER');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `passowrd` varchar(60) COLLATE utf8mb4_bin NOT NULL,
  `role_name` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `approval_status` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `username` varchar(60) COLLATE utf8mb4_bin NOT NULL,
  `employee_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `active`, `passowrd`, `role_name`, `approval_status`, `username`, `employee_id`) VALUES
(1, b'1', '$2a$10$PU8dNOsB2ptigfXDI3q6gOCcokV0Gi2z5l8f7LWaIqxvHNeS8JTQq', 'ADMIN', 'APPROVED', 'admin', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`id`, `role_id`) VALUES
(1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1qrvmgs8ta8vwp71csfwv3hb5` (`employee_id`),
  ADD KEY `FKk47lanhooqnidl8bw9cmfm4gw` (`module_id`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKbejtwvg9bxus2mffsm3swj3u9` (`department_id`);

--
-- Indexes for table `employee_bills_claim`
--
ALTER TABLE `employee_bills_claim`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKr4h6jp9vcqhqjc8c0q9a33xru` (`employee_id`);

--
-- Indexes for table `employee_dependants`
--
ALTER TABLE `employee_dependants`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5ncy6jt8j2jvcxxqfbo3r5w4v` (`employee_id`);

--
-- Indexes for table `employee_leaves`
--
ALTER TABLE `employee_leaves`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK2ffsdue8gwh38m9cqb92ijg38` (`employee_id`),
  ADD KEY `FKs4683mfepyi338vflewfkvfk3` (`leaves_categories_id`);

--
-- Indexes for table `employee_tasks`
--
ALTER TABLE `employee_tasks`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKm45tknfa92l9j9fa4ijfpaiqt` (`employee_id`);

--
-- Indexes for table `employee_web_histroy`
--
ALTER TABLE `employee_web_histroy`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKflihvl6oes45fjuc72xehyc1e` (`employee_id`);

--
-- Indexes for table `files`
--
ALTER TABLE `files`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKs2f5vtxqffvdol0ovyq6r24ok` (`employee_id`),
  ADD KEY `FKot4lrrth9jw2vrv8eqpx9vx4d` (`module_id`);

--
-- Indexes for table `leaves_categories`
--
ALTER TABLE `leaves_categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK211dk0pe7l3aibwce8yy61ota` (`employee_id`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`id`,`role_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `employee_bills_claim`
--
ALTER TABLE `employee_bills_claim`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `employee_dependants`
--
ALTER TABLE `employee_dependants`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `employee_leaves`
--
ALTER TABLE `employee_leaves`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `employee_tasks`
--
ALTER TABLE `employee_tasks`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `employee_web_histroy`
--
ALTER TABLE `employee_web_histroy`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `files`
--
ALTER TABLE `files`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `leaves_categories`
--
ALTER TABLE `leaves_categories`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `module`
--
ALTER TABLE `module`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

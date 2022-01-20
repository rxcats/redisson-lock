/*
SQLyog Ultimate v13.1.8 (64 bit)
MySQL - 5.7.36-39 : Database - game_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`game_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `game_db`;

/*Table structure for table `tournament_group` */

DROP TABLE IF EXISTS `tournament_group`;

CREATE TABLE `tournament_group` (
  `tid` bigint(20) NOT NULL DEFAULT '0',
  `group_id` int(11) NOT NULL DEFAULT '0',
  `user_count` int(11) NOT NULL DEFAULT '0',
  `total_user_count` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `tournament_group_member` */

DROP TABLE IF EXISTS `tournament_group_member`;

CREATE TABLE `tournament_group_member` (
  `tid` bigint(20) NOT NULL DEFAULT '0',
  `uid` bigint(20) NOT NULL DEFAULT '0',
  `group_id` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`tid`,`uid`,`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` bigint(20) NOT NULL DEFAULT '0',
  `platform_uid` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `platform_code` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `nickname` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `user_pic` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `user_lv` int(11) NOT NULL DEFAULT '0',
  `user_state` varchar(2) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `login_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`uid`),
  KEY `i_platform_uid` (`platform_uid`),
  KEY `i_nickname` (`nickname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

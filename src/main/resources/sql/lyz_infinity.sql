-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.32 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  8.1.0.4545
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
drop database if exists lyz_infinity;
create database lyz_infinity;
use lyz_infinity;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 lyz_infinity.class 结构
CREATE TABLE IF NOT EXISTS `class` (
  `class_id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 正在导出表  lyz_infinity.class 的数据：~0 rows (大约)
DELETE FROM `class`;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
/*!40000 ALTER TABLE `class` ENABLE KEYS */;


-- 导出  表 lyz_infinity.domain 结构
CREATE TABLE IF NOT EXISTS `domain` (
  `domain_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `domain_name` varchar(256) DEFAULT NULL,
  `naming_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`domain_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 正在导出表  lyz_infinity.domain 的数据：~0 rows (大约)
DELETE FROM `domain`;
/*!40000 ALTER TABLE `domain` DISABLE KEYS */;
/*!40000 ALTER TABLE `domain` ENABLE KEYS */;


-- 导出  表 lyz_infinity.domain_fields 结构
CREATE TABLE IF NOT EXISTS `domain_fields` (
  `domain_field_id` bigint(20) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `domain_id` bigint(20) DEFAULT NULL,
  `domain_field_name` varchar(256) DEFAULT NULL,
  `domain_field_type` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`domain_field_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 正在导出表  lyz_infinity.domain_fields 的数据：~0 rows (大约)
DELETE FROM `domain_fields`;
/*!40000 ALTER TABLE `domain_fields` DISABLE KEYS */;
/*!40000 ALTER TABLE `domain_fields` ENABLE KEYS */;


-- 导出  表 lyz_infinity.methods 结构
CREATE TABLE IF NOT EXISTS `methods` (
  `method_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `method_standard_name` varchar(256) DEFAULT NULL,
  `method_content` text,
  `method_token` text,
  `method_comment` text,
  `naming_id` bigint(20) DEFAULT NULL,
  `technical_stack` varchar(256) DEFAULT NULL,
  `language` varchar(256) DEFAULT NULL,
  `return_value` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`method_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- 正在导出表  lyz_infinity.methods 的数据：0 rows
DELETE FROM `methods`;
/*!40000 ALTER TABLE `methods` DISABLE KEYS */;
/*!40000 ALTER TABLE `methods` ENABLE KEYS */;


-- 导出  表 lyz_infinity.method_signature 结构
CREATE TABLE IF NOT EXISTS `method_signature` (
  `method_signature_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `method_id` bigint(20) DEFAULT NULL,
  `signature_type` varchar(256) DEFAULT NULL,
  `signature_name` varchar(256) DEFAULT NULL,
  `signature_position` int(10) DEFAULT NULL,
  PRIMARY KEY (`method_signature_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- 正在导出表  lyz_infinity.method_signature 的数据：0 rows
DELETE FROM `method_signature`;
/*!40000 ALTER TABLE `method_signature` DISABLE KEYS */;
/*!40000 ALTER TABLE `method_signature` ENABLE KEYS */;


-- 导出  表 lyz_infinity.naming 结构
CREATE TABLE IF NOT EXISTS `naming` (
  `naming_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `domain` varchar(256) DEFAULT NULL,
  `subdomain` varchar(256) DEFAULT NULL,
  `childdomain` varchar(256) DEFAULT NULL,
  `token` text,
  `comment` double DEFAULT NULL,
  PRIMARY KEY (`naming_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- 正在导出表  lyz_infinity.naming 的数据：0 rows
DELETE FROM `naming`;
/*!40000 ALTER TABLE `naming` DISABLE KEYS */;
/*!40000 ALTER TABLE `naming` ENABLE KEYS */;


-- 导出  表 lyz_infinity.prism 结构
CREATE TABLE IF NOT EXISTS `prism` (
  `prism_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `prism_name` varchar(256) DEFAULT NULL,
  `naming_id` varchar(256) DEFAULT NULL,
  `prism_token` text,
  `prism_comment` text NOT NULL,
  `domain_id` bigint(20) DEFAULT NULL,
  `dao_id` bigint(20) DEFAULT NULL,
  `daoimpl_id` bigint(20) DEFAULT NULL,
  `service_id` bigint(20) DEFAULT NULL,
  `serviceimpl_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`prism_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 正在导出表  lyz_infinity.prism 的数据：~0 rows (大约)
DELETE FROM `prism`;
/*!40000 ALTER TABLE `prism` DISABLE KEYS */;
/*!40000 ALTER TABLE `prism` ENABLE KEYS */;


-- 导出  表 lyz_infinity.token 结构
CREATE TABLE IF NOT EXISTS `token` (
  `token_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `token_name` varchar(256) DEFAULT NULL,
  `token_content` text,
  `token_comment` text,
  PRIMARY KEY (`token_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- 正在导出表  lyz_infinity.token 的数据：0 rows
DELETE FROM `token`;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
/*!40000 ALTER TABLE `token` ENABLE KEYS */;


-- 导出  表 lyz_infinity.unittest 结构
CREATE TABLE IF NOT EXISTS `unittest` (
  `unittest_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `unittest_name` varchar(256) DEFAULT NULL,
  `naming_id` varchar(256) DEFAULT NULL,
  `method_id` bigint(20) NOT NULL,
  `content` text,
  `comment` text,
  PRIMARY KEY (`unittest_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- 正在导出表  lyz_infinity.unittest 的数据：0 rows
DELETE FROM `unittest`;
/*!40000 ALTER TABLE `unittest` DISABLE KEYS */;
/*!40000 ALTER TABLE `unittest` ENABLE KEYS */;


-- 导出  表 lyz_infinity.utils 结构
CREATE TABLE IF NOT EXISTS `utils` (
  `util_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `naming_id` bigint(20) NOT NULL DEFAULT '0',
  `standard_name` varchar(256) DEFAULT NULL,
  `content` text,
  `comment` text,
  `token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`util_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- 正在导出表  lyz_infinity.utils 的数据：0 rows
DELETE FROM `utils`;
/*!40000 ALTER TABLE `utils` DISABLE KEYS */;
/*!40000 ALTER TABLE `utils` ENABLE KEYS */;


-- 导出  表 lyz_infinity.vars 结构
CREATE TABLE IF NOT EXISTS `vars` (
  `var_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `var_position` int(11) NOT NULL DEFAULT '0',
  `method_id` bigint(20) NOT NULL DEFAULT '0',
  `var_name` int(10) DEFAULT NULL,
  `var_value` int(10) DEFAULT NULL,
  `var_type` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`var_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- 正在导出表  lyz_infinity.vars 的数据：0 rows
DELETE FROM `vars`;
/*!40000 ALTER TABLE `vars` DISABLE KEYS */;
/*!40000 ALTER TABLE `vars` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

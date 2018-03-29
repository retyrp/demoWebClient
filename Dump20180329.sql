CREATE DATABASE  IF NOT EXISTS `account` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `account`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: account
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class` (
  `SERIALNO` varchar(64) NOT NULL COMMENT '课程号',
  `TEANO` varchar(64) NOT NULL COMMENT '教师ID',
  `UNIVERSITY` varchar(64) NOT NULL COMMENT '学校',
  `SCHOOL` varchar(64) NOT NULL COMMENT '学院',
  `BEGINTIME` varchar(64) NOT NULL COMMENT '上课时间',
  `ENDTIME` varchar(64) NOT NULL COMMENT '下课时间',
  `CLASSROOM` varchar(64) NOT NULL COMMENT '上课地点',
  `STATUSNOW` varchar(64) NOT NULL COMMENT '当前状态',
  `CLASSNAME` varchar(64) NOT NULL COMMENT '课程名称',
  `CLASSKIND` varchar(64) DEFAULT NULL COMMENT '课程种类',
  `OTHER` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`SERIALNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classsel`
--

DROP TABLE IF EXISTS `classsel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classsel` (
  `SERIALNO` varchar(64) NOT NULL COMMENT '序列号',
  `CLASSNO` varchar(64) NOT NULL COMMENT '课程号',
  `STUNO` varchar(64) NOT NULL COMMENT '学号',
  `TEANO` varchar(64) NOT NULL COMMENT '教师ID',
  `UPDATETIME` varchar(64) NOT NULL COMMENT '更新时间',
  `STATUSNOW` varchar(64) NOT NULL COMMENT '当前状态',
  `OTHER` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`SERIALNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='选课表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classsel`
--

LOCK TABLES `classsel` WRITE;
/*!40000 ALTER TABLE `classsel` DISABLE KEYS */;
/*!40000 ALTER TABLE `classsel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventc`
--

DROP TABLE IF EXISTS `eventc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eventc` (
  `SERIALNO` varchar(64) NOT NULL COMMENT '事件序列号',
  `POINTCID` varchar(64) NOT NULL COMMENT '事件创建者ID',
  `POINTNAME` varchar(64) NOT NULL COMMENT '事件名称',
  `POINTKIND` varchar(64) NOT NULL COMMENT '事件种类',
  `POINTBEGIN` varchar(64) NOT NULL COMMENT '开始时间',
  `POINTEND` varchar(64) NOT NULL COMMENT '结束时间',
  `ORGNAZATION` varchar(64) NOT NULL COMMENT '组织',
  `ADDRESS` varchar(64) NOT NULL COMMENT '地点',
  `STATUSNOW` varchar(64) NOT NULL COMMENT '当前状态',
  `OTHER` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`SERIALNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='事件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventc`
--

LOCK TABLES `eventc` WRITE;
/*!40000 ALTER TABLE `eventc` DISABLE KEYS */;
/*!40000 ALTER TABLE `eventc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regclass`
--

DROP TABLE IF EXISTS `regclass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `regclass` (
  `SERIALNO` varchar(64) NOT NULL COMMENT '序列号',
  `POINTNO` varchar(64) NOT NULL COMMENT '事件号',
  `ROLENO` varchar(64) NOT NULL COMMENT '登记角色标志ID',
  `POINTCNO` varchar(64) NOT NULL COMMENT '事件组织者ID',
  `REGTIME` varchar(64) NOT NULL COMMENT '登记时间',
  `STATUSNOW` varchar(64) NOT NULL COMMENT '状态（正常、迟到、病假、事假、缺到）',
  `OTHER` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`SERIALNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登记表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regclass`
--

LOCK TABLES `regclass` WRITE;
/*!40000 ALTER TABLE `regclass` DISABLE KEYS */;
/*!40000 ALTER TABLE `regclass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `UID` varchar(128) NOT NULL,
  `USERNAME` varchar(64) DEFAULT NULL,
  `USERPWD` varchar(64) DEFAULT NULL,
  `USEROLE` varchar(10) DEFAULT NULL,
  `USERINFO` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_info`
--

DROP TABLE IF EXISTS `role_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_info` (
  `SERIALNO` varchar(64) NOT NULL,
  `UPHONE` varchar(64) DEFAULT NULL,
  `UMAIL` varchar(64) DEFAULT NULL,
  `UCLASS` varchar(64) DEFAULT NULL,
  `USCHOOL` varchar(64) DEFAULT NULL,
  `UNIVERSITY` varchar(64) DEFAULT NULL,
  `UROOM` varchar(64) DEFAULT NULL,
  `USEX` varchar(8) DEFAULT NULL,
  `UTX` varchar(64) DEFAULT NULL,
  `UWEIBO` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`SERIALNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_info`
--

LOCK TABLES `role_info` WRITE;
/*!40000 ALTER TABLE `role_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service` (
  `SID` varchar(64) NOT NULL COMMENT '服务名',
  `SComment` varchar(64) NOT NULL COMMENT '服务描述',
  `SUpdate` varchar(64) NOT NULL COMMENT '服务更新时间',
  `SStart` varchar(64) NOT NULL COMMENT '启动时间',
  `SStop` varchar(64) NOT NULL COMMENT '停止时间',
  `SGrade` varchar(64) NOT NULL COMMENT '服务等级（权限）',
  `SAsso` varchar(64) NOT NULL COMMENT '关联服务（需求基础）',
  `SLauncher` varchar(64) NOT NULL COMMENT '启动优先级',
  `SStatus` varchar(64) NOT NULL COMMENT '状态',
  `SBackUp` varchar(64) NOT NULL COMMENT '可代替紧急服务',
  PRIMARY KEY (`SID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务注册表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` varchar(40) NOT NULL,
  `pwd` varchar(40) NOT NULL,
  `username` varchar(20) NOT NULL DEFAULT '同学',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES ('abc','1234','同学2'),('abcd','123','同学2');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemconfig`
--

DROP TABLE IF EXISTS `systemconfig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemconfig` (
  `s_key` varchar(64) NOT NULL COMMENT 'key',
  `s_value` varchar(64) NOT NULL COMMENT 'value',
  PRIMARY KEY (`s_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置映射表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemconfig`
--

LOCK TABLES `systemconfig` WRITE;
/*!40000 ALTER TABLE `systemconfig` DISABLE KEYS */;
INSERT INTO `systemconfig` VALUES ('ConfigUpdate',''),('SystemAdmin','8000014069'),('SystemName','网关服务'),('SystemPrivateKey_A','MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEAq8sfyj7nwldjUaS6'),('SystemPrivateKey_B','JGBjLWaMMgRR5bZF5ron9e87qr3pHV5C+bHPgyidL2RyhhWztyqcR5yfUFkZbQB4'),('SystemPrivateKey_C','gDfDaQIDAQABAkBKxJXqZORjPbtZLjNhX53qhYYgwqaOLn1PxNddfyhdz56az/z+'),('SystemPrivateKey_D','HirZJCmL7ZiqISL/4C44rp+ApCOHBXsbCdqBAiEA91uuLk0wHxih2huyCy1GDnuN'),('SystemPrivateKey_E','Mr1kN2V6+o2uDgQwsZkCIQCxy5vslvAOwHbHq+NnLcklb0iYe0Te73UL1+0hKUti'),('SystemPrivateKey_F','UQIhAMDM2wUH8Pwr7OveLnZ7wWFEeBgWeuia1rI4gHQ4W+PxAiEAhyHc8fZKoLux'),('SystemPrivateKey_G','y9MeNKa9EiHByHswfkz75jOIfMLZE/ECIQDIescsg4JNp3PWNoMR4lOkSUnkLWwm'),('SystemPrivateKey_H','==AK5GGJvdiSdBCq'),('SystemPublicKey_A','MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKvLH8o+58JXY1GkuiRgYy1mjDIEUeW2'),('SystemPublicKey_B','Rea6J/XvO6q96R1eQvmxz4MonS9kcoYVs7cqnEecn1BZGW0AeIA3w2kCAwEAAQ=='),('yes','no');
/*!40000 ALTER TABLE `systemconfig` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uac`
--

DROP TABLE IF EXISTS `uac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uac` (
  `RID` varchar(64) NOT NULL,
  `RNAME` varchar(64) DEFAULT NULL,
  `RKIND` varchar(64) DEFAULT NULL,
  `RBDATE` varchar(64) DEFAULT NULL,
  `RU` varchar(64) DEFAULT NULL,
  `REDATE` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`RID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uac`
--

LOCK TABLES `uac` WRITE;
/*!40000 ALTER TABLE `uac` DISABLE KEYS */;
/*!40000 ALTER TABLE `uac` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-29 19:45:35

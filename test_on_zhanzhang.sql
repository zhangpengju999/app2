-- MySQL dump 10.13  Distrib 5.7.13, for osx10.10 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.7.13

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
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorities` (
  `user_id` int(10) unsigned NOT NULL,
  `authority` varchar(36) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`user_id`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (1,'ROLE_ADMIN'),(1,'ROLE_DATAER'),(1,'ROLE_MANAGER'),(1,'ROLE_USER'),(2,'ROLE_USER'),(3,'ROLE_DATAER'),(3,'ROLE_USER'),(4,'ROLE_USER'),(5,'ROLE_USER');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emp`
--

DROP TABLE IF EXISTS `emp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emp` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emp`
--

LOCK TABLES `emp` WRITE;
/*!40000 ALTER TABLE `emp` DISABLE KEYS */;
/*!40000 ALTER TABLE `emp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(256) DEFAULT NULL,
  `articel` text,
  `creator_id` int(10) unsigned DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `is_top` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `creator_id` (`creator_id`),
  CONSTRAINT `notice_ibfk_1` FOREIGN KEY (`creator_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settle`
--

DROP TABLE IF EXISTS `settle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `settle` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `settle_name` varchar(100) NOT NULL,
  `settle_date` int(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `settle_name` (`settle_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settle`
--

LOCK TABLES `settle` WRITE;
/*!40000 ALTER TABLE `settle` DISABLE KEYS */;
INSERT INTO `settle` VALUES (1,'日结',1),(2,'周结',7),(3,'月结',30);
/*!40000 ALTER TABLE `settle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settle_money`
--

DROP TABLE IF EXISTS `settle_money`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `settle_money` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `value_item_id` int(10) unsigned DEFAULT NULL,
  `settle_date` date DEFAULT NULL,
  `purchase_price` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `value_item_id_fk` (`value_item_id`),
  CONSTRAINT `value_item_id_fk` FOREIGN KEY (`value_item_id`) REFERENCES `value_item` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settle_money`
--

LOCK TABLES `settle_money` WRITE;
/*!40000 ALTER TABLE `settle_money` DISABLE KEYS */;
/*!40000 ALTER TABLE `settle_money` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statistic`
--

DROP TABLE IF EXISTS `statistic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `statistic` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `value_item_id` int(10) unsigned DEFAULT NULL,
  `show_count` int(10) unsigned DEFAULT NULL,
  `click_count` int(10) DEFAULT NULL,
  `click_rate` double DEFAULT NULL,
  `CPM` double DEFAULT NULL,
  `income` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `value_item_id_fk1` (`value_item_id`),
  CONSTRAINT `value_item_id_fk1` FOREIGN KEY (`value_item_id`) REFERENCES `value_item` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statistic`
--

LOCK TABLES `statistic` WRITE;
/*!40000 ALTER TABLE `statistic` DISABLE KEYS */;
INSERT INTO `statistic` VALUES (1,8,10000,2000,0.2,20,200,'2017-02-03'),(2,8,10000,2000,0.2,20,200,'2017-02-04');
/*!40000 ALTER TABLE `statistic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `task_name` varchar(100) NOT NULL,
  `value_way_id` int(10) unsigned NOT NULL,
  `show_count` int(10) unsigned DEFAULT NULL,
  `click_count` int(10) unsigned DEFAULT NULL,
  `unit_price` int(10) unsigned DEFAULT NULL,
  `is_public` int(11) DEFAULT NULL,
  `seller` varchar(36) DEFAULT NULL,
  `settle` int(10) unsigned DEFAULT NULL,
  `creater` int(10) unsigned DEFAULT NULL,
  `desc_str` varchar(255) DEFAULT NULL,
  `divide_rate` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `task_name` (`task_name`),
  KEY `task_value_way_fk` (`value_way_id`),
  KEY `settle_id_fk` (`settle`),
  KEY `user_id_fk` (`creater`),
  CONSTRAINT `settle_id_fk` FOREIGN KEY (`settle`) REFERENCES `settle` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `task_value_way_fk` FOREIGN KEY (`value_way_id`) REFERENCES `value_way` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id_fk` FOREIGN KEY (`creater`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,'task1',1,10000,2000,3,1,'seller1',1,1,'testtask',NULL),(2,'test3',2,10001,1111,2,0,'ceshi',2,1,'ceshistr',NULL),(3,'test4',3,10001,1000,2,0,'ceshi',2,1,'ceshistr',NULL),(4,'task5',3,1222,1111,2,0,'ceshi',1,1,'',NULL);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `upload`
--

DROP TABLE IF EXISTS `upload`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `upload` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `value_item_id` int(10) unsigned DEFAULT NULL,
  `begin_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `show_count` int(10) DEFAULT NULL,
  `click_count` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `upload_value_item_id_fk` (`value_item_id`),
  CONSTRAINT `upload_value_item_id_fk` FOREIGN KEY (`value_item_id`) REFERENCES `value_item` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `upload`
--

LOCK TABLES `upload` WRITE;
/*!40000 ALTER TABLE `upload` DISABLE KEYS */;
/*!40000 ALTER TABLE `upload` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `password` varchar(128) DEFAULT NULL,
  `realname` varchar(64) DEFAULT NULL COMMENT '真实姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_ip` varchar(64) DEFAULT NULL COMMENT '创建IP',
  `passed` tinyint(1) DEFAULT NULL COMMENT '是否通过审核：true是，false否，null待审核',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  `phone_num` varchar(32) DEFAULT NULL,
  `bank_card` varchar(64) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `bank` varchar(128) DEFAULT NULL,
  `ispublic` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','$2a$10$lLEHyVY1wgh4MOgafE1Z6.qWBufYEH2RX/JfMM5RjGLGZDqdCZBnu','admin111',NULL,NULL,NULL,1,'13111111111','121212','123@qq.com','中国银行1',0),(2,'manager','$2a$10$Kh8KEaJBdZlVEORqJxu4YO2yE.Io/H.0okWTPffpxxiHmOaoXomfy','manager',NULL,NULL,1,0,NULL,NULL,NULL,NULL,0),(3,'dataer','$2a$10$dDXtezARf90UQVFVWrecoe2lsemygCr66Q9mbvU4knyzzHq5rWmf6','dataer',NULL,NULL,1,1,NULL,NULL,NULL,NULL,0),(4,'qudao1','$2a$10$uE5GYDweDVUjvI5nwZ/wy.ovdjB3lenDLVu6jUefIQAuj00uOtCcC','渠道1',NULL,NULL,1,1,'13111111111','121212','123@qq.com','中国银行',0),(5,'qudaotest','$2a$10$qTckb1VnTiXsgtsWwEUSleCqYqSkg/0VRbr7gModtlBRXkVdBKTfC','qudaotest',NULL,NULL,1,1,'123','','','中国银行',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `value_item`
--

DROP TABLE IF EXISTS `value_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `value_item` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `task_id` int(10) unsigned DEFAULT NULL,
  `channel_id` int(10) unsigned DEFAULT NULL,
  `is_assigned` int(10) DEFAULT NULL,
  `item_name` varchar(20) DEFAULT NULL,
  `on_line_time` date DEFAULT NULL,
  `down_line_time` date DEFAULT NULL,
  `purchase_price` double DEFAULT NULL,
  `desc_str` varchar(20) DEFAULT NULL,
  `site_id` int(10) unsigned DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL COMMENT '状态可分为：未结算 已结算 已支付 冻结 暂停',
  PRIMARY KEY (`id`),
  KEY `task_id_fk` (`task_id`),
  KEY `channel_id_fk` (`channel_id`),
  KEY `fk_site_id` (`site_id`),
  CONSTRAINT `channel_id_fk` FOREIGN KEY (`channel_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_site_id` FOREIGN KEY (`site_id`) REFERENCES `web_site` (`id`),
  CONSTRAINT `task_id_fk` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `value_item`
--

LOCK TABLES `value_item` WRITE;
/*!40000 ALTER TABLE `value_item` DISABLE KEYS */;
INSERT INTO `value_item` VALUES (8,2,1,0,'valueItem212','2017-06-23','2017-07-08',22,'yy',NULL,NULL),(9,2,1,0,'valueItem23','2017-06-01','2017-06-09',22,'yy',NULL,NULL),(10,2,2,0,'valueItem23','2017-06-01','2017-06-01',22,'2017-06-01',NULL,NULL);
/*!40000 ALTER TABLE `value_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `value_way`
--

DROP TABLE IF EXISTS `value_way`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `value_way` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `value_way_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `value_way_name` (`value_way_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `value_way`
--

LOCK TABLES `value_way` WRITE;
/*!40000 ALTER TABLE `value_way` DISABLE KEYS */;
INSERT INTO `value_way` VALUES (2,'cpa'),(3,'cpc'),(1,'cps');
/*!40000 ALTER TABLE `value_way` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_site`
--

DROP TABLE IF EXISTS `web_site`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `web_site` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(10) unsigned DEFAULT NULL,
  `channel_id` int(10) unsigned DEFAULT NULL,
  `web_name` varchar(128) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `url` text,
  `desc_str` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `channel_id_web_fk` (`channel_id`),
  CONSTRAINT `channel_id_web_fk` FOREIGN KEY (`channel_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_site`
--

LOCK TABLES `web_site` WRITE;
/*!40000 ALTER TABLE `web_site` DISABLE KEYS */;
INSERT INTO `web_site` VALUES (1,0,NULL,'url1',NULL,'www.baidu.com','testurl');
/*!40000 ALTER TABLE `web_site` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-14 12:22:46

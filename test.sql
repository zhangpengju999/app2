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
INSERT INTO `authorities` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER'),(3,'ROLE_DATAER'),(3,'ROLE_USER'),(4,'ROLE_USER'),(5,'ROLE_USER'),(7,'ROLE_BOSS'),(10,'ROLE_ADMIN'),(11,'ROLE_CHANNEL'),(12,'ROLE_OPERATOR'),(13,'ROLE_CHANNEL'),(14,'ROLE_CHANNEL'),(15,'ROLE_ADMIN'),(16,'ROLE_ADMIN');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `channel_settle`
--

DROP TABLE IF EXISTS `channel_settle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel_settle` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `value_item_id` int(10) unsigned NOT NULL,
  `show_count` int(10) unsigned DEFAULT NULL COMMENT '展现量',
  `click_count` int(10) DEFAULT '0',
  `click_rate` double DEFAULT NULL COMMENT '点击率',
  `CPM` double DEFAULT NULL COMMENT '千次收入',
  `income` double DEFAULT NULL COMMENT '总收入',
  `year` int(10) DEFAULT NULL COMMENT '数据的年份',
  `month` int(10) DEFAULT NULL COMMENT '数据的月份',
  `create_time` datetime DEFAULT NULL,
  `deduct_rate` double DEFAULT NULL,
  `result_count` int(10) DEFAULT '0' COMMENT 'For CPA and CPS',
  `result_rate` double DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `value_item_id_fk4` (`value_item_id`),
  CONSTRAINT `value_item_id_fk4` FOREIGN KEY (`value_item_id`) REFERENCES `value_item` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `channel_settle`
--

LOCK TABLES `channel_settle` WRITE;
/*!40000 ALTER TABLE `channel_settle` DISABLE KEYS */;
INSERT INTO `channel_settle` VALUES (1,12,10000,2000,0.2,100,160,2017,10,NULL,0.2,0,0);
/*!40000 ALTER TABLE `channel_settle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `channel_statistic`
--

DROP TABLE IF EXISTS `channel_statistic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel_statistic` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `value_item_id` int(10) unsigned DEFAULT NULL,
  `show_count` int(10) unsigned DEFAULT NULL,
  `click_count` int(10) DEFAULT '0',
  `click_rate` double DEFAULT NULL,
  `CPM` double DEFAULT NULL,
  `income` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  `deduct_rate` double DEFAULT NULL COMMENT '扣量比例',
  `result_count` int(10) DEFAULT '0' COMMENT 'For CPA and CPS',
  `result_rate` double DEFAULT '0',
  `purchase_price` double DEFAULT '0',
  `app_name` varchar(50) DEFAULT NULL,
  `adver_name` varchar(50) DEFAULT NULL,
  `fill_rate` double DEFAULT '0',
  `ecmp` double DEFAULT '0',
  `convert_rate` varchar(26) DEFAULT NULL,
  `date_str` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `value_item_id_fk1` (`value_item_id`),
  CONSTRAINT `value_item_id_fk1` FOREIGN KEY (`value_item_id`) REFERENCES `value_item` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `channel_statistic`
--

LOCK TABLES `channel_statistic` WRITE;
/*!40000 ALTER TABLE `channel_statistic` DISABLE KEYS */;
INSERT INTO `channel_statistic` VALUES (4,12,10000,2000,200,100,160,'2017-10-14',0.2,0,0,0,NULL,NULL,0,0,'0%','2017-10-14'),(5,12,10000,2000,0.2,100,160,'2017-10-15',0.2,NULL,0,0,NULL,NULL,0,0,'0%','2017-10-15'),(6,12,10000,2000,0.2,100,160,'2017-10-16',0.2,NULL,0,0,NULL,NULL,0,0,'0%','2017-10-16'),(9,12,NULL,2000,0,0,16.00000023841858,'2017-10-19',0.2,200,0,0,NULL,NULL,0,0,'0%','2017-10-19'),(21,20,10000,2000,0.2,100,160,'2017-11-15',0.2,NULL,0,0.1,'应用1','广告1',0.2,0.2,'0%','2017-11-15'),(23,22,10000,2000,0.2,1000,1600,'2017-11-10',0.2,NULL,0,1,'懒人听书','1',0.25,0.25,'0%','2017-11-10'),(24,22,10000,2000,0.2,1000,1600,'2017-11-11',0.2,NULL,0,1,'懒人听书','1',0.25,0.25,'0%','2017-11-11'),(25,22,10000,2000,0.2,1000,150.5,'2017-11-15',0.2,NULL,0,1,'懒人听书','3',0.25,5.8,'0%','2017-11-15'),(26,22,10000,334,3.34,1000,150.5,'2017-11-18',0.2,NULL,0,1,'懒人听书','3',0.25,5.8,'0%','2017-11-18'),(27,22,10000,313,3.13,1000,150.5,'2017-12-07',0.2,NULL,0,1,'懒人听书','20',25,5.8,'0%','2017-12-07'),(28,22,10000,313,3.13,1000,150.5,'2017-11-21',0.2,NULL,0,1,'懒人听书','4',25,5.8,'0%','2017-11-21'),(29,22,10000,313,3.13,1000,150.5,'2017-11-26',0.2,NULL,0,1,'懒人听书','9',25,5.8,'0%','2017-11-26'),(30,22,10000,313,3.13,1000,150.5,'2017-11-20',0.2,NULL,0,1,'懒人听书','3',25,5.8,'0%','2017-11-20'),(31,22,10000,313,3.13,1000,150.5,'2017-11-22',0.2,NULL,0,1,'懒人听书','5',25,5.8,'0%','2017-11-22'),(32,22,10000,313,3.13,1000,150.5,'2017-11-23',0.2,NULL,0,1,'懒人听书','6',25,5.8,'0%','2017-11-23'),(33,22,10000,313,3.13,1000,150.5,'2017-11-24',0.2,NULL,0,1,'懒人听书','7',25,5.8,'0%','2017-11-24'),(34,22,10000,313,3.13,1000,150.5,'2017-11-25',0.2,NULL,0,1,'懒人听书','8',25,5.8,'0%','2017-11-25'),(42,12,NULL,313,0,0,12.080000180006028,'2017-10-20',0.2,151,48.242811501597444,0.1,NULL,NULL,0,0,'48.24281%','2017-10-20'),(43,12,NULL,313,0,0,12.080000180006028,'2017-10-21',0.2,151,48.242811501597444,0.1,NULL,NULL,0,0,'48.24281%','2017-10-21'),(44,12,NULL,313,0,0,12.080000180006028,'2017-10-22',0.2,151,48.242811501597444,0.1,NULL,NULL,0,0,'48.24281%','2017-10-22'),(45,12,NULL,313,0,0,12.080000180006028,'2017-10-23',0.2,151,48.242811501597444,0.1,NULL,NULL,0,0,'48.24281%','2017-10-23'),(46,12,NULL,313,0,0,12.080000180006028,'2017-10-24',0.2,151,48.242811501597444,0.1,NULL,NULL,0,0,'48.24281%','2017-10-24'),(47,12,NULL,313,0,0,12.080000180006028,'2017-10-25',0.2,151,48.242811501597444,0.1,NULL,NULL,0,0,'48.24281%','2017-10-25'),(48,12,NULL,313,0,0,12.080000180006028,'2017-10-26',0.2,151,48.242811501597444,0.1,NULL,NULL,0,0,'48.24281%','2017-10-26');
/*!40000 ALTER TABLE `channel_statistic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `divide_rate`
--

DROP TABLE IF EXISTS `divide_rate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `divide_rate` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `rate` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `divide_rate`
--

LOCK TABLES `divide_rate` WRITE;
/*!40000 ALTER TABLE `divide_rate` DISABLE KEYS */;
INSERT INTO `divide_rate` VALUES (1,'十成',1),(2,'九成',0.9),(3,'八成',0.8),(4,'七成',0.7),(5,'六成',0.6),(6,'五成',0.5),(7,'四成',0.4),(8,'三成',0.3),(9,'两成',0.2),(10,'一成',0.1);
/*!40000 ALTER TABLE `divide_rate` ENABLE KEYS */;
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
-- Table structure for table `seller`
--

DROP TABLE IF EXISTS `seller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seller` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `info` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seller`
--

LOCK TABLES `seller` WRITE;
/*!40000 ALTER TABLE `seller` DISABLE KEYS */;
INSERT INTO `seller` VALUES (1,'seller1',NULL),(3,'seller3',NULL),(4,'seller2',NULL),(6,'seller4','诛仙');
/*!40000 ALTER TABLE `seller` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seller_settle`
--

DROP TABLE IF EXISTS `seller_settle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seller_settle` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sub_task_id` int(10) unsigned NOT NULL,
  `data_count` double DEFAULT NULL COMMENT 'CPS数据量',
  `show_count` int(10) unsigned DEFAULT NULL COMMENT '展现量',
  `click_count` int(10) DEFAULT '0',
  `click_rate` double DEFAULT NULL COMMENT '点击率',
  `CPM` double DEFAULT NULL COMMENT '千次收入',
  `income` double DEFAULT NULL COMMENT '总收入',
  `year` int(10) DEFAULT NULL COMMENT '数据的年份',
  `month` int(10) DEFAULT NULL COMMENT '数据的月份',
  `create_time` datetime DEFAULT NULL,
  `check_reduce_rate` double DEFAULT NULL,
  `result_count` int(10) DEFAULT '0' COMMENT 'For CPA and CPS',
  `result_rate` double DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `sub_task_id_fk3` (`sub_task_id`),
  CONSTRAINT `sub_task_id_fk3` FOREIGN KEY (`sub_task_id`) REFERENCES `sub_task` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seller_settle`
--

LOCK TABLES `seller_settle` WRITE;
/*!40000 ALTER TABLE `seller_settle` DISABLE KEYS */;
INSERT INTO `seller_settle` VALUES (1,1,0,10000,2000,0.2,2000,3200,2017,10,NULL,0.2,0,0);
/*!40000 ALTER TABLE `seller_settle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seller_statistic`
--

DROP TABLE IF EXISTS `seller_statistic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seller_statistic` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sub_task_id` int(10) unsigned NOT NULL,
  `data_count` double DEFAULT NULL COMMENT 'CPS数据量',
  `show_count` int(10) unsigned DEFAULT NULL COMMENT '展现量',
  `click_count` int(10) DEFAULT '0',
  `click_rate` double DEFAULT NULL COMMENT '点击率',
  `CPM` double DEFAULT NULL COMMENT '千次收入',
  `income` double DEFAULT NULL COMMENT '总收入',
  `check_reduce_rate` double DEFAULT NULL COMMENT '核减比例',
  `date` date DEFAULT NULL COMMENT '数据的日期，为具体某一天',
  `create_time` datetime DEFAULT NULL,
  `result_count` int(10) DEFAULT '0' COMMENT 'For CPA and CPS',
  `result_rate` double DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `sub_task_id_fk2` (`sub_task_id`),
  CONSTRAINT `sub_task_id_fk2` FOREIGN KEY (`sub_task_id`) REFERENCES `sub_task` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seller_statistic`
--

LOCK TABLES `seller_statistic` WRITE;
/*!40000 ALTER TABLE `seller_statistic` DISABLE KEYS */;
INSERT INTO `seller_statistic` VALUES (1,1,0,10000,2000,0.2,2000,3200,0.2,'2017-10-14',NULL,0,0);
/*!40000 ALTER TABLE `seller_statistic` ENABLE KEYS */;
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
-- Table structure for table `sub_task`
--

DROP TABLE IF EXISTS `sub_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_task` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent_id` int(10) unsigned DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `on_line_time` date DEFAULT NULL,
  `down_line_time` date DEFAULT '2030-01-01',
  `url` varchar(255) DEFAULT NULL,
  `operator` int(10) unsigned DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `operator_id_fk` (`operator`),
  KEY `parent_id_fk` (`parent_id`),
  CONSTRAINT `operator_id_fk` FOREIGN KEY (`operator`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `parent_id_fk` FOREIGN KEY (`parent_id`) REFERENCES `task` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_task`
--

LOCK TABLES `sub_task` WRITE;
/*!40000 ALTER TABLE `sub_task` DISABLE KEYS */;
INSERT INTO `sub_task` VALUES (1,11,'subtask1','2017-10-03','2017-10-27','www.baidu.com',11,NULL),(2,10,'web','2017-10-03','2017-10-07','www.baidu.com',12,NULL),(3,12,'subTask4','2017-10-03','2017-10-28','www.baidu.com',12,NULL),(4,11,'任务5','2017-11-01','2017-12-22','www.baidu.com',12,NULL),(5,11,'子任务6','2017-10-30','2017-11-30','www.qq.com',12,NULL),(6,10,'子任务7','2017-10-29','2017-12-01','www.qq.com',12,NULL),(7,11,'子任务8','2017-11-15','2017-11-17','http://weibo.com/ajaxlogin.php?framelogin=1&callback=parent.sinaSSOController.feedBackUrlCallBack',12,NULL),(8,12,'子任务9','2017-11-07','2017-12-01','www.baidu.com',12,NULL),(9,12,'子任务10','2017-10-29','2017-11-23','www.qq.com',12,NULL),(10,14,'子任务11','2017-11-14','2017-12-01','www.baidu.com',12,NULL),(11,10,'e',NULL,NULL,'',12,NULL),(12,17,'subtaskof19','2017-11-01','2017-12-09','',12,NULL),(13,11,'cpctest','2017-11-01','2017-12-09','www.baidu.com',12,NULL),(14,10,'testDown','2017-11-01',NULL,'test',12,NULL);
/*!40000 ALTER TABLE `sub_task` ENABLE KEYS */;
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
  `value_way_id` int(10) unsigned DEFAULT NULL,
  `show_count` int(10) unsigned DEFAULT NULL,
  `click_count` int(10) unsigned DEFAULT NULL,
  `unit_price` double DEFAULT NULL,
  `is_public` int(11) DEFAULT NULL,
  `seller` int(10) unsigned DEFAULT NULL,
  `settle` int(10) unsigned DEFAULT NULL,
  `creater` int(10) unsigned DEFAULT NULL,
  `desc_str` varchar(255) DEFAULT NULL,
  `divide_rate` double DEFAULT '0',
  `system` varchar(64) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `task_name` (`task_name`),
  KEY `task_value_way_fk` (`value_way_id`),
  KEY `settle_id_fk` (`settle`),
  KEY `user_id_fk` (`creater`),
  KEY `fk_seller` (`seller`),
  CONSTRAINT `fk_seller` FOREIGN KEY (`seller`) REFERENCES `seller` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `settle_id_fk` FOREIGN KEY (`settle`) REFERENCES `settle` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `task_value_way_fk` FOREIGN KEY (`value_way_id`) REFERENCES `value_way` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id_fk` FOREIGN KEY (`creater`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (10,'test9',3,1222,1111,2,0,1,1,1,'testurl',0,'ce','2017-09-26 21:07:16'),(11,'test2',3,1222,1111,2,0,4,1,1,'testurl',0,'ce2','2017-09-26 21:07:43'),(12,'test3',1,1222,1111,2,0,4,1,1,'testurl',0,'ce','2017-09-26 21:08:11'),(14,'testdelete',2,10000,2000,2,0,1,1,1,'testurl',0,'ce','2017-10-31 10:18:39'),(15,'d',3,NULL,2000,0,0,1,1,1,'',0,'','2017-11-02 11:09:06'),(16,'testCPA',2,NULL,NULL,0,0,1,1,1,'',0,'','2017-11-06 06:05:46'),(17,'task19',2,NULL,NULL,0,0,1,1,1,'',0,'','2017-11-06 19:37:30');
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','$2a$10$5Uh44Oj8rBDBD5/hdJsBTOucB38wdc9s4C8oOFDSfc7UawwjwOqGS','',NULL,NULL,1,1,'','','','',0),(2,'manager','$2a$10$Kh8KEaJBdZlVEORqJxu4YO2yE.Io/H.0okWTPffpxxiHmOaoXomfy','manager',NULL,NULL,1,1,NULL,NULL,NULL,NULL,0),(3,'dataer','$2a$10$dDXtezARf90UQVFVWrecoe2lsemygCr66Q9mbvU4knyzzHq5rWmf6','dataer',NULL,NULL,1,1,NULL,NULL,NULL,NULL,0),(4,'qudao1','$2a$10$TDkiEtaFXlFpdat8joxSI.bexKG6W5hl2kvcBivdBjFG82L1sIaNK','渠道1',NULL,NULL,1,1,'1311111111122','121212','123@qq.com','中国银行',0),(5,'qudaotest','$2a$10$qTckb1VnTiXsgtsWwEUSleCqYqSkg/0VRbr7gModtlBRXkVdBKTfC','qudaotest',NULL,NULL,1,1,'123','','','中国银行',0),(7,'boss','$2a$10$9ZrOLUuOrMTj56Xx.per0eSvTHu1ZzVpEC/3eipRhpETtZbBw0Nua','超级管理员',NULL,NULL,1,1,'13111111111','1112','123@qq.com','中国银行',0),(10,'admin1','$2a$10$Gvx3RF5UiEnCatcdk9KMYe15Z6nyvmEsMHiUw7IC4kzWe6../mD0e','管理员',NULL,NULL,1,1,'123','22','123@qq.com','中国银行',0),(11,'channel1','$2a$10$bEud4i5IJD5QBrerU52/TeFeZ9A99m.Owq3wm.FJAuN9WK0ux.iGS','渠道1',NULL,NULL,1,1,'223','221','11@qq.com','北京一行',0),(12,'operator2','$2a$10$5yuPZyIrQQ.SmEUVT778jeXSMizRvekJo78E/UGesaD/VBpCHzDT.','运营1',NULL,NULL,1,1,'223','22','11@qq.com','北京一行',0),(13,'channel3','$2a$10$M9z72xUbnmm.lnfQ1/6b1uGijLDC78L7lniJkELtQFYXbKk6.ouQe','渠道3',NULL,NULL,1,1,'223','111','11@qq.com','北京一行',0),(14,'channel4','$2a$10$S3ldMeFJf5oV2UUTDa/4oe6H8TwXvmYLsCjK8sLOVrRymFOS17Qta','渠道4',NULL,NULL,1,1,'223','111','11@qq.com','北京一行',0),(15,'admin222','$2a$10$7qfk1XXrNBHhcCwtGPluc.1Y7Uaq2bh14EwMC1YLPSkynm/Ld8Z1i','管理员222',NULL,NULL,1,1,'223','111','11@qq.com','北京一行',0),(16,'admin333','$2a$10$Upar8KQgiiIOKxS/8azIVeO3mfOc3Lo22VSmrI5Kkw3.IEtnGEWbO','管理员333',NULL,NULL,1,1,'223','111','11@qq.com','北京一行',0);
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
  `sub_task_id` int(10) unsigned DEFAULT NULL,
  `channel_id` int(10) unsigned DEFAULT NULL,
  `is_assigned` int(10) DEFAULT NULL,
  `item_name` varchar(20) DEFAULT NULL,
  `on_line_time` date DEFAULT NULL,
  `down_line_time` date DEFAULT NULL,
  `purchase_price` double DEFAULT NULL,
  `desc_str` varchar(512) DEFAULT NULL,
  `site_id` int(10) unsigned DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL COMMENT '状态可分为：未结算 已结算 已支付 冻结 暂停',
  `value_way_id` int(10) unsigned NOT NULL,
  `divide_rate` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `channel_id_fk` (`channel_id`),
  KEY `fk_site_id` (`site_id`),
  KEY `sub_task_id_fk` (`sub_task_id`),
  KEY `value_item_value_way_fk` (`value_way_id`),
  KEY `fk_divide_rate` (`divide_rate`),
  CONSTRAINT `channel_id_fk` FOREIGN KEY (`channel_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_divide_rate` FOREIGN KEY (`divide_rate`) REFERENCES `divide_rate` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_site_id` FOREIGN KEY (`site_id`) REFERENCES `web_site` (`id`),
  CONSTRAINT `sub_task_id_fk` FOREIGN KEY (`sub_task_id`) REFERENCES `sub_task` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `value_item_value_way_fk` FOREIGN KEY (`value_way_id`) REFERENCES `value_way` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `value_item`
--

LOCK TABLES `value_item` WRITE;
/*!40000 ALTER TABLE `value_item` DISABLE KEYS */;
INSERT INTO `value_item` VALUES (12,1,11,0,'valueItem21226','2017-10-01','2017-10-28',0.1,'testurl444',NULL,NULL,2,3),(13,1,13,0,'valueItem2122','2017-10-30','2017-11-07',0,'',NULL,NULL,2,3),(14,1,13,0,'valueitem4','2017-10-30','2017-11-14',0,'',NULL,NULL,2,3),(18,1,11,0,'valueItem2122ee',NULL,NULL,0,'',NULL,NULL,2,3),(19,1,13,0,'dddd','2017-11-06','2017-11-16',0,'',NULL,NULL,2,3),(20,4,13,0,'sub5','2017-11-01','2017-12-07',1,'',NULL,NULL,3,3),(22,14,11,0,'testDown2','2017-11-02',NULL,1,'',NULL,NULL,3,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_site`
--

LOCK TABLES `web_site` WRITE;
/*!40000 ALTER TABLE `web_site` DISABLE KEYS */;
INSERT INTO `web_site` VALUES (3,0,1,'url1','2017-09-16','www.baidu.com','testurl'),(5,0,1,'url1','2017-09-20','www.baidu.com','testurl');
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

-- Dump completed on 2017-12-10  6:53:17

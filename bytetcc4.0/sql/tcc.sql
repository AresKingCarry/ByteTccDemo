/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.224-userSystem
Source Server Version : 50720
Source Host           : 192.168.1.224:3306
Source Database       : tcc

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-10-26 11:40:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tcc_account_service
-- ----------------------------
DROP TABLE IF EXISTS `tcc_account_service`;
CREATE TABLE `tcc_account_service` (
  `trans_id` varchar(64) NOT NULL,
  `target_class` varchar(256) DEFAULT NULL,
  `target_method` varchar(128) DEFAULT NULL,
  `confirm_method` varchar(128) DEFAULT NULL,
  `cancel_method` varchar(128) DEFAULT NULL,
  `retried_count` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_time` datetime NOT NULL,
  `version` tinyint(4) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `invocation` longblob,
  `role` tinyint(4) NOT NULL,
  `pattern` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`trans_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tcc_inventory_service
-- ----------------------------
DROP TABLE IF EXISTS `tcc_inventory_service`;
CREATE TABLE `tcc_inventory_service` (
  `trans_id` varchar(64) NOT NULL,
  `target_class` varchar(256) DEFAULT NULL,
  `target_method` varchar(128) DEFAULT NULL,
  `confirm_method` varchar(128) DEFAULT NULL,
  `cancel_method` varchar(128) DEFAULT NULL,
  `retried_count` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_time` datetime NOT NULL,
  `version` tinyint(4) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `invocation` longblob,
  `role` tinyint(4) NOT NULL,
  `pattern` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`trans_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tcc_order_service
-- ----------------------------
DROP TABLE IF EXISTS `tcc_order_service`;
CREATE TABLE `tcc_order_service` (
  `trans_id` varchar(64) NOT NULL,
  `target_class` varchar(256) DEFAULT NULL,
  `target_method` varchar(128) DEFAULT NULL,
  `confirm_method` varchar(128) DEFAULT NULL,
  `cancel_method` varchar(128) DEFAULT NULL,
  `retried_count` tinyint(4) NOT NULL,
  `create_time` datetime NOT NULL,
  `last_time` datetime NOT NULL,
  `version` tinyint(4) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `invocation` longblob,
  `role` tinyint(4) NOT NULL,
  `pattern` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`trans_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

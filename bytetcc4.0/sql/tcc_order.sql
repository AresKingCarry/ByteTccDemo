/*
Navicat MySQL Data Transfer

Source Server         : 公司测试数据库-192.168.1.242
Source Server Version : 50720
Source Host           : 192.168.1.242:3306
Source Database       : tcc_order

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-10-26 11:40:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bytejta
-- ----------------------------
DROP TABLE IF EXISTS `bytejta`;
CREATE TABLE `bytejta` (
  `xid` varchar(32) NOT NULL DEFAULT '',
  `gxid` varchar(40) DEFAULT NULL,
  `bxid` varchar(40) DEFAULT NULL,
  `ctime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`xid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NOT NULL,
  `number` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '订单编号',
  `status` tinyint(4) NOT NULL COMMENT '订单状态,1:未支付 ,2:支付中 ,3:支付失败 ,4:支付成功',
  `product_id` varchar(128) COLLATE utf8mb4_bin NOT NULL COMMENT '产品id',
  `total_amount` decimal(10,0) NOT NULL COMMENT '付款金额',
  `count` int(4) NOT NULL COMMENT '购买数量',
  `user_id` varchar(128) COLLATE utf8mb4_bin NOT NULL COMMENT '购买人',
  PRIMARY KEY (`id`),
  KEY `index_number` (`number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1608755 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

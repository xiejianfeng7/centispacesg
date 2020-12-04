/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.2.53
 Source Server Type    : MySQL
 Source Server Version : 50645
 Source Host           : 192.168.2.53:3306
 Source Schema         : centispace_test

 Target Server Type    : MySQL
 Target Server Version : 50645
 File Encoding         : 65001

 Date: 04/12/2020 16:45:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_software_restart
-- ----------------------------
DROP TABLE IF EXISTS `tbl_software_restart`;
CREATE TABLE `tbl_software_restart`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `insert_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;

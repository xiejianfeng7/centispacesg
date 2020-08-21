/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.2.53
 Source Server Type    : MySQL
 Source Server Version : 50645
 Source Host           : 192.168.2.53:3306
 Source Schema         : centispace

 Target Server Type    : MySQL
 Target Server Version : 50645
 File Encoding         : 65001

 Date: 21/08/2020 17:41:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for available
-- ----------------------------
DROP TABLE IF EXISTS `available`;
CREATE TABLE `available`  (
  `aid` int(10) UNSIGNED NOT NULL COMMENT '卫星是否可用ID',
  `available` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否可用',
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for manufacturer
-- ----------------------------
DROP TABLE IF EXISTS `manufacturer`;
CREATE TABLE `manufacturer`  (
  `mid` int(11) UNSIGNED NOT NULL COMMENT '卫星制造商ID',
  `manu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '卫星制造商名称',
  PRIMARY KEY (`mid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `task_id` int(10) UNSIGNED NOT NULL COMMENT '卫星节点编号，与卫星相关数据填写，不涉及此项填FFFFFFFF。',
  `data_type_id` int(10) UNSIGNED NOT NULL COMMENT '数据类型标识',
  `device_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '源地址标识',
  `wl_date` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周计数（以2017年1月1日0时为起点）',
  `wl_time` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周内秒',
  `res1` int(10) UNSIGNED NULL DEFAULT NULL,
  `data_length` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '数据包长度',
  `content` blob NULL COMMENT '实际数据段内容',
  `recv_time` datetime(0) NULL DEFAULT NULL COMMENT '数据包内时间（根据微厘时换算）',
  `insert_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '数据入库时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `task_id`(`task_id`) USING BTREE,
  INDEX `wl_date`(`wl_date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5708665 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pack_503_1001_s
-- ----------------------------
DROP TABLE IF EXISTS `pack_503_1001_s`;
CREATE TABLE `pack_503_1001_s`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `CodeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编号',
  `SignalGndTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '地面时间',
  `SignalSatTime` datetime(0) NULL DEFAULT NULL COMMENT '星上时间',
  `OriginalValue` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '原始值',
  `EngineerValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程值',
  `StateValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28663 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pack_503_1002_s
-- ----------------------------
DROP TABLE IF EXISTS `pack_503_1002_s`;
CREATE TABLE `pack_503_1002_s`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `CodeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编号',
  `SignalGndTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '地面时间',
  `SignalSatTime` datetime(0) NULL DEFAULT NULL COMMENT '星上时间',
  `OriginalValue` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '原始值',
  `EngineerValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程值',
  `StateValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20233 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pack_503_1003_s
-- ----------------------------
DROP TABLE IF EXISTS `pack_503_1003_s`;
CREATE TABLE `pack_503_1003_s`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `CodeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编号',
  `SignalGndTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '地面时间',
  `SignalSatTime` datetime(0) NULL DEFAULT NULL COMMENT '星上时间',
  `OriginalValue` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '原始值',
  `EngineerValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程值',
  `StateValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 841 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pack_503_1004_s
-- ----------------------------
DROP TABLE IF EXISTS `pack_503_1004_s`;
CREATE TABLE `pack_503_1004_s`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `CodeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编号',
  `SignalGndTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '地面时间',
  `SignalSatTime` datetime(0) NULL DEFAULT NULL COMMENT '星上时间',
  `OriginalValue` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '原始值',
  `EngineerValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程值',
  `StateValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1772 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pack_503_1009_s
-- ----------------------------
DROP TABLE IF EXISTS `pack_503_1009_s`;
CREATE TABLE `pack_503_1009_s`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `CodeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编号',
  `SignalGndTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '地面时间',
  `SignalSatTime` datetime(0) NULL DEFAULT NULL COMMENT '星上时间',
  `OriginalValue` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '原始值',
  `EngineerValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程值',
  `StateValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 113806 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pack_503_100b_s
-- ----------------------------
DROP TABLE IF EXISTS `pack_503_100b_s`;
CREATE TABLE `pack_503_100b_s`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `CodeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编号',
  `SignalGndTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '地面时间',
  `SignalSatTime` datetime(0) NULL DEFAULT NULL COMMENT '星上时间',
  `OriginalValue` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '原始值',
  `EngineerValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程值',
  `StateValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9241 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pack_503_100c_s
-- ----------------------------
DROP TABLE IF EXISTS `pack_503_100c_s`;
CREATE TABLE `pack_503_100c_s`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `CodeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编号',
  `SignalGndTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '地面时间',
  `SignalSatTime` datetime(0) NULL DEFAULT NULL COMMENT '星上时间',
  `OriginalValue` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '原始值',
  `EngineerValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程值',
  `StateValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 559 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pack_503_100d_s
-- ----------------------------
DROP TABLE IF EXISTS `pack_503_100d_s`;
CREATE TABLE `pack_503_100d_s`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `CodeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编号',
  `SignalGndTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '地面时间',
  `SignalSatTime` datetime(0) NULL DEFAULT NULL COMMENT '星上时间',
  `OriginalValue` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '原始值',
  `EngineerValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程值',
  `StateValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45975 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pack_503_100e_s
-- ----------------------------
DROP TABLE IF EXISTS `pack_503_100e_s`;
CREATE TABLE `pack_503_100e_s`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `CodeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编号',
  `SignalGndTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '地面时间',
  `SignalSatTime` datetime(0) NULL DEFAULT NULL COMMENT '星上时间',
  `OriginalValue` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '原始值',
  `EngineerValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程值',
  `StateValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13571 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pack_503_100f_s
-- ----------------------------
DROP TABLE IF EXISTS `pack_503_100f_s`;
CREATE TABLE `pack_503_100f_s`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `CodeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编号',
  `SignalGndTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '地面时间',
  `SignalSatTime` datetime(0) NULL DEFAULT NULL COMMENT '星上时间',
  `OriginalValue` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '原始值',
  `EngineerValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程值',
  `StateValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 487 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pack_503_1010_s
-- ----------------------------
DROP TABLE IF EXISTS `pack_503_1010_s`;
CREATE TABLE `pack_503_1010_s`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `CodeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编号',
  `SignalGndTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '地面时间',
  `SignalSatTime` datetime(0) NULL DEFAULT NULL COMMENT '星上时间',
  `OriginalValue` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '原始值',
  `EngineerValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程值',
  `StateValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 457 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pack_503_1011_s
-- ----------------------------
DROP TABLE IF EXISTS `pack_503_1011_s`;
CREATE TABLE `pack_503_1011_s`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `CodeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编号',
  `SignalGndTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '地面时间',
  `SignalSatTime` datetime(0) NULL DEFAULT NULL COMMENT '星上时间',
  `OriginalValue` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '原始值',
  `EngineerValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程值',
  `StateValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 358 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pack_503_1013_s
-- ----------------------------
DROP TABLE IF EXISTS `pack_503_1013_s`;
CREATE TABLE `pack_503_1013_s`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `CodeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编号',
  `SignalGndTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '地面时间',
  `SignalSatTime` datetime(0) NULL DEFAULT NULL COMMENT '星上时间',
  `OriginalValue` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '原始值',
  `EngineerValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程值',
  `StateValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 358 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pack_503_1016_s
-- ----------------------------
DROP TABLE IF EXISTS `pack_503_1016_s`;
CREATE TABLE `pack_503_1016_s`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `CodeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编号',
  `SignalGndTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '地面时间',
  `SignalSatTime` datetime(0) NULL DEFAULT NULL COMMENT '星上时间',
  `OriginalValue` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '原始值',
  `EngineerValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程值',
  `StateValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3037 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pack_503_102b_s
-- ----------------------------
DROP TABLE IF EXISTS `pack_503_102b_s`;
CREATE TABLE `pack_503_102b_s`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `CodeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编号',
  `SignalGndTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '地面时间',
  `SignalSatTime` datetime(0) NULL DEFAULT NULL COMMENT '星上时间',
  `OriginalValue` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '原始值',
  `EngineerValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程值',
  `StateValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37756 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pack_503_102d_s
-- ----------------------------
DROP TABLE IF EXISTS `pack_503_102d_s`;
CREATE TABLE `pack_503_102d_s`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `CodeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编号',
  `SignalGndTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '地面时间',
  `SignalSatTime` datetime(0) NULL DEFAULT NULL COMMENT '星上时间',
  `OriginalValue` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '原始值',
  `EngineerValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程值',
  `StateValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3476 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pack_503_102f_s
-- ----------------------------
DROP TABLE IF EXISTS `pack_503_102f_s`;
CREATE TABLE `pack_503_102f_s`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `CodeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编号',
  `SignalGndTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '地面时间',
  `SignalSatTime` datetime(0) NULL DEFAULT NULL COMMENT '星上时间',
  `OriginalValue` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '原始值',
  `EngineerValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程值',
  `StateValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1123 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pack_503_1031_s
-- ----------------------------
DROP TABLE IF EXISTS `pack_503_1031_s`;
CREATE TABLE `pack_503_1031_s`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `CodeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编号',
  `SignalGndTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '地面时间',
  `SignalSatTime` datetime(0) NULL DEFAULT NULL COMMENT '星上时间',
  `OriginalValue` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '原始值',
  `EngineerValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程值',
  `StateValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 507 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pack_503_1032_s
-- ----------------------------
DROP TABLE IF EXISTS `pack_503_1032_s`;
CREATE TABLE `pack_503_1032_s`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `CodeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编号',
  `SignalGndTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '地面时间',
  `SignalSatTime` datetime(0) NULL DEFAULT NULL COMMENT '星上时间',
  `OriginalValue` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '原始值',
  `EngineerValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程值',
  `StateValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 141 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pack_503_1034_s
-- ----------------------------
DROP TABLE IF EXISTS `pack_503_1034_s`;
CREATE TABLE `pack_503_1034_s`  (
  `ID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `CodeID` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编号',
  `SignalGndTime` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '地面时间',
  `SignalSatTime` datetime(0) NULL DEFAULT NULL COMMENT '星上时间',
  `OriginalValue` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '原始值',
  `EngineerValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工程值',
  `StateValue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态值',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18547 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for package
-- ----------------------------
DROP TABLE IF EXISTS `package`;
CREATE TABLE `package`  (
  `pid` int(11) UNSIGNED NOT NULL COMMENT '遥测信息包ID',
  `ptype` int(11) NOT NULL COMMENT '遥测类型号',
  `psatellite` int(11) UNSIGNED NOT NULL COMMENT '所属卫星',
  `pname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '遥测包名称',
  PRIMARY KEY (`pid`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE,
  INDEX `psatellite`(`psatellite`) USING BTREE,
  CONSTRAINT `package_ibfk_1` FOREIGN KEY (`psatellite`) REFERENCES `satellite` (`scid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for parameter
-- ----------------------------
DROP TABLE IF EXISTS `parameter`;
CREATE TABLE `parameter`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `satellite_id` int(10) UNSIGNED NOT NULL COMMENT '参数所属卫星',
  `package_id` int(10) UNSIGNED NOT NULL COMMENT '参数所属包',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数名',
  `byte_address` int(11) NULL DEFAULT NULL COMMENT '字节位置',
  `bit_address` int(11) NULL DEFAULT NULL COMMENT '字节内比特起始位置',
  `bits_num` int(11) NULL DEFAULT NULL COMMENT '数据长度（bit）',
  `value_type` int(11) NULL DEFAULT NULL COMMENT '值类型',
  `handle_function` int(11) NULL DEFAULT NULL COMMENT '处理函数',
  `maximum` decimal(10, 0) NULL DEFAULT NULL COMMENT '最大值',
  `minimum` decimal(10, 0) NULL DEFAULT NULL COMMENT '最小值',
  `trans` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '翻译',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `satellite_id`(`satellite_id`) USING BTREE,
  INDEX `package_id`(`package_id`) USING BTREE,
  CONSTRAINT `parameter_ibfk_1` FOREIGN KEY (`satellite_id`) REFERENCES `satellite` (`scid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `parameter_ibfk_2` FOREIGN KEY (`package_id`) REFERENCES `package` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1733 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for pictures
-- ----------------------------
DROP TABLE IF EXISTS `pictures`;
CREATE TABLE `pictures`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `picture` mediumblob NULL COMMENT '图片',
  `recv_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '插入时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3747 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for satellite
-- ----------------------------
DROP TABLE IF EXISTS `satellite`;
CREATE TABLE `satellite`  (
  `scid` int(10) UNSIGNED NOT NULL COMMENT '卫星ID号',
  `smanufacturer` int(10) UNSIGNED NOT NULL COMMENT '卫星制造商',
  `savailable` int(10) UNSIGNED NOT NULL COMMENT '卫星是否可用',
  `sname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '卫星名称',
  PRIMARY KEY (`scid`) USING BTREE,
  UNIQUE INDEX `scid`(`scid`) USING BTREE,
  INDEX `smanufacturer`(`smanufacturer`) USING BTREE,
  INDEX `savailable`(`savailable`) USING BTREE,
  CONSTRAINT `satellite_ibfk_1` FOREIGN KEY (`smanufacturer`) REFERENCES `manufacturer` (`mid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `satellite_ibfk_2` FOREIGN KEY (`savailable`) REFERENCES `available` (`aid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for table_00020101
-- ----------------------------
DROP TABLE IF EXISTS `table_00020101`;
CREATE TABLE `table_00020101`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `task_id` int(10) UNSIGNED NOT NULL COMMENT '卫星节点编号，与卫星相关数据填写，不涉及此项填FFFFFFFF。',
  `data_type_id` int(10) UNSIGNED NOT NULL COMMENT '数据类型标识',
  `device_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '源地址标识',
  `wl_date` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周计数（以2017年1月1日0时为起点）',
  `wl_time` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周内秒',
  `res1` int(10) UNSIGNED NULL DEFAULT NULL,
  `data_length` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '数据包长度',
  `content` blob NULL COMMENT '实际数据段内容',
  `recv_time` datetime(0) NULL DEFAULT NULL COMMENT '数据包内时间（根据微厘时换算）',
  `insert_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '数据入库时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `task_id`(`task_id`) USING BTREE,
  INDEX `wl_date`(`wl_date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2630160 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for table_00110201
-- ----------------------------
DROP TABLE IF EXISTS `table_00110201`;
CREATE TABLE `table_00110201`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `task_id` int(10) UNSIGNED NOT NULL COMMENT '卫星节点编号，与卫星相关数据填写，不涉及此项填FFFFFFFF。',
  `data_type_id` int(10) UNSIGNED NOT NULL COMMENT '数据类型标识',
  `device_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '源地址标识',
  `wl_date` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周计数（以2017年1月1日0时为起点）',
  `wl_time` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周内秒',
  `res1` int(10) UNSIGNED NULL DEFAULT NULL,
  `data_length` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '数据包长度',
  `content` blob NULL COMMENT '实际数据段内容',
  `recv_time` datetime(0) NULL DEFAULT NULL COMMENT '数据包内时间（根据微厘时换算）',
  `insert_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '数据入库时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `task_id`(`task_id`) USING BTREE,
  INDEX `wl_date`(`wl_date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 189489 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for table_00110301
-- ----------------------------
DROP TABLE IF EXISTS `table_00110301`;
CREATE TABLE `table_00110301`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `task_id` int(10) UNSIGNED NOT NULL COMMENT '卫星节点编号，与卫星相关数据填写，不涉及此项填FFFFFFFF。',
  `data_type_id` int(10) UNSIGNED NOT NULL COMMENT '数据类型标识',
  `device_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '源地址标识',
  `wl_date` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周计数（以2017年1月1日0时为起点）',
  `wl_time` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周内秒',
  `res1` int(10) UNSIGNED NULL DEFAULT NULL,
  `data_length` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '数据包长度',
  `content` blob NULL COMMENT '实际数据段内容',
  `recv_time` datetime(0) NULL DEFAULT NULL COMMENT '数据包内时间（根据微厘时换算）',
  `insert_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '数据入库时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `task_id`(`task_id`) USING BTREE,
  INDEX `wl_date`(`wl_date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2535481 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for table_00110501
-- ----------------------------
DROP TABLE IF EXISTS `table_00110501`;
CREATE TABLE `table_00110501`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `task_id` int(10) UNSIGNED NOT NULL COMMENT '卫星节点编号，与卫星相关数据填写，不涉及此项填FFFFFFFF。',
  `data_type_id` int(10) UNSIGNED NOT NULL COMMENT '数据类型标识',
  `device_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '源地址标识',
  `wl_date` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周计数（以2017年1月1日0时为起点）',
  `wl_time` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周内秒',
  `res1` int(10) UNSIGNED NULL DEFAULT NULL,
  `data_length` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '数据包长度',
  `content` blob NULL COMMENT '实际数据段内容',
  `recv_time` datetime(0) NULL DEFAULT NULL COMMENT '数据包内时间（根据微厘时换算）',
  `insert_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '数据入库时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `task_id`(`task_id`) USING BTREE,
  INDEX `wl_date`(`wl_date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9607 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for table_00120102
-- ----------------------------
DROP TABLE IF EXISTS `table_00120102`;
CREATE TABLE `table_00120102`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `task_id` int(10) UNSIGNED NOT NULL COMMENT '卫星节点编号，与卫星相关数据填写，不涉及此项填FFFFFFFF。',
  `data_type_id` int(10) UNSIGNED NOT NULL COMMENT '数据类型标识',
  `device_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '源地址标识',
  `wl_date` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周计数（以2017年1月1日0时为起点）',
  `wl_time` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周内秒',
  `res1` int(10) UNSIGNED NULL DEFAULT NULL,
  `data_length` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '数据包长度',
  `content` blob NULL COMMENT '实际数据段内容',
  `recv_time` datetime(0) NULL DEFAULT NULL COMMENT '数据包内时间（根据微厘时换算）',
  `insert_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '数据入库时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `task_id`(`task_id`) USING BTREE,
  INDEX `wl_date`(`wl_date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for table_00210102
-- ----------------------------
DROP TABLE IF EXISTS `table_00210102`;
CREATE TABLE `table_00210102`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `task_id` int(10) UNSIGNED NOT NULL COMMENT '卫星节点编号，与卫星相关数据填写，不涉及此项填FFFFFFFF。',
  `data_type_id` int(10) UNSIGNED NOT NULL COMMENT '数据类型标识',
  `device_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '源地址标识',
  `wl_date` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周计数（以2017年1月1日0时为起点）',
  `wl_time` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周内秒',
  `res1` int(10) UNSIGNED NULL DEFAULT NULL,
  `data_length` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '数据包长度',
  `content` blob NULL COMMENT '实际数据段内容',
  `recv_time` datetime(0) NULL DEFAULT NULL COMMENT '数据包内时间（根据微厘时换算）',
  `insert_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '数据入库时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `task_id`(`task_id`) USING BTREE,
  INDEX `wl_date`(`wl_date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for table_004d0001
-- ----------------------------
DROP TABLE IF EXISTS `table_004d0001`;
CREATE TABLE `table_004d0001`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `task_id` int(10) UNSIGNED NOT NULL COMMENT '卫星节点编号，与卫星相关数据填写，不涉及此项填FFFFFFFF。',
  `data_type_id` int(10) UNSIGNED NOT NULL COMMENT '数据类型标识',
  `device_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '源地址标识',
  `wl_date` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周计数（以2017年1月1日0时为起点）',
  `wl_time` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周内秒',
  `res1` int(10) UNSIGNED NULL DEFAULT NULL,
  `data_length` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '数据包长度',
  `content` blob NULL COMMENT '实际数据段内容',
  `recv_time` datetime(0) NULL DEFAULT NULL COMMENT '数据包内时间（根据微厘时换算）',
  `insert_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '数据入库时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `task_id`(`task_id`) USING BTREE,
  INDEX `wl_date`(`wl_date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for table_004d0002
-- ----------------------------
DROP TABLE IF EXISTS `table_004d0002`;
CREATE TABLE `table_004d0002`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `task_id` int(10) UNSIGNED NOT NULL COMMENT '卫星节点编号，与卫星相关数据填写，不涉及此项填FFFFFFFF。',
  `data_type_id` int(10) UNSIGNED NOT NULL COMMENT '数据类型标识',
  `device_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '源地址标识',
  `wl_date` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周计数（以2017年1月1日0时为起点）',
  `wl_time` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周内秒',
  `res1` int(10) UNSIGNED NULL DEFAULT NULL,
  `data_length` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '数据包长度',
  `content` blob NULL COMMENT '实际数据段内容',
  `recv_time` datetime(0) NULL DEFAULT NULL COMMENT '数据包内时间（根据微厘时换算）',
  `insert_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '数据入库时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `task_id`(`task_id`) USING BTREE,
  INDEX `wl_date`(`wl_date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for table_004d0103
-- ----------------------------
DROP TABLE IF EXISTS `table_004d0103`;
CREATE TABLE `table_004d0103`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `task_id` int(10) UNSIGNED NOT NULL COMMENT '卫星节点编号，与卫星相关数据填写，不涉及此项填FFFFFFFF。',
  `data_type_id` int(10) UNSIGNED NOT NULL COMMENT '数据类型标识',
  `device_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '源地址标识',
  `wl_date` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周计数（以2017年1月1日0时为起点）',
  `wl_time` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周内秒',
  `res1` int(10) UNSIGNED NULL DEFAULT NULL,
  `data_length` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '数据包长度',
  `content` blob NULL COMMENT '实际数据段内容',
  `recv_time` datetime(0) NULL DEFAULT NULL COMMENT '数据包内时间（根据微厘时换算）',
  `insert_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '数据入库时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `task_id`(`task_id`) USING BTREE,
  INDEX `wl_date`(`wl_date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for table_004d0104
-- ----------------------------
DROP TABLE IF EXISTS `table_004d0104`;
CREATE TABLE `table_004d0104`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `task_id` int(10) UNSIGNED NOT NULL COMMENT '卫星节点编号，与卫星相关数据填写，不涉及此项填FFFFFFFF。',
  `data_type_id` int(10) UNSIGNED NOT NULL COMMENT '数据类型标识',
  `device_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '源地址标识',
  `wl_date` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周计数（以2017年1月1日0时为起点）',
  `wl_time` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '微厘时周内秒',
  `res1` int(10) UNSIGNED NULL DEFAULT NULL,
  `data_length` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '数据包长度',
  `content` blob NULL COMMENT '实际数据段内容',
  `recv_time` datetime(0) NULL DEFAULT NULL COMMENT '数据包内时间（根据微厘时换算）',
  `insert_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '数据入库时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `task_id`(`task_id`) USING BTREE,
  INDEX `wl_date`(`wl_date`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `birthday` date NULL DEFAULT NULL,
  `insert_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Procedure structure for test
-- ----------------------------
DROP PROCEDURE IF EXISTS `test`;
delimiter ;;
CREATE PROCEDURE `test`(IN id int)
BEGIN
   CASE id
	 WHEN 1 THEN SELECT SignalValue FROM pack_503_100c_s;
	 WHEN 2 THEN SELECT CodeID FROM pack_503_100c_s;
	 END CASE;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;

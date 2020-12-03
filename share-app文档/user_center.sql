/*
 Navicat Premium Data Transfer

 Source Server         : su
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 120.25.149.156:3306
 Source Schema         : user_center

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 18/10/2020 12:17:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bonus_event_log
-- ----------------------------
DROP TABLE IF EXISTS `bonus_event_log`;
CREATE TABLE `bonus_event_log`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `user_id` int NULL DEFAULT NULL COMMENT 'user.id',
  `value` int NULL DEFAULT NULL COMMENT '积分操作值',
  `event` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发生的事件',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_bonus_event_log_user1_idx`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '积分变更记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bonus_event_log
-- ----------------------------
INSERT INTO `bonus_event_log` VALUES (1, 1, 50, 'CONTRIBUTE', '2020-10-08 03:19:52', '投稿积分');
INSERT INTO `bonus_event_log` VALUES (2, 1, 50, 'CONTRIBUTE', '2020-10-08 03:21:32', '投稿积分');
INSERT INTO `bonus_event_log` VALUES (3, 1, 50, 'CONTRIBUTE', '2020-10-08 03:24:14', '投稿积分');
INSERT INTO `bonus_event_log` VALUES (4, 1, 50, 'CONTRIBUTE', '2020-10-08 03:37:34', '投稿积分');
INSERT INTO `bonus_event_log` VALUES (5, 1, 50, 'CONTRIBUTE', '2020-10-08 04:02:36', '投稿积分');
INSERT INTO `bonus_event_log` VALUES (6, 1, 50, 'CONTRIBUTE', '2020-10-08 06:52:32', '投稿积分');
INSERT INTO `bonus_event_log` VALUES (7, 1, 50, 'CONTRIBUTE', '2020-10-08 06:53:04', '投稿积分');
INSERT INTO `bonus_event_log` VALUES (8, 2, -20, 'CONTRIBUTE', '2020-10-15 20:47:53', '投稿积分');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `wx_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '微信id',
  `wx_nickname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '微信昵称',
  `roles` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '角色',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '头像地址',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  `bonus` int NOT NULL DEFAULT 300 COMMENT '积分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分享' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '', 'crazy boy', 'user', 'https://thirdwx.qlogo.cn/mmopen/vi_32/qVIF6bVCRc8sp6ibpZ8TuUxMEfibbfrdAVetWFiauxNRK4jmG50us3Esg3WIdTUW07fibMq7rbpdsXbl9PYxwib47eQ/132', '2020-10-15 10:38:36', '2020-10-15 10:38:36', 100);
INSERT INTO `user` VALUES (2, 'oRrdQt_BbW-rCwyRnykraUKtlzRA', 'crazy boy', 'user', 'https://thirdwx.qlogo.cn/mmopen/vi_32/UK2vCwwicEjyBAdLFeVmou4G4iad38CR5ANp3t5Ero5R4sVFMcDrfzH0AqiaO4QLT0hIWXxzgF5zPcRia4W33o7zuQ/132', '2020-10-15 20:42:13', '2020-10-15 20:42:13', 80);

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : su
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 120.25.149.156:3306
 Source Schema         : content_center

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 18/10/2020 12:17:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mid_user_share
-- ----------------------------
DROP TABLE IF EXISTS `mid_user_share`;
CREATE TABLE `mid_user_share`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `share_id` int NOT NULL COMMENT 'share.id',
  `user_id` int NOT NULL COMMENT 'user.id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_mid_user_share_share1_idx`(`share_id`) USING BTREE,
  INDEX `fk_mid_user_share_user1_idx`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户-分享中间表【描述用户购买的分享】' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mid_user_share
-- ----------------------------
INSERT INTO `mid_user_share` VALUES (1, 1, 2);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '内容',
  `show_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否显示 0:否 1:是',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '微服务', 1, '2020-10-04 20:45:49');

-- ----------------------------
-- Table structure for share
-- ----------------------------
DROP TABLE IF EXISTS `share`;
CREATE TABLE `share`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int NOT NULL DEFAULT 0 COMMENT '发布人id',
  `title` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标题',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '修改时间',
  `is_original` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否原创 0:否 1:是',
  `author` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '作者',
  `cover` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '封面',
  `summary` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '概要信息',
  `price` int NOT NULL DEFAULT 0 COMMENT '价格（需要的积分）',
  `download_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '下载地址',
  `buy_count` int NOT NULL DEFAULT 0 COMMENT '下载数 ',
  `show_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否显示 0:否 1:是',
  `audit_status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过',
  `reason` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '审核不通过原因',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分享表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of share
-- ----------------------------
INSERT INTO `share` VALUES (1, 1, 'Java', '2020-09-29 15:33:10', '2020-09-29 15:33:14', 1, 'su', 'https://public-cdn-oss.mosoteach.cn/mssvc/cover/2020/09/7f41b776db0ef26d6bb4d92c61435bcc.jpg?x-oss-process=style/s100x100', 'java从入门到精通', 20, 'https://shimo.im/docs/TYYwTHhPpxPwC3vp/read', 5, 1, 'PASS', '无');
INSERT INTO `share` VALUES (2, 2, 'springboot', '2020-09-29 16:42:20', '2020-09-29 16:42:22', 0, 'su', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/7f41b776db0ef26d6bb4d92c61435bcc.jpg', 'springboot学习', 30, 'https://shimo.im/docs/TYYwTHhPpxPwC3vp/read', 10, 1, 'PASS', '无');
INSERT INTO `share` VALUES (3, 3, 'spring', '2020-09-29 18:56:32', '2020-09-29 18:56:34', 0, 'su', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/7f41b776db0ef26d6bb4d92c61435bcc.jpg', 'spring介绍', 20, 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/7f41b776db0ef26d6bb4d92c61435bcc.jpg', 25, 1, 'PASS', '无');
INSERT INTO `share` VALUES (4, 1, '111', '2020-10-06 20:57:34', '2020-10-06 20:57:34', 0, 'su', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/7f41b776db0ef26d6bb4d92c61435bcc.jpg', '11', 11, '111', 0, 1, 'PASS', '');
INSERT INTO `share` VALUES (5, 1, 'ss', '2020-10-08 02:10:32', '2020-10-08 02:10:32', 1, 'su', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/7f41b776db0ef26d6bb4d92c61435bcc.jpg', 'ss', 10, 'ss', 0, 1, 'PASS', '');
INSERT INTO `share` VALUES (6, 1, 'ds', '2020-10-08 07:10:34', '2020-10-08 07:10:34', 1, 'su', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/7f41b776db0ef26d6bb4d92c61435bcc.jpg', 'sdf', 10, 'dd', 0, 1, 'NOT_YET', '');
INSERT INTO `share` VALUES (7, 1, 'dfsda', '2020-10-08 19:21:04', '2020-10-08 19:21:04', 1, 'su', 'https://student-manage-ll.oss-cn-beijing.aliyuncs.com/micro-service/7f41b776db0ef26d6bb4d92c61435bcc.jpg', 'sfasdf', 20, 'sss', 0, 1, 'NOT_YET', '');
INSERT INTO `share` VALUES (8, 1, 'sql', '2020-10-15 20:52:03', '2020-10-15 20:52:03', 1, 'su', 'https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3892521478,1695688217&fm=26&gp=0.jpg', 'sql学习', 11, 'dggh', 0, 1, 'NOT_YET', '');
INSERT INTO `share` VALUES (9, 1, 'HTML', '2020-10-15 20:53:44', '2020-10-15 20:53:44', 1, 'su', 'https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3892521478,1695688217&fm=26&gp=0.jpg', 'HTML学习', 11, 'gshdkdk', 0, 1, 'NOT_YET', '');
INSERT INTO `share` VALUES (10, 1, 'HTML', '2020-10-15 20:54:09', '2020-10-15 20:54:09', 1, 'su', 'https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3892521478,1695688217&fm=26&gp=0.jpg', 'HTML学习', 11, 'gshdkdk', 0, 1, 'NOT_YET', '');
INSERT INTO `share` VALUES (11, 1, 'HTML', '2020-10-15 20:55:01', '2020-10-15 20:55:01', 1, 'su', 'https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3892521478,1695688217&fm=26&gp=0.jpg', 'HTML学习', 11, 'gshdkdk', 0, 1, 'NOT_YET', '');
INSERT INTO `share` VALUES (12, 1, '微服务', '2020-10-15 20:56:33', '2020-10-15 20:56:33', 1, 'su', 'https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3892521478,1695688217&fm=26&gp=0.jpg', '微服务学习', 11, 'a6fddss6d4f5ds456', 0, 1, 'NOT_YET', '');
INSERT INTO `share` VALUES (13, 1, '微服务', '2020-10-15 20:57:27', '2020-10-15 20:57:27', 0, 'su', 'https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3892521478,1695688217&fm=26&gp=0.jpg', '微服务学习', 11, 'https://ext.dcloud.net.cn/plugin?id=1261', 0, 1, 'NOT_YET', '');

SET FOREIGN_KEY_CHECKS = 1;

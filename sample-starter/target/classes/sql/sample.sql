#todo 你的建表语句,包含索引
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
create database interview;
use interview;
drop table `order`;
CREATE TABLE `order` (
                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                         `order_sn` varchar(32) default 0 COMMENT '订单编号',
                         `sku_id` varchar(32) NOT NULL COMMENT 'sku的ID',
                         `amount` bigint(20) default 0 COMMENT '购买数量',
                         `status` tinyint(4)  DEFAULT 0 COMMENT '订单状态：0-待支付、1-待发货、2-已发货、3-已完成、4-已取消',
                         `total_amount` decimal(20,2) default 0 COMMENT '订单总金额',
                         `pay_amount` decimal(20,2) default 0 COMMENT '支付金额',
                         `pay_type` tinyint(4) default 0 COMMENT '支付方式：1-支付宝、2-微信支付',
                         `pay_time` datetime DEFAULT current_timestamp COMMENT '支付时间',
                         `create_time` datetime  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         `update_time` datetime  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `order_sn` (`order_sn`)
)ENGINE = InnoDB AUTO_INCREMENT = 77 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = DYNAMIC;
select * from `order`;
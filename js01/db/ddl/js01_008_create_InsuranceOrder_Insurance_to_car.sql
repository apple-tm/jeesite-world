drop table if exists `InsuranceOrderToCar`;
CREATE TABLE `InsuranceOrderToCar`(
`order_id` int(11) NOT NULL  COMMENT '订单id',
`car_id` int(11) NOT NULL  COMMENT '车辆id',
`status` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '状态 自定义使用',
`created_time` datetime DEFAULT NULL COMMENT '创建时间',
`created_by` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '创建人 ',
`updated_time` datetime DEFAULT NULL COMMENT '更新时间',
`updated_by` varchar(20) COLLATE utf8_unicode_ci COMMENT '更新人'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

alter table `InsuranceOrderToCar` add constraint FK_ID_CAR foreign key(car_id) REFERENCES InsuranceVehicles(id)
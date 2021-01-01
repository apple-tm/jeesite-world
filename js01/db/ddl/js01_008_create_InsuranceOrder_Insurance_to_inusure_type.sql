drop table if exists `InsuranceOrderToInsureType`;
CREATE TABLE `InsuranceOrderToInsureType`(
`order_id` int(11) NOT NULL  COMMENT '订单id',
`insure_id` int(11) NOT NULL  COMMENT '险种id',
`status` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '状态 自定义使用',
`created_time` datetime DEFAULT NULL COMMENT '创建时间',
`created_by` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '创建人 ',
`updated_time` datetime DEFAULT NULL COMMENT '更新时间',
`updated_by` varchar(20) COLLATE utf8_unicode_ci COMMENT '更新人'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

alter table `InsuranceOrderToInsureType` add constraint FK_ID_INSURE_TYPE foreign key(insure_id) REFERENCES InsuranceVehicles(id)
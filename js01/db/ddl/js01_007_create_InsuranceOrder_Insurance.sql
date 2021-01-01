drop table if exists `InsuranceOrder`;
CREATE TABLE `InsuranceOrder` (
`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
`fee_sum` decimal(10,2) NOT NULL COMMENT '保险费总计',
`insure_start_time` datetime NOT NULL COMMENT '保险起始时间',
`insure_end_time` datetime NOT NULL COMMENT '保险终止时间',
`insurer_id` int(11) NOT NULL COMMENT '保险人 ID，一个订单只有一个保险人',
`status` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '状态 自定义使用',
`created_time` datetime DEFAULT NULL COMMENT '创建时间',
`created_by` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '创建人 ',
`updated_time` datetime DEFAULT NULL COMMENT '更新时间',
`updated_by` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '更新人',
`the_insured_id` int(11) NOT NULL COMMENT '被保险人 ID',
PRIMARY KEY (`id`),
KEY `FK_ID` (`insurer_id`),
KEY `FK_INSURED_ID` (`the_insured_id`),
CONSTRAINT `FK_ID` FOREIGN KEY (`insurer_id`) REFERENCES `Insurer` (`id`),
CONSTRAINT `FK_INSURED_ID` FOREIGN KEY (`the_insured_id`) REFERENCES `theinsured` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

drop table if exists `Insurer`;
CREATE TABLE `Insurer`(
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '保险人id',
  `corporateName` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '保险公司名称',
  `corporateAddress` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '保险公司地址',
  `phoneNumber` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `postalCode` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '邮政编码',

  `status` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '状态 自定义使用',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '创建人 ',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(20) COLLATE utf8_unicode_ci COMMENT '更新人',
  PRIMARY KEY (`id`)

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



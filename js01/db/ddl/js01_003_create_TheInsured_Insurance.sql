CREATE TABLE `theinsured` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '被保险人id',
  `InsuredName` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '被保险人',
  `InsuredIdNumber` varchar(18) COLLATE utf8_unicode_ci NOT NULL COMMENT '被保险人证件号码',
  `DriverSLicense` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '行驶证车主',
  `address` varchar(11) COLLATE utf8_unicode_ci NOT NULL COMMENT ' 地址',
  `telephoneNumber` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `status` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '状态 自定义使用',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人 ',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `InsuredIdNumber` (`InsuredIdNumber`),
  UNIQUE KEY `InsuredName` (`InsuredName`),
  UNIQUE KEY `DriverSLicense` (`DriverSLicense`),
  UNIQUE KEY `telephoneNumber` (`telephoneNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `TypesOfInsurance`(
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '被保险人id',
  `typeOfInsurance` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '承担险种',
  `insuranceAmount` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '保险金额/责任限额',
  `accidentAbsoluteDeductible` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '每次事故绝对免赔额/率',
  `irrespectiveOfPercentage` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '不计免赔率',
  `insurancePremium` double(10,2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '保险费',

  `status` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '状态 自定义使用',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '创建人 ',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(20) COLLATE utf8_unicode_ci COMMENT '更新人',
  PRIMARY KEY (`id`)

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
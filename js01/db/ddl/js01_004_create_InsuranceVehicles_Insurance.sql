CREATE TABLE `InsuranceVehicles` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户 ID',
  `plateNumber` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '号牌号码',
  `numberPlateColour` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '号牌颜色',
  `vhicleType` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '机动车种类',
  `brandModel` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT ' 厂牌型号',
  `engineumberN` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '发动机号',
  `vinOrFrameNumber` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT 'VIN码/车架号',
  `carColor` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '车身颜色',
  `nuclearLoad` int(3) COLLATE utf8_unicode_ci NOT NULL COMMENT ' 核定载客',
  `nuclearLoadMass` double(10,2) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '核定载质量',
  `displacement` double(10,2) COLLATE utf8_unicode_ci NOT NULL COMMENT '排量',
  `negotiatingRealValue` double(10,2)  COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '协商实际价值',
  `DateOfFirstRegistration` datetime DEFAULT NULL COMMENT ' 初次登记日期',
  `natureOfUse` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '使用性质',
  `vehicleUse` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '车辆用途',

  `status` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '状态 自定义使用',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '创建人 ',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(20) COLLATE utf8_unicode_ci COMMENT '更新人',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
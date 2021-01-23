CREATE TABLE `user_entity` (
  `user_entity_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `phone_number` varchar(11) COLLATE utf8_unicode_ci NOT NULL COMMENT '手机号码',
  `user_password` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',

  `status` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '状态 自定义使用',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '创建人 ',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`user_entity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


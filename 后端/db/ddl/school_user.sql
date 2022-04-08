DROP TABLE IF EXISTS `school_user`;
CREATE TABLE `school_user` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `user_pwd` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `user_gender` varchar(255) DEFAULT NULL COMMENT '用户性别',
  `user_class` varchar(255) DEFAULT NULL COMMENT '用户所在班级',
  `user_role` varchar(255) DEFAULT NULL COMMENT '用户角色',
  `user_tel` varchar(255) DEFAULT NULL COMMENT '用户电话',
  `creat_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `creat_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

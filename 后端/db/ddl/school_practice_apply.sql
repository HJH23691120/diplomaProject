DROP TABLE IF EXISTS `school_practice_apply`;
CREATE TABLE `school_practice_apply` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `user_table_id` varchar(255) DEFAULT NULL COMMENT '用户表主键',
  `unit_name` varchar(255) DEFAULT NULL COMMENT '单位名称',
  `practice_job` varchar(255) DEFAULT NULL COMMENT '实习岗位',
  `firm_contact` varchar(255) DEFAULT NULL COMMENT '企业联系人',
  `firm_tel` varchar(255) DEFAULT NULL COMMENT '企业电话',
  `firm_tutor` varchar(255) DEFAULT NULL COMMENT '企业导师',
  `practice_treatment` varchar(255) DEFAULT NULL COMMENT '实习待遇',
  `practice_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '实习时间',
  `practice_place` varchar(255) DEFAULT NULL COMMENT '实习地点',
  `uploaad_prove` varchar(255) DEFAULT NULL COMMENT '上传证明材料',
  `agree_apply` varchar(255) DEFAULT NULL COMMENT '是否同意申请(1，同意  0，打回）',
  `creat_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `creat_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `car` (
  `id` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `price` decimal(10,2) DEFAULT NULL COMMENT '价格',
  `buy_date` varchar(50) DEFAULT NULL COMMENT '购买日期',
  `km` varchar(255) DEFAULT NULL COMMENT '万公里',
  `img_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `source` varchar(255) DEFAULT NULL COMMENT '数据来源',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
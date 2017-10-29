DROP TABLE IF EXISTS `jijin_daily`;
create table jijin_daily(
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `searchDate` varchar(32) DEFAULT NULL COMMENT '日期',
  `productType` varchar(8) DEFAULT NULL COMMENT '產品類型{"3":"封闭式基金","22":"ETF","35":"LOF","36":"分級LOF","18":"基金总体"}',
  `trdVol` DECIMAL(32,2) DEFAULT NULL COMMENT '成交量(万份),2位有效數字',
  `trdVol1` DECIMAL(32,6) DEFAULT NULL COMMENT '成交量(万份)',
  `trdAmt` DECIMAL(32,10) DEFAULT NULL COMMENT '成交金额(亿元)',
  `trdTm` DECIMAL(32,2) DEFAULT NULL COMMENT '成交笔数(万笔),2位有效數',
  `trdTm1` DECIMAL(32,6) DEFAULT NULL COMMENT '成交笔数(万笔)',
  `marketValue` DECIMAL(32,10) DEFAULT NULL COMMENT '市价总值(亿元)',
  `negotiableValue` DECIMAL(32,10) DEFAULT NULL COMMENT '流通市值(亿元)',
  `profitRate` DECIMAL(8,2) DEFAULT NULL,
  `profitRate1` DECIMAL(8,4) DEFAULT NULL,
  `istVol` DECIMAL(8) DEFAULT NULL,
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8mb4 comment='日基金成交概况';
create index idx_jijin_daily_ct on jijin_daily(create_time);

DROP TABLE IF EXISTS `jijin_monthly`;
create TABLE  jijin_monthly(
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `productType` varchar(8) DEFAULT NULL COMMENT '產品類型36',
  `inYear` varchar(16) DEFAULT NULL COMMENT '年份',
  `month` varchar(16) DEFAULT NULL COMMENT '月份',
  `exchangeRate` VARCHAR(16) DEFAULT NULL,
  `mmarketValue` DECIMAL(32,16) DEFAULT NULL,
  `mmarketValuePre` DECIMAL(32,16) DEFAULT NULL,
  `mmaxTrAmt` DECIMAL(32,16) DEFAULT NULL COMMENT '最高成交金额(亿元)0.47',
  `mmaxTrAmtDate` varchar(16) DEFAULT NULL COMMENT '最高成交金额日期2017-06-27',
  `mmaxTrAmtPre` DECIMAL(32,16) DEFAULT NULL COMMENT '最高成交金额(亿元)0.46520695',
  `mmaxTrVol` DECIMAL(32,8) DEFAULT NULL COMMENT '最高成交量(万份)4955.51',
  `mmaxTrVolDate` varchar(16) DEFAULT NULL COMMENT '最高成交量日期2017-06-07',
  `mmaxTrVolPre` DECIMAL(32,16) DEFAULT NULL COMMENT '最高成交量(万份)4955.5108',
  `mmaxhighTrn` DECIMAL(32,8) DEFAULT NULL COMMENT '最高成交笔数(万笔)0.28',
  `mmaxhighTrnDate` varchar(16) DEFAULT NULL COMMENT '最高成交笔数日期2017-06-01',
  `mmaxhighTrnPre` DECIMAL(32,16) DEFAULT NULL COMMENT '最高成交笔数(万笔)0.2789',
  `mminLowTrn` DECIMAL(32,8) DEFAULT NULL COMMENT '最低成交笔数(万笔)0.12',
  `mminLowTrnDate` varchar(16) DEFAULT NULL COMMENT '最低成交笔数日期2017-06-29',
  `mminLowTrnPre` DECIMAL(32,16) DEFAULT NULL COMMENT '最低成交笔数(万笔)0.1169',
  `mminTrAmt` DECIMAL(32,8) DEFAULT NULL COMMENT '最低成交金额(亿元)0.12',
  `mminTrAmtDate` varchar(16) DEFAULT NULL COMMENT '最低成交金额日期2017-06-20',
  `mminTrAmtPre` DECIMAL(32,16) DEFAULT NULL COMMENT '最低成交金额(亿元)0.11753471',
  `mminTrVol` DECIMAL(32,8) DEFAULT NULL COMMENT '最低成交量(万份)1271.91',
  `mminTrVolDate` varchar(16) DEFAULT NULL COMMENT '最低成交量(万份)2017-06-20',
  `mminTrVolPre` DECIMAL(32,16) DEFAULT NULL COMMENT '最低成交量(万份)1271.9121',
  `mnegotiableValue` DECIMAL(32,16) DEFAULT NULL,
  `mnegotiableValuePre` DECIMAL(32,16) DEFAULT NULL,
  `mprofitRate` VARCHAR(16) DEFAULT NULL,
  `mprofitRatePre` varchar(16) DEFAULT NULL,
  `mtotalAmt` DECIMAL(32,16) DEFAULT NULL COMMENT  '总成交金额(亿元)5.81',
  `mtotalAmtPre` DECIMAL(32,16) DEFAULT NULL COMMENT '总成交金额(亿元)5.81265539',
  `mtotalTx` DECIMAL(32,16) DEFAULT NULL COMMENT '总成交笔数(万笔)4.16',
  `mtotalTxDate` varchar(16) DEFAULT NULL COMMENT '总成交笔数22',
  `mtotalTxPre` DECIMAL(32,16) DEFAULT NULL COMMENT '总成交笔数(万笔)4.1638',
  `mtotalVol` DECIMAL(32,16) DEFAULT NULL COMMENT '总成交量(万份)63541.55',
  `mtotalVolPre` DECIMAL(32,16) DEFAULT NULL COMMENT '总成交量(万份)63541.5481',
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8mb4 comment='月基金成交概況';
create index idx_jijin_daily_ct on jijin_monthly(create_time);

DROP TABLE IF EXISTS `atomic_task` ;
create TABLE `atomic_task`(
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `task_name` varchar(64) DEFAULT  NULL COMMENT 'task名稱',
  `task_no` varchar(64) DEFAULT NULL COMMENT 'task number',
  `status` VARCHAR(32) DEFAULT NULL COMMENT '狀態;START,PROCESSING,SUCCESS,FAILED',
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8mb4 comment='原子任務表';
alter table `atomic_task` add UNIQUE INDEX `idx_atomic_task_name_no` (`task_name`, `task_no`);


create table `fund_product_info`(
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `product_code` varchar(8) DEFAULT NULL COMMENT '產品代碼',
  `product_name` varchar(128) DEFAULT NULL COMMENT '產品名稱',
  `product_type` varchar(128) DEFAULT NULL COMMENT '產品類型,股票型',
  `net_worth` DECIMAL(8,4) DEFAULT NULL COMMENT '單位淨值',
  `net_worth_date` varchar(128) DEFAULT NULL COMMENT '日期',
  `grow_rate_daily` DECIMAL(8,4) DEFAULT NULL COMMENT '日增長率',
  `grow_rate_weekly` DECIMAL(8,4) DEFAULT NULL COMMENT '近一周增長率',
  `grow_rate_1month` DECIMAL(8,4) DEFAULT NULL COMMENT '近1月增長率',
  `grow_rate_3month` DECIMAL(8,4) DEFAULT NULL COMMENT '近3月增長率',
  `grow_rate_6month` DECIMAL(8,4) DEFAULT NULL COMMENT '近6月增長率',
  `grow_rate_1year` DECIMAL(8,4) DEFAULT NULL COMMENT '近1年增長率',
  `grow_rate_2year` DECIMAL(8,4) DEFAULT NULL COMMENT '近2年增長率',
  `grow_rate_3year` DECIMAL(8,4) DEFAULT NULL COMMENT '近3年增長率',
  `grow_rate_this_year` DECIMAL(8,4) DEFAULT NULL COMMENT '今年以來增長率',
  `grow_rate_from_born` DECIMAL(8,4) DEFAULT NULL COMMENT '成立以來增長率',
  `fee_rate_buy_show` varchar(128) DEFAULT NULL COMMENT '手續費展示',
  `fee_rate_buy_real` varchar(128) DEFAULT NULL COMMENT '手續費真實',
  `fee_rate_redeem` varchar(128) DEFAULT NULL COMMENT '贖回手續費',
  `min_buy_amount` varchar(128) DEFAULT NULL COMMENT '起購金額',
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8mb4 comment='基金產品信息';
create index idx_fd_product_info on fund_product_info(create_time);



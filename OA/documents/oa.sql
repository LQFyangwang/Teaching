CREATE DATABASE IF NOT EXISTS oa1 DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

/**
	部门表（周敏祥）
*/
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `depid` varchar(32) NOT NULL COMMENT '部门编号',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `empid` varchar(32) DEFAULT NULL COMMENT '负责人编号',
  `des` varchar(500) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认可用',
  PRIMARY KEY (`depid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	员工角色表
*/
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `roleid` varchar(32) NOT NULL COMMENT '角色编号',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `des` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	员工表
*/
DROP TABLE IF EXISTS `t_emp`;
CREATE TABLE `t_emp` (
  `empid` varchar(32) NOT NULL COMMENT '员工编号',
  `depid` varchar(32) NOT NULL COMMENT '部门编号，来源于部门表',
  `roleid` varchar(32) NOT NULL COMMENT '角色编号',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `pwd` varchar(50) NOT NULL COMMENT '密码',
  `idcard` varchar(18) NOT NULL COMMENT '身份证号',
  `nation` varchar(10) DEFAULT NULL COMMENT '籍贯',
  `gender` varchar(4) NOT NULL COMMENT '性别',
  `fingerno` varchar(10) NOT NULL COMMENT '指纹编号',
  `birthday` datetime NOT NULL COMMENT '出生日期',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `qq` varchar(15) DEFAULT NULL COMMENT 'qq号',
  `wechat` varchar(50) DEFAULT NULL COMMENT '微信号',
  `address` varchar(255) DEFAULT NULL COMMENT '家庭地址',
  `married` varchar(4) DEFAULT NULL COMMENT '是否结婚',
  `contactname` varchar(20) DEFAULT NULL COMMENT '联系人姓名',
  `contactphone` varchar(11) DEFAULT NULL COMMENT '联系人手机号',
  `college` varchar(20) DEFAULT NULL COMMENT '毕业院校',
  `major` varchar(20) DEFAULT NULL COMMENT '专业',
  `eduback` varchar(5) DEFAULT NULL COMMENT '学历',
  `bankname` varchar(50) DEFAULT NULL COMMENT '开户行名称',
  `accountname` varchar(20) DEFAULT NULL COMMENT '银行卡姓名',
  `accountno` varchar(30) DEFAULT NULL COMMENT '银行账号',
  `alipay` varchar(100) DEFAULT NULL COMMENT '支付宝账号',
  `hireday` datetime NOT NULL COMMENT '入职时间',
  `resignday` datetime DEFAULT NULL COMMENT '离职时间',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，默认可用',
  PRIMARY KEY (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	员工教育背景表
*/
DROP TABLE IF EXISTS `t_edu`;
CREATE TABLE `t_edu` (
  `eduid` varchar(32) NOT NULL COMMENT '教育经历编号',
  `empid` varchar(32) NOT NULL COMMENT '员工编号，来源于员工表',
  `school` varchar(50) DEFAULT NULL COMMENT '毕业学校',
  `startday` datetime DEFAULT NULL COMMENT '开始时间',
  `endday` datetime DEFAULT NULL COMMENT '结束时间',
  `des` varchar(500) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`eduid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	员工工作经历表
*/
DROP TABLE IF EXISTS `t_exp`;
CREATE TABLE `t_exp` (
  `expid` varchar(32) NOT NULL COMMENT '工作经历编号',
  `empid` varchar(32) NOT NULL COMMENT '员工编号，来源于员工表',
  `company` varchar(50) DEFAULT NULL COMMENT '公司名称',
  `startday` datetime DEFAULT NULL COMMENT '入职时间',
  `endday` datetime DEFAULT NULL COMMENT '离职时间',
  `jobtitle` varchar(50) DEFAULT NULL COMMENT '职位',
  `des` varchar(500) DEFAULT NULL COMMENT '工作描述',
  PRIMARY KEY (`expid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	值班表
*/
DROP TABLE IF EXISTS `t_duty`;
CREATE TABLE `t_duty` (
  `dutyid` varchar(32) NOT NULL COMMENT '值班编号',
  `weekday` varchar(10) DEFAULT NULL COMMENT '周几',
  `empid1` varchar(32) DEFAULT NULL COMMENT '员工1',
  `add1` varchar(20) DEFAULT NULL COMMENT '地点1',
  `empid2` varchar(32) DEFAULT NULL,
  `add2` varchar(32) DEFAULT NULL,
  `empid3` varchar(32) DEFAULT NULL,
  `add3` varchar(20) DEFAULT NULL,
  `empid4` varchar(32) DEFAULT NULL,
  `add4` varchar(20) DEFAULT NULL,
  `empid5` varchar(32) DEFAULT NULL,
  `add5` varchar(20) DEFAULT NULL,
  `empid6` varchar(32) DEFAULT NULL,
  `add6` varchar(20) DEFAULT NULL,
  `empid7` varchar(32) DEFAULT NULL,
  `add7` varchar(20) DEFAULT NULL,
  `empid8` varchar(32) DEFAULT NULL,
  `add8` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`dutyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	员工考勤时间信息表(杨泽桥)
*/
DROP TABLE IF EXISTS `t_empcheckinginfo`;
CREATE TABLE `t_empcheckinginfo` (
  `checkinginfoid` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `checkingtime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`checkinginfoid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	员工考勤表
*/
DROP TABLE IF EXISTS `t_empchecking`;
CREATE TABLE `t_empchecking` (
  `checkingid` varchar(32) NOT NULL COMMENT '考勤编号',
  `empid` varchar(32) NOT NULL COMMENT '员工编号',
  `checkingday` datetime DEFAULT NULL COMMENT '打卡日期',
  `time1` datetime DEFAULT NULL COMMENT '时间点1',
  `time2` datetime DEFAULT NULL,
  `time3` datetime DEFAULT NULL,
  `time4` datetime DEFAULT NULL,
  `time5` datetime DEFAULT NULL,
  `time6` datetime DEFAULT NULL,
  `time7` datetime DEFAULT NULL,
  `time8` datetime DEFAULT NULL,
  PRIMARY KEY (`checkingid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	员工考勤申诉表
*/
DROP TABLE IF EXISTS `t_empappeal`;
CREATE TABLE `t_empappeal` (
  `appealid` varchar(32) NOT NULL COMMENT '请假编号',
  `empid` varchar(32) NOT NULL COMMENT '员工编号',
  `appealday` datetime DEFAULT NULL COMMENT '提交时间',
  `des` varchar(255) DEFAULT NULL COMMENT '请假描述',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  `firstlevel` int(11) DEFAULT '0' COMMENT '直属上司审核，默认为不通过',
  `secondlevel` int(11) DEFAULT '0' COMMENT '老板审核，默认为不通过',
  `pass` int(11) DEFAULT '0' COMMENT '通过状态，默认为不通过',
  PRIMARY KEY (`appealid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	员工反馈表
*/
DROP TABLE IF EXISTS `t_empfeedback`;
CREATE TABLE `t_empfeedback` (
  `feedbackid` varchar(32) NOT NULL COMMENT '反馈编号',
  `empid` varchar(32) NOT NULL COMMENT '员工编号',
  `feedbackday` datetime DEFAULT NULL COMMENT '反馈时间',
  `des` varchar(500) DEFAULT NULL COMMENT '反馈详情',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  PRIMARY KEY (`feedbackid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	研讨会表
*/
DROP TABLE IF EXISTS `t_meetting`;
CREATE TABLE `t_meetting` (
  `meettingid` varchar(32) NOT NULL COMMENT '研讨会编号',
  `empid` varchar(32) NOT NULL COMMENT '主持人编号（员工编号）',
  `meettingday` datetime DEFAULT NULL COMMENT '研讨会时间',
  `createdday` datetime DEFAULT NULL COMMENT '添加时间',
  `des` varchar(500) DEFAULT NULL COMMENT '研讨会详情',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  PRIMARY KEY (`meettingid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	工作日志表
*/
DROP TABLE IF EXISTS `t_report`;
CREATE TABLE `t_report` (
  `reportid` varchar(32) NOT NULL COMMENT '工作日志编号',
  `empid` varchar(32) NOT NULL COMMENT '员工编号',
  `reportday` datetime DEFAULT NULL COMMENT '日志时间',
  `des` varchar(500) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  PRIMARY KEY (`reportid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	员工请假表（王建强）
*/
DROP TABLE IF EXISTS `t_empleave`;
CREATE TABLE `t_empleave` (
  `leaveid` varchar(32) NOT NULL COMMENT '请假编号',
  `empid` varchar(32) NOT NULL COMMENT '员工编号',
  `starttime` datetime DEFAULT NULL COMMENT '开始时间',
  `endtime` datetime DEFAULT NULL COMMENT '结束时间',
  `leaveday` datetime DEFAULT NULL COMMENT '提交时间',
  `des` varchar(255) DEFAULT NULL COMMENT '请假描述',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  `firstlevel` int(11) DEFAULT '0' COMMENT '直属上司审核，默认为不通过',
  `secondlevel` int(11) DEFAULT '0' COMMENT '老板审核，默认为不通过',
  `pass` int(11) DEFAULT '0' COMMENT '通过状态，默认为不通过',
  PRIMARY KEY (`leaveid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	工资基本信息表
*/
DROP TABLE IF EXISTS `t_salaryinfo`;
CREATE TABLE `t_salaryinfo` (
  `salaryinfoid` varchar(32) NOT NULL COMMENT '工资情况编号',
  `empid` varchar(32) NOT NULL COMMENT '员工编号',
  `basicsalary` double NOT NULL COMMENT '基本工资',
  `jobsalary` double NOT NULL COMMENT '岗位工资',
  PRIMARY KEY (`salaryinfoid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	工资发放表
*/
DROP TABLE IF EXISTS `t_salary`;
CREATE TABLE `t_salary` (
  `salaryid` varchar(32) NOT NULL COMMENT '工资发放编号',
  `empid` varchar(32) NOT NULL COMMENT '员工编号',
  `extrasalary` double DEFAULT NULL COMMENT '奖励',
  `subsalary` double DEFAULT NULL COMMENT '扣罚',
  `salaryday` datetime DEFAULT NULL COMMENT '发放时间',
  `totalsalary` double DEFAULT NULL COMMENT '总工资',
  PRIMARY KEY (`salaryid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	课程表
*/
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
  `courseid` varchar(32) NOT NULL COMMENT '课程编号',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `des` varchar(255) DEFAULT NULL COMMENT '描述',
  `totalhour` int(11) DEFAULT NULL COMMENT '总课时数',
  `term` int(11) DEFAULT NULL COMMENT '学期',
  `courseorder` int(11) DEFAULT NULL COMMENT '课程顺序',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  PRIMARY KEY (`courseid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	班级表
*/
DROP TABLE IF EXISTS `t_grade`;
CREATE TABLE `t_grade` (
  `gradeid` varchar(32) NOT NULL COMMENT '班级编号',
  `name` varchar(20) DEFAULT NULL COMMENT '班级名称',
  `empid1` varchar(32) NOT NULL COMMENT '班主任',
  `empid2` varchar(32) NOT NULL COMMENT '任课老师',
  `empid3` varchar(32) NOT NULL COMMENT '辅导老师',
  `des` varchar(500) DEFAULT NULL COMMENT '描述',
  `quantity` int(11) NOT NULL COMMENT '班级最大人数',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  PRIMARY KEY (`gradeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	课程进度表
*/
DROP TABLE IF EXISTS `t_progress`;
CREATE TABLE `t_progress` (
  `progressid` varchar(32) NOT NULL COMMENT '课程进度编号',
  `empid` varchar(32) NOT NULL COMMENT '员工编号',
  `gradeid` varchar(32) NOT NULL COMMENT '班级编号',
  `des` varchar(500) DEFAULT NULL COMMENT '进度详情',
  `progressday` datetime DEFAULT NULL COMMENT '添加进度的时间',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  PRIMARY KEY (`progressid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	宿舍表（阿沙）
*/
DROP TABLE IF EXISTS `t_room`;
CREATE TABLE `t_room` (
  `roomid` varchar(32) NOT NULL COMMENT '宿舍编号',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `stuid` varchar(32) DEFAULT NULL COMMENT '宿舍长编号',
  `quantity` int(11) NOT NULL COMMENT '宿舍最大人数',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  PRIMARY KEY (`roomid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	学生信息表
*/
DROP TABLE IF EXISTS `t_stu`;
CREATE TABLE `t_stu` (
  `stuid` varchar(32) NOT NULL COMMENT '学生编号',
  `stuno` varchar(20) DEFAULT NULL COMMENT '学号',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `pwd` varchar(50) DEFAULT NULL COMMENT '密码',
  `idcard` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `qq` varchar(20) DEFAULT NULL COMMENT 'qq号',
  `wechat` varchar(50) DEFAULT NULL COMMENT '微信号',
  `school` varchar(50) DEFAULT NULL COMMENT '毕业学校',
  `age` int(11) NOT NULL COMMENT '年龄',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `gender` varchar(5) NOT NULL COMMENT '性别',
  `address` varchar(255) DEFAULT NULL COMMENT '家庭地址',
  `nation` varchar(20) DEFAULT NULL COMMENT '籍贯',
  `residence` varchar(10) DEFAULT NULL COMMENT '户口性质',
  `gradeid` varchar(32) DEFAULT NULL COMMENT '班级编号',
  `roomid` varchar(32) DEFAULT NULL COMMENT '宿舍编号',
  `parentname` varchar(20) DEFAULT NULL COMMENT '家长姓名',
  `parentphone` varchar(11) DEFAULT NULL COMMENT '家长手机号',
  `startday` datetime DEFAULT NULL COMMENT '入学时间',
  `empid` varchar(32) DEFAULT '' COMMENT '招生老师',
  `des` varchar(500) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  `stustatus` varchar(10) DEFAULT NULL COMMENT '学生状态，包括"意向","预定","正式"',
  `role` varchar(25) DEFAULT NULL COMMENT '班干部角色',
  `roleid` varchar(32) DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`stuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	学生考试成绩表
*/
DROP TABLE IF EXISTS `t_score`;
CREATE TABLE `t_score` (
  `scoreid` varchar(32) NOT NULL COMMENT '成绩编号',
  `stuid` varchar(32) NOT NULL COMMENT '学生编号',
  `courseid` varchar(32) NOT NULL COMMENT '课程编号',
  `score` float DEFAULT NULL COMMENT '成绩',
  `testday` datetime DEFAULT NULL COMMENT '考试时间',
  PRIMARY KEY (`scoreid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	学生考勤表
*/
DROP TABLE IF EXISTS `t_stuchecking`;
CREATE TABLE `t_stuchecking` (
  `checkingid` varchar(32) NOT NULL COMMENT '学生考勤编号',
  `stuid` varchar(32) NOT NULL COMMENT '学生编号',
  `checkingday` datetime DEFAULT NULL COMMENT '考勤日期',
  `checking1` varchar(20) DEFAULT NULL COMMENT '上午',
  `checking2` varchar(20) DEFAULT NULL COMMENT '中午',
  `checking3` varchar(20) DEFAULT NULL COMMENT '下午',
  PRIMARY KEY (`checkingid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	学生反馈表
*/
DROP TABLE IF EXISTS `t_stufeedback`;
CREATE TABLE `t_stufeedback` (
  `feedbackid` varchar(32) NOT NULL COMMENT '反馈编号',
  `stuid` varchar(32) NOT NULL COMMENT '学生编号',
  `feedbackday` datetime DEFAULT NULL COMMENT '反馈时间',
  `des` varchar(500) DEFAULT NULL COMMENT '反馈详情',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认可用',
  PRIMARY KEY (`feedbackid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	学生请假表
*/
DROP TABLE IF EXISTS `t_stuleave`;
CREATE TABLE `t_stuleave` (
  `leaveid` varchar(32) NOT NULL COMMENT '学生请假编号',
  `stuid` varchar(32) NOT NULL COMMENT '学生编号',
  `starttime` datetime DEFAULT NULL COMMENT '开始时间',
  `endtime` datetime DEFAULT NULL COMMENT '结束时间',
  `leaveday` datetime DEFAULT NULL COMMENT '提交时间',
  `des` varchar(255) DEFAULT NULL COMMENT '请假说明',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  `firstlevel` int(11) DEFAULT '0' COMMENT '任课老师审核，默认不通过',
  `secondlevel` int(11) DEFAULT '0' COMMENT '班主任审核，默认不通过',
  `pass` int(11) DEFAULT '0' COMMENT '通过状态，默认为不通过',
  PRIMARY KEY (`leaveid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	学生谈心表（锦辉）
*/	
DROP TABLE IF EXISTS `t_talk`;
CREATE TABLE `t_talk` (
  `talkid` varchar(32) NOT NULL COMMENT '谈心编号',
  `empid` varchar(32) NOT NULL COMMENT '员工编号',
  `stuid` varchar(32) NOT NULL COMMENT '学生编号',
  `talkday` datetime DEFAULT NULL COMMENT '谈心时间',
  `des` varchar(500) DEFAULT NULL COMMENT '谈心详情',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用状态',
  PRIMARY KEY (`talkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	就业信息表
*/
DROP TABLE IF EXISTS `t_job`;
CREATE TABLE `t_job` (
  `jobid` varchar(32) NOT NULL COMMENT '工作编号',
  `stuid` varchar(32) NOT NULL COMMENT '学生编号',
  `company` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `jobtitle` varchar(50) DEFAULT NULL COMMENT '职位',
  `salary` double DEFAULT NULL COMMENT '月薪',
  `welfare` varchar(255) DEFAULT NULL COMMENT '福利待遇',
  `address` varchar(255) DEFAULT NULL COMMENT '公司地址',
  `comphone` varchar(11) DEFAULT NULL COMMENT '公司联系方式',
  `hireday` datetime DEFAULT NULL COMMENT '入职时间',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用状态',
  PRIMARY KEY (`jobid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	巡查表
*/
DROP TABLE IF EXISTS `t_check`;
CREATE TABLE `t_check` (
  `checkid` varchar(32) NOT NULL COMMENT '巡查编号',
  `empid` varchar(32) NOT NULL COMMENT '员工编号',
  `checktime` datetime DEFAULT NULL COMMENT '巡查时间',
  `weekday` varchar(10) DEFAULT NULL COMMENT '周几',
  `gradeid` varchar(32) DEFAULT NULL COMMENT '班级编号',
  `roomid` varchar(32) DEFAULT NULL COMMENT '宿舍编号',
  `des` varchar(500) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`checkid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	物品类型表
*/
DROP TABLE IF EXISTS `t_goodstype`;
CREATE TABLE `t_goodstype` (
  `goodstypeid` varchar(32) NOT NULL COMMENT '物品类型编号',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `des` varchar(500) DEFAULT NULL COMMENT '描述',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，默认可用',
  PRIMARY KEY (`goodstypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	物品表
*/
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `goodsid` varchar(32) NOT NULL COMMENT '物品编号',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `quantity` int(11) NOT NULL COMMENT '总数',
  `des` varchar(255) DEFAULT NULL COMMENT '描述',
  `unitprice` double DEFAULT NULL COMMENT '单价',
  `buyday` datetime DEFAULT NULL COMMENT '购买时间',
  `goodstypeid` varchar(32) NOT NULL COMMENT '物品类型编号，来源于物品类型表',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，默认可用',
  PRIMARY KEY (`goodsid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	物品申购表
*/
DROP TABLE IF EXISTS `t_goodsapp`;
CREATE TABLE `t_goodsapp` (
  `goodsappid` varchar(32) NOT NULL COMMENT '物品申购编号',
  `empid` varchar(32) NOT NULL COMMENT '员工编号',
  `appday` datetime DEFAULT NULL COMMENT '申购时间',
  `goodsname` varchar(50) DEFAULT NULL COMMENT '申购的物品名称',
  `quantity` int(11) DEFAULT NULL COMMENT '申购物品的数量',
  `des` varchar(500) DEFAULT NULL COMMENT '申购原因',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认可用',
  `appstatus` int(11) DEFAULT NULL COMMENT '申购状态，成功则为1，失败则为0',
  PRIMARY KEY (`goodsappid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	物品领用表(陈俊)
*/
DROP TABLE IF EXISTS `t_goodsuse`;
CREATE TABLE `t_goodsuse` (
  `useid` varchar(32) NOT NULL COMMENT '领用编号',
  `goodsid` varchar(32) NOT NULL COMMENT '物品编号，来源于物品表',
  `empid` varchar(32) NOT NULL COMMENT '员工编号，来源于员工表',
  `quantity` int(11) DEFAULT NULL COMMENT '领用的物品数量',
  `useday` datetime DEFAULT NULL COMMENT '信用时间',
  `ereturnday` datetime DEFAULT NULL COMMENT '预估归还时间',
  `returnday` datetime DEFAULT NULL COMMENT '归还时间',
  PRIMARY KEY (`useid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	收入类型表
*/
DROP TABLE IF EXISTS `t_incometype`;
CREATE TABLE `t_incometype` (
  `incometypeid` varchar(32) NOT NULL COMMENT '收入类别编号',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `des` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  PRIMARY KEY (`incometypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	收入表
*/
DROP TABLE IF EXISTS `t_income`;
CREATE TABLE `t_income` (
  `incomeid` varchar(32) NOT NULL COMMENT '收入编号',
  `incometypeid` varchar(32) NOT NULL COMMENT '收入类型编号',
  `incomeday` datetime DEFAULT NULL COMMENT '收入时间',
  `des` varchar(255) DEFAULT NULL COMMENT '收入详情',
  `income` double DEFAULT NULL COMMENT '收入金额',
  `empid` varchar(32) NOT NULL COMMENT '员工编号',
  `stuid` varchar(32) DEFAULT NULL COMMENT '学生编号',
  PRIMARY KEY (`incomeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	支出类型表
*/
DROP TABLE IF EXISTS `t_paytype`;
CREATE TABLE `t_paytype` (
  `paytypeid` varchar(32) NOT NULL COMMENT '支出类别编号',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `des` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  PRIMARY KEY (`paytypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	支出信息表
*/
DROP TABLE IF EXISTS `t_pay`;
CREATE TABLE `t_pay` (
  `payid` varchar(32) NOT NULL COMMENT '支出编号',
  `paytypeid` varchar(32) NOT NULL COMMENT '支出类别编号',
  `payday` datetime DEFAULT NULL COMMENT '支出时间',
  `des` varchar(255) DEFAULT NULL COMMENT '描述',
  `pay` double DEFAULT NULL COMMENT '支出金额',
  `empid` varchar(32) DEFAULT NULL COMMENT '员工编号',
  `toname` varchar(50) DEFAULT NULL COMMENT '收款人姓名',
  `tophone` varchar(11) DEFAULT NULL COMMENT '收款人联系方式',
  PRIMARY KEY (`payid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	公告类型表
*/
DROP TABLE IF EXISTS `t_noticetype`;
CREATE TABLE `t_noticetype` (
  `noticetypeid` varchar(32) NOT NULL COMMENT '公告类型编号',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `des` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  PRIMARY KEY (`noticetypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	公告表
*/
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `noticeid` varchar(32) NOT NULL COMMENT '公告编号',
  `name` varchar(50) DEFAULT NULL COMMENT '公告标题',
  `des` varchar(500) DEFAULT NULL COMMENT '公告详情',
  `typeid` varchar(32) NOT NULL COMMENT '公告类型',
  `noticeday` datetime DEFAULT NULL COMMENT '发布时间',
  `empid` varchar(32) NOT NULL COMMENT '发布人',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  PRIMARY KEY (`noticeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	学生总结表
*/	
DROP TABLE IF EXISTS `t_summary`;
CREATE TABLE `t_summary` (
  `summaryid` varchar(32) NOT NULL COMMENT '总结编号',
  `empid` varchar(32) NOT NULL COMMENT '员工编号',
  `stuid` varchar(32) NOT NULL COMMENT '学生编号',
  `summaryday` datetime DEFAULT NULL COMMENT '总结时间',
  `des` varchar(500) DEFAULT NULL COMMENT '总结详情',
  `term` int(11) DEFAULT NULL COMMENT '第几个学期的总结',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用状态',
  PRIMARY KEY (`summaryid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	权限和角色的关联关系表
*/
DROP TABLE IF EXISTS `t_authority_role`;
CREATE TABLE `t_authority_role` (
  `authorityroleid` int(11) NOT NULL AUTO_INCREMENT,
  `authorityid` int(11) NOT NULL COMMENT '权限表的id',
  `roleid` varchar(32) COMMENT '角色的id',
  PRIMARY KEY (`authorityroleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/**
	权限表
*/
DROP TABLE IF EXISTS `t_authority`;
CREATE TABLE `t_authority` (
  `authorityid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '类的全限定名，如：com.ht.action.EmpAction.pager',
  `des` varchar(255) NOT NULL COMMENT '对这个类里面的方法的描述，如：分页查找员工',
  PRIMARY KEY (`authorityid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

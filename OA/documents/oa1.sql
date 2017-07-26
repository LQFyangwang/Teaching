/*
Navicat MySQL Data Transfer

Source Server         : Mysql
Source Server Version : 50715
Source Host           : localhost:3306
Source Database       : oa

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2017-01-17 21:12:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_authority`;
CREATE TABLE `t_authority` (
  `authorityid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '类的全限定名，如：com.ht.action.EmpAction.pager',
  `des` varchar(255) NOT NULL COMMENT '对这个类里面的方法的描述，如：分页查找员工',
  PRIMARY KEY (`authorityid`)
) ENGINE=InnoDB AUTO_INCREMENT=216 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_authority
-- ----------------------------
INSERT INTO `t_authority` VALUES ('1', 'com.ht.action.ScoreAction.gradeByPager', '分页查询学生成绩');
INSERT INTO `t_authority` VALUES ('2', 'com.ht.action.StuLeaveAction.pager', '分页查询学生请假信息');
INSERT INTO `t_authority` VALUES ('3', 'com.ht.action.StuLeaveAction.frost', '冻结学生请假信息');
INSERT INTO `t_authority` VALUES ('4', 'com.ht.action.StuLeaveAction.activation', '激活学生请假信息');
INSERT INTO `t_authority` VALUES ('5', 'com.ht.action.StuLeaveAction.pagerByStuName', '根据学生姓名搜索学生的请假信息');
INSERT INTO `t_authority` VALUES ('6', 'com.ht.action.StuLeaveAction.pagerByLeaveDay', '根据请假的时间段搜索学生的请假信息');
INSERT INTO `t_authority` VALUES ('7', 'com.ht.action.StuFeedbackAction.pager', '分页查询所有的反馈信息');
INSERT INTO `t_authority` VALUES ('8', 'com.ht.action.StuFeedbackAction.frost', '冻结学生反馈信息');
INSERT INTO `t_authority` VALUES ('9', 'com.ht.action.StuFeedbackAction.activation', '激活学生反馈信息');
INSERT INTO `t_authority` VALUES ('10', 'com.ht.action.StuFeedbackAction.pagerByStuName', '根据学生姓名搜素学生的反馈信息');
INSERT INTO `t_authority` VALUES ('11', 'com.ht.action.StuFeedbackAction.pagerByDay', '根据反馈的时间段来搜索学生的反馈信息');
INSERT INTO `t_authority` VALUES ('12', 'com.ht.action.JobAction.pager', '分页查询学生就业的信息');
INSERT INTO `t_authority` VALUES ('13', 'com.ht.action.JobAction.frost', '冻结学生就业情况的信息');
INSERT INTO `t_authority` VALUES ('14', 'com.ht.action.JobAction.activation', '激活学生就业情况的信息');
INSERT INTO `t_authority` VALUES ('15', 'com.ht.action.JobAction.pagerByStuName', '根据学生姓名搜索学生的就业情况信息');
INSERT INTO `t_authority` VALUES ('16', 'com.ht.action.JobAction.pagerByDay', '根据学生就业的时间段搜索学生的就业情况信息');
INSERT INTO `t_authority` VALUES ('17', 'com.ht.action.JobAction.add', '添加学生的就业情况信息');
INSERT INTO `t_authority` VALUES ('18', 'com.ht.action.JobAction.edit', '编辑学生的就业情况信息');
INSERT INTO `t_authority` VALUES ('19', 'com.ht.action.TalkAction.pager', '分页查询所有的学生谈心信息');
INSERT INTO `t_authority` VALUES ('20', 'com.ht.action.TalkAction.frost', '冻结学生的谈心信息');
INSERT INTO `t_authority` VALUES ('21', 'com.ht.action.TalkAction.activation', '激活学生的谈心信息');
INSERT INTO `t_authority` VALUES ('22', 'com.ht.action.TalkAction.pagerByName', '根据老师或者学生的姓名搜素谈心信息');
INSERT INTO `t_authority` VALUES ('23', 'com.ht.action.TalkAction.pagerByDay', '根据谈心的时间段搜索谈心信息');
INSERT INTO `t_authority` VALUES ('24', 'com.ht.action.TalkAction.add', '添加学生谈心信息');
INSERT INTO `t_authority` VALUES ('25', 'com.ht.action.TalkAction.edit', '修改学生谈心信息');
INSERT INTO `t_authority` VALUES ('26', 'com.ht.action.StuCheckingAction.gradeByPager', '根据班级id和月份分页查询学生的考勤信息');
INSERT INTO `t_authority` VALUES ('27', 'com.ht.action.SummaryAction.pager', '分页查询所有的学生总结信息');
INSERT INTO `t_authority` VALUES ('28', 'com.ht.action.SummaryAction.frost', '冻结学生的总结信息');
INSERT INTO `t_authority` VALUES ('29', 'com.ht.action.SummaryAction.activation', '激活学生的总结信息');
INSERT INTO `t_authority` VALUES ('30', 'com.ht.action.SummaryAction.pagerByName', '根据老师姓名或者学生姓名搜索总结信息');
INSERT INTO `t_authority` VALUES ('31', 'com.ht.action.SummaryAction.pagerByDay', '根据总结的时间段搜索总结信息');
INSERT INTO `t_authority` VALUES ('32', 'com.ht.action.SummaryAction.add', '添加总结信息');
INSERT INTO `t_authority` VALUES ('33', 'com.ht.action.SummaryAction.edit', '修改总结信息');
INSERT INTO `t_authority` VALUES ('34', 'com.ht.action.SalaryInfoAction1.pager', '分页查询所有的员工的工资信息');
INSERT INTO `t_authority` VALUES ('35', 'com.ht.action.SalaryInfoAction1.pagerByName', '根据员工姓名搜索员工的工资信息');
INSERT INTO `t_authority` VALUES ('36', 'com.ht.action.SalaryInfoAction1.pagerByDay', '根据发放工资的时间段搜索员工的工资信息');
INSERT INTO `t_authority` VALUES ('37', 'com.ht.action.SalaryInfoAction1.add', '添加员工的工资信息');
INSERT INTO `t_authority` VALUES ('38', 'com.ht.action.SalaryInfoAction1.edit', '修改员工工资信息');
INSERT INTO `t_authority` VALUES ('39', 'com.ht.action.RoomAction.pager', '分页查询宿舍');
INSERT INTO `t_authority` VALUES ('40', 'com.ht.action.RoomAction.save', '添加宿舍');
INSERT INTO `t_authority` VALUES ('41', 'com.ht.action.RoomAction.update', '修改宿舍');
INSERT INTO `t_authority` VALUES ('42', 'com.ht.action.RoomAction.frost', '冻结宿舍');
INSERT INTO `t_authority` VALUES ('43', 'com.ht.action.RoomAction.activation', '激活宿舍');
INSERT INTO `t_authority` VALUES ('44', 'com.ht.action.RoomAction.fuzzySearch', '根据宿舍名称模糊搜索');
INSERT INTO `t_authority` VALUES ('45', 'com.ht.action.GradeAction.pager', '分页查询班级');
INSERT INTO `t_authority` VALUES ('46', 'com.ht.action.GradeAction.save', '添加班级');
INSERT INTO `t_authority` VALUES ('47', 'com.ht.action.GradeAction.update', '修改班级');
INSERT INTO `t_authority` VALUES ('48', 'com.ht.action.GradeAction.frost', '冻结班级');
INSERT INTO `t_authority` VALUES ('49', 'com.ht.action.GradeAction.activation', '激活班级');
INSERT INTO `t_authority` VALUES ('50', 'com.ht.action.GradeAction.fuzzySearch', '根据班级名称模糊搜索');
INSERT INTO `t_authority` VALUES ('51', 'com.ht.action.StuAction.intentionStuByPager', '意向学生分页');
INSERT INTO `t_authority` VALUES ('52', 'com.ht.action.StuAction.reserveStuByPager', '预定学生分页');
INSERT INTO `t_authority` VALUES ('53', 'com.ht.action.StuAction.officialStuByPager', '正式学生分页');
INSERT INTO `t_authority` VALUES ('54', 'com.ht.action.StuAction.save?flag=1', '添加意向');
INSERT INTO `t_authority` VALUES ('55', 'com.ht.action.StuAction.update?flag=1', '修改意向');
INSERT INTO `t_authority` VALUES ('56', 'com.ht.action.StuAction.updateStuStatus', '转为预定，正式学生');
INSERT INTO `t_authority` VALUES ('57', 'com.ht.action.StuAction.frost', '冻结学生');
INSERT INTO `t_authority` VALUES ('58', 'com.ht.action.StuAction.activation', '激活学生');
INSERT INTO `t_authority` VALUES ('59', 'com.ht.action.StuAction.addStusToGrade', '班级批量添加学生');
INSERT INTO `t_authority` VALUES ('60', 'com.ht.action.StuAction.addStusToRoom', '宿舍批量添加学生');
INSERT INTO `t_authority` VALUES ('61', 'com.ht.action.StuAction.fuzzySearch1', '根据学生的名字模糊搜索，意向');
INSERT INTO `t_authority` VALUES ('62', 'com.ht.action.StuAction.fuzzySearch2', '根据学生的名字模糊搜索，预定');
INSERT INTO `t_authority` VALUES ('63', 'com.ht.action.StuAction.fuzzySearch3', '根据学生的名字模糊搜索，正式');
INSERT INTO `t_authority` VALUES ('64', 'com.ht.action.StuAction.details', '预定，正式学生详情页');
INSERT INTO `t_authority` VALUES ('65', 'com.ht.action.StuAction.officialStuByPager1', '班级添加学生时分页查找正式学生班级id为空的');
INSERT INTO `t_authority` VALUES ('66', 'com.ht.action.StuAction.roomByPager', '根据宿舍id分页查询该宿舍下的所有学生');
INSERT INTO `t_authority` VALUES ('67', 'com.ht.action.StuAction.gradeByPager', '宿舍添加学生时按班级查找宿舍id为空的学生');
INSERT INTO `t_authority` VALUES ('68', 'com.ht.action.StuAction.gradeIdByPager', '根据班级id查询该班级的所有学生');
INSERT INTO `t_authority` VALUES ('69', 'com.ht.action.StuAction.query_type', '添加收入时添加学生的下拉框');
INSERT INTO `t_authority` VALUES ('70', 'com.ht.action.RoleAction.add', '添加角色');
INSERT INTO `t_authority` VALUES ('71', 'com.ht.action.RoleAction.update', '修改角色');
INSERT INTO `t_authority` VALUES ('72', 'com.ht.action.RoleAction.query', '分页查询数据库中的数据');
INSERT INTO `t_authority` VALUES ('73', 'com.ht.action.RoleAction.fuzzySearch', '根据名称查询角色id');
INSERT INTO `t_authority` VALUES ('74', 'com.ht.action.RoleAction.frost', '根据对象的简单类名和id字段的名称，和状态来更新状态');
INSERT INTO `t_authority` VALUES ('75', 'com.ht.action.RoleAction.activation', '根据对象的简单类名和id字段的名称，和状态来更新状态');
INSERT INTO `t_authority` VALUES ('76', 'com.ht.action.NoticeAction.add', '添加系统公告');
INSERT INTO `t_authority` VALUES ('77', 'com.ht.action.NoticeAction.update', '修改系统公告');
INSERT INTO `t_authority` VALUES ('78', 'com.ht.action.NoticeAction.query', '分页查询数据库中的数据');
INSERT INTO `t_authority` VALUES ('79', 'com.ht.action.NoticeAction.pagerByDay', '根据时间段搜索');
INSERT INTO `t_authority` VALUES ('80', 'com.ht.action.NoticeAction.frost', '根据对象的简单类名和id字段的名称，和状态来更新状态');
INSERT INTO `t_authority` VALUES ('81', 'com.ht.action.NoticeAction.activation', '根据对象的简单类名和id字段的名称，和状态来更新状');
INSERT INTO `t_authority` VALUES ('82', 'com.ht.action.NoticeTypeAction.add', '添加公告类型');
INSERT INTO `t_authority` VALUES ('83', 'com.ht.action.NoticeTypeAction.update', '修改公告类型');
INSERT INTO `t_authority` VALUES ('84', 'com.ht.action.NoticeTypeAction.querypager', '分页查询数据库的数');
INSERT INTO `t_authority` VALUES ('85', 'com.ht.action.NoticeTypeAction.frost', '根据对象的简单类名和id字段的名称，和状态来更新状态');
INSERT INTO `t_authority` VALUES ('86', 'com.ht.action.NoticeTypeAction.activation', '根据对象的简单类名和id字段的名称，和状态来更新状态');
INSERT INTO `t_authority` VALUES ('87', 'com.ht.action.EmpCheckingInfoAction.add', '添加打卡时间');
INSERT INTO `t_authority` VALUES ('88', 'com.ht.action.EmpCheckingInfoAction.update', '修改打卡时间');
INSERT INTO `t_authority` VALUES ('89', 'com.ht.action.EmpCheckingInfoAction.query', '分页查询数据库中的数据');
INSERT INTO `t_authority` VALUES ('90', 'com.ht.action.EmpAction.empSave', '添加员工');
INSERT INTO `t_authority` VALUES ('91', 'com.ht.action.EmpAction.empPager', '分页查询员工');
INSERT INTO `t_authority` VALUES ('92', 'com.ht.action.EmpAction.empUpdate', '更新员工');
INSERT INTO `t_authority` VALUES ('93', 'com.ht.action.EmpAction.empInformat', '更新个人信息');
INSERT INTO `t_authority` VALUES ('94', 'com.ht.action.EmpAction.frost', '状态的冻结');
INSERT INTO `t_authority` VALUES ('95', 'com.ht.action.EmpAction.activation', '状态的激活');
INSERT INTO `t_authority` VALUES ('96', 'com.ht.action.EmpAction.fuzzySearch', '根据员工的名称模糊查询');
INSERT INTO `t_authority` VALUES ('97', 'com.ht.action.DeptAction.DeptTypeAll', '查询出所有的部门用于显示到combobx');
INSERT INTO `t_authority` VALUES ('98', 'com.ht.action.GoodsAction.goodsPage', '分页查询物品');
INSERT INTO `t_authority` VALUES ('99', 'com.ht.action.GoodsAction.goodsUpdate ', '更新物品');
INSERT INTO `t_authority` VALUES ('100', 'com.ht.action.GoodsAction.goodsType', '查询出所有的物品类型用于显示到combobx');
INSERT INTO `t_authority` VALUES ('101', 'com.ht.action.GoodsAction.frost ', '状态的冻结');
INSERT INTO `t_authority` VALUES ('102', 'com.ht.action.GoodsAction.activation', '状态的激活');
INSERT INTO `t_authority` VALUES ('103', 'com.ht.action.GoodsAppAction.goodsAppPager', '分页查询物品的申购');
INSERT INTO `t_authority` VALUES ('104', 'com.ht.action.GoodsAppAction.update', '更新物品申购');
INSERT INTO `t_authority` VALUES ('105', 'com.ht.action.GoodsAppAction.addGoodsAPP', '添加物品申购');
INSERT INTO `t_authority` VALUES ('106', 'com.ht.action.GoodsAppAction.frost', '状态的冻结');
INSERT INTO `t_authority` VALUES ('107', 'com.ht.action.GoddsAppAction.activation ', '状态的激活');
INSERT INTO `t_authority` VALUES ('108', 'com.ht.action.GoodsTypeAction.goodsTypeSave', '添加物品类型');
INSERT INTO `t_authority` VALUES ('109', 'com.ht.action.GoodsTypeAction.goodsTypePage', '分页查询物品类型');
INSERT INTO `t_authority` VALUES ('110', 'com.ht.action.GoodsTypeAction.goodsUpdate', '物品类型的更新');
INSERT INTO `t_authority` VALUES ('111', 'com.ht.action.GoodsTypeAction.frost', '状态的冻结');
INSERT INTO `t_authority` VALUES ('112', 'com.ht.action.GoodsTypeAction.activation', '状态的激活');
INSERT INTO `t_authority` VALUES ('113', 'com.ht.action.GoodsUseAction.GoodsUseSave ', '添加物品领用');
INSERT INTO `t_authority` VALUES ('114', 'com.ht.action.GoodsUseAction.GoodsUsePage', '分页查询物品领用');
INSERT INTO `t_authority` VALUES ('115', 'com.ht.action.GoodsUseAction.GoodsUseUpdate', '更新物品领用');
INSERT INTO `t_authority` VALUES ('116', 'com.ht.action.CourseAction.add', '添加课程');
INSERT INTO `t_authority` VALUES ('117', 'com.ht.action.CourseAction.update', '修改课程');
INSERT INTO `t_authority` VALUES ('118', 'com.ht.action.CourseAction.pager', '分页查询数据库中的数据');
INSERT INTO `t_authority` VALUES ('119', 'com.ht.action.CourseAction.frost', '根据对象的简单类名和id字段的名称，和状态来更新状态');
INSERT INTO `t_authority` VALUES ('120', 'com.ht.action.CourseAction.activation', '根据对象的简单类名和id字段的名称，和状态来更新状态');
INSERT INTO `t_authority` VALUES ('121', 'com.ht.action.CourseAction.fuzzySearch', '根据课程的名称来进行模糊查询');
INSERT INTO `t_authority` VALUES ('122', 'com.ht.action.EmpFeedBackAction.pager', '分页查询数据库中的数据');
INSERT INTO `t_authority` VALUES ('123', 'com.ht.action.EmpFeedBackAction.add', '添加员工反馈');
INSERT INTO `t_authority` VALUES ('124', 'com.ht.action.EmpFeedBackAction.update', '修改员工反馈');
INSERT INTO `t_authority` VALUES ('125', 'com.ht.action.EmpFeedBackAction.frost', '根据对象的简单类名和id字段的名称，和状态来更新状态');
INSERT INTO `t_authority` VALUES ('126', 'com.ht.action.EmpFeedBackAction.activation', '根据对象的简单类名和id字段的名称，和状态来更新状态');
INSERT INTO `t_authority` VALUES ('127', 'com.ht.action.EmpFeedBackAction.pagerByEmpName', '根据员工姓名和类对象的简单类名模糊搜索数据');
INSERT INTO `t_authority` VALUES ('128', 'com.ht.action.EmpFeedBackAction.pagerByDay', '根据时间段搜索，根据传递进来的开始时间和结束时间，和对象的简单类名，和开始对象中时间的属性名称进行搜索');
INSERT INTO `t_authority` VALUES ('129', 'com.ht.action.MeettingAction.pager', '分页查询数据库中的数据');
INSERT INTO `t_authority` VALUES ('130', 'com.ht.action.MeettingAction.frost', '根据对象的简单类名和id字段的名称，和状态来更新状态');
INSERT INTO `t_authority` VALUES ('131', 'com.ht.action.MeettingAction.activation', '根据对象的简单类名和id字段的名称，和状态来更新状态');
INSERT INTO `t_authority` VALUES ('132', 'com.ht.action.MeettingAction.add', '添加研讨会');
INSERT INTO `t_authority` VALUES ('133', 'com.ht.action.MeettingAction.update', '修改研讨会');
INSERT INTO `t_authority` VALUES ('134', 'com.ht.action.MeettingAction.pagerByDay', '根据时间段搜索，根据传递进来的开始时间和结束时间，和对象的简单类名，和开始对象中时间的属性名称进行搜索');
INSERT INTO `t_authority` VALUES ('135', 'com.ht.action.MeettingAction.pagerByName', '根据员工姓名和类对象的简单类名模糊搜索数据');
INSERT INTO `t_authority` VALUES ('136', 'com.ht.action.ProgressAction.save', '添加课程进度');
INSERT INTO `t_authority` VALUES ('137', 'com.ht.action.ProgressAction.update', '修改课程进度');
INSERT INTO `t_authority` VALUES ('138', 'com.ht.action.ProgressAction.pager', '分页查询数据库中的数据');
INSERT INTO `t_authority` VALUES ('139', 'com.ht.action.ProgressAction.frost', '根据对象的简单类名和id字段的名称，和状态来更新状态');
INSERT INTO `t_authority` VALUES ('140', 'com.ht.action.ProgressAction.activation', '根据对象的简单类名和id字段的名称，和状态来更新状态');
INSERT INTO `t_authority` VALUES ('141', 'com.ht.action.ProgressAction.pagerByEmpName', '根据员工姓名和类对象的简单类名模糊搜索数据');
INSERT INTO `t_authority` VALUES ('142', 'com.ht.action.PayAction.query_page', '分页查询所有支出信息');
INSERT INTO `t_authority` VALUES ('143', 'com.ht.action.PayAction.save', '添加支出记录');
INSERT INTO `t_authority` VALUES ('144', 'com.ht.action.PayAction.update', '修改支出记录');
INSERT INTO `t_authority` VALUES ('145', 'com.ht.action.PayTypeAction.query_page', '分页查询所有支出类别');
INSERT INTO `t_authority` VALUES ('146', 'com.ht.action.PayTypeAction.query_status', '分页查询冻结的支出类别');
INSERT INTO `t_authority` VALUES ('147', 'com.ht.action.PayTypeAction.save', '添加支出类别');
INSERT INTO `t_authority` VALUES ('148', 'com.ht.action.PayTypeAction.update', '修改支出类别');
INSERT INTO `t_authority` VALUES ('149', 'com.ht.action.PayTypeAction.query_name', '按条件查询支出类别');
INSERT INTO `t_authority` VALUES ('150', 'com.ht.action.PayTypeAction.query_type', '查询支出类别的id和name');
INSERT INTO `t_authority` VALUES ('151', 'com.ht.action.PayTypeAction.frost', '查询是否冻结');
INSERT INTO `t_authority` VALUES ('152', 'com.ht.action.PayTypeAction.activation', '查询是否激活');
INSERT INTO `t_authority` VALUES ('153', 'com.ht.action.IncomeAction.query_page', '分页查询所有收入信息');
INSERT INTO `t_authority` VALUES ('154', 'com.ht.action.IncomeAction.save', '添加收入记录');
INSERT INTO `t_authority` VALUES ('155', 'com.ht.action.IncomeAction.update', '修改收入记录');
INSERT INTO `t_authority` VALUES ('156', 'com.ht.action.IncomeTypeAction.query_page', '分页查询所有收入类别');
INSERT INTO `t_authority` VALUES ('157', 'com.ht.action.IncomeTypeAction.query_status', '分页查询冻结的收入类别');
INSERT INTO `t_authority` VALUES ('158', 'com.ht.action.IncomeTypeAction.save', '添加收入类别');
INSERT INTO `t_authority` VALUES ('159', 'com.ht.action.IncomeTypeAction.update', '修改收入类别');
INSERT INTO `t_authority` VALUES ('160', 'com.ht.action.IncomeTypeAction.query_name', '按条件查询收入类别');
INSERT INTO `t_authority` VALUES ('161', 'com.ht.action.IncomeTypeAction.query_type', '查询收入类别的id和name');
INSERT INTO `t_authority` VALUES ('162', 'com.ht.action.IncomeTypeAction.frost', '查询是否冻结');
INSERT INTO `t_authority` VALUES ('163', 'com.ht.action.IncomeTypeAction.activation', '查询是否激活');
INSERT INTO `t_authority` VALUES ('164', 'com.ht.action.IncomeTypeAction.queryByName', '根据名称查询收入类型id');
INSERT INTO `t_authority` VALUES ('165', 'com.ht.action.DeptAction.queryPage', '查询所有部门分页');
INSERT INTO `t_authority` VALUES ('166', 'com.ht.action.DeptAction.addDept', '添加部门');
INSERT INTO `t_authority` VALUES ('167', 'com.ht.action.DeptAction.update', '更新部门');
INSERT INTO `t_authority` VALUES ('168', 'com.ht.action.DeptAction.frost', '冻结部门状态');
INSERT INTO `t_authority` VALUES ('169', 'com.ht.action.DeptAction.activation', '激活部门状态');
INSERT INTO `t_authority` VALUES ('170', 'com.ht.action.EmpLeaveAction.pager', '查询所有请假分页');
INSERT INTO `t_authority` VALUES ('171', 'com.ht.action.EmpLeaveAction.secondPager', '老板专属请假分页');
INSERT INTO `t_authority` VALUES ('172', 'com.ht.action.EmpLeaveAction.firstPager', '主任专属请假分页');
INSERT INTO `t_authority` VALUES ('173', 'com.ht.action.EmpLeaveAction.byIdPager', '自己专属请假分页(总经理除外)');
INSERT INTO `t_authority` VALUES ('174', 'com.ht.action.EmpLeaveAction.add', '添加请假');
INSERT INTO `t_authority` VALUES ('175', 'com.ht.action.EmpLeaveAction.update', '更新请假');
INSERT INTO `t_authority` VALUES ('176', 'com.ht.action.EmpLeaveAction.acceptFirst', '接受请假申请(主任)');
INSERT INTO `t_authority` VALUES ('177', 'com.ht.action.EmpLeaveAction.refuseFirst', '回绝请假申请(主任)');
INSERT INTO `t_authority` VALUES ('178', 'com.ht.action.EmpLeaveAction.acceptSecond', '接受请假申请(总经理)');
INSERT INTO `t_authority` VALUES ('179', 'com.ht.action.EmpLeaveAction.refuseSecond', '回绝请假申请(总经理)');
INSERT INTO `t_authority` VALUES ('180', 'com.ht.action.EmpLeaveAction.activationStatus', '激活状态)');
INSERT INTO `t_authority` VALUES ('181', 'com.ht.action.EmpLeaveAction.freezeStatus', '冻结状态');
INSERT INTO `t_authority` VALUES ('182', 'com.ht.action.DutyAction.pager', '查询所有值班分页');
INSERT INTO `t_authority` VALUES ('183', 'com.ht.action.DutyAction.update', '更新值班');
INSERT INTO `t_authority` VALUES ('184', 'com.ht.action.CheckAction.pager', '查询所有巡查分页');
INSERT INTO `t_authority` VALUES ('185', 'com.ht.action.CheckAction.add', '添加巡查');
INSERT INTO `t_authority` VALUES ('186', 'com.ht.action.CheckAction.pagerByDay', '时间段查询巡查分页');
INSERT INTO `t_authority` VALUES ('187', 'com.ht.action.CheckAction.pagerByName', '员工名查询巡查分页');
INSERT INTO `t_authority` VALUES ('188', 'com.ht.action.ReportAction.pager', '查询所有日志分页');
INSERT INTO `t_authority` VALUES ('189', 'com.ht.action.ReportAction.add', '添加日志');
INSERT INTO `t_authority` VALUES ('190', 'com.ht.action.ReportAction.update', '更新日志');
INSERT INTO `t_authority` VALUES ('191', 'com.ht.action.ReportAction.pagerByDay', '时间段查询日志分页');
INSERT INTO `t_authority` VALUES ('192', 'com.ht.action.ReportAction.pagerByName', '员工名查询日志分页');
INSERT INTO `t_authority` VALUES ('193', 'com.ht.action.ReportAction.frost', '冻结日志');
INSERT INTO `t_authority` VALUES ('194', 'com.ht.action.ReportAction.activation', '激活日志');
INSERT INTO `t_authority` VALUES ('195', 'com.ht.action.NoticeTypeAction.fuzzySearch', '公告类型模糊搜索');
INSERT INTO `t_authority` VALUES ('196', 'com.ht.action.GoodsAction.goodsAdd', '用品添加');
INSERT INTO `t_authority` VALUES ('197', 'com.ht.action.StuAction.save?flag=2', '添加预定学生');
INSERT INTO `t_authority` VALUES ('198', 'com.ht.action.StuAction.save?flag=3', '添加正式学生');
INSERT INTO `t_authority` VALUES ('199', 'com.ht.action.StuAction.update?flag=2', '更新预定学生');
INSERT INTO `t_authority` VALUES ('200', 'com.ht.action.StuAction.update?flag=3', '更新正式学生');
INSERT INTO `t_authority` VALUES ('201', 'com.ht.action.CheckAction.update', '更新巡查');
INSERT INTO `t_authority` VALUES ('202', 'com.ht.action.EmpLeaveAction.pagerByDay', '时间段查询请假');
INSERT INTO `t_authority` VALUES ('203', 'com.ht.action.EmpLeaveAction.pagerByName', '员工名查询请假');
INSERT INTO `t_authority` VALUES ('204', 'com.ht.action.showAction.empPage', '只有总经理,校长,教务主任,后勤主任,财务主任,招生主任,才有权限进入员工管理');
INSERT INTO `t_authority` VALUES ('205', 'com.ht.action.showAction.noticeType', '只有总经理,校长,才有权限进入公告类型管理');
INSERT INTO `t_authority` VALUES ('206', 'com.ht.action.showAction.stuLeave', '只有总经理,校长,班主任,任课老师,辅导老师,才有权限进入学生请假');
INSERT INTO `t_authority` VALUES ('207', 'com.ht.action.showAction.empCheckingInfo', '只有总经理,校长,才有权限进入学生考勤');
INSERT INTO `t_authority` VALUES ('208', 'com.ht.action.showAction.goodsType', '只有总经理,后勤主任,才有权限进入物品类型');
INSERT INTO `t_authority` VALUES ('209', 'com.ht.action.showAction.role', '只有总经理,校长,才有权限进入角色类型');
INSERT INTO `t_authority` VALUES ('210', 'com.ht.action.showAction.paytype', '只有总经理,财务主任,才有权限进入支出类型');
INSERT INTO `t_authority` VALUES ('211', 'com.ht.action.showAction.incometype', '只有总经理,财务主任,才有权限进入收入类型');
INSERT INTO `t_authority` VALUES ('212', 'com.ht.action.showAction.pay', '只有总经理,财务主任,才有权限进入支出管理');
INSERT INTO `t_authority` VALUES ('213', 'com.ht.action.showAction.income', '只有总经理,财务主任,才有权限进入收入管理');
INSERT INTO `t_authority` VALUES ('214', 'com.ht.action.NoticeTypeAction.fuzzySearch', '公告类型模糊搜索');
INSERT INTO `t_authority` VALUES ('215', 'com.ht.action.ShowAction.salaryinfo', '工资基本信息');

-- ----------------------------
-- Table structure for t_authority_role
-- ----------------------------
DROP TABLE IF EXISTS `t_authority_role`;
CREATE TABLE `t_authority_role` (
  `authorityroleid` int(11) NOT NULL AUTO_INCREMENT,
  `authorityid` int(11) NOT NULL COMMENT '权限表的id',
  `roleid` varchar(32) DEFAULT NULL COMMENT '角色的id',
  PRIMARY KEY (`authorityroleid`)
) ENGINE=InnoDB AUTO_INCREMENT=1138 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_authority_role
-- ----------------------------
INSERT INTO `t_authority_role` VALUES ('1', '1', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('2', '1', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('3', '1', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('4', '1', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('5', '1', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('6', '1', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('7', '1', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('8', '1', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('9', '1', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('10', '1', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('11', '1', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('12', '2', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('13', '2', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('14', '2', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('15', '2', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('16', '2', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('17', '2', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('18', '3', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('19', '4', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('20', '5', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('21', '5', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('22', '5', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('23', '5', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('24', '5', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('25', '5', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('26', '6', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('27', '6', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('28', '6', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('29', '6', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('30', '6', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('31', '6', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('32', '7', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('33', '7', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('34', '7', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('35', '7', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('36', '7', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('37', '7', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('38', '7', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('39', '7', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('40', '7', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('41', '7', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('42', '7', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('43', '8', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('44', '9', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('45', '10', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('46', '10', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('47', '10', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('48', '10', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('49', '10', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('50', '10', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('51', '10', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('52', '10', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('53', '10', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('54', '10', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('55', '10', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('56', '11', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('57', '11', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('58', '11', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('59', '11', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('60', '11', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('61', '11', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('62', '11', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('63', '11', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('64', '11', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('65', '11', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('66', '11', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('67', '12', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('68', '12', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('69', '12', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('70', '12', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('71', '12', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('72', '12', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('73', '12', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('74', '12', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('75', '12', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('76', '12', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('77', '12', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('78', '13', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('79', '13', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('80', '13', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('81', '14', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('82', '14', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('83', '14', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('84', '15', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('85', '15', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('86', '15', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('87', '15', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('88', '15', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('89', '15', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('90', '15', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('91', '15', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('92', '15', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('93', '15', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('94', '15', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('95', '16', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('96', '16', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('97', '16', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('98', '16', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('99', '16', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('100', '16', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('101', '16', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('102', '16', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('103', '16', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('104', '16', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('105', '16', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('106', '17', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('107', '17', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('108', '17', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('109', '18', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('110', '18', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('111', '18', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('112', '19', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('113', '19', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('114', '19', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('115', '19', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('116', '19', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('117', '19', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('118', '19', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('119', '19', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('120', '19', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('121', '19', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('122', '19', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('123', '20', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('124', '20', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('125', '20', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('126', '21', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('127', '21', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('128', '21', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('129', '22', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('130', '22', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('131', '22', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('132', '22', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('133', '22', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('134', '22', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('135', '22', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('136', '22', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('137', '22', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('138', '22', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('139', '22', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('140', '23', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('141', '23', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('142', '23', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('143', '23', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('144', '23', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('145', '23', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('146', '23', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('147', '23', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('148', '23', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('149', '23', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('150', '23', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('151', '24', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('152', '24', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('153', '24', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('154', '25', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('155', '25', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('156', '25', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('157', '26', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('158', '26', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('159', '26', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('160', '26', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('161', '26', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('162', '26', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('163', '26', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('164', '26', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('165', '26', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('166', '26', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('167', '26', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('168', '27', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('169', '27', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('170', '27', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('171', '27', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('172', '27', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('173', '27', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('174', '27', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('175', '27', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('176', '27', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('177', '27', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('178', '27', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('179', '28', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('180', '28', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('181', '28', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('182', '29', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('183', '29', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('184', '29', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('185', '30', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('186', '30', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('187', '30', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('188', '30', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('189', '30', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('190', '30', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('191', '30', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('192', '30', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('193', '30', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('194', '30', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('195', '30', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('196', '31', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('197', '31', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('198', '31', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('199', '31', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('200', '31', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('201', '31', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('202', '31', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('203', '31', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('204', '31', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('205', '31', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('206', '31', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('207', '32', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('208', '32', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('209', '32', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('210', '33', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('211', '33', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('212', '33', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('213', '34', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('214', '34', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('215', '34', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('216', '34', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('217', '34', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('218', '34', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('219', '34', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('220', '34', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('221', '34', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('222', '34', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('223', '35', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('224', '35', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('225', '36', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('226', '36', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('227', '36', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('228', '36', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('229', '36', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('230', '36', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('231', '36', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('232', '36', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('233', '36', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('234', '36', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('235', '37', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('236', '37', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('237', '38', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('238', '38', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('239', '39', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('240', '39', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('241', '39', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('242', '39', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('243', '39', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('244', '39', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('245', '39', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('246', '39', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('247', '39', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('248', '39', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('249', '40', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('250', '40', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('251', '40', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('252', '41', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('253', '41', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('254', '41', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('255', '42', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('256', '42', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('257', '42', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('258', '43', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('259', '43', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('260', '43', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('261', '44', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('262', '44', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('263', '44', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('264', '44', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('265', '44', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('266', '44', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('267', '44', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('268', '44', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('269', '44', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('270', '44', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('271', '45', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('272', '45', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('273', '45', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('274', '45', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('275', '45', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('276', '45', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('277', '45', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('278', '45', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('279', '45', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('280', '45', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('281', '46', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('282', '46', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('283', '46', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('284', '47', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('285', '47', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('286', '47', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('287', '48', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('288', '48', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('289', '48', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('290', '49', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('291', '49', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('292', '49', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('293', '50', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('294', '50', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('295', '50', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('296', '50', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('297', '50', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('298', '50', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('299', '50', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('300', '50', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('301', '50', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('302', '50', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('303', '51', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('304', '51', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('305', '51', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('306', '51', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('307', '51', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('308', '51', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('309', '51', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('310', '51', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('311', '51', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('312', '51', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('313', '52', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('314', '52', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('315', '52', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('316', '52', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('317', '52', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('318', '52', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('319', '52', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('320', '52', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('321', '52', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('322', '52', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('323', '53', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('324', '53', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('325', '53', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('326', '53', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('327', '53', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('328', '53', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('329', '53', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('330', '53', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('331', '53', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('332', '53', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('333', '53', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('334', '54', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('335', '54', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('336', '54', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('337', '54', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('338', '54', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('339', '54', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('340', '54', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('341', '54', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('342', '54', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('343', '54', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('344', '55', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('345', '55', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('346', '55', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('347', '55', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('348', '55', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('349', '55', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('350', '55', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('351', '55', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('352', '55', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('353', '55', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('354', '56', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('355', '56', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('356', '56', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('357', '57', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('358', '57', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('359', '57', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('360', '57', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('361', '57', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('362', '57', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('363', '57', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('364', '57', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('365', '57', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('366', '57', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('367', '58', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('368', '58', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('369', '58', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('370', '58', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('371', '58', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('372', '58', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('373', '58', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('374', '58', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('375', '58', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('376', '58', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('377', '59', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('378', '59', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('379', '59', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('380', '60', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('381', '60', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('382', '60', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('383', '61', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('384', '61', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('385', '61', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('386', '61', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('387', '61', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('388', '61', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('389', '61', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('390', '61', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('391', '61', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('392', '61', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('393', '62', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('394', '62', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('395', '62', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('396', '62', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('397', '62', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('398', '62', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('399', '62', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('400', '62', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('401', '62', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('402', '62', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('403', '63', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('404', '63', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('405', '63', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('406', '63', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('407', '63', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('408', '63', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('409', '63', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('410', '63', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('411', '63', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('412', '63', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('413', '64', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('414', '64', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('415', '64', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('416', '64', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('417', '64', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('418', '64', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('419', '64', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('420', '64', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('421', '64', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('422', '64', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('423', '65', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('424', '65', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('425', '65', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('426', '66', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('427', '66', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('428', '66', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('429', '66', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('430', '66', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('431', '66', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('432', '66', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('433', '66', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('434', '66', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('435', '66', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('436', '67', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('437', '67', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('438', '67', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('439', '68', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('440', '68', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('441', '68', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('442', '68', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('443', '68', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('444', '68', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('445', '68', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('446', '68', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('447', '68', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('448', '68', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('449', '69', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('450', '69', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('451', '70', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('452', '70', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('453', '71', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('454', '71', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('455', '72', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('456', '72', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('457', '73', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('458', '73', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('459', '74', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('460', '74', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('461', '75', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('462', '75', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('463', '76', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('464', '76', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('465', '76', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('466', '76', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('467', '76', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('468', '76', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('469', '77', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('470', '77', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('471', '77', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('472', '77', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('473', '77', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('474', '77', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('475', '78', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('476', '78', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('477', '78', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('478', '78', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('479', '78', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('480', '78', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('481', '78', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('482', '78', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('483', '78', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('484', '78', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('485', '78', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('486', '79', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('487', '79', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('488', '79', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('489', '79', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('490', '79', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('491', '79', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('492', '79', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('493', '79', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('494', '79', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('495', '79', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('496', '79', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('497', '80', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('498', '80', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('499', '80', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('500', '80', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('501', '80', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('502', '80', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('503', '81', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('504', '81', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('505', '81', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('506', '81', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('507', '81', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('508', '81', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('509', '82', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('510', '82', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('511', '83', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('512', '83', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('513', '84', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('514', '84', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('515', '85', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('516', '85', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('517', '86', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('518', '86', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('519', '87', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('520', '87', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('521', '88', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('522', '88', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('523', '89', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('524', '89', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('525', '195', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('526', '195', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('527', '90', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('528', '90', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('529', '90', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('530', '90', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('531', '90', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('532', '92', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('533', '92', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('534', '92', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('535', '92', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('536', '92', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('537', '96', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('538', '96', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('539', '96', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('540', '96', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('541', '96', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('542', '91', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('543', '91', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('544', '91', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('545', '91', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('546', '91', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('547', '94', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('548', '94', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('549', '94', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('550', '94', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('551', '94', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('552', '95', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('553', '95', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('554', '95', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('555', '95', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('556', '95', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('557', '93', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('558', '93', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('559', '93', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('560', '93', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('561', '93', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('562', '93', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('563', '93', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('564', '93', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('565', '93', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('566', '93', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('567', '93', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('568', '98', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('569', '98', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('570', '98', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('571', '98', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('572', '98', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('573', '98', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('574', '98', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('575', '98', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('576', '98', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('577', '98', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('578', '98', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('579', '99', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('580', '99', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('581', '101', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('582', '101', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('583', '102', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('584', '102', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('585', '108', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('586', '108', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('587', '109', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('588', '109', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('589', '110', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('590', '110', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('591', '111', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('592', '111', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('593', '112', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('594', '112', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('595', '113', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('596', '113', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('597', '113', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('598', '113', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('599', '113', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('600', '113', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('601', '113', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('602', '113', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('603', '113', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('604', '113', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('605', '113', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('606', '114', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('607', '114', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('608', '114', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('609', '114', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('610', '114', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('611', '114', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('612', '114', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('613', '114', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('614', '114', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('615', '114', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('616', '114', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('617', '115', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('618', '115', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('619', '115', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('620', '115', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('621', '115', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('622', '115', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('623', '115', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('624', '115', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('625', '115', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('626', '115', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('627', '115', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('628', '103', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('629', '103', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('630', '103', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('631', '103', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('632', '103', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('633', '103', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('634', '103', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('635', '103', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('636', '103', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('637', '103', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('638', '104', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('639', '104', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('640', '104', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('641', '104', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('642', '104', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('643', '104', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('644', '104', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('645', '104', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('646', '104', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('647', '104', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('648', '105', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('649', '105', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('650', '105', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('651', '105', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('652', '105', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('653', '105', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('654', '105', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('655', '105', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('656', '105', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('657', '105', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('658', '106', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('659', '106', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('660', '106', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('661', '106', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('662', '106', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('663', '106', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('664', '106', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('665', '106', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('666', '106', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('667', '106', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('668', '107', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('669', '107', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('670', '107', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('671', '107', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('672', '107', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('673', '107', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('674', '107', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('675', '107', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('676', '107', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('677', '107', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('678', '116', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('679', '116', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('680', '132', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('681', '132', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('682', '117', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('683', '117', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('684', '133', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('685', '133', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('686', '119', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('687', '119', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('688', '120', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('689', '120', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('690', '130', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('691', '130', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('692', '131', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('693', '131', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('694', '121', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('695', '121', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('696', '135', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('697', '135', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('698', '134', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('699', '134', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('700', '118', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('701', '118', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('702', '118', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('703', '118', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('704', '118', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('705', '118', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('706', '118', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('707', '118', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('708', '118', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('709', '118', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('710', '118', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('711', '138', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('712', '138', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('713', '138', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('714', '138', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('715', '138', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('716', '138', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('717', '138', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('718', '138', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('719', '138', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('720', '138', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('721', '138', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('722', '129', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('723', '129', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('724', '129', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('725', '129', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('726', '129', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('727', '129', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('728', '129', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('729', '129', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('730', '129', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('731', '129', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('732', '123', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('733', '123', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('734', '123', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('735', '123', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('736', '123', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('737', '123', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('738', '123', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('739', '123', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('740', '123', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('741', '123', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('742', '122', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('743', '122', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('744', '122', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('745', '122', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('746', '122', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('747', '122', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('748', '122', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('749', '122', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('750', '122', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('751', '122', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('752', '124', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('753', '124', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('754', '124', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('755', '124', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('756', '124', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('757', '124', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('758', '124', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('759', '124', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('760', '124', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('761', '124', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('762', '125', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('763', '125', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('764', '125', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('765', '125', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('766', '125', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('767', '125', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('768', '125', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('769', '125', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('770', '125', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('771', '125', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('772', '126', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('773', '126', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('774', '126', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('775', '126', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('776', '126', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('777', '126', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('778', '126', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('779', '126', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('780', '126', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('781', '126', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('782', '127', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('783', '127', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('784', '127', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('785', '127', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('786', '127', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('787', '127', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('788', '127', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('789', '127', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('790', '127', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('791', '127', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('792', '128', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('793', '128', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('794', '128', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('795', '128', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('796', '128', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('797', '128', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('798', '128', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('799', '128', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('800', '128', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('801', '128', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('802', '136', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('803', '136', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('804', '136', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('805', '137', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('806', '137', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('807', '137', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('808', '139', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('809', '139', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('810', '139', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('811', '140', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('812', '140', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('813', '140', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('814', '141', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('815', '141', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('816', '141', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('817', '142', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('818', '142', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('819', '143', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('820', '143', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('821', '144', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('822', '144', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('823', '145', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('824', '145', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('825', '146', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('826', '146', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('827', '147', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('828', '147', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('829', '148', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('830', '148', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('831', '149', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('832', '149', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('833', '150', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('834', '150', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('835', '151', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('836', '151', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('837', '152', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('838', '152', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('839', '153', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('840', '153', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('841', '154', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('842', '154', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('843', '155', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('844', '155', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('845', '156', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('846', '156', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('847', '157', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('848', '157', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('849', '158', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('850', '158', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('851', '159', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('852', '159', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('853', '160', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('854', '160', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('855', '161', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('856', '161', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('857', '162', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('858', '162', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('859', '163', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('860', '163', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('861', '164', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('862', '164', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('863', '165', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('864', '165', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('865', '165', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('866', '165', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('867', '165', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('868', '165', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('869', '165', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('870', '165', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('871', '165', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('872', '165', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('873', '166', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('874', '167', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('875', '168', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('876', '169', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('877', '170', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('878', '171', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('879', '172', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('880', '172', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('881', '172', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('882', '172', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('883', '172', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('884', '173', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('885', '173', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('886', '173', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('887', '173', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('888', '173', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('889', '173', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('890', '173', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('891', '173', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('892', '173', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('893', '174', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('894', '174', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('895', '174', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('896', '174', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('897', '174', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('898', '174', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('899', '174', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('900', '174', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('901', '174', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('902', '175', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('903', '175', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('904', '175', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('905', '175', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('906', '175', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('907', '175', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('908', '175', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('909', '175', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('910', '175', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('911', '176', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('912', '176', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('913', '176', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('914', '176', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('915', '176', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('916', '177', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('917', '177', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('918', '177', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('919', '177', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('920', '177', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('921', '178', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('922', '179', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('923', '180', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('924', '180', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('925', '180', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('926', '180', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('927', '180', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('928', '180', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('929', '180', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('930', '180', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('931', '180', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('932', '181', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('933', '181', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('934', '181', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('935', '181', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('936', '181', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('937', '181', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('938', '181', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('939', '181', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('940', '181', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('941', '182', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('942', '182', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('943', '182', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('944', '182', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('945', '182', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('946', '182', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('947', '182', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('948', '182', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('949', '182', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('950', '182', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_authority_role` VALUES ('951', '182', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('952', '183', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('953', '183', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('954', '184', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('955', '184', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('956', '184', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('957', '184', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('958', '184', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('959', '184', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('960', '184', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('961', '184', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('962', '184', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('963', '184', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('964', '185', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('965', '185', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('966', '185', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('967', '185', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('968', '185', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('969', '185', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('970', '185', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('971', '185', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('972', '185', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('973', '185', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('974', '186', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('975', '186', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('976', '186', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('977', '187', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('978', '187', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('979', '187', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('980', '188', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('981', '188', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('982', '188', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('983', '188', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('984', '188', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('985', '188', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('986', '188', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('987', '188', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('988', '188', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('989', '188', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('990', '189', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('991', '189', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('992', '189', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('993', '189', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('994', '189', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('995', '189', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('996', '189', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('997', '189', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('998', '189', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('999', '189', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1000', '190', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('1001', '190', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('1002', '190', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('1003', '190', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('1004', '190', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('1005', '190', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('1006', '190', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('1007', '190', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('1008', '190', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('1009', '190', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1010', '191', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('1011', '191', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('1012', '191', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('1013', '191', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('1014', '191', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('1015', '191', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1016', '192', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('1017', '192', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('1018', '192', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('1019', '192', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('1020', '192', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('1021', '192', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1022', '193', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('1023', '193', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('1024', '193', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('1025', '193', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('1026', '193', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('1027', '193', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('1028', '193', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('1029', '193', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('1030', '193', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('1031', '193', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1032', '194', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('1033', '194', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('1034', '194', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('1035', '194', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('1036', '194', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('1037', '194', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('1038', '194', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('1039', '194', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('1040', '194', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('1041', '194', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1042', '196', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1043', '196', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('1044', '91', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1045', '92', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1046', '94', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1047', '95', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1048', '96', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1049', '90', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1050', '112', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('1051', '112', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1052', '197', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('1053', '197', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('1054', '197', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('1055', '197', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('1056', '197', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('1057', '197', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('1058', '197', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('1059', '197', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('1060', '197', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('1061', '197', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1062', '198', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('1063', '198', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('1064', '198', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('1065', '198', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('1066', '198', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('1067', '198', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('1068', '198', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('1069', '198', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('1070', '198', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('1071', '198', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1072', '199', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('1073', '199', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('1074', '199', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('1075', '199', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('1076', '199', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('1077', '199', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('1078', '199', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('1079', '199', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('1080', '199', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('1081', '199', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1082', '200', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('1083', '200', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('1084', '200', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1085', '201', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('1086', '201', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('1087', '201', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('1088', '201', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('1089', '201', '4028808c596d9c2001596d9f24200000');
INSERT INTO `t_authority_role` VALUES ('1090', '201', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('1091', '201', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('1092', '201', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('1093', '201', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('1094', '201', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1095', '202', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1096', '202', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('1097', '202', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('1098', '202', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('1099', '202', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('1100', '202', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('1101', '203', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1102', '203', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('1103', '203', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('1104', '203', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('1105', '203', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('1106', '203', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('1107', '204', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1108', '204', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('1109', '204', '4028808c5972e397015972e95e740001');
INSERT INTO `t_authority_role` VALUES ('1110', '204', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('1111', '204', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('1112', '204', '4028808c5972e397015972e8afe70000');
INSERT INTO `t_authority_role` VALUES ('1113', '205', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1114', '205', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('1115', '206', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1116', '206', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('1117', '206', '4028808c5961a80a015961bcb7100000');
INSERT INTO `t_authority_role` VALUES ('1118', '206', '4028808c5961a80a015961bd2bc70001');
INSERT INTO `t_authority_role` VALUES ('1119', '206', '4028808c5961a80a015961bda7030002');
INSERT INTO `t_authority_role` VALUES ('1120', '195', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1121', '195', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('1122', '207', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1123', '207', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('1124', '208', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1125', '208', '4028808c596d9c2001596d9ff7a30001');
INSERT INTO `t_authority_role` VALUES ('1126', '209', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1127', '209', '4028808c59697c0c015969a165b2000a');
INSERT INTO `t_authority_role` VALUES ('1128', '210', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1129', '210', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('1130', '211', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1131', '211', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('1132', '212', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1133', '212', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('1134', '213', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1135', '213', '4028808c596d9c2001596da08cad0002');
INSERT INTO `t_authority_role` VALUES ('1136', '215', 'cd8089aec7f211e6a24b3065ec373466');
INSERT INTO `t_authority_role` VALUES ('1137', '215', '4028808c596d9c2001596da08cad0002');

-- ----------------------------
-- Table structure for t_check
-- ----------------------------
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

-- ----------------------------
-- Records of t_check
-- ----------------------------
INSERT INTO `t_check` VALUES ('4028daf859a63e5f0159a642e5160000', '69f49c83c7f411e6a24b3065ec373466', '2017-01-16 15:51:15', '4', '4028da1c5925b27e015925b2a8340000', null, '一切正常');
INSERT INTO `t_check` VALUES ('4028daf859a63e5f0159a64347920001', '69f49c83c7f411e6a24b3065ec373466', '2017-01-16 15:52:01', '1', null, '4028808c59695d0301596961f8260001', '太脏');

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
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

-- ----------------------------
-- Records of t_course
-- ----------------------------
INSERT INTO `t_course` VALUES ('4028808c593f4b2b01593f4cd47b0000', 'Java', 'Java学习1个学期', '12', '1', '3', '1');
INSERT INTO `t_course` VALUES ('4028808c593f4b2b01593f4dc3730001', 'C语言', 'C语言学习', '6', '1', '2', '1');
INSERT INTO `t_course` VALUES ('4028808c593f4b2b01593f4e527e0002', 'HTML', 'HTML基础学习', '2', '1', '1', '1');
INSERT INTO `t_course` VALUES ('4028808c593f4b2b01593f4f7ef50003', 'SQL Server', 'SQL Server基础学习', '6', '1', '4', '1');
INSERT INTO `t_course` VALUES ('4028808c59695d03015969697c260005', 'My SQL', 'My SQL的基础学习', '6', '3', '5', '1');

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `depid` varchar(32) NOT NULL COMMENT '部门编号',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `empid` varchar(32) DEFAULT NULL COMMENT '负责人编号',
  `des` varchar(500) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认可用',
  PRIMARY KEY (`depid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES ('297e3390594d742f01594d8c86840001', '教务部', '4028a081596ee67201596ef5fc810000', '任课老师和辅导老师老师所在的部门', '1');
INSERT INTO `t_dept` VALUES ('297e3390594d742f01594d8ce4440002', '后勤部', '4028a081596ee67201596f1aee40000c', '后勤部的人员部门', '1');
INSERT INTO `t_dept` VALUES ('3d314f24c7f311e6a24b3065ec373466', '总裁部', '69f49c83c7f411e6a24b3065ec373466', '总经理所在的部门', '1');
INSERT INTO `t_dept` VALUES ('4028808c5969256c01596927d8880000', '招生部', '4028a081596ee67201596f20efa4000f', '负责招生，招生老师所在的部门', '1');
INSERT INTO `t_dept` VALUES ('4028808c596df29201596e8ace9c0000', '行政部', '4028808c59685ee001596864f57a0000', '校长和班主任所在的部门', '1');
INSERT INTO `t_dept` VALUES ('4028a081593652bb0159365c87f20001', '财务部', '4028a081596ee67201596f1dd900000e', '负责财政管理', '1');

-- ----------------------------
-- Table structure for t_duty
-- ----------------------------
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

-- ----------------------------
-- Records of t_duty
-- ----------------------------
INSERT INTO `t_duty` VALUES ('2c64d1b9d15d11e6aa093065ec373466', '1', '4028a081596ee67201596ef5fc810000', '16班级', '4028808c5967eae5015967fa466a0000', '15班级', '4028a081596ee67201596f0fbafb0008', '全部班级', '4028808c59685ee001596864f57a0000', '全部班级', null, null, null, null, null, null, null, null);
INSERT INTO `t_duty` VALUES ('2e052b76d15d11e6aa093065ec373466', '2', '4028a081596ee67201596f068d0e0005', '16班级', '4028a081596ee67201596f29983f0010', '15班级', '4028a081596ee67201596f03221f0004', '全部班级', '4028808c59685ee001596864f57a0000', '全部班级', null, null, null, null, null, null, null, null);
INSERT INTO `t_duty` VALUES ('69f49c83c7f411e6a24b3065ec31111', '3', '4028a081596ee67201596f18484a000b', '16班级', '4028a081596ee67201596f09a1250006', '15班级', '4028a081596ee67201596f0fbafb0008', '全部班级', '4028808c59685ee001596864f57a0000', '全部班级', null, null, null, null, null, null, null, null);
INSERT INTO `t_duty` VALUES ('69f49c83c7f411e6a24b3065ec31234', '4', '297e3390594d742f01594d8a22ea0000', '16班级', '4028a081596ee67201596f0cb2de0007', '15班级', '4028a081596ee67201596efa6b0f0003', '全部班级', '4028808c59685ee001596864f57a0000', '全部班级', null, null, null, null, null, null, null, null);
INSERT INTO `t_duty` VALUES ('69f49c83c7f411e6a24b3065ec32211', '5', '4028a081596ee67201596ef5fc810000', '16班级', '4028a081596ee67201596f1613b2000a', '15班级', '4028a081596ee67201596f03221f0004', '全部班级', '4028808c59685ee001596864f57a0000', '全部班级', null, null, null, null, null, null, null, null);
INSERT INTO `t_duty` VALUES ('69f49c83c7f411e6a24b3065ec36699', '7', '4028a081596ee67201596f12e8ba0009', '16班级', '4028a081596ee67201596f0cb2de0007', '15班级', '4028a081596ee67201596efa6b0f0003', '全部班级', '4028808c59685ee001596864f57a0000', '全部班级', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for t_edu
-- ----------------------------
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

-- ----------------------------
-- Records of t_edu
-- ----------------------------

-- ----------------------------
-- Table structure for t_emp
-- ----------------------------
DROP TABLE IF EXISTS `t_emp`;
CREATE TABLE `t_emp` (
  `empid` varchar(32) NOT NULL COMMENT '员工编号',
  `depid` varchar(32) NOT NULL COMMENT '部门编号，来源于部门表',
  `roleid` varchar(32) NOT NULL COMMENT '角色编号',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `pwd` varchar(50) NOT NULL COMMENT '密码',
  `idcard` varchar(18) NOT NULL COMMENT '身份证号',
  `nation` varchar(10) DEFAULT NULL COMMENT '籍贯',
  `gender` varchar(6) NOT NULL COMMENT '性别',
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

-- ----------------------------
-- Records of t_emp
-- ----------------------------
INSERT INTO `t_emp` VALUES ('297e3390594d742f01594d8a22ea0000', '297e3390594d742f01594d8c86840001', '4028808c5961a80a015961bd2bc70001', '王根参', '4QrcOUm6Wau+VuBX8g+IPg==', '360735199511201216', '江西', 'male', '00007', '1995-11-20 00:00:00', 'wgs@126.com', '13672297775', '673630487', 'Wgssmart', '江西省赣州市', '是', '王某某', '18279777665', '赣南师范学院', '软件开发', '大学', '建设银行', '王根参', '6222021001116246016', '13672297775', '2016-12-13 00:00:00', null, '1');
INSERT INTO `t_emp` VALUES ('4028808c5967eae5015967fa466a0000', '297e3390594d742f01594d8c86840001', '4028808c5961a80a015961bda7030002', '朱吉祥', '4QrcOUm6Wau+VuBX8g+IPg==', '360735199506112320', '江西', 'male', '00008', '1985-06-11 00:00:00', 'zjx@126.com', '18160774004', '57323487', 'Zjxsmart', '江西赣州南康区', '否', '陈某某', '18798987898', '江西赣州技师学院', '软件开发', '大专', '建设银行', '朱吉祥', '787889877657763456', '18160774004', '2017-01-03 00:00:00', null, '1');
INSERT INTO `t_emp` VALUES ('4028808c59685ee001596864f57a0000', '4028808c596df29201596e8ace9c0000', '4028808c59697c0c015969a165b2000a', '廖文汉', '4QrcOUm6Wau+VuBX8g+IPg==', '333333333333333312', '江西', 'male', '00002', '1988-06-08 00:00:00', 'lwh@126.com', '15970062018', '3233222222', 'Liaosmart', '江西赣州', '是', '廖某某', '33333333333', '江西赣州技师学院', '校长养成', '大专', '建设银行', '廖文汉', '7736376273283232768', '15970062018', '2017-01-11 00:00:00', '2017-01-17 00:00:00', '1');
INSERT INTO `t_emp` VALUES ('4028808c5972e397015972f1de7d0002', '4028808c5969256c01596927d8880000', '4028808c596d9c2001596d9f24200000', '杨梅', '4QrcOUm6Wau+VuBX8g+IPg==', '345586465769675648', '江西', 'male', '00019', '2017-01-16 00:00:00', 'ym@126.com', '18980987654', '23535453', 'Ymsmart', '江西赣州', '是', '杨某某', '23543634222', '江西赣州技师学院', '招生老师养成', '大专', '建设银行', '杨梅', '4364575675756756992', '18980987654', '2017-01-06 00:00:00', null, '1');
INSERT INTO `t_emp` VALUES ('4028a081596ee67201596ef5fc810000', '297e3390594d742f01594d8c86840001', '4028808c5972e397015972e95e740001', '赖国荣', '4QrcOUm6Wau+VuBX8g+IPg==', '360334333233343296', '江西', 'male', '00003', '2017-01-05 00:00:00', 'lgr@126.com', '15374363882', '3333444433', 'Lgrsmart', '江西赣州石城', '是', '赖某某', '38798878988', '江西赣州技师学院', '软件开发', '大专', '建设银行', '赖国荣', '6767766232323231744', '15374363882', '2017-01-05 00:00:00', null, '1');
INSERT INTO `t_emp` VALUES ('4028a081596ee67201596efa6b0f0003', '4028808c596df29201596e8ace9c0000', '4028808c5961a80a015961bcb7100000', '王鹏英', '4QrcOUm6Wau+VuBX8g+IPg==', '333443323243333440', '江西', 'female', '00005', '2017-01-01 00:00:00', 'wpy@126.com', '18270728002', '3432343232', 'Wpysmart', '江西赣州章贡区', '否', '王某某', '18798987789', '江西赣州技师学院', '班主任养成', '大专', '建设银行', '王鹏英', '3234234324234233344', '18270728002', '2017-01-05 00:00:00', null, '1');
INSERT INTO `t_emp` VALUES ('4028a081596ee67201596f03221f0004', '4028808c596df29201596e8ace9c0000', '4028808c5961a80a015961bcb7100000', '何庆', '4QrcOUm6Wau+VuBX8g+IPg==', '345323532234234240', '江西', 'male', '00004', '2017-01-02 00:00:00', 'hq@126.com', '13767728600', '345678909', 'Hqsmart', '江西赣州上饶', '是', '何某某', '18798898723', '赣南师范大学', '班主任养成', '大学', '建设银行', '何庆', '3438879823789287936', '13767728600', '2017-01-05 00:00:00', '2017-01-16 00:00:00', '1');
INSERT INTO `t_emp` VALUES ('4028a081596ee67201596f068d0e0005', '297e3390594d742f01594d8c86840001', '4028808c5961a80a015961bd2bc70001', '刘飞', '4QrcOUm6Wau+VuBX8g+IPg==', '343872983798374848', '江西', 'male', '00006', '2017-01-02 00:00:00', 'lf@126.com', '18270062525', '34342324', 'Lfsmart', '江西赣州章贡区', '否', '刘某某', '18723842354', '江西赣州技师学院', '软件开发', '大专', '建设银行', '刘飞', '3438794875394853376', '18270062525', '2017-01-03 00:00:00', null, '1');
INSERT INTO `t_emp` VALUES ('4028a081596ee67201596f09a1250006', '297e3390594d742f01594d8c86840001', '4028808c5961a80a015961bda7030002', '钟深财', '4QrcOUm6Wau+VuBX8g+IPg==', '345434476543653440', '江西', 'male', '00009', '2017-01-16 00:00:00', 'zsc@126.com', '13726287177', '5678998545', 'Zscsmart', '江西赣州章贡区', '否', '钟某某', '45789876542', '江西赣州技师学院', '软件开发', '大专', '建设银行', '钟深财', '5678909876543457280', '13726287177', '2017-01-04 00:00:00', null, '1');
INSERT INTO `t_emp` VALUES ('4028a081596ee67201596f0cb2de0007', '297e3390594d742f01594d8c86840001', '4028808c5961a80a015961bd2bc70001', '董新星', '4QrcOUm6Wau+VuBX8g+IPg==', '343664325677654784', '江西', 'male', '00010', '2017-01-23 00:00:00', 'dxx@126.com', '18664643365', '534453', 'Dxxsmart', '江西赣州章贡区', '是', '董某某', '23466767452', '江西赣州技师学院', '软件开发', '大专', '建设银行', '董新星', '4568654354434645504', '18664643365', '2017-01-19 00:00:00', null, '1');
INSERT INTO `t_emp` VALUES ('4028a081596ee67201596f0fbafb0008', '4028808c596df29201596e8ace9c0000', '4028808c5961a80a015961bcb7100000', '周玉祥', '4QrcOUm6Wau+VuBX8g+IPg==', '436867643535753472', '江西', 'male', '00011', '2017-01-24 00:00:00', 'zyx@126.com', '13479996398', '126753453', 'Zyxsmart', '江西赣州章贡区', '否', '周某某', '14455754434', '江西赣州技师学院', '班主任养成', '大专', '建设银行', '周玉祥', '4344475454565786624', '13479996398', '2017-01-08 00:00:00', null, '1');
INSERT INTO `t_emp` VALUES ('4028a081596ee67201596f12e8ba0009', '297e3390594d742f01594d8c86840001', '4028808c5961a80a015961bd2bc70001', '曾建文', '4QrcOUm6Wau+VuBX8g+IPg==', '435654535345636672', '江西', 'male', '00012', '2017-01-21 00:00:00', 'zjw@126.com', '18211570079', '698237985', 'Zjwsmart', '江西赣州章贡区', '是', '曾某某', '45433234362', '赣州技师学院', '软件开发', '大专', '建设银行', '曾建文', '3363437454345434624', '18211570079', '2017-01-02 00:00:00', null, '1');
INSERT INTO `t_emp` VALUES ('4028a081596ee67201596f1613b2000a', '297e3390594d742f01594d8c86840001', '4028808c5961a80a015961bda7030002', '唐洁龙', '4QrcOUm6Wau+VuBX8g+IPg==', '354654343645666688', '江西', 'male', '00013', '2017-01-11 00:00:00', 'tjl@12.com', '15879743015', '3463534532', 'Tjlsmart', '江西赣州章贡区', '是', '唐某某', '14364564344', '赣州技师学院', '软件开发', '大专', '建设银行', '唐洁龙', '4346434524534534656', '15879743015', '2017-01-08 00:00:00', null, '1');
INSERT INTO `t_emp` VALUES ('4028a081596ee67201596f18484a000b', '297e3390594d742f01594d8c86840001', '4028808c5961a80a015961bd2bc70001', '刘成胜', '4QrcOUm6Wau+VuBX8g+IPg==', '235334534534534560', '江西', 'male', '00014', '2017-01-24 00:00:00', 'lcs@126.com', '15579788260', '2354534', 'Lcssmart', '江西赣州章贡区', '是', '刘某某', '16433236434', '江西赣州技师学院', '软件开发', '大专', '建设银行', '刘成胜', '3233457453786554880', '15579788260', '2017-01-25 00:00:00', null, '1');
INSERT INTO `t_emp` VALUES ('4028a081596ee67201596f1aee40000c', '297e3390594d742f01594d8ce4440002', '4028808c596d9c2001596d9ff7a30001', '李三', '4QrcOUm6Wau+VuBX8g+IPg==', '234565477686745760', '江西', 'male', '00015', '2017-01-14 00:00:00', 'ls@126.com', '18279700988', '233464253', 'Lssmart', '江西赣州信丰', '是', '李某某', '23524543533', '江西赣州技师学院', '后勤主任养成', '大专', '建设银行', '李三', '3453463453346353664', '18279700988', '2017-01-17 00:00:00', null, '1');
INSERT INTO `t_emp` VALUES ('4028a081596ee67201596f1dd900000e', '4028a081593652bb0159365c87f20001', '4028808c596d9c2001596da08cad0002', '吕晨', '4QrcOUm6Wau+VuBX8g+IPg==', '235365463454634528', '江西', 'male', '00016', '2017-01-11 00:00:00', 'lc@126.com', '17890987656', '23365454', 'Lcsmart', '江西赣州石城', '是', '吕某某', '13453464534', '江西赣州技师学院', '财政管理', '大专', '建设银行', '吕晨', '3345436435434524160', '17890987656', '2017-01-02 00:00:00', null, '1');
INSERT INTO `t_emp` VALUES ('4028a081596ee67201596f20efa4000f', '4028808c5969256c01596927d8880000', '4028808c5972e397015972e8afe70000', '李丽', '4QrcOUm6Wau+VuBX8g+IPg==', '233342342342342336', '江西', 'female', '00017', '2017-01-02 00:00:00', 'll@126.com', '18279700223', '23242342', 'Llsmart', '江西赣州石城', '是', '李某某', '12534674543', '江西赣州技师学院', '招生老师养成', '大专', '建设银行', '李丽', '3543645657655555584', '18279700223', '2017-01-02 00:00:00', null, '1');
INSERT INTO `t_emp` VALUES ('4028a081596ee67201596f29983f0010', '297e3390594d742f01594d8c86840001', '4028808c5961a80a015961bd2bc70001', '刘军', '4QrcOUm6Wau+VuBX8g+IPg==', '325346453474565760', '江西', 'male', '00018', '2017-01-14 00:00:00', 'lj@126.com', '18174099373', '134534534', 'Ljsmart', '江西赣州', '否', '刘某某', '23323634254', '江西赣州技师学院', '软件开发', '大专', '建设银行', '刘军', '3456475463464454656', '18174099373', '2017-01-17 00:00:00', null, '1');
INSERT INTO `t_emp` VALUES ('4028a0815983d4b8015983d865aa0000', '4028808c5969256c01596927d8880000', '4028808c596d9c2001596d9f24200000', '刘兰兰', '4QrcOUm6Wau+VuBX8g+IPg==', '333333333333334464', '江西', 'female', '00020', '2017-01-09 00:00:00', 'lll@126.com', '18289890987', '123124543', 'Lllsmart', '江西赣州', '是', '刘某某', '12432424533', '江西赣州技师学院', '招生老师养成', '大专', '建设银行', '刘兰兰', '3353534342342342144', '18289890987', '2017-01-09 00:00:00', null, '1');
INSERT INTO `t_emp` VALUES ('69f49c83c7f411e6a24b3065ec373466', '3d314f24c7f311e6a24b3065ec373466', 'cd8089aec7f211e6a24b3065ec373466', '陈生武', '4QrcOUm6Wau+VuBX8g+IPg==', '360735199812292096', '江西', 'male', '00001', '2016-12-22 00:00:00', 'csw@126.com', '18279700221', '672630222', 'Cswsmart', '江西赣州', '是', '陈某某', '18723487687', '江西赣州技师学院', '老板养成', '大学', '建设银行', '陈生武', '6222021001116298240', '1872347687', '2016-12-22 00:00:00', '2017-01-17 00:00:00', '1');

-- ----------------------------
-- Table structure for t_empappeal
-- ----------------------------
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

-- ----------------------------
-- Records of t_empappeal
-- ----------------------------

-- ----------------------------
-- Table structure for t_empchecking
-- ----------------------------
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

-- ----------------------------
-- Records of t_empchecking
-- ----------------------------

-- ----------------------------
-- Table structure for t_empcheckinginfo
-- ----------------------------
DROP TABLE IF EXISTS `t_empcheckinginfo`;
CREATE TABLE `t_empcheckinginfo` (
  `checkinginfoid` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `checkingtime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`checkinginfoid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_empcheckinginfo
-- ----------------------------
INSERT INTO `t_empcheckinginfo` VALUES ('4028808c596df29201596e8db1c30001', '上班打卡', '08:00');
INSERT INTO `t_empcheckinginfo` VALUES ('4028808c596df29201596e8e2f430002', '下班打卡', '17:00');
INSERT INTO `t_empcheckinginfo` VALUES ('4028808c596df29201596e8ead000003', '加班上班打卡', '19:00');
INSERT INTO `t_empcheckinginfo` VALUES ('4028808c596df29201596e8ed9c20004', '加班下班打卡', '21:00');

-- ----------------------------
-- Table structure for t_empfeedback
-- ----------------------------
DROP TABLE IF EXISTS `t_empfeedback`;
CREATE TABLE `t_empfeedback` (
  `feedbackid` varchar(32) NOT NULL COMMENT '反馈编号',
  `empid` varchar(32) NOT NULL COMMENT '员工编号',
  `feedbackday` datetime DEFAULT NULL COMMENT '反馈时间',
  `des` varchar(500) DEFAULT NULL COMMENT '反馈详情',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  PRIMARY KEY (`feedbackid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_empfeedback
-- ----------------------------
INSERT INTO `t_empfeedback` VALUES ('4028808c59697c0c01596980bf590000', '297e3390594d742f01594d8a22ea0000', '2017-01-04 00:00:00', '测试方法', '1');
INSERT INTO `t_empfeedback` VALUES ('402880af59ac17ff0159ac19f5ba0000', '69f49c83c7f411e6a24b3065ec373466', '2017-01-17 19:05:00', '食堂吃饭问题', '1');
INSERT INTO `t_empfeedback` VALUES ('402880af59ac17ff0159ac267b350002', '4028a081596ee67201596ef5fc810000', '2017-01-17 19:18:40', '交学费', '1');
INSERT INTO `t_empfeedback` VALUES ('402880af59ac17ff0159ac27406c0003', '4028a081596ee67201596f20efa4000f', '2017-01-17 19:19:31', '测试', '1');

-- ----------------------------
-- Table structure for t_empleave
-- ----------------------------
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

-- ----------------------------
-- Records of t_empleave
-- ----------------------------

-- ----------------------------
-- Table structure for t_exp
-- ----------------------------
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

-- ----------------------------
-- Records of t_exp
-- ----------------------------

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
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

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES ('402880af59ac7aee0159ac7d99490001', '篮球', '20', '篮球', '100', '2017-01-17 20:53:50', '4028808c5969256c0159692e934e0002', '1');
INSERT INTO `t_goods` VALUES ('402880af59ac7aee0159ac7f3efc0005', '电脑桌', '1', '电脑桌', '90', '2017-01-17 20:55:37', '4028808c5969256c01596935acab0004', '1');
INSERT INTO `t_goods` VALUES ('402880af59ac7aee0159ac82fd480008', '羽毛球', '20', '羽毛球', '40', '2017-01-17 20:59:43', '4028808c5969256c0159692e934e0002', '1');
INSERT INTO `t_goods` VALUES ('402880af59ac7aee0159ac84b35d000c', '水', '20', '水', '2', '2017-01-17 21:01:35', '4028808c5969256c0159692d84060001', '1');
INSERT INTO `t_goods` VALUES ('4028da1d59ac31550159ac362c040000', '引用水', '100', '老师和学生喝的水', '12', '2017-01-17 19:35:48', '4028808c5969256c0159692d84060001', '1');
INSERT INTO `t_goods` VALUES ('4028da1d59ac31550159ac37353e0001', '电脑', '200', '学生上课用的电脑', '3000', '2017-01-17 19:36:56', '4028808c5969256c015969367da90005', '0');
INSERT INTO `t_goods` VALUES ('4028da1d59ac31550159ac3878f50002', '白板', '5', '上课用的白板', '100', '2017-01-17 00:00:00', '4028da1d59ac31550159ac38d9000003', '1');

-- ----------------------------
-- Table structure for t_goodsapp
-- ----------------------------
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
  `goodstypeid` varchar(32) NOT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`goodsappid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_goodsapp
-- ----------------------------
INSERT INTO `t_goodsapp` VALUES ('402880af59ac7aee0159ac82c4c10007', '69f49c83c7f411e6a24b3065ec373466', '2017-01-17 20:59:28', '羽毛球', '20', '上体育课用的', '1', '1', '4028808c5969256c0159692e934e0002', '40');
INSERT INTO `t_goodsapp` VALUES ('402880af59ac7aee0159ac849258000b', '69f49c83c7f411e6a24b3065ec373466', '2017-01-17 21:01:26', '水', '20', '测试测试', '1', '1', '4028808c5969256c0159692d84060001', '2');

-- ----------------------------
-- Table structure for t_goodstype
-- ----------------------------
DROP TABLE IF EXISTS `t_goodstype`;
CREATE TABLE `t_goodstype` (
  `goodstypeid` varchar(32) NOT NULL COMMENT '物品类型编号',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `des` varchar(500) DEFAULT NULL COMMENT '描述',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，默认可用',
  PRIMARY KEY (`goodstypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_goodstype
-- ----------------------------
INSERT INTO `t_goodstype` VALUES ('4028808c5969256c0159692d84060001', '消耗类', '比如水杯、水、纸等等一些用了就没有的东西', '1');
INSERT INTO `t_goodstype` VALUES ('4028808c5969256c0159692e934e0002', '体育用品', '比如篮球、羽毛球等等一些体育器材', '1');
INSERT INTO `t_goodstype` VALUES ('4028808c5969256c01596935acab0004', '桌椅类', '比如办公桌椅，学生桌椅', '1');
INSERT INTO `t_goodstype` VALUES ('4028808c5969256c015969367da90005', '电器类', '比如，电脑，鼠标，键盘，音响，麦克风，投影仪等等', '1');
INSERT INTO `t_goodstype` VALUES ('4028da1d59ac31550159ac38d9000003', '教学类', '上课需要用的，如白板，笔', '1');

-- ----------------------------
-- Table structure for t_goodsuse
-- ----------------------------
DROP TABLE IF EXISTS `t_goodsuse`;
CREATE TABLE `t_goodsuse` (
  `useid` varchar(32) NOT NULL COMMENT '领用编号',
  `goodsid` varchar(32) NOT NULL COMMENT '物品编号，来源于物品表',
  `empid` varchar(32) NOT NULL COMMENT '员工编号，来源于员工表',
  `quantity` int(11) DEFAULT NULL COMMENT '领用的物品数量',
  `useday` datetime DEFAULT NULL COMMENT '信用时间',
  `ereturnday` datetime DEFAULT NULL COMMENT '预估归还时间',
  `returnday` datetime DEFAULT NULL COMMENT '归还时间',
  `returnstatus` int(11) DEFAULT NULL,
  `checkstatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`useid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_goodsuse
-- ----------------------------

-- ----------------------------
-- Table structure for t_grade
-- ----------------------------
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

-- ----------------------------
-- Records of t_grade
-- ----------------------------
INSERT INTO `t_grade` VALUES ('4028808c59697c0c0159698a524a0002', '16秋预科', '4028a081596ee67201596efa6b0f0003', '4028a081596ee67201596ef5fc810000', '4028808c5967eae5015967fa466a0000', '16秋的学生', '34', '1');
INSERT INTO `t_grade` VALUES ('4028808c597114d801597144ae110000', '16春预科', '4028a081596ee67201596f03221f0004', '4028a081596ee67201596f09a1250006', '4028a081596ee67201596f1613b2000a', '16届的学生', '35', '1');
INSERT INTO `t_grade` VALUES ('4028808c597114d80159714969e50001', '15秋1班', '4028a081596ee67201596f0fbafb0008', '4028a081596ee67201596f29983f0010', '4028a081596ee67201596f09a1250006', '15秋1班的学生', '45', '1');
INSERT INTO `t_grade` VALUES ('4028da1c5925b27e015925b2a8340000', '15秋预科', '4028a081596ee67201596f03221f0004', '297e3390594d742f01594d8a22ea0000', '4028808c5967eae5015967fa466a0000', '15届的学生', '20', '1');
INSERT INTO `t_grade` VALUES ('4028da1c5925b27e015925b2e0da0001', '15春预科', '4028a081596ee67201596f03221f0004', '4028a081596ee67201596f0cb2de0007', '4028a081596ee67201596f1613b2000a', '15春的学生测试', '30', '1');

-- ----------------------------
-- Table structure for t_income
-- ----------------------------
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

-- ----------------------------
-- Records of t_income
-- ----------------------------
INSERT INTO `t_income` VALUES ('4028808c59697c0c01596991c9560009', '4028808c59697c0c0159698f2c3a0006', '2017-01-04 21:01:19', '预定报名费', '100', '4028808c5967eae5015967fa466a0000', '37310718c90711e6bd803065ec373466');
INSERT INTO `t_income` VALUES ('4028808c5971c037015971c1fa5c0001', '4028808c5971bc42015971bf6aae0002', '2017-01-06 11:10:55', '预定报名费', '500', '69f49c83c7f411e6a24b3065ec373466', '4028808c5971bc42015971bdf80c0000');
INSERT INTO `t_income` VALUES ('4028808c5971c037015971c271210002', '4028808c5971bc42015971bf6aae0002', '2017-01-06 11:11:25', '预定报名费', '500', '69f49c83c7f411e6a24b3065ec373466', '4028808c5969256c0159695980e1000e');
INSERT INTO `t_income` VALUES ('4028808c5971c037015971c322c40004', '4028808c59697c0c0159698f2c3a0006', '2017-01-06 11:12:11', '学费', '3500', '69f49c83c7f411e6a24b3065ec373466', '4028808c5971c037015971c2e1c80003');
INSERT INTO `t_income` VALUES ('4028808c5971c037015971c4b1d50006', '4028808c5971bc42015971bf6aae0002', '2017-01-06 11:13:53', '预定报名费', '500', '69f49c83c7f411e6a24b3065ec373466', '4028808c5971c037015971c4b0bf0005');
INSERT INTO `t_income` VALUES ('4028da1d59ac31550159ac4186ca0008', '4028808c5971bc42015971bf6aae0002', '2017-01-17 19:48:13', '预定报名费', '500', '4028a081596ee67201596f20efa4000f', '4028da1d59ac31550159ac41013e0007');
INSERT INTO `t_income` VALUES ('4028da1d59ac31550159ac430a060009', '4028808c59697c0c0159698f2c3a0006', '2017-01-17 19:49:52', '学费', '3500', '4028a081596ee67201596f20efa4000f', '4028da1d59ac31550159ac41013e0007');
INSERT INTO `t_income` VALUES ('4028da1d59ac31550159ac442723000b', '4028808c59697c0c0159698f2c3a0006', '2017-01-17 19:51:05', '学费', '3500', '4028a081596ee67201596f20efa4000f', '4028da1d59ac31550159ac4426c3000a');
INSERT INTO `t_income` VALUES ('4028da1d59ac31550159ac4691c6000d', '4028808c59697c0c0159698f2c3a0006', '2017-01-17 19:53:43', '学费', '3500', '4028a081596ee67201596f03221f0004', '4028da1d59ac31550159ac46919d000c');
INSERT INTO `t_income` VALUES ('4028da1d59ac31550159ac47bdd4000f', '4028808c59697c0c0159698f2c3a0006', '2017-01-17 19:55:00', '学费', '3500', '4028a081596ee67201596f03221f0004', '4028da1d59ac31550159ac47bd87000e');
INSERT INTO `t_income` VALUES ('4028da1d59ac4d220159ac61c011001e', '4028808c59697c0c0159698f2c3a0006', '2017-01-17 20:23:24', '学费', '3500', '4028a081596ee67201596f03221f0004', '4028da1d59ac4d220159ac61bfcd001d');
INSERT INTO `t_income` VALUES ('4028da1d59ac4d220159ac632f730020', '4028808c59697c0c0159698f2c3a0006', '2017-01-17 20:24:58', '学费', '3500', '4028a081596ee67201596f03221f0004', '4028da1d59ac4d220159ac632f3a001f');
INSERT INTO `t_income` VALUES ('4028da1d59ac6c620159ac6f2f770001', '4028808c59697c0c0159698f2c3a0006', '2017-01-17 20:38:05', '学费', '3500', '69f49c83c7f411e6a24b3065ec373466', '4028da1d59ac6c620159ac6f2f050000');

-- ----------------------------
-- Table structure for t_incometype
-- ----------------------------
DROP TABLE IF EXISTS `t_incometype`;
CREATE TABLE `t_incometype` (
  `incometypeid` varchar(32) NOT NULL COMMENT '收入类别编号',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `des` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  PRIMARY KEY (`incometypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_incometype
-- ----------------------------
INSERT INTO `t_incometype` VALUES ('4028808c59697c0c0159698ee5fe0005', '项目收入', '老师们做项目的一些收入', '1');
INSERT INTO `t_incometype` VALUES ('4028808c59697c0c0159698f2c3a0006', '学费', '缴纳学生学费的收入测试', '1');
INSERT INTO `t_incometype` VALUES ('4028808c5971bc42015971bf6aae0002', '预定报名费', '意向学生转为预订学生时收的费用', '1');

-- ----------------------------
-- Table structure for t_job
-- ----------------------------
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

-- ----------------------------
-- Records of t_job
-- ----------------------------
INSERT INTO `t_job` VALUES ('297e5afe593b726401593b7465640000', 'd483660cc81b11e6a24b3065ec373466', '赣州房产股份有限公司', '总管', '6000', '五险一金', '赣州市南康区', '1890993', '2016-12-26 00:00:00', '1');
INSERT INTO `t_job` VALUES ('4028a0815936943a0159369583970000', '37310718c90711e6bd803065ec373466', '赣州美嘉有限公司', '销售经理', '7000', '五险一金', '赣州市章贡区', '1678987', '2016-12-25 00:00:00', '1');
INSERT INTO `t_job` VALUES ('4028da2659823dce0159823fe41c0000', '37310718c90711e6bd803065ec373466', '深圳科技有限公司', '组长', '8000', '五险一金', '赣州市兴国县', '1789905', '2017-01-09 00:00:00', '1');
INSERT INTO `t_job` VALUES ('4028da2659823dce0159824526a30001', 'd483660cc81b11e6a24b3065ec373466', '上海科技有限公司', '项目经理', '11000', '五险一金', '赣州市崇义县', '1675566', '2017-01-09 00:00:00', '1');
INSERT INTO `t_job` VALUES ('4028da265982496b0159824d1c9e0000', 'd483660cc81b11e6a24b3065ec373466', '腾讯科技有限集团', '员工', '9000', '包吃包住 五险一金', '赣州市会昌县', '1778898', '2017-01-09 00:00:00', '1');
INSERT INTO `t_job` VALUES ('ad669d31c90d11e6bd803065ec373466', '15d3c0c8c90711e6bd803065ec373466', '赣州科技有限公司', '技术总监', '8200', '五险一金', '赣州市章贡区', '1990009', '2016-12-25 00:00:00', '1');

-- ----------------------------
-- Table structure for t_meetting
-- ----------------------------
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

-- ----------------------------
-- Records of t_meetting
-- ----------------------------
INSERT INTO `t_meetting` VALUES ('4028da1d59ab142a0159ab1676a20000', '4028a081596ee67201596ef5fc810000', '2017-01-17 00:00:00', '2017-01-17 00:00:00', '减学费', '1');
INSERT INTO `t_meetting` VALUES ('4028daf859a4976c0159a4f9e8ba0006', '69f49c83c7f411e6a24b3065ec373466', '2017-01-18 00:00:00', '2017-01-16 00:00:00', '百花齐放，百家争鸣。', '1');

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `noticeid` varchar(32) NOT NULL COMMENT '公告编号',
  `name` varchar(50) DEFAULT NULL COMMENT '公告标题',
  `des` varchar(500) DEFAULT NULL COMMENT '公告详情',
  `typeid` varchar(32) NOT NULL COMMENT '公告类型',
  `noticeday` datetime DEFAULT NULL COMMENT '发布时间',
  `empid` varchar(32) NOT NULL COMMENT '发布人',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，默认可用',
  PRIMARY KEY (`noticeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES ('4028808c5977413301597741fa070000', '停水公告', '于2017-02-17日开始全校停水，大家做好存水工作', '4028808c5961a80a015961c3cc9b0005', '2017-01-13 14:03:45', '69f49c83c7f411e6a24b3065ec373466', '1');
INSERT INTO `t_notice` VALUES ('4028808c5977413301597742a0f70001', '表扬公告', '陈俊同学捡到了一张饭卡上交到班主任处，值得大家去学习', '4028da265995bbc20159966dd1e80000', '2017-01-07 15:36:13', '69f49c83c7f411e6a24b3065ec373466', '1');
INSERT INTO `t_notice` VALUES ('4028808c5977413301597742c07c0002', '放假通知', '于2017-01-21开始放假，2017-02-14日全体师生返校', '4028808c5977413301597742c12d0002', '2017-01-07 09:33:33', '69f49c83c7f411e6a24b3065ec373466', '1');
INSERT INTO `t_notice` VALUES ('4028da1d59ac4d220159ac6b04730021', '活动公告', '本周六举行野炊，各个部门做好学生安全的工作', '4028808c5961a80a015961c307990004', '2017-01-17 20:32:37', '4028808c59685ee001596864f57a0000', '1');
INSERT INTO `t_notice` VALUES ('4028da1d59ac6c620159ac7386910002', '放假公告', '春节放假改为1月20号', '4028808c5977413301597742c12d0002', '2017-01-17 20:42:21', '69f49c83c7f411e6a24b3065ec373466', '1');

-- ----------------------------
-- Table structure for t_noticetype
-- ----------------------------
DROP TABLE IF EXISTS `t_noticetype`;
CREATE TABLE `t_noticetype` (
  `noticetypeid` varchar(32) NOT NULL COMMENT '公告类型编号',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `des` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  PRIMARY KEY (`noticetypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_noticetype
-- ----------------------------
INSERT INTO `t_noticetype` VALUES ('4028808c594422ce015945205a490001', '奖励公告', '通告表扬学生或老师的通告', '1');
INSERT INTO `t_noticetype` VALUES ('4028808c5961a80a015961c23fed0003', '惩罚公告', '学生或老师表现不好的通报批评', '1');
INSERT INTO `t_noticetype` VALUES ('4028808c5961a80a015961c307990004', '活动公告', '学生组织的活动，如郊游', '1');
INSERT INTO `t_noticetype` VALUES ('4028808c5961a80a015961c3cc9b0005', '其他公告', '用作其他公告', '1');
INSERT INTO `t_noticetype` VALUES ('4028808c5977413301597742c12d0002', '放假公告', '用作放假的通知', '1');
INSERT INTO `t_noticetype` VALUES ('4028da265995bbc20159966dd1e80000', '表扬公告', '好人好事表扬', '1');

-- ----------------------------
-- Table structure for t_pay
-- ----------------------------
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

-- ----------------------------
-- Records of t_pay
-- ----------------------------
INSERT INTO `t_pay` VALUES ('402880af59ac7aee0159ac7d4de30000', '4028808c59697c0c0159698d87580004', '2017-01-17 20:53:04', '测试测试', '100', '69f49c83c7f411e6a24b3065ec373466', '陈生武', '18272897343');
INSERT INTO `t_pay` VALUES ('402880af59ac7aee0159ac84b39a000d', '402880af59ac7aee0159ac8432e2000a', '2017-01-17 21:01:35', '测试测试', '40', '69f49c83c7f411e6a24b3065ec373466', null, null);

-- ----------------------------
-- Table structure for t_paytype
-- ----------------------------
DROP TABLE IF EXISTS `t_paytype`;
CREATE TABLE `t_paytype` (
  `paytypeid` varchar(32) NOT NULL COMMENT '支出类别编号',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `des` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  PRIMARY KEY (`paytypeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_paytype
-- ----------------------------
INSERT INTO `t_paytype` VALUES ('4028808c59697c0c0159698d11330003', '聚餐', '聚餐的支出', '1');
INSERT INTO `t_paytype` VALUES ('4028808c59697c0c0159698d87580004', '旅游', '旅游的支出', '1');
INSERT INTO `t_paytype` VALUES ('402880af59ac7aee0159ac8432e2000a', '申购', '员工申请购买用品', '1');

-- ----------------------------
-- Table structure for t_progress
-- ----------------------------
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

-- ----------------------------
-- Records of t_progress
-- ----------------------------
INSERT INTO `t_progress` VALUES ('4028808c598c572a01598c5a5afc0000', '297e3390594d742f01594d8a22ea0000', '4028808c59697c0c0159698a524a0002', '测试', '2017-01-30 00:00:00', '1');
INSERT INTO `t_progress` VALUES ('4028808c598c572a01598c5cb8760001', '4028a081596ee67201596ef5fc810000', '4028808c597114d80159714969e50001', '测试', '2017-01-03 00:00:00', '1');
INSERT INTO `t_progress` VALUES ('4028da1d59ab142a0159ab1ba40c0001', '297e3390594d742f01594d8a22ea0000', '4028da1c5925b27e015925b2a8340000', '明天答辩', '2017-01-17 00:00:00', '1');

-- ----------------------------
-- Table structure for t_report
-- ----------------------------
DROP TABLE IF EXISTS `t_report`;
CREATE TABLE `t_report` (
  `reportid` varchar(32) NOT NULL COMMENT '工作日志编号',
  `empid` varchar(32) NOT NULL COMMENT '员工编号',
  `reportday` datetime DEFAULT NULL COMMENT '日志时间',
  `des` varchar(500) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  PRIMARY KEY (`reportid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_report
-- ----------------------------
INSERT INTO `t_report` VALUES ('4028808c5969256c015969495a6c000b', '69f49c83c7f411e6a24b3065ec373466', '2017-01-04 19:42:03', '我今天什么都没有做。', '1');
INSERT INTO `t_report` VALUES ('4028808c5969256c0159694b0b0f000c', '69f49c83c7f411e6a24b3065ec373466', '2017-01-03 19:43:41', '做了什么什么。。。', '1');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `roleid` varchar(32) NOT NULL COMMENT '角色编号',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  `des` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  PRIMARY KEY (`roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('4028808c5961a80a015961bcb7100000', '班主任', '班导，一个班导可以有多个班级', '1');
INSERT INTO `t_role` VALUES ('4028808c5961a80a015961bd2bc70001', '任课老师', '学生上课的老师', '1');
INSERT INTO `t_role` VALUES ('4028808c5961a80a015961bda7030002', '辅导老师', '给学生做辅导的老师', '1');
INSERT INTO `t_role` VALUES ('4028808c59697c0c015969a165b2000a', '校长', '管理所有的班主任', '1');
INSERT INTO `t_role` VALUES ('4028808c596d9c2001596d9f24200000', '招生老师', '招生部的负责招学生', '1');
INSERT INTO `t_role` VALUES ('4028808c596d9c2001596d9ff7a30001', '后勤主任', '后勤部的员工', '1');
INSERT INTO `t_role` VALUES ('4028808c596d9c2001596da08cad0002', '财务主任', '负责财政的', '1');
INSERT INTO `t_role` VALUES ('4028808c5972e397015972e8afe70000', '招生主任', '管理所有的招生老师', '1');
INSERT INTO `t_role` VALUES ('4028808c5972e397015972e95e740001', '教务主任', '管理所有的任课老师和辅导老师', '1');
INSERT INTO `t_role` VALUES ('4028da265981b09d015981d91d6f0001', '学生', '所有的学生', '1');
INSERT INTO `t_role` VALUES ('cd8089aec7f211e6a24b3065ec373466', '总经理', '公司最高层', '1');

-- ----------------------------
-- Table structure for t_room
-- ----------------------------
DROP TABLE IF EXISTS `t_room`;
CREATE TABLE `t_room` (
  `roomid` varchar(32) NOT NULL COMMENT '宿舍编号',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `stuid` varchar(32) DEFAULT NULL COMMENT '宿舍长编号',
  `quantity` int(11) NOT NULL COMMENT '宿舍最大人数',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认为可用',
  PRIMARY KEY (`roomid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_room
-- ----------------------------
INSERT INTO `t_room` VALUES ('4028808c59695d0301596961d37d0000', '4-101', '', '6', '1');
INSERT INTO `t_room` VALUES ('4028808c59695d0301596961f8260001', '4-201', '', '6', '1');
INSERT INTO `t_room` VALUES ('4028808c59695d03015969621ded0002', '4-103', '', '6', '1');
INSERT INTO `t_room` VALUES ('4028808c59695d030159696249aa0003', '8-101', '', '6', '1');

-- ----------------------------
-- Table structure for t_salary
-- ----------------------------
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

-- ----------------------------
-- Records of t_salary
-- ----------------------------
INSERT INTO `t_salary` VALUES ('4028808c59765e300159765f7b6b0001', '4028a081596ee67201596ef5fc810000', '100', '50', '2017-01-07 08:41:26', '7550');
INSERT INTO `t_salary` VALUES ('4028808c597662550159766395f00001', '4028808c5972e397015972f1de7d0002', '200', '60', '2017-01-06 00:00:00', '3140');
INSERT INTO `t_salary` VALUES ('4028da26597679410159767a5e0c0001', '4028a081596ee67201596efa6b0f0003', '100', '30', '2017-01-07 00:00:00', '4070');
INSERT INTO `t_salary` VALUES ('4028da26598b2b1001598b3024950003', '4028a081596ee67201596f068d0e0005', '343', '434', '2017-01-03 00:00:00', '3677');
INSERT INTO `t_salary` VALUES ('4028da26598b2b1001598b30ba360005', '4028a081596ee67201596f03221f0004', '345', '345', '2016-12-26 00:00:00', '43890');
INSERT INTO `t_salary` VALUES ('4028da26598b2b1001598b31e34e0007', '4028a081596ee67201596f20efa4000f', '34', '34', '2017-01-02 00:00:00', '34877');
INSERT INTO `t_salary` VALUES ('4028da26598b2b1001598b37c7240009', '4028a081596ee67201596f1aee40000c', '345', '34', '2017-01-02 00:00:00', '47118');
INSERT INTO `t_salary` VALUES ('cf70da43d40211e6849e3065ec373466', '297e3390594d742f01594d8a22ea0000', '100', '50', '2017-01-03 00:00:00', '7050');

-- ----------------------------
-- Table structure for t_salaryinfo
-- ----------------------------
DROP TABLE IF EXISTS `t_salaryinfo`;
CREATE TABLE `t_salaryinfo` (
  `salaryinfoid` varchar(32) NOT NULL COMMENT '工资情况编号',
  `empid` varchar(32) NOT NULL COMMENT '员工编号',
  `basicsalary` double NOT NULL COMMENT '基本工资',
  `jobsalary` double NOT NULL COMMENT '岗位工资',
  PRIMARY KEY (`salaryinfoid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_salaryinfo
-- ----------------------------
INSERT INTO `t_salaryinfo` VALUES ('366524efd40211e6849e3065ec373466', '297e3390594d742f01594d8a22ea0000', '6000', '1000');
INSERT INTO `t_salaryinfo` VALUES ('4028808c59765e300159765f796a0000', '4028a081596ee67201596ef5fc810000', '6500', '1000');
INSERT INTO `t_salaryinfo` VALUES ('4028808c597662550159766395150000', '4028808c5972e397015972f1de7d0002', '2000', '1000');
INSERT INTO `t_salaryinfo` VALUES ('402880af59ac7aee0159ac89b661000e', '4028808c5967eae5015967fa466a0000', '3000', '1000');
INSERT INTO `t_salaryinfo` VALUES ('402880af59ac7aee0159ac89e307000f', '4028808c59685ee001596864f57a0000', '4000', '1000');
INSERT INTO `t_salaryinfo` VALUES ('402880af59ac7aee0159ac8a440e0010', '4028a081596ee67201596f09a1250006', '4000', '1000');
INSERT INTO `t_salaryinfo` VALUES ('402880af59ac7aee0159ac8a79d60011', '4028a081596ee67201596f0cb2de0007', '3000', '1000');
INSERT INTO `t_salaryinfo` VALUES ('402880af59ac7aee0159ac8aadb50012', '4028a081596ee67201596f0fbafb0008', '3000', '1000');
INSERT INTO `t_salaryinfo` VALUES ('402880af59ac7aee0159ac8ad74e0013', '4028a081596ee67201596f12e8ba0009', '3000', '1000');
INSERT INTO `t_salaryinfo` VALUES ('402880af59ac7aee0159ac8afb9e0014', '4028a081596ee67201596f1613b2000a', '1000', '1000');
INSERT INTO `t_salaryinfo` VALUES ('402880af59ac7aee0159ac8b424e0015', '4028a081596ee67201596f18484a000b', '3000', '1000');
INSERT INTO `t_salaryinfo` VALUES ('402880af59ac7aee0159ac8b850d0016', '4028a081596ee67201596f1dd900000e', '2000', '1000');
INSERT INTO `t_salaryinfo` VALUES ('402880af59ac7aee0159ac8bb3650017', '4028a081596ee67201596f29983f0010', '4000', '1000');
INSERT INTO `t_salaryinfo` VALUES ('402880af59ac7aee0159ac8bd6350018', '4028a0815983d4b8015983d865aa0000', '4000', '1000');
INSERT INTO `t_salaryinfo` VALUES ('402880af59ac7aee0159ac8c15260019', '69f49c83c7f411e6a24b3065ec373466', '7000', '2000');
INSERT INTO `t_salaryinfo` VALUES ('4028da26597679410159767a5d110000', '4028a081596ee67201596efa6b0f0003', '3000', '1000');
INSERT INTO `t_salaryinfo` VALUES ('4028da26598b2b1001598b30241d0002', '4028a081596ee67201596f068d0e0005', '3334', '434');
INSERT INTO `t_salaryinfo` VALUES ('4028da26598b2b1001598b30b9b90004', '4028a081596ee67201596f03221f0004', '43545', '345');
INSERT INTO `t_salaryinfo` VALUES ('4028da26598b2b1001598b31e2d00006', '4028a081596ee67201596f20efa4000f', '34534', '343');
INSERT INTO `t_salaryinfo` VALUES ('4028da26598b2b1001598b37c6a50008', '4028a081596ee67201596f1aee40000c', '43354', '3453');

-- ----------------------------
-- Table structure for t_score
-- ----------------------------
DROP TABLE IF EXISTS `t_score`;
CREATE TABLE `t_score` (
  `scoreid` varchar(36) NOT NULL COMMENT '成绩编号',
  `stuid` varchar(32) NOT NULL COMMENT '学生编号',
  `courseid` varchar(32) NOT NULL COMMENT '课程编号',
  `score` float DEFAULT NULL COMMENT '成绩',
  `testday` datetime DEFAULT NULL COMMENT '考试时间',
  PRIMARY KEY (`scoreid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_score
-- ----------------------------
INSERT INTO `t_score` VALUES ('5ffdd75fdcaf11e696ddf832e40312b3', '4028da1d59ac31550159ac41013e0007', '4028808c593f4b2b01593f4cd47b0000', '80', '2017-01-17 20:20:19');
INSERT INTO `t_score` VALUES ('60170b5bdcaf11e696ddf832e40312b3', '4028da1d59ac31550159ac41013e0007', '4028808c593f4b2b01593f4dc3730001', '90', '2017-01-17 20:20:20');
INSERT INTO `t_score` VALUES ('601dce6adcaf11e696ddf832e40312b3', '4028da1d59ac31550159ac41013e0007', '4028808c593f4b2b01593f4e527e0002', '80', '2017-01-17 20:20:20');
INSERT INTO `t_score` VALUES ('6025a50cdcaf11e696ddf832e40312b3', '4028da1d59ac31550159ac41013e0007', '4028808c593f4b2b01593f4f7ef50003', '90', '2017-01-17 20:20:20');
INSERT INTO `t_score` VALUES ('602e1e8ddcaf11e696ddf832e40312b3', '4028da1d59ac31550159ac41013e0007', '4028808c59695d03015969697c260005', '80', '2017-01-17 20:20:20');
INSERT INTO `t_score` VALUES ('6036cf02dcaf11e696ddf832e40312b3', '4028da1d59ac31550159ac4426c3000a', '4028808c593f4b2b01593f4cd47b0000', '89', '2017-01-17 20:20:20');
INSERT INTO `t_score` VALUES ('6040ff99dcaf11e696ddf832e40312b3', '4028da1d59ac31550159ac4426c3000a', '4028808c593f4b2b01593f4dc3730001', '95', '2017-01-17 20:20:20');
INSERT INTO `t_score` VALUES ('604903a7dcaf11e696ddf832e40312b3', '4028da1d59ac31550159ac4426c3000a', '4028808c593f4b2b01593f4e527e0002', '94', '2017-01-17 20:20:20');
INSERT INTO `t_score` VALUES ('6051e8a7dcaf11e696ddf832e40312b3', '4028da1d59ac31550159ac4426c3000a', '4028808c593f4b2b01593f4f7ef50003', '92', '2017-01-17 20:20:20');
INSERT INTO `t_score` VALUES ('6059fcb9dcaf11e696ddf832e40312b3', '4028da1d59ac31550159ac4426c3000a', '4028808c59695d03015969697c260005', '91', '2017-01-17 20:20:20');
INSERT INTO `t_score` VALUES ('606466dcdcaf11e696ddf832e40312b3', '4028da1d59ac31550159ac46919d000c', '4028808c593f4b2b01593f4cd47b0000', '81', '2017-01-17 20:20:20');
INSERT INTO `t_score` VALUES ('606c5d91dcaf11e696ddf832e40312b3', '4028da1d59ac31550159ac46919d000c', '4028808c593f4b2b01593f4dc3730001', '91', '2017-01-17 20:20:20');
INSERT INTO `t_score` VALUES ('60756969dcaf11e696ddf832e40312b3', '4028da1d59ac31550159ac46919d000c', '4028808c593f4b2b01593f4e527e0002', '94', '2017-01-17 20:20:20');
INSERT INTO `t_score` VALUES ('607e4378dcaf11e696ddf832e40312b3', '4028da1d59ac31550159ac46919d000c', '4028808c593f4b2b01593f4f7ef50003', '95', '2017-01-17 20:20:20');
INSERT INTO `t_score` VALUES ('60869243dcaf11e696ddf832e40312b3', '4028da1d59ac31550159ac46919d000c', '4028808c59695d03015969697c260005', '96', '2017-01-17 20:20:20');
INSERT INTO `t_score` VALUES ('609007bcdcaf11e696ddf832e40312b3', '4028da1d59ac31550159ac47bd87000e', '4028808c593f4b2b01593f4cd47b0000', '81', '2017-01-17 20:20:20');
INSERT INTO `t_score` VALUES ('609903ebdcaf11e696ddf832e40312b3', '4028da1d59ac31550159ac47bd87000e', '4028808c593f4b2b01593f4dc3730001', '94', '2017-01-17 20:20:20');
INSERT INTO `t_score` VALUES ('60a106dedcaf11e696ddf832e40312b3', '4028da1d59ac31550159ac47bd87000e', '4028808c593f4b2b01593f4e527e0002', '96', '2017-01-17 20:20:20');
INSERT INTO `t_score` VALUES ('60aa9fa6dcaf11e696ddf832e40312b3', '4028da1d59ac31550159ac47bd87000e', '4028808c593f4b2b01593f4f7ef50003', '93', '2017-01-17 20:20:20');
INSERT INTO `t_score` VALUES ('60b24ec8dcaf11e696ddf832e40312b3', '4028da1d59ac31550159ac47bd87000e', '4028808c59695d03015969697c260005', '63', '2017-01-17 20:20:21');

-- ----------------------------
-- Table structure for t_stu
-- ----------------------------
DROP TABLE IF EXISTS `t_stu`;
CREATE TABLE `t_stu` (
  `stuid` varchar(32) NOT NULL COMMENT '学生编号',
  `stuno` varchar(20) DEFAULT NULL COMMENT '学号',
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `pwd` varchar(50) DEFAULT NULL COMMENT '密码',
  `idcard` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `qq` varchar(20) DEFAULT NULL COMMENT 'qq号',
  `wechat` varchar(50) DEFAULT NULL COMMENT '微信号',
  `school` varchar(50) DEFAULT NULL COMMENT '毕业学校',
  `age` int(11) NOT NULL COMMENT '年龄',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `gender` varchar(6) NOT NULL COMMENT '性别',
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
  `roleid` varchar(32) DEFAULT '4028da265981b09d015981d91d6f0001' COMMENT '辨别学生的角色，默认是学生的id',
  PRIMARY KEY (`stuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_stu
-- ----------------------------
INSERT INTO `t_stu` VALUES ('15d3c0c8c90711e6bd803065ec373466', '201701', '李四', '4QrcOUm6Wau+VuBX8g+IPg==', '360732199902111212', '18279700226', '567898778', '567898778', '赣州技师学院', '18', '2017-01-24 00:00:00', 'male', '赣州市', '赣州市', '城市', null, '4028808c59695d0301596961f8260001', '李大四', '16778789090', '2017-01-06 11:10:55', '4028a081596ee67201596f20efa4000f', '暂无', '1', 'official', '学习委员', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('37310718c90711e6bd803065ec373466', '201702', '小兰', '4QrcOUm6Wau+VuBX8g+IPg==', '360732199902112212', '18279700228', '123322223', '567898778', '赣州技师学院', '19', '2017-01-24 00:00:00', 'female', '赣州市', '赣州市', '城市', '4028808c59697c0c0159698a524a0002', '4028808c59695d0301596961d37d0000', '大大兰', '17878786565', '2017-01-06 11:10:55', '4028a081596ee67201596f20efa4000f', '暂无', '1', 'official', '学生', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('4028808c5969256c0159694e5059000d', '201703', '小明', '4QrcOUm6Wau+VuBX8g+IPg==', '360732199902112312', '18279878765', '909998776', '567898778', '赣州技师学院', '17', '2017-01-24 00:00:00', 'male', '赣州市', '赣州市', '城市', null, '4028808c59695d0301596961f8260001', '大小明', '15578787676', '2017-01-06 11:10:55', '4028a081596ee67201596f20efa4000f', '暂无', '1', 'intention', '学生', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('4028808c5969256c0159695980e1000e', '201704', '小丽', '4QrcOUm6Wau+VuBX8g+IPg==', '360732199902112142', '12345656766', '566777890', '567898778', '赣州技师学院', '19', '2017-01-24 00:00:00', 'female', '赣州市', '赣州市', '城市', '4028808c597114d801597144ae110000', '4028808c59695d0301596961f8260001', '大小丽', '15656567676', '2017-01-06 11:11:26', '4028a081596ee67201596f20efa4000f', '暂无', '1', 'reserve', '学生', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('4028808c59697c0c01596985821d0001', '201705', '小王', '4QrcOUm6Wau+VuBX8g+IPg==', '360732199902112152', '17889890909', '226777890', '567898778', '赣州技师学院', '0', '2017-01-24 00:00:00', 'male', '赣州市', '赣州市', '城市', '4028da1c5925b27e015925b2a8340000', '4028808c59695d0301596961f8260001', '老王', '17789890909', '2017-01-06 00:00:00', '4028a081596ee67201596f20efa4000f', '暂无', '1', 'official', '学生', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('4028808c5971bc42015971bdf80c0000', '201706', '小张', '4QrcOUm6Wau+VuBX8g+IPg==', '360732199902112152', '12356567878', '677666990', '567898778', '赣州技师学院', '18', '2017-01-24 00:00:00', 'male', '赣州市', '赣州市', '城市', '4028808c59697c0c0159698a524a0002', '4028808c59695d0301596961d37d0000', '大小张', '12789890099', '2017-01-06 11:10:55', '69f49c83c7f411e6a24b3065ec373466', '暂无', '1', 'reserve', '学生', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('4028808c5971c037015971c2e1c80003', '201707', '小陈', '4QrcOUm6Wau+VuBX8g+IPg==', '360732199902112152', '15578787878', '778990889', '567898778', '赣州技师学院', '19', '2017-01-02 00:00:00', 'male', '赣州市', '赣州市', '城市', null, '4028808c59695d0301596961d37d0000', '大小陈', '15576564545', '2017-01-06 11:12:11', '69f49c83c7f411e6a24b3065ec373466', '暂无', '1', 'official', '学生', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('4028808c5971c037015971c4b0bf0005', '201708', '小丑', '4QrcOUm6Wau+VuBX8g+IPg==', '360732199902112152', '18989890909', '667776667', '567898778', '赣州技师学院', '18', '2017-01-03 00:00:00', 'male', '赣州市', '赣州市', '城市', '4028808c59697c0c0159698a524a0002', '4028808c59695d030159696249aa0003', '大小丑', '15567678787', '2017-01-06 11:10:55', '69f49c83c7f411e6a24b3065ec373466', '暂无', '1', 'reserve', '学生', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('4028da1d59ac31550159ac3b0de50006', null, '小王', null, null, '12345456676', '4445555', '56454545', '赣州二中', '0', '2017-01-16 00:00:00', 'male', null, '江西', null, null, null, null, null, null, '69f49c83c7f411e6a24b3065ec373466', null, '1', 'intention', null, '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('4028da1d59ac31550159ac41013e0007', '201710', '陈俊', '4QrcOUm6Wau+VuBX8g+IPg==', '334443323234324333', '18279700665', '209876573', 'chen', '赣州技师学院', '19', '1997-12-13 00:00:00', 'male', '江西赣州信丰', '江西', '农村', '4028da1c5925b27e015925b2a8340000', '4028808c59695d0301596961f8260001', '陈某', '32123243345', '2017-01-17 00:00:00', '4028a081596ee67201596f20efa4000f', '', '1', 'official', '生活委员', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('4028da1d59ac31550159ac4426c3000a', '201711', '王彬', '4QrcOUm6Wau+VuBX8g+IPg==', '323243345453345666', '12345678998', '', '', '', '1', '2015-09-24 00:00:00', 'male', '', '', '', '4028da1c5925b27e015925b2a8340000', '4028808c59695d0301596961f8260001', '', '', '2017-01-17 00:00:00', '4028a081596ee67201596f20efa4000f', '', '1', 'official', '班长', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('4028da1d59ac31550159ac46919d000c', '201712', '危锦辉', '4QrcOUm6Wau+VuBX8g+IPg==', '360735199812292119', '18279700225', '672630243', 'Wjhsmart', '江西赣州技师学院', '18', '1998-12-29 00:00:00', 'male', '江西赣州石城县', '江西', '农村', '4028da1c5925b27e015925b2a8340000', '4028808c59695d0301596961f8260001', '危某某', '18279478876', '2017-01-17 00:00:00', '4028a081596ee67201596f03221f0004', '测试测试', '1', 'official', '学习委员', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('4028da1d59ac31550159ac47bd87000e', '201713', '沙晓丽', '4QrcOUm6Wau+VuBX8g+IPg==', '345678665343434344', '11223553434', '2344652324', 'Sxlsmart', '赣州技师学院', '18', '1998-05-28 00:00:00', 'female', '', '', '农村', '4028da1c5925b27e015925b2a8340000', '4028808c59695d0301596961d37d0000', '沙某某', '12432435342', '2017-01-17 00:00:00', '4028a081596ee67201596f03221f0004', '', '1', 'official', '学生', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('4028da1d59ac4d220159ac61bfcd001d', '201714', '邱康', '4QrcOUm6Wau+VuBX8g+IPg==', '234232342342342343', '23423312432', '231534534232', '57442345', '江西赣州技师学院', '0', '2017-01-02 00:00:00', 'male', '江西赣州南康', '江西', '农村', '4028da1c5925b27e015925b2a8340000', null, '邱某某', '12131243234', '2017-01-17 00:00:00', '4028a081596ee67201596f03221f0004', '', '1', 'official', '学生', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('4028da1d59ac4d220159ac632f3a001f', '201715', '赖太君', '4QrcOUm6Wau+VuBX8g+IPg==', '234345345323423433', '23426343534', '', '', '', '0', '2016-12-29 00:00:00', 'male', '', '', '', '4028da1c5925b27e015925b2a8340000', null, '', '', '2017-01-17 00:00:00', '4028a081596ee67201596f03221f0004', '', '1', 'official', '学生', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('4028da1d59ac6c620159ac6f2f050000', '201716', '刘莎莎', '4QrcOUm6Wau+VuBX8g+IPg==', '324326346546534544', '43453642534', '5436768743', '344354', '赣南医学院', '18', '1998-03-11 00:00:00', 'male', '江西赣州石城', '江西', '农村', '4028da1c5925b27e015925b2a8340000', null, '刘某某', '12454234545', '2017-01-10 00:00:00', '69f49c83c7f411e6a24b3065ec373466', '', '1', 'official', '学生', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('4028da2259ac56070159ac5b7d660000', '201710', '姚勇', '4QrcOUm6Wau+VuBX8g+IPg==', '360222222222222222', '13387078192', '124356768', '12312425', '赣州技师学院', '19', '1998-01-07 00:00:00', 'male', '赣州市章贡区', '赣州市', '赣州市', '4028da1c5925b27e015925b2a8340000', '4028808c59695d0301596961d37d0000', '危某某', '21344543239', '2017-01-17 00:00:00', '69f49c83c7f411e6a24b3065ec373466', '好开心来这所学校!', '1', 'official', '学生', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('4028da2259ac56070159ac5f3d770002', '201711', '王建强', '4QrcOUm6Wau+VuBX8g+IPg==', '362222222222222222', '12345678911', '12345435643', '213214', '赣州技师学院', '20', '1997-01-08 00:00:00', 'male', '赣州市章贡区', '赣州市', '赣州市', '4028da1c5925b27e015925b2a8340000', '4028808c59695d0301596961d37d0000', '王某某', '12325223221', '2017-01-17 00:00:00', '69f49c83c7f411e6a24b3065ec373466', 'nice!!!!!', '1', 'official', '学生', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('4028da2259ac56070159ac616b100004', '201712', '郭昶', '4QrcOUm6Wau+VuBX8g+IPg==', '321222222222222222', '12312312324', '312214124', '2142121412', '赣州技师学院', '19', '1998-01-14 00:00:00', 'male', '赣州市', '赣州市', '赣州市', '4028da1c5925b27e015925b2a8340000', '4028808c59695d0301596961d37d0000', '郭某某', '23251232423', '2017-01-17 00:00:00', '69f49c83c7f411e6a24b3065ec373466', '好好学习!', '1', 'official', '学生', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('4028da2259ac56070159ac636c1e0006', '201713', '张文星', '4QrcOUm6Wau+VuBX8g+IPg==', '342222222222222222', '87687645456', '435576556456', '3243256346', '赣州技师学院', '19', '1998-01-22 00:00:00', 'male', '赣州市', '赣州市', '赣州市', '4028da1c5925b27e015925b2a8340000', '4028808c59695d03015969621ded0002', '张某某', '12332213234', '2017-01-17 00:00:00', '69f49c83c7f411e6a24b3065ec373466', '暂无', '1', 'official', '学生', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('4028da2259ac56070159ac64bdf00008', '201714', '邹敏祥', '4QrcOUm6Wau+VuBX8g+IPg==', '345666444444444444', '12435656545', '1245436312311', '234235234223', '赣州技师学院', '20', '1997-01-08 00:00:00', 'male', '赣州市', '赣州市', '赣州市', '4028da1c5925b27e015925b2a8340000', '4028808c59695d03015969621ded0002', '邹某某', '24235377124', '2017-01-17 00:00:00', '69f49c83c7f411e6a24b3065ec373466', '暂无', '1', 'official', '学生', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('4028da2259ac56070159ac6623a4000a', '201715', '郭玉清', '4QrcOUm6Wau+VuBX8g+IPg==', '312245565555555555', '12343563235', '124567654', '4325345435', '赣州技师学院', '18', '1999-01-19 00:00:00', 'male', '赣州市', '赣州市', '赣州市', '4028da1c5925b27e015925b2a8340000', '4028808c59695d030159696249aa0003', '沙某某', '33534324323', '2017-01-17 00:00:00', '69f49c83c7f411e6a24b3065ec373466', '好好学习天天向上!', '1', 'official', '纪律委员', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('4028da2259ac56070159ac6792fd000c', '201716', '钟燕玲', '4QrcOUm6Wau+VuBX8g+IPg==', '324326666666666666', '21312414145', '1234562131', '31245325', '赣州技师学院', '19', '1998-01-20 00:00:00', 'female', '赣州市', '赣州市', '赣州市', '4028da1c5925b27e015925b2a8340000', '4028808c59695d030159696249aa0003', '钟某某', '43246236236', '2017-01-17 00:00:00', '69f49c83c7f411e6a24b3065ec373466', '暂无', '1', 'official', '学生', '4028da265981b09d015981d91d6f0001');
INSERT INTO `t_stu` VALUES ('d483660cc81b11e6a24b3065ec373466', '201709', '张三', '4QrcOUm6Wau+VuBX8g+IPg==', '360732199902112152', '18279700222', '999000880', '567898778', '赣州技师学院', '18', '2017-01-24 00:00:00', 'male', '赣州市', '赣州市', '城市', null, '4028808c59695d030159696249aa0003', '张大三', '15545453434', '2017-01-06 11:10:55', '4028a081596ee67201596f20efa4000f', '暂无', '1', 'official', '学生', '4028da265981b09d015981d91d6f0001');

-- ----------------------------
-- Table structure for t_stuchecking
-- ----------------------------
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

-- ----------------------------
-- Records of t_stuchecking
-- ----------------------------
INSERT INTO `t_stuchecking` VALUES ('4028da1d59ac4d220159ac5f80560012', '4028da1d59ac31550159ac47bd87000e', '2017-01-16 00:00:00', '正常', '正常', '正常');
INSERT INTO `t_stuchecking` VALUES ('4028da1d59ac4d220159ac5f81200013', '4028da1d59ac31550159ac47bd87000e', '2017-01-08 00:00:00', '正常', '迟到', '正常');
INSERT INTO `t_stuchecking` VALUES ('4028da1d59ac4d220159ac5f815c0014', '4028da1d59ac31550159ac47bd87000e', '2017-01-10 00:00:00', '正常', '迟到', '正常');
INSERT INTO `t_stuchecking` VALUES ('4028da1d59ac4d220159ac5f81820015', '4028da1d59ac31550159ac47bd87000e', '2017-01-11 00:00:00', '迟到', '缺勤', '正常');
INSERT INTO `t_stuchecking` VALUES ('4028da1d59ac4d220159ac5f81a40016', '4028da1d59ac31550159ac46919d000c', '2017-01-07 00:00:00', '正常', '正常', '正常');
INSERT INTO `t_stuchecking` VALUES ('4028da1d59ac4d220159ac5f81e70017', '4028da1d59ac31550159ac46919d000c', '2017-01-06 00:00:00', '正常', '正常', '正常');
INSERT INTO `t_stuchecking` VALUES ('4028da1d59ac4d220159ac5f82430018', '4028da1d59ac31550159ac46919d000c', '2017-01-03 00:00:00', '正常', '正常', '正常');
INSERT INTO `t_stuchecking` VALUES ('4028da1d59ac4d220159ac5f82660019', '4028da1d59ac31550159ac46919d000c', '2017-01-04 00:00:00', '正常', '正常', '正常');
INSERT INTO `t_stuchecking` VALUES ('4028da1d59ac4d220159ac5f828c001a', '4028da1d59ac31550159ac46919d000c', '2017-01-04 00:00:00', '正常', '正常', '正常');
INSERT INTO `t_stuchecking` VALUES ('4028da1d59ac4d220159ac5f82a8001b', '4028da1d59ac31550159ac4426c3000a', '2017-01-04 00:00:00', '正常', '正常', '正常');
INSERT INTO `t_stuchecking` VALUES ('4028da1d59ac4d220159ac5f82da001c', '4028da1d59ac31550159ac41013e0007', '2017-01-17 00:00:00', '正常', '正常', '正常');

-- ----------------------------
-- Table structure for t_stufeedback
-- ----------------------------
DROP TABLE IF EXISTS `t_stufeedback`;
CREATE TABLE `t_stufeedback` (
  `feedbackid` varchar(32) NOT NULL COMMENT '反馈编号',
  `stuid` varchar(32) NOT NULL COMMENT '学生编号',
  `feedbackday` datetime DEFAULT NULL COMMENT '反馈时间',
  `des` varchar(500) DEFAULT NULL COMMENT '反馈详情',
  `status` int(11) DEFAULT '1' COMMENT '状态，默认可用',
  PRIMARY KEY (`feedbackid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_stufeedback
-- ----------------------------
INSERT INTO `t_stufeedback` VALUES ('297e5afe5993250a01599325d7db0000', 'd483660cc81b11e6a24b3065ec373466', '2017-01-12 00:00:00', '老师讲课进度有点慢', '1');
INSERT INTO `t_stufeedback` VALUES ('402880af59ac17ff0159ac2a64bc0006', '15d3c0c8c90711e6bd803065ec373466', '2017-01-17 19:22:47', '食堂饭太难吃了', '1');
INSERT INTO `t_stufeedback` VALUES ('70ef541dca9911e6aac13065ec373466', 'd483660cc81b11e6a24b3065ec373466', '2016-12-25 00:00:00', '食堂的饭太难吃了等等', '1');
INSERT INTO `t_stufeedback` VALUES ('7a4e7d49ca9911e6aac13065ec373466', '37310718c90711e6bd803065ec373466', '2016-12-25 19:58:41', '食堂的饭吃到苍蝇了', '1');
INSERT INTO `t_stufeedback` VALUES ('8ca59021ca9911e6aac13065ec373466', 'd483660cc81b11e6a24b3065ec373466', '2016-12-25 19:59:12', '老师老是迟到', '1');

-- ----------------------------
-- Table structure for t_stuleave
-- ----------------------------
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

-- ----------------------------
-- Records of t_stuleave
-- ----------------------------
INSERT INTO `t_stuleave` VALUES ('297e5afe5993014301599302c93a0000', 'd483660cc81b11e6a24b3065ec373466', '2017-01-02 00:00:00', '2017-01-04 00:00:00', '2017-01-24 00:00:00', '身体不舒服需要去医院检查一下身体', '1', '0', '0', '0');
INSERT INTO `t_stuleave` VALUES ('402880af59ac17ff0159ac28db5c0004', '15d3c0c8c90711e6bd803065ec373466', '2017-01-16 00:00:00', '2017-01-18 00:00:00', '2017-01-17 19:21:01', '肚子痛', '1', '0', '0', '0');
INSERT INTO `t_stuleave` VALUES ('402880af59ac17ff0159ac29d3550005', '15d3c0c8c90711e6bd803065ec373466', '2017-01-15 00:00:00', '2017-01-17 00:00:00', '2017-01-17 19:22:01', '测试测试', '1', '0', '0', '0');
INSERT INTO `t_stuleave` VALUES ('4028da265995a0e7015995ae3d110000', 'd483660cc81b11e6a24b3065ec373466', '2017-01-02 00:00:00', '2017-01-19 00:00:00', '2017-01-13 00:00:00', '我要做手术', '1', '1', '1', '1');
INSERT INTO `t_stuleave` VALUES ('65ddc7ebcbd511e6b0333065ec373466', '37310718c90711e6bd803065ec373466', '2016-12-26 09:41:25', '2016-12-27 09:41:33', '2016-12-27 09:41:36', '肚子痛', '0', '0', '0', '0');
INSERT INTO `t_stuleave` VALUES ('6a108ca7cbd511e6b0333065ec373466', '37310718c90711e6bd803065ec373466', '2016-12-21 09:41:52', '2016-12-22 09:41:56', '2016-12-22 09:42:00', '身体不舒服', '1', '0', '0', '0');
INSERT INTO `t_stuleave` VALUES ('710334a6cbd511e6b0333065ec373466', '15d3c0c8c90711e6bd803065ec373466', '2016-12-23 09:43:52', '2016-12-24 09:43:57', '2016-12-24 09:44:00', '头疼', '1', '0', '0', '0');
INSERT INTO `t_stuleave` VALUES ('7790b048cbd511e6b0333065ec373466', 'd483660cc81b11e6a24b3065ec373466', '2016-12-25 09:44:05', '2016-12-27 09:44:10', '2016-12-27 09:44:13', '身体不舒服', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for t_summary
-- ----------------------------
DROP TABLE IF EXISTS `t_summary`;
CREATE TABLE `t_summary` (
  `summaryid` varchar(32) NOT NULL COMMENT '总结编号',
  `empid` varchar(32) NOT NULL COMMENT '员工编号',
  `stuid` varchar(32) NOT NULL COMMENT '学生编号',
  `summaryday` datetime DEFAULT NULL COMMENT '总结时间',
  `des` varchar(500) DEFAULT NULL COMMENT '总结详情',
  `term` int(11) DEFAULT NULL COMMENT '第几个学期的总结',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '状态，默认为可用状态',
  PRIMARY KEY (`summaryid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_summary
-- ----------------------------
INSERT INTO `t_summary` VALUES ('4028da26595f319001595f329fcb0000', '69f49c83c7f411e6a24b3065ec373466', '15d3c0c8c90711e6bd803065ec373466', '2017-01-08 00:00:00', '该学生在校表现极差，不尊重老师', '2', '0');
INSERT INTO `t_summary` VALUES ('4028da26595f3dde01595f3fb95e0000', '69f49c83c7f411e6a24b3065ec373466', '15d3c0c8c90711e6bd803065ec373466', '2017-01-01 00:00:00', '该学生在校表现极差，不尊重老师', '1', '0');
INSERT INTO `t_summary` VALUES ('4028da265981adf5015981afb1910001', '69f49c83c7f411e6a24b3065ec373466', 'd483660cc81b11e6a24b3065ec373466', '2017-01-09 00:00:00', '该学生在校表现极差，不尊重老师', '1', '1');
INSERT INTO `t_summary` VALUES ('4028da265981b09d015981b2cd8a0000', '69f49c83c7f411e6a24b3065ec373466', '4028808c5969256c0159694e5059000d', '2017-01-09 00:00:00', '该学生在校表现很好', '1', '1');
INSERT INTO `t_summary` VALUES ('66221b9fd0e111e68cec3065ec373466', '297e3390594d742f01594d8a22ea0000', '15d3c0c8c90711e6bd803065ec373466', '2017-01-02 00:00:00', '该学生表现的很好', '1', '0');
INSERT INTO `t_summary` VALUES ('7118067dd0e111e68cec3065ec373466', '297e3390594d742f01594d8a22ea0000', '37310718c90711e6bd803065ec373466', '2017-01-01 19:50:34', '该学生在校很认真的学习，尊重老师', '1', '1');
INSERT INTO `t_summary` VALUES ('87848e89d0e111e68cec3065ec373466', '69f49c83c7f411e6a24b3065ec373466', 'd483660cc81b11e6a24b3065ec373466', '2016-12-21 19:50:38', '该学生品行不当', '1', '1');

-- ----------------------------
-- Table structure for t_talk
-- ----------------------------
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

-- ----------------------------
-- Records of t_talk
-- ----------------------------
INSERT INTO `t_talk` VALUES ('297e5afe593b5bc201593b6691a10002', '4028a081596ee67201596f03221f0004', '37310718c90711e6bd803065ec373466', '2016-12-01 00:00:00', '上课经常睡觉', '1');
INSERT INTO `t_talk` VALUES ('297e5afe593b726401593b75a2790001', '4028a081596ee67201596f03221f0004', '15d3c0c8c90711e6bd803065ec373466', '2016-12-07 00:00:00', '没有心思在学习', '1');
INSERT INTO `t_talk` VALUES ('297e5afe593b726401593b75d7dc0002', '69f49c83c7f411e6a24b3065ec373466', '37310718c90711e6bd803065ec373466', '2016-12-16 00:00:00', '上课不听讲', '0');
INSERT INTO `t_talk` VALUES ('297e5afe593b726401593b7610400003', '297e3390594d742f01594d8a22ea0000', '15d3c0c8c90711e6bd803065ec373466', '2016-12-27 00:00:00', '上课没有乐趣', '1');
INSERT INTO `t_talk` VALUES ('297e5afe593b726401593b7643550004', '69f49c83c7f411e6a24b3065ec373466', '15d3c0c8c90711e6bd803065ec373466', '2016-12-07 00:00:00', '聊聊人生', '1');
INSERT INTO `t_talk` VALUES ('297e5afe593b948601593babd6f80000', '297e3390594d742f01594d8a22ea0000', '37310718c90711e6bd803065ec373466', '2016-11-02 00:00:00', '没有心思在学习', '1');
INSERT INTO `t_talk` VALUES ('4028808c59695d030159697430010006', '69f49c83c7f411e6a24b3065ec373466', 'd483660cc81b11e6a24b3065ec373466', '2017-01-04 00:00:00', '上课经常睡觉', '1');
INSERT INTO `t_talk` VALUES ('4028da26592a75ea01592a7812ab0000', '69f49c83c7f411e6a24b3065ec373466', '37310718c90711e6bd803065ec373466', '2016-12-02 00:00:00', '上课没有乐趣', '1');
INSERT INTO `t_talk` VALUES ('4028da26592a75ea01592a784e100001', '69f49c83c7f411e6a24b3065ec373466', 'd483660cc81b11e6a24b3065ec373466', '2016-12-24 00:00:00', '没有心思在学习', '1');
INSERT INTO `t_talk` VALUES ('4028da26592a819401592a822d0d0000', '69f49c83c7f411e6a24b3065ec373466', 'd483660cc81b11e6a24b3065ec373466', '2016-12-04 00:00:00', '上课不听讲', '1');
INSERT INTO `t_talk` VALUES ('4028da26592a86b301592a8783fd0000', '69f49c83c7f411e6a24b3065ec373466', 'd483660cc81b11e6a24b3065ec373466', '2016-12-23 15:58:57', '聊聊人生', '1');
INSERT INTO `t_talk` VALUES ('4028da26592aa29e01592aa334eb0000', '69f49c83c7f411e6a24b3065ec373466', 'd483660cc81b11e6a24b3065ec373466', '2016-12-13 00:00:00', '上课没有乐趣', '0');
INSERT INTO `t_talk` VALUES ('4028da26592ab4ee01592ab74a4a0000', '69f49c83c7f411e6a24b3065ec373466', 'd483660cc81b11e6a24b3065ec373466', '2016-12-20 00:00:00', '上课不听讲', '0');
INSERT INTO `t_talk` VALUES ('4028da2659819dd40159819fc5ed0000', '69f49c83c7f411e6a24b3065ec373466', 'd483660cc81b11e6a24b3065ec373466', '2017-01-09 00:00:00', '上课没有乐趣', '0');
INSERT INTO `t_talk` VALUES ('4028da265981adf5015981af39480000', '69f49c83c7f411e6a24b3065ec373466', '37310718c90711e6bd803065ec373466', '2017-01-09 00:00:00', '上课没有乐趣', '0');

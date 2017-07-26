create database MyQQ --新建数据库名为QQ
 on primary -- 主文件
(name = 'MyQQ', filename = 'd:\MyQQ.mdf', size = 10mb, maxsize = unlimited, filegrowth = 1mb ) 
 log on -- 日志文件
(name = 'MyQQ_log', filename = 'd:\MyQQ_log.ldf', size = 2mb, maxsize = 1024gb, filegrowth = 10%)
go

use MyQQ;
-- 个人信息表
create table t_account (
	number varchar(10) primary key, -- 账号
	pwd varchar(32) not null, -- 密码
	nickname varchar(30) not null, -- 昵称
	gender char(2) not null, -- 性别
	age int not null, -- 年龄
	autograph varchar(1000), -- 签名
	provice varchar(10) not null, -- 省
	city varchar(10) not null, -- 市
	area varchar(10) not null, -- 区
	status varchar(10) not null, -- 在线状态
	head varchar(100) not null default 'head', -- 头像名称
	mobile varchar(50) not null, -- 电话号码
	skin varchar(30) not null default 'main_background4' -- 皮肤
);

-- 设置check约束
alter table t_account add constraint CK_account_status check (status in('online', 'offline', 'leave', 'busy', 'qme', 'dont', 'hidden'));

-- 创建好友表
create table t_friends (
	id int primary key identity(1, 1),
	account_number1 varchar(10) not null,
	account_number2 varchar(10) not null
);

-- 添加外键约束
alter table t_friends add constraint FK_friends_account_number1 foreign key(account_number1) references t_account(number);
alter table t_friends add constraint FK_friends_account_number2 foreign key(account_number2) references t_account(number);

-- 创建消息表
create table t_message (
	id int primary key identity(1, 1),
	account_number1 varchar(10) not null,
	account_number2 varchar(10) not null,
	message varchar(1000),
	send_time datetime not null
);

-- 添加外键约束
alter table t_message add constraint FK_message_account_number1 foreign key(account_number1) references t_account(number);
alter table t_message add constraint Fk_message_account_number2 foreign key(account_number2) references t_account(number);
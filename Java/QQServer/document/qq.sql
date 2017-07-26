CREATE DATABASE qq
 ON  PRIMARY 
( NAME = 'qq', FILENAME = 'C:\Program Files (x86)\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\qq.mdf' , SIZE = 10MB , MAXSIZE = UNLIMITED, FILEGROWTH = 1MB )
 LOG ON 
( NAME = 'qq_log', FILENAME = 'C:\Program Files (x86)\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\qq_log.ldf' , SIZE = 2MB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO

use qq;
create table t_account(
	number varchar(10) primary key,
	pwd varchar(30) not null,
	nickname varchar(30) not null,
	gender char(2) not null,
	status varchar(10) not null,
	headIcon varchar(100) not null default 'head'
);


alter table t_account add constraint ck_account_status check(status in('online', 'offline', 'leave', 'busy'));

create table t_friends(
	id int primary key identity(1, 1),
	account_number1 varchar(10) not null,
	account_number2 varchar(10) not null
);

alter table t_friends add constraint fk_friends_account_number1 foreign key(account_number1) references t_account(number);
alter table t_friends add constraint fk_friends_account_number2 foreign key(account_number2) references t_account(number);

create table t_message(
	id bigint primary key identity(1,1),
	account_number1 varchar(10) not null,
	account_number2 varchar(10) not null,
	message varchar(1000),
	send_time datetime not null
);
alter table t_message add constraint fk_message_account_number1 foreign key(account_number1) references t_account(number);
alter table t_message add constraint fk_message_account_number2 foreign key(account_number2) references t_account(number);
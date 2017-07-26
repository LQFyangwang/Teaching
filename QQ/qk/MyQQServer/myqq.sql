create database myqq

use myqq
create table account(
	number varchar(10) primary key,
	pwd varchar(32) not null ,
	nickname varchar(30)not null,
	autograph varchar(100),
	gender char(2) not null,
	age int not null,
	adres varchar(20),
	contact varchar(11) ,
	birthday varchar(100),
	states varchar(20) default 'offline',
	headicon varchar(100) default 'head'
)
delete  from account

select * from account;
alter table account add constraint ck_account_states check(states in('online', 'offline', 'leave', 'busy','dont','hidden','qme'));


create table friends(
	id  int primary key identity(1,1),
	account_number1 varchar(10) not null,
	account_number2 varchar(10) not null
)

delete from friends
select *from friends

alter table friends add constraint FK_friends_account_number1 foreign key(account_number1) references account(number)
alter table friends add constraint FK_friends_account_number2 foreign key(account_number2) references account(number)

create table blacks(
	id  int primary key identity(1,1),
	account_number1 varchar(10) not null,
	account_number2 varchar(10) not null
)
select *from blacks
delete from blacks

alter table blacks add constraint FK_blacks_account_number1 foreign key(account_number1) references account(number)
alter table blacks add constraint FK_blacks_account_number2 foreign key(account_number2) references account(number)

create table t_message(
	id int primary key identity(1,1),
	account_number1 varchar(10)not null,
	account_number2 varchar(10)not null,
	message varchar(1000),
	send_time datetime 
)

alter table t_message add constraint FK_t_message_account_number1 foreign key(account_number1) references account(number)
alter table t_message add constraint FK_t_message_account_number2 foreign key(account_number2) references account(number)

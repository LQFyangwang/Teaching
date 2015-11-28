#include "stdio.h"
/**
	数据类型的长度跟cpu的位数相关
	假设cpu是16位的
	int 16
	short 16
	long 32
	假设cpu是32位的
	int 32
	short 16
	long 32
	假设cpu是64位的
	int 32
	short 16
	long 64
	 
	假设cpu是32位
	float: 32位，并且7位有效数字
	doube:64位，有16位有效数字
	
	关键字：编程语言中具有特殊含义的字符串，是c语言中的保留字，不能用作其他用途
*/
#define PI 3.141592654  //#define用于定义常量: #define 常量名称 常量值
#define NICKNAME 'W'

void main()
{
	int xm_age_1, xm_age_2;//声明变量（变量的声明）：数据类型 变量名;
	int xm_age_3 = 20, xm_age_4 = 30;//变量的声明及定义，相同类型的可以放在同一行。推荐一个变量就一行
	
	//数值类型
	int age = 17; // integer 整数 -2的15次方/2 -32768 ~ 32767
	int age_1 = -17;
	long age_2 = 100000000;// 长整型数
	short age_3 = 17;//短整型
	unsigned age_4 = 17;// sign表示符号，signed有符号的，unsigned无符号的整数，0到2的15次方-1 0 ~ 65535
	float age_5 = 17.5; // float 浮点数
	double age_6 = 16.898989888; // double 双精度数
	//非数值类型，字符，日期2015-10-30 星期五
	char name = 'A'; //charset 字符类型 char name = 'Wgs';是错误的，char只能存储一个字符
	
	int xm_age = 20; //赋值符，把整型数20赋给xm_age这个变量    定义变量（变量的定义）:数据类型 变量名称 = 变量初始值;	
	float xm_height = 175.8;
	long xm_money = 1000000000;
	char xm_nickname = 'C';
	char xm_nickname_1 = 67;// a-z 97-122 A-Z 65-90
	double xm_123 = 10.000000003;
	xm_age_1 = 30;//变量的赋值，变量的初始化，如果没有赋初始值，那么c编译器会给这个变量一个随机值，这个随机值没有任何意义
	xm_age_2 = 30;
	printf("小明的年龄是：%d岁，%d岁，身高是%fcm\n", xm_age, xm_age_1, xm_height);//占位符，将来会用具体的变量值替换掉，%d表示整数, %f是表示浮点数
	printf("小明的存款：%ld元\n", xm_money);//%ld表示long长整型数
	printf("小明的昵称是：%c, %c\n", xm_nickname, xm_nickname_1);//%c表示char字符类型
	printf("double:%.9lf\n", xm_123);//%f %lf默认只显示6个小数位
	printf("输出反斜杠：$\\\n");
	printf("圆周率：%.9f\n", PI);
	printf("常量nickname:%c\n", NICKNAME);
	
	/**
	转义字符
	\n表示换行
	\t表示制表符 tab
	\r表示回车符
	\b表示退格符
	\\输出\
	*/
}
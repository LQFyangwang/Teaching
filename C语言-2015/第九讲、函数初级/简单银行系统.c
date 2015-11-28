/* Note:Your choice is C IDE */
#include "stdio.h"
#include "string.h"


/**
	自定义函数：   函数的返回值类型 函数的名称() {
						函数的执行过程;
						return 指定类型的数据;
					}

	1、在使用自定义函数时，需要在main方法前声明有该函数:     函数的返回值类型 函数的名称();
	2、假设不想在前面进行函数声明（函数原型说明），把函数的定义放在main函数之前


	void func_name() {}  无参无返回值的函数
	void func_name(datatype1, datatype2, ....){} 有参无返回值的函数
	datatype func_name(){return datatype} 无参有返回值的函数
	datatype func_name(datatype1, datatype2, ...){return datatype} 有参有返回值的函数
*/

void getCard(); // 对我们自定义的函数进行声明
void findBank();
int getMoney();
int getMoney1();

int loginCheck(char[]);

int getMoney2(char[]); // getMoney2接收一个int类型的数据

/**
	把函数的定义放在main函数前，则不需要对函数进行额外的声明
*/
void printInfo() {
	printf("欢迎来到招商银行\n");
}

/**
	void表示空，返回值为空。返回值：一个函数的运算结果返回给被调用的函数
	void表示返回值为空，调用函数不需要从被调用函数获取结果
	
	返回值可以是以下类型：int, float, char, double, long, short
	
*/
int main()
{
	int money;
    //开始调用取款相关的方法（函数）
    getCard();
    findBank();
    printInfo();
    money = getMoney();
    if(money != -1) {
    	printf("我已经成功拿到%d元\n", money);
    } else {
    	printf("现在再去取\n");
    	getMoney1();
    }
    
    getMoney2("123456");
    return 0; // return表示返回相关数据类型的值
}

/**
	把银行卡给某人
*/
void getCard() { // 函数的定义=声明+实现
	printf("小明获得银行卡\n");
}

/**
	找一个银行
*/
void findBank() {
	printf("小明来到南门口的招商银行\n");
}

int getMoney() {
	int money; // password密码
	char password[6];
	printf("请输入密码：\n");
	scanf("%s", &password);
	//如果碰到分支结构，每个分支都应该return指定类型的数据
	if(loginCheck(password)) { // loginCheck(char[])方法被getMoney()方法嵌套调用了
		printf("输入密码正确，请输入取款金额: \n");
		scanf("%d", &money);
		printf("成功取出%d元\n", money);
		return money;
	} else {
		printf("你的卡哪捡来的？\n");
		return -1;
	}
}

int loginCheck(char pwd[]) {
	return strcmp(pwd, "123456") == 0 ? 1 : 0;
}

int getMoney1() {
	int money = -1; // password密码
	char password[6];
	printf("请输入密码：\n");
	scanf("%s", &password);
	if(loginCheck(password)) {
		printf("输入密码正确，请输入取款金额: \n");
		scanf("%d", &money);
		printf("成功取出%d元\n", money);
	} else {
		printf("你的卡哪捡来的？\n");
		money = -1;
	}
	return money; // 可以在整个函数的最后面返回指定类型的值，但是如果前面出现分支结构，则每个分支都应该确保返回的值是一个确定的数据
}

/**
getMoney2(char password[]) 有参数的方法
*/
int getMoney2(char password[]) {
	int money;
	if(loginCheck(password)) {
		printf("输入密码正确，请输入取款金额: \n");
		scanf("%d", &money);
		printf("成功取出%d元\n", money);
	} else {
		printf("你的卡哪捡来的？\n");
		money = -1;
	}
	return money; 
}
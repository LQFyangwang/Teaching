#include "stdio.h"
#include "stdlib.h"
#include "time.h"
#include "string.h"

void openAccount(); //开户
void save(); //存款
void query(); //查询
void getMoney(); //取款
void transfer(); //转账

int accept(int accNum, float money); // 接收转账

void operation(); //选择相应的操作

int randAccount(); // 随机生成账号
void printAccountInfo(int count); // 打印账户信息

int checkAccount(); //检查账号是否正确

struct {
	char name[20]; // 账户名称
	int account; // 账户账号
	char password[6]; //账户密码
	float money; //账户余额
} person[100];

int count = 0; //统计当前有多少个银行账号

int main()
{
    operation();
    return 0;
}

/**
	选择相应的操作，按6退出系统
*/
void operation() {
	int choice; // 操作选择
	do {
		printf("**********欢迎来到坑钱银行*********\n");
		printf("选择以下操作：1、开户 2、存款 3、取款 4、转账 5、查询 6、退出系统\n");
		scanf("%d", &choice);
		switch(choice) {
			case 1: openAccount(); break;
			case 2: save(); break;
			case 3: getMoney(); break;
			case 4: transfer(); break;
			case 5: query(); break;
			default:break;
		}
	} while(choice != 6);
}

void openAccount() {
	char pwd[6];
	printf("请输入您的账号名称：\n");
	scanf("%s", &person[count].name);
	printf("请输入密码：\n");
	scanf("%s", &person[count].password);
	printf("请确认密码：\n");
	scanf("%s", &pwd);
	if(strcmp(pwd, person[count].password) == 0) { // 两次密码一致
		person[count].account = randAccount();
		person[count].money = 0.f;
		printAccountInfo(count);
		printf("账户密码：%s，请牢记该密码\n", pwd);
		count++;
	} else {
		printf("两次密码不一致\n");
	}
}

/**
	产生一个随机的账号
*/
int randAccount() {
	int acc;
	int i;
	srand(time(NULL));
	acc = rand();
	for(i = 0; i < count; i++) {
		if(person[i].account == acc) {
			acc <<= 2; // 左移两位
		}
	}
	return acc;
}

/**
	打印账户信息，包括账户名，账号及余额
*/
void printAccountInfo(int count) {
	printf("**********账户信息如下**********\n");
	printf("账户名：%s\n", person[count].name);
	printf("账号：%d\n", person[count].account);
	printf("余额：%.2f\n", person[count].money);
}

/**
	存款，存款前需要判断账号及密码是否正确
*/
void save() {
	float m;
	int count = checkAccount();
	if(count != -1) { // 如果账号检测为真
		printf("请输入存款金额：\n");
		scanf("%f", &m);
		person[count].money += m; //更新余额
		printAccountInfo(count);
	}
}

/**
	检查账号及密码是否正确
*/
int checkAccount() {
	int accNum;
	char pwd[6];
	int i;
	printf("请输入账号：\n");
	scanf("%d", &accNum);
	printf("请输入密码：\n");
	scanf("%s", &pwd);
	for(i = 0; i < count; i++) {
		if(accNum == person[i].account && strcmp(pwd, person[i].password) == 0) {
			return i;//找到了某个账号符合条件，则返回该账号的count
		}
	}
	printf("用户名或密码错误\n");
	return -1;
}

/**
	取款，取款前需要判断账号及密码是否正确，余额需要变更
*/
void getMoney() {
	float m;
	int count = checkAccount();
	if(count != -1) {
		printf("请输入取款金额：\n");
		scanf("%f", &m);
		if(m > person[count].money) {
			printf("余额不足\n");
		} else {
			person[count].money -= m; //更新余额
			printf("成功取出%f元\n", m);
			printAccountInfo(count);
		}
	}
}

/**
	转账，取款前需要判断账号及密码是否正确，余额需要变更
*/
void transfer() {
	int acc1, acc2;
	float m;
	int count = checkAccount();
	if(count != -1) {
		printf("请输入对方账号：\n");
		scanf("%d", &acc1);
		printf("请确认对方账号：\n");
		scanf("%d", &acc2);
		if(acc1 == acc2) {
			printf("请输入转账金额(此笔转账需要5元手续费)：\n");
			scanf("%f", &m);
			if(m > person[count].money) {
				printf("余额不足\n");
			} else {
				int accCount = accept(acc1, m); // 嵌套调用接收转账的方法
				if(accCount != -1) {
					person[count].money -= (m + 5); // 更新余额
					printf("转账成功\n");
				}
				printAccountInfo(count);
			}
		} else {
			printf("两次输入的账号不一致\n");
		}
	}
}

/**
	接收转账方法，告诉此方法哪个账户来接收转账，以及接收多少钱
*/
int accept(int accNum, float m) {
	int i;
	for(i = 0; i < count; i++) {
		if(person[i].account == accNum) {
			person[i].money += m;
			return i;	
		}
		
	}
	printf("不存在该账号\n");
	return -1;
}

void query() {
	int count = checkAccount();
	if(count != -1) {
		printAccountInfo(count);
	}
}


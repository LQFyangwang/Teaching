#include <stdio.h>

void main() {
	char userName; // 用户名
	int password; // 密码
	printf("输入用户名及密码：\n");
	scanf("%s %d", &userName, &password);
	if(userName == 'W') {
		if(password > 1000 && password <= 9999) { // 只有当用户名输入正确的情况下，才需要去判断密码，否则直接告诉用户用户名有误
			printf("登录成功\n");
		} else {
			printf("用户密码有误！\n");
		}
	} else {
		printf("用户名有误!\n");
	}
}
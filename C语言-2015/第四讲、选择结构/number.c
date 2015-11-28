#include <stdio.h>

void main() {
	int number;
	printf("输入一个整数：\n");
	scanf("%d", &number);
	if(number % 2 == 0) {
		printf("偶数\n");
	} else {
		printf("奇数\n");
	}
}
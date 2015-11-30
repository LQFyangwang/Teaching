#include "stdio.h"

int add();
int minus();
int max();
int min();

/**
把变量定义在所有函数的外部（习惯定义在整个程序的开始部分），这样的变量叫做全局变量
全局变量能够被程序中所有函数使用

耦合度：多个函数使用相同的外部变量，说明耦合度大（独立性低）。将来的程序设计一定要注意降低耦合度
*/
int a = 10, b = 20; // 定义

int c; // 声明，然后再某个函数内部进行初始化

void main()
{
	// int a = 10, b = 20; a和b在add和minus函数中都找不到
	c = 20;
	printf("全局变量a = %d，全局变量b = %d\n", a, b);
	printf("%d\n", add());
	printf("%d\n", minus());
	printf("%d\n", max());
	printf("%d\n", min());
	
	printf("c = %d\n", c); // 全局变量可以在任何函数内部进行操作，每个函数都可以对其进行赋值，
							// 变量的值等于最后一次赋值操作的值
}

int add() {
	return a + b;
}

int minus() {
	return a - b;
}

int max() {
	return a > b ? a : b;
}

int min() {
	c = 5;
	return a < c ? a : c;
}
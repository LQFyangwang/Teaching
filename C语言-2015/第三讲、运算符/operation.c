#include "stdio.h"

void main() {
	int a = 5; // 赋值运算符
	int b = 3;
	int c = -5;
	int sum;
	sum = a + b;// a + b 的结果赋值给sum变量 操作数 运算符 操作数
	printf("a + b + c = %d\n", a + b + c);
	printf("a - b = %d\n", a - b);
	printf("a * b = %d\n", a * b);
	printf("a / b = %d\n", a / b); // 取整数部分 1
	printf("a %% b = %d\n", a % b); // 取模/取余 2
	
	/**
	a + b + c - d
	a * b + c * d
	a * b - c * d
	a / b +/- c / d
	
	a * b / c * d  
	
	a * b / (c * d)
	a * (b / c * d)
	
	a * (b / (c * d))
	*/
	
}
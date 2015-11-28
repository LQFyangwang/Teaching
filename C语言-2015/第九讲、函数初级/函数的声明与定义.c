/* Note:Your choice is C IDE */
#include "stdio.h"

/**
	函数定义时的参数列表就叫形参
	函数调用时的参数列表就叫实参
*/
int add(int, int);
int add1(int, int, int); // 声明时形参部分只需要指定形参的数据类型，而不需要指定形参的名称
float minus(float, float);

char toupper(char);
char tolower(char);

int add2(int, char); 

int check(int, int);

void login();
void login1();

void main()
{
	int a = 10, d = 20;
 	int c = add(5, 5);   // add(5, 5)  5, 5为实际参数   实参
 	int check_result;
 	printf("%d\n", c);
 	printf("%d\n", add(a, d)); // 调用时的实参的名称跟定义时形参的名称没有关系，
 								//只需要确定调用时的实参的数据类型与定义时的形参的数据类型一致，数据的个数也一致
 	printf("%d\n", add1(1, 2, 3));
 	
 	printf("%f\n", minus(2.0f, 1.0f));
 	
 	printf("%c\n", toupper('a'));
 	printf("%c\n", tolower('A'));
 	
 	printf("%d\n", add2(10, 'a')); // 方法调用时，实参的顺序必须与方法定义时的顺序一致
 	
 	check_result = check(10, 10);
 	printf("%d\n", check_result);
 	
 	login();
 	login1();
}

/**
	对两个数进行相加操作，需要告诉add方法对哪两个数进行相加操作，
	用形式参数来指定方法对哪两个数操作， 形参
*/
int add(int a, int b) { // int a, int b 形式参数
	return a + b;
	// int c = a + b;
	// return c;
}

int add1(int a, int b, int c) {
	return a + b + c;
}

float minus(float a, float b) {
	return a - b;
}

char toupper(char c) {
	return c - 32;
}

char tolower(char c) {
	return c + 32;
}

int add2(int a, char c) {
	return a + c;
}

int check(int a, int b) {
	return a == b; // return也可以返回条件表达式的值
}

void login() {
	printf("login");
	return; // return; 就表示函数结束
}

void login1() {
	if(1) {
		return; // return; 就表示函数结束，后面的代码不再执行，可以用来提前结束函数的运行
	}
	printf("login后半部分的代码");
}
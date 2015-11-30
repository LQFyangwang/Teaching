#include "stdio.h"
#include "string.h"

/**
	第一、在传值调用中，函数中传递的是形式参数的值即内容，
	这时形式参数的改变不会影响实参。
	第二、在传址调用中，函数中传递的是形式参数的地址，
	这时函数形式参数的改变会影响实参。其原因是：传址是形参和实参共用一个地址内存
	
	当使用数组作为参数时，实参与形参指向同一个数组地址，所以修改了形参值，实参的值也会跟着变
*/

int add(int a, int b) {
	return a + b;
}

void printArray(int a[], int length) { // 打印数组信息
	int i = 0;
	a[3] = 20;
	for(;i < length; i++) {
		printf("%d  ", a[i]);
	}
	printf("\n");
}

void printCharArray(char c[]) { // 数组作为形参时，可以不需要指明数组的长度
	int length = strlen(c);
	int i;
	c[0] = 'A'; // c传递进来的是首元素的地址，c[0]重新赋值后，指的是c[0]这个内存空间地址所指向的值变了
	for(i = 0; i < length; i++) {
		printf("%c", c[i]);
	}
	printf("\n");
}

/**
	int, float, char, double, long, short实参传递给形参，实际上传递的是实参变量所对应的值，这种方法调用叫做传值调用
*/
void main()
{
	int a = 10, b = 20;
	int c[10] = {1, 2, 3, 4};
	char d[10] = {'a', 'b', 'c'};
	int i = 0;
	printf("%d\n", add(a, b));   
	printArray(c, 10); // c是一个数组，数组名称是整个数组首元素的地址，传址调用
	for(i = 0; i < 10; i++) {
		printf("%d  ", c[i]);
	}
	printf("\n");
	printCharArray(d); // 传递的是整个d数组首元素的地址，传址调用
	printf("%s\n", d);
}

#include "stdio.h"

int a = 10;
int c = 10;

void add() {
	int a = 20; // a和b定义在函数内部，都是局部变量，只能够作用于该函数内部，出了该函数其所占用的内存空间会被释放
	int b = 30; // 在add函数中定义的局部变量a与b跟在外部定义的全局变量a和b没有关系，名字相同但add函数使用的是add内部定义的a与b
	c = 20; // 直接使用了外部定义的全局变量
	printf("%d + %d = %d, c = %d\n", a, b, a + b, c);//如果名字相同，在函数内部使用的变量一定局部变量
}

void main()
{
    add();
    printf("a = %d\n", a);
    printf("c = %d\n", c);
}
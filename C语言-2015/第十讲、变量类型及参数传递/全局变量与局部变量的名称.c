#include "stdio.h"

int a = 5, b = 5;

int add() {
	int a;
	int b;
	a = 10;
	b = 10;
}

int minus() {
	int a;
	int b;
	a = 20;
	b = 20;
}

void main()
{
    int a = 5;
    /**
    	复合语句内的局部变量只在复合语句内有效
    */
    {
    	int a = 6;
    	printf("a = %d\n", a);
    }
    
    printf("main a = %d\n", a);
}
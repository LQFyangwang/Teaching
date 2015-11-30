#include "stdio.h"

void add() {
	int sum, num1, num2;
	scanf("%d %d", &num1, &num2);
	sum = num1 + num2;
	printf("add sum = %d\n", sum);
}

/**
	main函数中的变量sum与add函数中的变量sum同名，但是他们在不同的作用范围内，只会在各自的作用域内起作用
*/
void main()
{
    int sum = 0;
    add();
    printf("main sum = %d\n", sum);
}
#include "stdio.h"

#define PI 3.14
#define N 5

#define SUM 5 + 6

#define SUM_1 b + d

void main()
{
	/**
	define定义的常量，最终只起到一个替换的作用
	*/
	int a[N]; // int a[5];
	int b = 5;
	int c = 5;
 	printf("%.2f\n", PI * 5 * 5); // printf("%.2f", 3.14 * 5 * 5)
 	printf("%d\n", SUM); // printf("%d\n", 5 + 6);
 	
 	printf("%d\n", SUM_1); // printf("%d\n", b + c);
}
#include "stdio.h"
void main()
{
	int a, b;
    printf("请输入两个整数：\n");
    scanf("%d %d", &a, &b);
    printf("a > b: %d\n", a > b);// c语言中，非0即表示真，默认的真用1来表示，0表示假
    printf("a < b: %d\n", a < b);
    printf("a == b: %d\n", a == b);// 一个=号表示赋值，==表示判断是否相等
    printf("a >= b: %d\n", a >= b);// >=或者<=只要满足其中一个判断就可以
    printf("a <= b: %d\n", a <= b);
    printf("a != b: %d\n", a != b);//!=表示不等判断
}
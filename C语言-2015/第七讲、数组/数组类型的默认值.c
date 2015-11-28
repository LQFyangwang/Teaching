#include "stdio.h"
void main()
{
    int a[10] = {1, 2, 3};//int, long类型未被初始的默认为0
    float b[10] = {1.0f, 2.0f, 3.0f};// 0.000000
    double c[10] = {1.0, 2.0, 3.0};// 0.000000
    char d[10] = {'a', 'b', 'c'};// 默认为空字符
    int i;
    for(i = 0; i < 10; i++) {
    	printf("%d\t", a[i]);
    }
    printf("\n");
    for(i = 0; i < 10; i++) {
    	printf("%.2f\t", b[i]);
    }
    printf("\n");
    for(i = 0; i < 10; i++) {
    	printf("%lf\t", c[i]);
    }
    printf("\n");
	for(i = 0; i < 10; i++) {
		printf("%c\t", d[i]);
	}
}
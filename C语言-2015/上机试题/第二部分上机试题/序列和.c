#include "stdio.h"
void main()
{
	float a = 2, b = 1, temp;
	int i;
	float sum = 0.f;
	
	/**
		i = 0, 此时a = 2， b = 1
		sum = 0 + 2/1;
		然后开始做交换，temp = 2, a = temp + b = 2 + 1 = 3, b = temp =2
		i++后下一次循环，a = 3, b = 2
		sum = 2/1 + 3/2,
		然后开始做交换，temp = 3, a = temp + b = 3 + 2 = 5, b = temp = 3
		i++后下一次循环，a = 5, b = 3
		sum = 2/1 + 3/2 + 5/3
	*/
	
	for(i = 0; i < 20; i++) {
		sum += a / b;
		temp = a;
		a = temp + b;
		b = temp; 
	} 
	printf("sum=%f\n", sum);
}
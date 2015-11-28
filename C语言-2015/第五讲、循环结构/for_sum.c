#include "stdio.h"
void main()
{
	int i;
	int sum = 0;
	for(i = 0; i < 100; i++) {
		sum += (i + 1);
	}   
	printf("1+2+3+...+100=%d\n", sum);
}
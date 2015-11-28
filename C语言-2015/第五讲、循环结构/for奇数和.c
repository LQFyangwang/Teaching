#include "stdio.h"
void main()
{
    int i = 0;
    int sum=0;
	for(i=1;i<7;i=i+2)//i=1初始化表达式对i重新赋值了
	{
		sum+=i;
	}
	printf("sum=%d\n", sum);
}
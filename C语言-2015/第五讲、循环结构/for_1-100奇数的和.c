#include "stdio.h"
void main()
{
    int i;
    int sum = 0;
    for(i = 0; i < 100; i+=2) { //不能写成i+2，因为i+2并没有给i进行重新赋值
    	sum += (i + 1);
    }
    printf("1+3+5+...+99=%d\n", sum);
}
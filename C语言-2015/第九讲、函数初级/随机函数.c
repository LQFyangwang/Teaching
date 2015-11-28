#include "stdio.h" // input and output
#include "stdlib.h" // std standard 标准的, lib library 库 
#include "time.h"

void main()
{
	int a;
	//printf("%ld\n", time(0));
	srand(time(NULL)); // stdlib.h  time time.h time(0) time(NULL)系统当前时间的毫秒数
   a = rand();
    printf("%d", a);
}
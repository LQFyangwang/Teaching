#include "stdio.h"
void main()
{
    int i = 0; // 初始化条件
    int pao = 0;
    while(i < 3) { // 条件表达式
    	pao = (i + 1) * 400;
    	i++; // 增量表达式
    }
    printf("跑完%d米\n", pao);
}
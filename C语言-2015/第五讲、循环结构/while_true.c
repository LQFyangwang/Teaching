#include "stdio.h"
void main()
{
	while(0) { // 0为假，意味着整体条件表达式为假
		printf("我在循环里==0\n");
	}
    while(1) { // 1表示真，意味着整体条件表达式为真
    	printf("我在循环里==1\n");
    }
}
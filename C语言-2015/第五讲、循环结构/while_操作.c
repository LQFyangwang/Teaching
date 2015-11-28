#include "stdio.h"
void main()
{
	int xuanze = 0;
	int a, b;
    printf("请选择操作:1.输入两个数，2.其他，3.表示退出\n");
    scanf("%d", &xuanze);
    while(xuanze != 3) { // 只要用户没有输入3，条件就成立，继续执行循环体，一旦用户输入3，条件成立，结束循环
    	if(xuanze == 1) {
	    	printf("请输入数据:\n");
	    	scanf("%d %d", &a, &b);
	    	printf("a + b = %d\n", a + b);
    	} else if(xuanze == 2) {
    		printf("其他\n");
    	} else {
    		printf("选择操作只有1,2,3");
    	}
    	printf("\n请选择操作:1.输入两个数，2.其他，3.表示退出\n");
    	scanf("%d", &xuanze);
    }
}
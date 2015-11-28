#include "stdio.h"
void main()
{
    int a[10] = {10, 23, 34, 78, 89, 90, 56, 73, 29, 100};
    int b, i;
    int flag = 0; // 标志位，如果找到元素，把标志位设为1
    printf("请输入一个数：\n");
    scanf("%d", &b);
    for(i = 0; i < 10; i++) {
    	if(a[i] == b) {
    		printf("第%d个\n", i + 1);
    		flag = 1;
    		break;
    	}
    }
    if(flag == 0) {
    	printf("未找到\n");
    }
}
#include "stdio.h"
void main()
{
    int a[10];
    int i, max, min;
    printf("请输入10个整数：\n");
    for(i = 0; i < 10; i++) {
    	printf("请输入第%d个整数：\n", i + 1);
    	scanf("%d", &a[i]);
    }
    max = a[0];
    min = a[0];
    for(i = 0; i < 10; i++) {
    	if(max < a[i]) {
    		max = a[i];
    	}
    	if(min > a[i]) {
    		min = a[i];
    	}
    }
    
    printf("max=%d, min=%d\n", max, min);
}
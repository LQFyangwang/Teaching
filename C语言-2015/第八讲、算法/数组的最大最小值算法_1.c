#include "stdio.h"
void main()
{
    int a[10];
    int i, max = -10000000, min = 10000000; // 确实不懂的，不要看了
    printf("请输入10个整数：\n");
    for(i = 0; i < 10; i++) {
    	printf("请输入第%d个整数：\n", i + 1);
    	scanf("%d", &a[i]);
    	if(max < a[i]) {
    		max = a[i]; // -999
    	}
    	if(min > a[i]) {
    		min = a[i];
    	}
    }
    
    printf("max=%d, min=%d\n", max, min);
}
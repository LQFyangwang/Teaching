#include "stdio.h"
void main()
{
    float income[12];
    float sum = 0.f;
    int i;
    printf("请输入各个月份的收入：\n");
    for(i = 0; i < 12; i++) {
    	scanf("%f", &income[i]);
    	sum += income[i];
    }
    
   	printf("每个月的收入如下：\n");
   	for(i = 0; i < 12; i++) {
   		printf("%.2f\t", income[i]);
   	}
   	printf("\n总收入为：%.26f\n", sum);
    
}
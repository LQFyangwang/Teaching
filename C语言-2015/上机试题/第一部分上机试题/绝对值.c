#include "stdio.h"
void main()
{
    int num, jueduizhi, jueduizhi_1;
    printf("请输入一个整数\n");
    scanf("%d", &num);
    if(num >= 0) {
    	jueduizhi = num;
    }
    if(num < 0) {
    	jueduizhi = -num;
    }
    printf("绝对值是：%d\n", jueduizhi);
    if(num >= 0) {
    	jueduizhi_1 = num;
    } else {
    	jueduizhi_1 = -num;
    }
    printf("绝对值是：%d\n", jueduizhi_1);
}
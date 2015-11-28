#include "stdio.h"
void main()
{
    float chang, kuan;
    printf("请输入长和宽:\n");
    scanf("%f,%f", &chang, &kuan);
    if(chang > 0 && kuan > 0) {
    	printf("面积为：%.2f\n", chang * kuan);
    } else {
    	printf("长和宽必须大于0\n");
    }
}
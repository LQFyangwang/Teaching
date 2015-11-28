#include "stdio.h"
#define PI 3.14
void main()
{
    float r;
    printf("请输入圆的半径:\n");
    scanf("%f", &r);
    if(r > 0) {
    	printf("周长：%.2f\n", 2 * PI * r);
    	printf("面积：%.2f\n", PI * r * r);
    } else {
    	printf("半径必须大于0\n");
    }
}
#include "stdio.h"
#define PI 3.1415
void main()
{
    float gao;
    float banjin;
    char c;
    // float tiji;
    printf("请输入高和半径：\n");
    scanf("%f %f", &gao, &banjin);
    // tiji = PI * banjin * banjin * gao;
    printf("圆柱体的体积是：%f\n", PI * banjin * banjin * gao);
    
    printf("请随意输入一个字符，一个整数，一个浮点数\n");
    scanf("%s", &c);
    printf("%c\n", c);
}
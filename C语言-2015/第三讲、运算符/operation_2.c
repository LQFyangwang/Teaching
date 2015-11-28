#include "stdio.h"
void main()
{
    int a = 10;
    int b = 10;
    int c = a * b; // 100
    b *= a; // 结果：100，复合运算符， b *= a ==> b = b * a; 左边的b既是操作数，又是用来存储结果的，右边的a是操作数
    printf("b *= a = %d\n", b);
    b /= a; // 结果：10，b = b / a
    printf("b /= a = %d\n", b);
    b += a; // 结果：20， b = b + a
    printf("b += a = %d\n", b);
    b -= a; // 结果：10，b = b - a
    printf("b -= a = %d\n", b);
    b %= a; // 结果：0，b = b % a
    printf("b %%= a = %d\n", b);
    b += ++a; //结果：11， b = b + (++a)
    printf("b += ++a = %d\n", b);
} 
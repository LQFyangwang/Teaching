/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
    int a = 20, b = 10, c = 30;
    int max, min;
    max = a > b ? a : b; // 三目表达式 ?:  表达式 ? 结果1 : 结果2 对表达式进行判断，如果为真，则得到结果1，如果为假，则得到结果2
    max = max > c ? max : c;
    min = a < b ? a : b;
    min = min < c ? min : c;
    printf("max is: %d, min is: %d\n", max, min);
}
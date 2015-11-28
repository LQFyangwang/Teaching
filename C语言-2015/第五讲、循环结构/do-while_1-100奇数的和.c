/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
    int i = 1;
    int sum = 0;
    // 1-100所有奇数的和
    do {
    	sum += i;
    	i += 2; 
    } while(i < 100);
    printf("1+3+5+7+...+99=%d\n", sum);
}
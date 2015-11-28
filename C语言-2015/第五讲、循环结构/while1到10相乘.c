#include "stdio.h"
void main()
{
    int i = 0;
    int zhi = 1;
    while(i < 10) {
    	zhi = zhi * (i +1);
    	i++;
    }
    printf("1*2*3*...*10 = %d\n", zhi);
}
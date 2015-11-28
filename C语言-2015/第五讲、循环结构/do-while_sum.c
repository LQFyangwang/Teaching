/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
    int i = 0;
    int sum = 0;
    do {
    	sum += ++i;
    	// sum += (i + 1);
    	// i++;
    } while(i < 100);
    printf("1+2+3+...+100=%d\n", sum);
}
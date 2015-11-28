/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
    int i;
    int total = 1;
    for(i = 0; i < 9; i++) {
    	total = (total + 1) * 2;
    }
    printf("共%d个桃子\n", total);
}
/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
    int i = 0;
    int j = 0;
    do {
    	printf("%d\t", i + 1);
    	i++;
    } while(i < 100);
    printf("\n\n");
    do {
    	printf("%d\t", ++j);
    } while(j < 100);
}
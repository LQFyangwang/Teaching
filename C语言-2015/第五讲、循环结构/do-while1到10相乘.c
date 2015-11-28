/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
	int i = 0;
	int result = 1;
	do {
		result *= (i + 1);
		i++;
		// result *= ++i;
	} while(i < 10);
	printf("1*2*3*...*10=%d\n", result);
}
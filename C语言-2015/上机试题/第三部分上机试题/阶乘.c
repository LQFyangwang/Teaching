/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
   	int i;
   	long a = 1;
   	long sum = 0;
   	for(i = 1; i <= 10; i++) {
   		a = a * i;
   		sum += a;
   	}
   	
   	printf("sum = %ld\n", sum);
}
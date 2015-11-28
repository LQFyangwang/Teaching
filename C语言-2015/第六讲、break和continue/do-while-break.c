#include "stdio.h"
void main()
{
	int i = 0;
    do {
    	printf("%d\n", i);
    	if(i == 5) {
    		break;
    	}
    	i++;
    } while(1);
    
    printf("\n");
    i = 0;
    do {
    	printf("%d\n", i);
    	i++;
    } while(i <= 5);
}
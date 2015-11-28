#include "stdio.h"
void main()
{
    int i = 0;
    while(1) {
    	printf("%d\n", i);
    	if(i == 10) {
    		break;
    	}
    	i++;
    }
    
    printf("\n");
    i = 0;
    while(1) {
    	if(i == 10) {
    		break;
    	}
    	
    	printf("%d\n", i);
    	i++;
    }
}
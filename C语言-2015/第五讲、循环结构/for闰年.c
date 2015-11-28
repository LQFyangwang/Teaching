#include "stdio.h"
void main()
{
    int year;
    int count = 0;
    for(year = 1900; year <= 2100; year++) {
    	if(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
    		count++;
    		printf("%d\t", year);
    		if(count % 5 == 0) {
    			printf("\n");
    		}
    	}
    }
}
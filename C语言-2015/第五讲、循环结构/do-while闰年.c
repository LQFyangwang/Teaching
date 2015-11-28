/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
    int year_begin = 1900;
    int year_end = 2100;
    int count = 0;
    do {
    	if(year_begin % 400 == 0 || (year_begin % 4 == 0 && year_begin % 100 != 0)) {
    		count++;
    		printf("%d\t", year_begin);
    		if(count % 9 == 0) {
    			printf("\n");
    		}
    	}
    	year_begin++;
    } while(year_begin < year_end);
}
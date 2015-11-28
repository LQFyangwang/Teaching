#include "stdio.h"
void main()
{
    float total = 100, half = 50;
    // float total = 100, half = 100;
    int i;
    for(i = 0; i < 10; i++) {
    	total += 2 * half;
    	half = half / 2;
    	// half = half / 2;
    	// total += 2 * half; 
    }
    printf("总距离：%f，第10次：%f\n", total, half * 2);
}
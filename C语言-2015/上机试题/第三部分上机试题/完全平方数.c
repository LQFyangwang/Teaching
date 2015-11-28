#include "stdio.h"
#include "math.h"

void main()
{
    int i;
    int a, b;
    for(i = 0; i < 100000; i++) {
    	a = sqrt(i + 100);
    	b = sqrt(i + 168);
    	if(a * a == i + 100 && b * b == i + 168) {
    		printf("%d  ", i);
    	}
    }
}
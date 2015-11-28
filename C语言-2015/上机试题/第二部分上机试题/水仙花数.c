/* Note:Your choice is C IDE */
#include "stdio.h"
#include "math.h"

void main()
{
    int i;
    int a, b, c;
    for(i = 100; i < 1000; i++) {
    	a = i / 100;
    	b = i / 10 % 10;
    	c = i % 10;
    	if( i == pow(a, 3) + pow(b, 3) + pow(c, 3)) { // pow(a, b) => a的b次方
    		printf("%d\t", i);
    	}
    }
}
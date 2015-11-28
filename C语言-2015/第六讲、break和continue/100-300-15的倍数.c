#include "stdio.h"
void main()
{
    //100 - 300间找3个15的倍数
    int i;
    int count = 0;
    for(i = 100; i <= 300; i++) {
    	if(i % 15 == 0) {
    		printf("%d\t", i);
    		count++;
    	}
    	if(count == 3) {
    		break;
    	}
    }
}
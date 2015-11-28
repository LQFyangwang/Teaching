/* Note:Your choice is C IDE */
#include "stdio.h"
#include "math.h"//数学函数头文件

void main()
{
    int i, j;
    int r;
    int flag, count = 0;
    for(i = 101; i <= 200; i++) {
    	flag = 0;
    	r = sqrt(i); // sqrt(double a)求平方根函数
    	for(j = 2; j <= r; j++) {
    		if(i % j == 0) {
    			flag = 1;
    			break;
    		}
    	}
    	if(flag == 0) {
    		count++;
    		printf("%d\t", i);
    	}
    }
    printf("\n共有%d个素数", count);
}
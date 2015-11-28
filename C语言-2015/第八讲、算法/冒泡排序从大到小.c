#include "stdio.h"
void main()
{
    int a[10] = {89, 46, 76, 78, 98, 12, 10, 9, 7, 59};
    int i, j, temp;
    /**
    	从整个数组的最后一个数开始，依次与它前面一个数比较，如果比前面一个大，则跟前面这个数进行交换，否则不动
    */
    for(i = 0; i < 10; i++) {
    	for(j = 9; j > i; j--) {
    		temp = a[j];
    		if(a[j] > a[j - 1]) { // 从小到大排序，改变>为<
    			a[j] = a[j - 1];
    			a[j - 1] = temp;
    		}
    	}
    }
    
    printf("排好序:\n");
    for(i = 0; i < 10; i++) {
    	printf("%d  ", a[i]);
    }
}
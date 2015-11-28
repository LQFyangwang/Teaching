#include "stdio.h"
void main()
{
	/**
	每一趟循环找出一个最小值，放到元素的前面，下一趟循环由剩下的元素进行循环比较
	*/
    int a[10] = {78, 45, 98, 23, 46, 12, 9, 80, 53, 25};
    int i, j, temp, pos, min;
    for(i = 0; i < 10; i++) {
    	temp = a[i];
    	min = a[i];
    	pos = i;
    	for(j = i; j < 10; j++) {
    		if(min > a[j]) {
    			pos = j; // 如果a[j]还要小于min，则重新赋值给min，并且记录最小值的下标
    			min = a[j];
    		}
    	} 
    	a[i] = min;
    	a[pos] = temp;
    }
    for(i = 0; i < 10; i++) {
    	printf("%d\t", a[i]); 
    }
}
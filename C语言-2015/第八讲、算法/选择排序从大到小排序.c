#include "stdio.h"
void main()
{
	/**
	每一趟循环找出一个最小值，按顺序放到数组前面，下一趟循环由剩下的元素进行循环比较
	*/
    int a[10] = {78, 45, 98, 23, 46, 12, 9, 80, 53, 25};
    int i, j, temp, pos, max; // temp是临时变量，pos是找到的最大值的索引号
    for(i = 0; i < 10; i++) {
    	temp = a[i];
    	max = a[i];
    	pos = i;
    	for(j = i; j < 10; j++) {
    		if(max < a[j]) {
    			pos = j; // 如果a[j]还要小于min，则重新赋值给min，并且记录最小值的下标
    			max = a[j];
    		}
    	} 
    	a[i] = max;
    	a[pos] = temp;
    }
    for(i = 0; i < 10; i++) {
    	printf("%d\t", a[i]);
    }
}
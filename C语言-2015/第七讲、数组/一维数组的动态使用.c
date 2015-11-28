#include "stdio.h"
void main()
{
    int a[10] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}; // 最大下标为9，超过9就意味着越界
    int b[10];
    int i;
    
    printf("%d\n", a[0]);//使用某个位置上的值，直接通过数组的名称来引用，再通过下标（索引号）指定具体的某个位置上的值：a[下标]
    printf("%d\n", a[1]);
    printf("%d\n", a[2]);
    printf("%d\n", a[3]);
    printf("%d\n", a[4]);
    printf("%d\n", a[5]);
    printf("%d\n", a[6]);
    
    for(i = 0; i < 10; i++) {
    	printf("%d\t", a[i]);
    }
    printf("\n");
    i = 0;
    while(i < 10) {
    	printf("%d\t", a[i]);
    	i++;
    }
    printf("\n");
    for(i = 0; i < 10; i++) {
    	printf("%d\t", i);
    }
    printf("\n");
    
    a[1] = 10;//对数组中的某个位置赋值，a[下标] = 值;
    printf("%d\n", a[1]);
    
    for(i = 0; i < 10; i++) {
    	b[i] = i + 10;
    	printf("%d\t", b[i]);
    }
}
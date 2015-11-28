/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
    int i, count1 = 0, count2 = 0;
    int a[10];
    printf("请输入10个整数：\n");
    for(i = 0; i < 10; i++) {
    	scanf("%d", &a[i]);
    	if(a[i] % 2 == 0) {
    		count1++;
    	} else {
    		count2++;
    	}
    }
    
    printf("偶数：%d,奇数：%d\n", count1, count2);
}
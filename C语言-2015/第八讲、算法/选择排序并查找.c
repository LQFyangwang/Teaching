#include "stdio.h"
void main()
{
    int a[10];
    int i, j, pos, temp, max; // pos用来存储最大值在数组中的索引号（下标），temp用来存储会被替换的值，max用来存储最大值
    int num, middle, begin = 0, end = 9, flag = 0;// middle数组索引的中间索引，begin开始索引，end结束索引
    
    printf("请输入10个整数：\n");
    for(i = 0; i < 10; i++) {
    	scanf("%d", &a[i]);
    }
    
    printf("未排序：");
    for(i = 0; i < 10; i++) {
    	printf("%d  ", a[i]);
    }
    
    for(i = 0; i < 10; i++) {
    	temp = a[i];
    	max = a[i];
    	pos = i;
    	for(j = i; j < 10; j++) {
    		if(max < a[j]) {
    			pos = j; // a[j] > max, 证明a[j]是此时的最大值，把a[j]在数组中的索引j存储到pos变量里
    			max = a[j]; 
    		}
    	}
    	a[i] = max;
    	a[pos] = temp;
    }
    
    printf("\n排序后的数组: \n");
    for(i = 0; i < 10; i++) {
    	printf("%d  ", a[i]);
    }
    
    middle = 10 / 2;
    
    printf("\n请输入需要查找的值：\n");
    scanf("%d", &num);
    if(num == a[middle]) {
    	printf("第%d个\n", middle + 1);
    	flag = 1;
    } else if(num > a[middle]) { // 从大到小排序，当num>a[middle]时，num在前半部分
    	end = middle - 1;
    } else {
    	begin = middle + 1;
    }
    
    if(flag == 0) {
    	for(i = begin; i <= end; i++) {
    		if(a[i] == num) {
    			flag = 1;
    			printf("第%d个\n", i + 1);
    			break;
    		}
    	}
    }
    
    if(flag == 0) {
    	printf("未找到!");
    }
    
}
#include "stdio.h"
void main()
{
    int a[15] = {10, 13, 15, 18, 24, 28, 36, 47, 57, 59, 69, 75, 79, 80, 100};// 使用折半查找的前提是排好序
    int b, i;
    int flag = 0;
    int middle, begin = 0, end = 14; // begin开始索引， end结束索引， middle中间位置索引
    printf("请输入一个数:\n");
    scanf("%d", &b);
    middle = 15 / 2;
    if(a[middle] == b) {
    	flag = 1;
    	printf("第%d个\n", middle);
    } else if(a[middle] < b) { // 用户输入的数在后半部分，则重新给begin赋值
    	begin = middle + 1;
    } else if(a[middle] > b) { // 用户输入的数在前半部分，则重新给end赋值
    	end = middle - 1;
    }
    if(flag == 0) {
    	for(i = begin; i <= end; i++ ) {
    		printf("for");
    		if(a[i] == b) {
    			flag = 1;
    			printf("第%d个\n", i);
    			break;
    		}
    	}
    }
    if(flag == 0) {
    	printf("未找到\n");
    }
}
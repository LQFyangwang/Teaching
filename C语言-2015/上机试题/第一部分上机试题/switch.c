#include "stdio.h"
void main()
{
    int num;
    printf("请输入1-10的整数：\n");
    scanf("%d", &num);
    if(num >=1 && num <= 10) {
    	switch(num) {
    		case 1:printf("one\n");break;
    		case 2:printf("two\n");break;
    		case 3:printf("three\n");break;
    		default:break;
    	}
    } else {
    	printf("请输入1-10的整数：\n");
    }
}
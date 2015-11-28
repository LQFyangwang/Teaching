#include "stdio.h"
void main()
{
    int i = 0, flag = 0, password;
    printf("请输入口令：\n");
    scanf("%d", &password);
    ++i;
    if(password != 123456) {
    	printf("请输入口令：\n");
    	scanf("%d", &password);
    	++i;
    	if(password != 123456) {
    		printf("请输入口令：\n");
    		scanf("%d", &password);
    		++i;
    	} else {
    		flag = 1;
    	}
    } else {
    	flag = 1;
    }
    if(flag == 1) {
    	printf("口令正确\n");
    } else {
    	printf("口令错误\n");
    }
}
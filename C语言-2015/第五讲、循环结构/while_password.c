#include "stdio.h"
void main()
{
    int i = 0;
    int password;
    int flag = 0;
    while(i < 3) {
    	printf("请输入密码\n");
    	scanf("%d", &password);
    	if(password == 123456) {
    		flag = 1;
    	}
    	i++;
    }
    
    if(flag == 1) {
    	printf("口令正确\n");
    } else {
    	printf("口令错误\n");
    }
}
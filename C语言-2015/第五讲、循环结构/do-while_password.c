/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
    int i = 0;
    int password;
    int flag = 0;
    do {
    	printf("请输入密码\n");
    	scanf("%d", &password);
    	if(password == 123456) {
    		flag = 1;	
    	}
    	i++;
    } while(i < 3);
    
    if(flag == 1) {
    	printf("密码正确\n");
    } else {
    	printf("密码错误\n");
    }
}
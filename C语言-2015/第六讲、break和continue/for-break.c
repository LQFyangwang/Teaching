#include "stdio.h"
void main()
{
    int i = 0;
    for(;;) {
    	if(i == 10) {  //0==10不成立所以不执行if里面的语句
    		break; // 当i等于10时，结束整个循环（跳出循环），在break后的语句也不再执行了
    	}
    	
    	printf("%d\n", i);
    	i++;
    	
    }
    
    printf("\n");
    for(i = 0;;i++) {
    	printf("%d\n", i);
    	if(i == 10) {
    		break;
    	}
    }
    
    printf("\n");
    for(i = 0;i <= 10;i++) {
    	printf("%d\n", i);
    }
}
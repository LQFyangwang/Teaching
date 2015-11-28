#include "stdio.h"
void main()
{
    int month, day;
    printf("请输入月份\n");
    scanf("%d", &month);
    switch(month) {
    	case 1:
    	case 3:
    	case 5:
    	case 7:
    	case 8:
    	case 10:
    	case 12: 
    		day = 31; 
    	break; // 以上的所有case都判断，未碰到break，就不会结束判断
    	case 2: 
    		day = 29; 
    	break;
    	case 4:
    	case 6:
    	case 9:
    	case 11:
    		day = 30; 
    	break;
    	default:
    		printf("月份输入有误\n");
    	break;
    }
    
    printf("有%d天", day);
}
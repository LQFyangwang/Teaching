#include "stdio.h"
void main()
{
    int month, day;
    printf("请输入月份\n");
    scanf("%d", &month);
    switch(month) { // switch(需要对谁进行判断){case语句}
    	case 1: // case后面的条件值必须是一个确定的数值，不能是表达式
    		day = 31; 
    		printf("aaa\n"); // case语句后可以有多个语句，可以不加大括号 
    	break;// if(month == 1)
    	case 2: day = 29; break;;// if(month == 2)
    	case 3: day = 31; break;
    	case 4: day = 30; break;
    	case 5: day = 31; break;
    	case 6: day = 30; break;
    	case 7: day = 31; break;
    	case 8: day = 31; break;
    	case 9: day = 30; break;
    	case 10: day = 31; break;
    	case 11: day = 30; break;
    	case 12: day = 31; break;
    	// case 1: day = 31; break; case后面的常量值不能重复出现
    	default: // 类似于if...else if...else结构中的else部分。default语句可以省略不写，根据实际情况是否加上
    		printf("输入的月份有误\n");
    	break;
    }
    printf("有%d天\n", day);
}
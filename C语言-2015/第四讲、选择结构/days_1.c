#include "stdio.h"

void main() {
	/**
		if...else if...else这种结构，判断的次数可能减少（跟条件的顺序有关）
	*/
	int month, day;
	printf("请输入月份\n");
	scanf("%d", &month);
	if(month == 1) {
		day = 31;
	} else if(month == 2) {
		day = 29;	   
	} else if(month == 12) {
		day = 31;
	} else { // 以上所有条件都不成立
		printf("不好意思，输入错误了\n");
	}
	printf("有%d天\n", day);
}
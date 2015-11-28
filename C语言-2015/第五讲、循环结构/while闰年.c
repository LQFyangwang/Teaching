#include "stdio.h"
void main()
{
	int year = 1900; // 开始年份
	int year_end = 2010; // 结束年份
	int count = 0; // 计数器
	while(year <= year_end) {
		if(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
			count++;
			printf("%d\t", year);
			if(count % 5 == 0) { // 每5个换行
				printf("\n");
			}
		}
		year++;
	}   
}
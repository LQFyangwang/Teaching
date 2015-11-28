#include "stdio.h"

void main()
{
	int year, month, day;
	int x, total;
	printf("输入年月日，用空格隔开\n");
	scanf("%d %d %d", &year, &month, &day);
	switch(month) {
		case 1: x = 0; break;
		case 2: x = 31; break; // 1
		case 3: x = 59; break; // 1, 2
		case 4: x = 90; break; // 1, 2, 3
		case 5: x = 120; break; // 1, 2, 3, 4
		case 6: x = 151; break; // 1, 2, 3, 4, 5
		case 7: x = 181; break; // 1, 2, 3,4,5,6
		case 8: x = 212; break; // 1,2,3,4,5,6,7
		case 9: x = 243; break; // 1,2,3,4,5,6,7,8
		case 10: x = 273; break; // 1,2,3,4,5,6 ,7,8,9
		case 11: x = 304; break; // 1,2,3,4,5,6,7,8,9,10
		case 12: x = 334; break; // 1,2,3,4,5,6,7,8,9,10,11
	}
	total = x + day;
	if((year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) && month > 2) {
		total++;
	}
	printf("这是第%d天\n", total);
}
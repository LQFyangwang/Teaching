#include "stdio.h"

void main() {
	/**
		连续写多个if条件判断，每一个if都会进行判断
	*/
	int month;
	int day;
	printf("请输入任何一个月份（1-12）\n");
	scanf("%d", &month);
	if(month <= 0 || month > 12) // 如果if语句体只有一句，可以不用大括号，否则需要加上大括号。但是请都加上大括号
		printf("输入的月份有误，请输入1到12的整数值\n");
		printf("aaaa\n"); // 这个不属于上面这个条件判断语句的
	printf("开始判断是否等于1");
	if(month == 1) {
		printf("month == 1\n");
		day = 31;
	} 
	printf("开始判断是否等于2");
	if(month == 2) {
		printf("month == 2\n");
		day = 29;
	}
	printf("开始判断是否等于3");
	if(month == 3) {
		printf("month == 3\n");
		day = 31;
	}
	printf("开始判断是否等于4");
	if(month == 4) {
		printf("month == 4\n");
		day = 30;
	} 
	printf("开始判断是否等于5");
	if(month == 5) {
		printf("month == 5\n");
		day = 31;
	}
	printf("开始判断是否等于6");
	if(month == 6) {
		printf("month == 6\n");
		day = 30;
	}
	printf("开始判断是否等于7");
	if(month == 7) {
		printf("month == 7\n");
		day = 31;
	}
	printf("开始判断是否等于8");
	if(month == 8) {
		printf("month == 8\n");
		day = 31;
	}
	printf("开始判断是否等于9");
	if(month == 9) {
		printf("month == 9\n");
		day = 30;
	}
	printf("开始判断是否等于10");
	if(month == 10) {
		printf("month == 10\n");
		day = 31;
	} 
	printf("开始判断是否等于11");
	if(month == 11) {
		printf("month == 11\n");
		day = 30;
	}
	printf("开始判断是否等于12");
	if(month == 12) {
		printf("month == 12\n");
		day = 31;
	}
	printf("开始判断是否等于1");
	if(month == 1) {
		printf("month == 1\n");
		day = 31;
	}
	printf("\n判断结束！\n");
	
	printf("输入的月份%d有%d天\n", month, day);
}
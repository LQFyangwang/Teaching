#include <stdio.h>

/**
判断100以内的正整数的奇数偶数
*/
void main() {
	int number;
	printf("输入一个100以内的整数：\n");
	scanf("%d", &number);
	if(number >= 0 && number <= 100) { // if的嵌套使用，在if条件判断语句中继续写条件判断语句。只有在外层条件为真的时候，内层的条件判断才会被执行
		if(number % 2 == 0) {
			printf("偶数\n");
		} else {
			printf("奇数\n");
		}
	} else {
		printf("输入有误，请重新输入\n");
	}
	
}
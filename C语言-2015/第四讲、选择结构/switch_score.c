#include "stdio.h"
void main()
{
	int score;
	char grade;
	printf("输入分数：\n");
	scanf("%d", &score);
	switch(score / 10) {//把分数转化成了一个可表示某个分数区间的常量值
		case 9: // case后面的语句一定是常量值，不能是其他表达式
		case 10:
			grade = 'A';
		break;
		case 8:
			grade = 'B';
		break;
		case 7:
			grade = 'C';
		break;
		default: grade = 'd'; break;
	}   
	printf("Grade: %c\n", grade);
}
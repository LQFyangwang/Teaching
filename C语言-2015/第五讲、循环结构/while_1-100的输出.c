#include "stdio.h"
void main()
{
	int i = 0;
	int j = 0;
	while(i < 100) {
		printf("%d\t", ++i); // ++i已经做了自境操作，所以后面没必要再写增量表达式
	}
	printf("\n");
	while(j < 100) {
		printf("%d\t", j + 1);
		j++; // ++j j++从最终结果来说没有区别，未对循环条件形成影响
	}
}
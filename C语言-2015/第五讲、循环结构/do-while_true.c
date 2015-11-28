/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
	/**
		while是先判断后执行，do-while先执行后判断
		while可能一次都不执行，do-while至少会执行一次
	*/
	while(0) {
		printf("while循环里，条件为假");
	}   
	
	do {
		printf("do-while循环里，条件为假");
	} while(0);
}
/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
	char c;
	int space = 0, letter = 0, digit = 0, other = 0;
	printf("请输入一串字符:\n");
	do {
		scanf("%c", &c);
		if(c == ' ') {
			space++;
		} else if(c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
			letter++;
		} else if(c >= '0' && c <= '9') {
			digit++;
		} else if(c == '\n') {} 
		else {
			other++;
		}
	} while(c != '\n');
	printf("%d个空格，%d字母，%d个数字，%d个其他字符 \n",space,letter, digit, other);
}
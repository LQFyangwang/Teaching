/* Note:Your choice is C IDE */
#include "stdio.h"
#include "string.h"

void main()
{
	char c[80];
	char temp;
	int length;
	int i;
    printf("请输入字符串：\n");
   	scanf("%s", &c);
    length = strlen(c);
    for(i = 0; i < length / 2; i++) { // i < length / 2，只需要换长度的一半
    	temp = c[i];
    	c[i] = c[length - i - 1];
    	c[length - i - 1] = temp;
    }
    printf("%s", c);
}
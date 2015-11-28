/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
    char a;
    char b[80];
    int i, flag = 0;
    scanf("%c", &a);
    printf("%c\n", a);
    printf("请输入字符串\n");
    scanf("%s", &b); // %s用来输入字符数组
    for(i = 0; i < 80; i++) {
    	if(b[i] == '\0') { // \0表示字符串的结束
    		break;
    	}
    	printf("%c", b[i]);
    }
}
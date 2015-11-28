/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
    char a = '\x41';
    char b = '\101';
    char c = '\0'; // \0是字符串的结束标志
    int aa = 3;
    int i = 2;
    printf("%c, %c, %c\n", a, b, c);
    printf("%d\n", a + b > c);
    printf("%d\n", aa+=aa-=aa*=aa); // 0
    printf("%d\n", (++i)+(++i)+(++i));
}
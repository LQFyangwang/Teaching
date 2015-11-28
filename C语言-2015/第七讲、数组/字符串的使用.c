/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
    char a[10] = {'a', 'b'};
    char b[10] = "hello"; // 这种形式的字符串后面默认以\0结束
    
    char c[][10] = {"hello", "hello1"};
    
    int i = 0;
    for(; i < 10; i++) {
    	printf("%c", a[i]);
    }
    for(i = 0; i < 10; i++) {
    	printf("%c", b[i]);
    }
    printf("%s", a); // 输出字符串用%s，后面不会有乱码或者空格
    printf("%s", b);
    printf("\n");
    for(i = 0; i < 2; i++) { // 对行进行循环
    	printf("%s", c[i]);
    }
}
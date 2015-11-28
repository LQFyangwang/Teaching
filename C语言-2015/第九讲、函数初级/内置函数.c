#include "stdio.h"
#include "math.h" //引用数学函数头math.h
#include "ctype.h" // 引用字符头文件
#include "stdlib.h"

#include "string.h" //字符串处理函数头文件 

/**
	printf, scanf, getchar, putchar, gets, puts  stdio.h
	sqrt, pow, ceil, floow   math.h
	toupper, tolower  ctype.h
	rand, exit   stdlib.h
	strcpy, strcmp, strcat, strlen, strlwr, strupr   string.h
*/
void main()
{
	int a = 100;
	char c;
	char b[15] = "hello";
	char d[10] = "hello1";
	char e[10];
	char f[10] = "HELLo";
	char g;
	char h[10] = "a";
    printf("%lf\n", sqrt(a)); //sqrt(double) 求平方根，得出的结果是double类型的
    printf("%lf\n", pow(2, 3)); // pow(a, b)求a的b次方，得出的结果是double类型的
    printf("%f\n", ceil(2.4)); //大于等于2.4的最小整数，得出的结果是double类型的
    printf("%f\n", floor(2.4)); //小于等于2.4的最大整数，得出的结果是double类型的
    printf("%c\n", toupper('a')); //得到小写字符对应的大写字母
    printf("%c\n", tolower('A')); //得到大写字符对应的小写字母
    printf("%d\n", rand()); //得出的结果是int类型的

    if(strcmp(b, d)) { // strcmp(字符串1, 字符串2)对字符串1与字符串2进行比较，如果相等，结果为0， 如果不相等，结果为1
    	printf("b 和 d字符串不相等\n");
    } else {
    	printf("b 和 d字符串相等\n");
    }
    
    strcpy(e, b); // 由b字符串copy到e字符串， 目标字符串放到前面，目标字符串的大小一定要大于等于源字符串的大小 
    printf("%s\n", e);
    
    strcat(b, d); // d字符串连接到b字符串后面，目标字符串的大小要大于等于连接后的字符串的大小 
    printf("%s\n", b);
    
    printf("b字符串的长度：%d\n", strlen(b)); // strlen(字符串)求字符串的长度（有多少个字符）
    
    printf("%s\n", strupr(b)); // strupr(字符串) 把字符串变成大写
    
    printf("%s\n", strlwr(f)); // strlwr(字符串) 把字符串变成小写
    
    // 字符输入及输出函数
    printf("请输入字符：\n");
    g = getchar(); //从控制台输入字符，放到变量g
    putchar(g); //输出字符到控制台
    
    printf("\n请输入字符串：\n");
    gets(h); // gets(字符串数组) 输入字符串存储到括号内的字符串数组中
    
    puts(h); // 直接输出字符串
    
    do {
    	printf("\n请选择操作：");
    	scanf("%c", &c);
    	if(c == 'n' || c == 'N') {
    		exit(0); // 当用户输入n或N时，结束程序
    	}
    } while(1);
}
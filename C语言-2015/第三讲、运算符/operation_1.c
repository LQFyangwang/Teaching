#include "stdio.h"

void main()
{
	//增量运算符，自增或自减
    int a = 0;
    int b = a++; //++表示自增，自增1 先把a赋值给b，再对a进行加1操作，赋值给a自己
    int c = a--; //--表示自减，自减1 先把a赋值给c，再对a进行减1操作，赋值给a自己
    /**
    a = 0;
    int b = a;    b =0
    再a = a + 1   a = 1
    再int c = a;  c = 1
    再a = a - 1;  a = 0
    
    a++, a--是先进行赋值操作，再进行增量操作
    */
    int d = 0;
    int e = ++d;
    int f = --d;
    
    // int g = 5++;只能对变量进行增量操作
    /**
    d = 0;
    先d = d + 1; d = 1
    再int e = d; e = 1
    再d = d - 1; d = 0
    再int f = d; f = 0
    
    ++a, --a是先进行增量操作，再进行赋值操作
    */
    printf("a = %d, b = %d, c = %d\n", a, b, c);// 0 0 1
    printf("d = %d, e = %d, f = %d\n", d, e, f);// 0 1 0
}
#include "stdio.h"

/**
	实参的值不受形参值变化的影响
*/

void func(int a) { // 形参变量a只作用于该函数
	a = 5; // 整个函数执行完后，b的内存空间会被释放
	printf("func a = %d\n", a);
}

void func1(int b) {
	b = 5;
	printf("func b = %d\n", b);
}

void main()
{
    int a = 0;
    func(a); // func(int a) func函数的形参a接收int a=0这个值，此时func形参a会变成0，
    			//然后func的形参a进行重新赋值，形参a = 5，一旦函数结束，形参a所占用的内存空间会释放
    printf("main a = %d\n", a);
    func1(a);
    printf("main a = %d\n", a);
}
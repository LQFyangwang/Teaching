/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
    int i;
    for(i = 0; i <= 100; i++) {
    	if(i % 5 !=0) {
    		printf("%d\t", i);
    	}
    }
    
    printf("\n");
    /**
    当i = 4, 4 <= 100，条件成立，
    进入循环体内部
    判断i能否被5整除，4 % 5 != 0，所以条件不成立，输出i；
    接着i++, 此时i = 5
    继续判断5 <= 100，条件成立，进入循环体内部
    判断i能否被5整除，5 % 5=0，条件成立，则进入到if语句体内部，执行continue,
    continue后面的所有语句都不再执行，直接进入下一次循环，执行i++，再判断
    */
    for(i = 0; i <= 100; i++) {
    	if(i % 5 == 0){
    		continue;
    	}
    	printf("%d\t", i);
    }
}
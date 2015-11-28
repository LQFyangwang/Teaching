/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
	int i;
    for(i = 0; i < 100; i++) {
    	if(i == 88) {
    		printf("找到%d\n", i);
    		break; // 未加break，从89-100，还需要判断，这些判断是没有意义，并且会影响执行效率。所以需要加上break，提前跳出循环，效率更高
    	}
    }
}
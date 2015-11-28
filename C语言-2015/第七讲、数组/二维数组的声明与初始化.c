/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
	/**
		二维数组：数据类型 数组名称[行数][列数];  行与列都从0开始
	*/
    int a[3][3]; // 3* 3 = 9个元素
    float b[5][5]; // 5 * 5 = 25个元素
    char c[2][10];
    
    int d[3][5] = {
	    	{1, 2, 3}, 
	    	{4, 5, 6}, 
	    	{7, 8, 9, 10, 11}
    };
    
    printf("%d\n", d[2][4]);
    
   	d[2][4] = 15;
    
    printf("%d\n", d[2][4]);
}
#include "stdio.h"
void main()
{
    int nian;
    printf("请输入一年:\n");
    scanf("%d", &nian);
    if(nian > 0) {
    	if(nian % 400 == 0 || (nian % 4 == 0 && nian % 100 !=0)) {
    		printf("闰年\n");
    	} else {
    		printf("不是闰年（平年）\n");
    	}
    } else {
    	printf("年必须是一个正整数\n");
    }
}
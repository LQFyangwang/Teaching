/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
	int score[5];
	int i;
    printf("请输入5个成绩\n");
    for(i = 0; i < 5; i++) {
    	scanf("%d", &score[i]);
    }
    printf("所有成绩如下：\n");
    for(i = 0; i < 5; i++) {
    	printf("%d\t", score[i]);
    }
}
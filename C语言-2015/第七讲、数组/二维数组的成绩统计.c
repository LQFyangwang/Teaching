/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
    // 输入3个班同学的成绩，每个班5个同学
    int score[3][5];
    int i, j, sum = 0;
    for(i = 0; i < 3; i++){
    	printf("请输入第%d班的成绩\n", i + 1);
    	for(j = 0; j < 5; j++) {
    		scanf("%d", &score[i][j]);
    		sum += score[i][j];
    	}
    }
    printf("总成绩：%d\n", sum);
}
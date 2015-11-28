/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
    int i;
    int score[10];
    int sum = 0, count = 0;
    printf("输入10个成绩：\n");
    for(i = 0; i < 10; i++) {
    	scanf("%d", &score[i]);
    	sum += score[i];
    	if(score[i] >= 60) {
    		count++;
    	}
    }
    printf("平均成绩:%.2f，及格率：%.2f%%\n", sum / 10.f, count / 10.f * 100); //10.f 10.0f   float
}
#include "stdio.h"
void main()
{
    float score[10];
    int i, count1 = 0, count2 = 0;
    printf("输入成绩：\n");
    for(i = 0; i < 10; i++) {
    	scanf("%f", &score[i]);
    	if(score[i] < 60) {
    		count1++;
    	} else if(score[i] >= 80) {
    		count2++;
    	}
    }
    printf("<60: %d, >=80: %d\n", count1, count2);
}
#include "stdio.h"
void main()
{
    int score = 95;
    char grade;
    // 90-100 A, 80-90 B 其余都是C
    if(score >= 90 && score <= 100) {
    	grade = 'A';
    } else if(score >= 80 && score < 90) {
 		grade = 'B';   
    } else {
    	grade = 'C';
    }
    printf("Grade: %c\n", grade);
}
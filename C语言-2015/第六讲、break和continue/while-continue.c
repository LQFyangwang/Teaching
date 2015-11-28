#include<stdio.h>
void main ()
{
    int i=0;
	while(i<5)
	{
		printf("* * * * *\n");       //一共要输出多少行"*"？
		
		i++;

		if(i==2)  
			continue;    //当i等于2时跳过continue后面的语句，继续下一次循环

		printf("i=%d\n",i);      //该语句执行了多少次？
	}
}

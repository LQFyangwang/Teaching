#include "stdio.h"
void main()
{
    int a=2,b=3; 
	switch(a)
	{
		case 2: b++;
		case 1:b++; break; // 前面case 2未加break语句，所以会继续执行此case语句后的b++
		case 3:b++;break;
	}
	printf("b = %d\n", b);
}
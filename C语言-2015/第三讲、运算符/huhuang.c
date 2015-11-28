#include "stdio.h"
void main()
{
    int a = 3;
    int b = 2;
    
    int temp;
    temp = a; // temp = 3
    a = b; // a = 2;
    b = temp; // b = 3
    printf("%d, %d", a, b);
}
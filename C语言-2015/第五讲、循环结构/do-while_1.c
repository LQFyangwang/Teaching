/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
	int pao = 0;
    int i = 0;
    do {
    	pao = 400 * (i + 1);
    	i++;
    } while(pao < 1200);
    printf("跑完%d米\n", pao);
}
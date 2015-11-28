#include "stdio.h"
void main()
{
	int i, j, k;
	int count = 0;
	for(i = 1; i <= 4; i++) {
		for(j = 1; j <= 4; j++) {
			for(k = 1; k <= 4; k++) {
				if(i != j && i != k && j != k) {
					count++;
					printf("%d%d%d\t", i, j, k);
				}
			}
		}
	}  
	printf("\n共%d个", count);
}
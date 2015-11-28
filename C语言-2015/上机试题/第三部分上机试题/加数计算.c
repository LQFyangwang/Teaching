/* Note:Your choice is C IDE */
#include "stdio.h"
void main()
{
	int a, b;
	int i, sum = 0, c = 0; // c用来算每个加数
	printf("请输入数据：\n");
	scanf("%d %d", &a, &b); // a = 3, b = 4   3 + 33 + 333 + 3333
	for(i = 0; i < b; i++) {
		c = a + c * 10;
		sum += c;
	}
	printf("结果为：%d\n", sum);
}
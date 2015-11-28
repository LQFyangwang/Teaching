/* Note:Your choice is C IDE */
#include "stdio.h"
#include "conio.h"

void main()
{
	int i;
	for(i = 0; i < 16; i++) {
		textbackground(i);
		textcolor(i + 20);
		cprintf("look at me!\r\n");	
	}
}
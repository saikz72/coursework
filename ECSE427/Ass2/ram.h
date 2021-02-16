#include <stdio.h>
void addToRam(FILE *file, int *start, int *end);
int removeFromRam(int start, int end);
extern int failureFlag;
extern char *ram[1000];

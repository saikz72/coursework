/**
 * this file contains two helper function to parse the content of the data file and to compute a number raise to a certain power. 
 *****************************************************************************************************************
 *Author                        Dept                            Date                            Note
 *****************************************************************************************************************
 *Saikou                        Soft. Eng                       April 14, 2020                  initial Version
 *
**/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//parse the data file to desired ouput
void parse(char* str, int* c, int* e){
	sscanf(str, "%d %d", c, e);
}

//computes x to the power exp
int powi(int x, int exp){
	int res = 1;
	int i;
	for(i = 0; i < exp; i++){
		res *= x;;
	}
	return res;
}

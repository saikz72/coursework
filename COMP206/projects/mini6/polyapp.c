/**
 * this file contains the main method where the simulation runs from. polynomials. 
 *****************************************************************************************************************
 *Author                        Dept                            Date                            Note
 *****************************************************************************************************************
 *Saikou                        Soft. Eng                       April 14, 2020                  initial Version
 *
**/

#include <stdio.h>
#include <stdlib.h>
#include "poly.h"
#include "utils.h"

int main(int argc, char* argv[]){
	if(argc == 1){
		printf("%s", "Pass in argument\n");
		return 0;
	}
	char arr[100];
	FILE *file = fopen(argv[1], "rt");	//open file in read mode 
	
	int coeff;
	int expo;	
	while(fgets(arr,99, file)){
		//fgets(arr,99,file);
		parse(arr, &coeff, &expo);
		int responds = addPolyTerm(coeff, expo);
		if(responds == -1){
			printf("Insufficient memory space\n");
		}
	}
	//calling displayPolynomial from poly.c file
	displayPolynomial();
	
	//calling evaluatePolynomial from poly.c file
	for(int i = -2; i <= 2; i++){
		printf("for x = %d, y = %d\n", i, evaluatePolynomial(i));
	}
	return 0;
}

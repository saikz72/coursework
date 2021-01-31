#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct MEM{
	char* var;
	char* value;
};

struct MEM memory[1000];
int size = 0;		//keeps track of the size of memory used

int set(char* varName, char* str){
	int i;
	int errorCode = -1;
	for(i = 0; i < size; ++i){
		if(strcmp(memory[i].var, varName) == 0){
			memory[i].value = str;
			errorCode = 0;
			return errorCode;
		}
	}
	if(size < 1000){
		memory[i].var = varName;
		memory[i].value = str;
		size++;
		errorCode = 0;
		return errorCode;
	}
	return errorCode;
}

int print(char *var){
	int i;
	int errorCode = -1;
	for(i = 0; i < size; ++i){
		if(strcmp(memory[i].var, var) == 0){
			printf("%s\n", memory[i].value);
			errorCode = 0;
			return errorCode;
		}
	}
	errorCode = -2;		//valid command but variable does not exist
	printf("Variable does not exist\n");
	return errorCode;
}

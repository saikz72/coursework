#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include "interpreter.h"

int parse(char *input, char *tokens[]) {
	int errorCode;
	char buffer[1000];
	int i=0, b=0, t=0; // counters for input, buffer and tokens

	while(*(input+i) != '\0') {
		// skip leading spaces
		for(;*(input+i)==' ';i++);  // super cool no?!
	
		// exit if end of string
		if (*(input+i) == '\0') break;

		// otherwise, add word to buffer
		b=0;
		while(*(input+i)!=' ' && *(input+i)!='\0') {
			buffer[b] = *(input+i);
			i++;
			b++;
		}
		buffer[b]='\0'; // make it a string

		// create a token
		tokens[t] = strdup(buffer);
		t++;
	}
	tokens[t] = NULL;
	errorCode = interpreter(tokens);
	return errorCode;
}

int main(int argc, char *argv[]){
	int errorCode;
    char *tokens[100];
	char input[1000];			
	//read from cmd
	printf("Welcome to thuram's shell\n"); 
	printf("Version 1.0 created January 2020\n");

	while(true){
		// printf("$ -> ");
		fgets(input, 999, stdin);
		strtok(input, "\n");
		errorCode = parse(input, tokens);
		if(errorCode == -1){
			printf("Unknown command\n");
		}		
	}
	return 0;
}


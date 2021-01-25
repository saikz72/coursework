#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include "interpreter.h"

int parse(char *string, char *tokens[]) {
	char buffer[1000];
	int s=0, b=0, t=0; // counters for string, buffer and tokens

	while(*(string+s) != '\0') {
		// skip leading spaces
		for(;*(string+s)==' ';s++);  // super cool no?!

		// exit if end of string
		if (*(string+s) == '\0') break;

		// otherwise, add word to buffer
		b=0;
		while(*(string+s)!=' ' && *(string+s)!='\0') {
			buffer[b] = *(string+s);
			s++;
			b++;
		}
		buffer[b]='\0'; // make it a string

		// create a token
		tokens[t] = strdup(buffer);
		t++;
	}
	tokens[t] = NULL;
	return interpreter(tokens);
}

int main(int argc, char *argv[]){
	int errorCode;
    char *tokens[100];
	char input[1000];			
	//read from cmd
	printf("Welcome to thuram's shell\n"); 
	printf("Version 1.0 created January 2020\n");
	while(true){
		printf("$ -> ");
		fgets(input, 999, stdin);
		errorCode = parse(input, tokens);
		if(errorCode == -1){
			printf("Unknown command\n");
		}		
	}
	return 0;
}


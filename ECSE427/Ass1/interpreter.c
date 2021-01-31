#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "shell.h"
#include "shellmemory.h"
/****** private helper functions *******/

//display all commands 
int help(){
	printf("COMMAND                        DESCRIPTION \n");
	printf("************************************************************************\n");
	printf("help ------------------------> Displays all the commands\n");
	printf("quit ------------------------> Exits/terminates the shell with Bye!\n");
	printf("set VAR STRING --------------> Assigns a value to shell memory\n");
	printf("print VAR -------------------> Displays the STRING assigned to VAR\n");
	printf("run SCRIPT.TXT --------------> Executes the file SCRIPT.TXT\n");
	return 0;	
}

//quits the program
int quit(){
	printf("Bye!\n");
	exit(0);
	return 0;
}

//runs a script that user passed 
int runScript(char *script){
	int errorCode = -1;
	if(script == NULL){
		errorCode = -2;		//to avoid shell from printing unknown command
		printf("Syntax Error: Usage 'run [filename]'\n");
		return errorCode;
	}
	char line[1000];
	char* tokens[1000];
	
	FILE *p = fopen(script, "rt");
	
	fgets(line, 990, p);
	strtok(line, "\n");
	while(!feof(p)){
		errorCode = parse(line, tokens);
		if(errorCode != 0){
			fclose(p);			
			return errorCode;
		}
		fgets(line, 999, p);
		strtok(line, "\n");

	}
	fclose(p);
	return errorCode;
}

//assigns a value to shell memory
int setVar(char *var, char *str){
	int errorCode = -1;
	if(var == NULL || str == NULL){
		printf("Syntax error: Usage 'set [variable] [string]\n'");
		return errorCode;
	}
	errorCode = set(var, str);
	return errorCode;
}

//displays the string assigned to var
int printVar(char *var){
	int errorCode = -1;
	if(var == NULL){
		printf("Syntax error: Usage 'print [variable]\n'");
		return errorCode;
	}
	errorCode = print(var);
	return errorCode;
}

void upperCaseInterpreter(char *tokens[]){	
	if((strcmp(tokens[0], "HELP") == 0) || (strcmp(tokens[0], "QUIT") == 0) || (strcmp(tokens[0], "PRINT") == 0) || (strcmp(tokens[0], "RUN") == 0) || (strcmp(tokens[0], "SET") == 0)){
		printf("Commands are case sensitive. Please use lower cases instead.\n");	
	}
}

int interpreter(char *tokens[]){
	int errorCode = -1;		//invalid command	
	//note commands are case sensitive
	upperCaseInterpreter(tokens);
	if(strcmp(tokens[0], "help") == 0){
		//user inputs help
		errorCode = help();
	}else if(strcmp(tokens[0], "quit") == 0){
		//user inputs quit
		errorCode = quit();
	}else if(strcmp(tokens[0], "print") == 0){
		//user inputs print
		errorCode = printVar(tokens[1]);
	}else if(strcmp(tokens[0], "run") == 0){
		//user inputs run
		errorCode = runScript(tokens[1]);
	}else if(strcmp(tokens[0], "set") == 0){
	
		//user inputs set
		errorCode = setVar(tokens[1], tokens[2]);
	}
	return errorCode;
}


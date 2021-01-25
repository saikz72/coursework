#include <stdio.h>
#include <stdlib.h>
#include <string.h>

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
	exit(0);
	return 0;
}

//runs a script that user passed 
int runScript(char *script){
	return 0;
}

//assigns a value to shell memory
int setVar(char *var, char *str){
	return 0;
}

//displays the string assigned to var
int print(char *var){
	return 0;
}

void upperCaseInterpreter(char *tokens[]){	
	if((strcmp(tokens[0], "HELP\n") == 0) || (strcmp(tokens[0], "QUIT\n") == 0) || (strcmp(tokens[0], "PRINT\n") == 0) || (strcmp(tokens[0], "RUN\n") == 0) || (strcmp(tokens[0], "SET\n") == 0)){
		printf("Commands are case sensitive. Please use lower cases instead.\n");	
	}
}

int interpreter(char *tokens[]){
	int errorCode = -1;		//invalid command	
	//note commands are case sensitive
	upperCaseInterpreter(tokens);
	if(strcmp(tokens[0], "help\n") == 0){
		//user inputs help
		errorCode = help();
	}else if(strcmp(tokens[0], "quit\n") == 0){
		//user inputs quit
		errorCode = quit();
	}else if(strcmp(tokens[0], "print\n") == 0){
		//user inputs print
		errorCode = print(tokens[1]);
	}else if(strcmp(tokens[0], "run\n") == 0){
		//user inputs run
		errorCode = runScript(tokens[1]);
	}else if(strcmp(tokens[0], "set\n") == 0){
		//user inputs set
		errorCode = setVar(tokens[0], tokens[1]);
	}
	return errorCode;
}

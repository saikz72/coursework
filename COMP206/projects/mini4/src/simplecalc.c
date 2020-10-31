/*
 * Program to implement a simple calculator
 * *********************************************************
 * Author	Dept. 		Date		Notes
 * *********************************************************
 * Saikou C	Software Eng	Feb 18 2020	initial version
 * Saikou C 	Software Eng 	Feb 22 2020 	completed
 */

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char* argv[]){

	//check if user passed 3 arguments
	if (argc != 4){
		printf ("Error: usage is simplecalc <x> <op> <y>\n");
		return 1;
	}else{
		//variables
		int result = 0;
		int first_num , second_num;
		char op;

	       	sscanf(argv[1], "%d", &first_num);		//convert 1st argument to an int and save in first_num
		sscanf(argv[3], "%d", &second_num);		//convert 3rd argument to an int and save in second_num
		sscanf(argv[2], "%c", &op );			//save 2nd argument in op as a character
		
		switch (op) {

			 //do if argument is +
			case '+':
			result = first_num + second_num;
	    		break;
              		//do if argument is -
        		case '-':
            		result = first_num - second_num;
            		break;
         	
			//do if argument is *
        		case '*':
            		result = first_num * second_num;
            		break;
             		
			//do if argument is /
        		case '/':
            		result = first_num / second_num;
            		break;	
			
			//do if argument is invalid
			default:
            		printf("%c is Invalid operation.\n", op);
            		return 2; 
     			}
    	printf("%d\n" ,result);
	return 0;
	}	
		
}


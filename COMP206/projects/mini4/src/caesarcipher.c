/*
 * Program to implement Caesar's cipher.
 * *********************************************************
 * Author	Dept. 		Date		Notes
 * *********************************************************
 * Saikou C	Software Eng	Feb 18 2020	initial version
 * Saikou C	Software Eng	Feb 22 2020 	completed 
 */
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char* argv[]){

//check if user passes an argument or not
if (argc == 1){
	printf( "Error: usage is caesarcipher <offset> \n");
	return 1;
}else{
	 int character, offset;
	 sscanf(argv[1], "%d", &offset);
	 
	 //read as long as end of file is not reach
	 while ((character = getchar()) != EOF) {
		 //check if offset is positive
		 if (character >= 'a' && character <= 'z' && offset > 0) {
			 char new_character = ((character - 'a' + offset) % 26) + 'a';   //shifting character by offset value to the right
			 putchar(new_character);

		 //check if offset is negative
		 }else if( character >= 'a' && character <= 'z' && offset < 0){
			 char new_character = character + offset;
           		 
			 if (new_character > 'z') {
				 new_character = 'a' + (new_character - 'z' - 1);
			 }else if (new_character < 'a') {
				 new_character = 'z' - ('a' - new_character - 1);
			 }
			 putchar(new_character);
		 }else{
			 putchar(character);
                }
	 }
	 return 0;
   }
}

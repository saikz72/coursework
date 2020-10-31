/*
 * this program simulates how to create a bank accoun and conduction of transactions
 ** ******************************************************************
 *Author		Dept 		Date				Notes
 **********************************************************************
 *Saikou		Software eng	Mar 10, 2020    initial version
 *Saikou		Software eng	March 26, 2020	Updated version
 *
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

//function protoype
int addAccount(char* accNum, FILE *file);
int makeDeposit(char* accNum, FILE *file, char* deposit);
int makeWithdrawals(char* accNum, FILE *file, char* withdrawals);

int main(int argc, char* argv[]){
	// user inputs wrong number of arguments
	if( argc == 1)
	{
		fprintf(stderr, "%s", "Error, incorrect usage!\n -a ACCTNUM NAME\n -d ACCTNUM DATE AMOUNT\n -w ACCTNUM DATE AMOUNT\n");
		return 1;
	}
	else if( argc == 3 && argv[1][1] == 'a' )
	{
		fprintf(stderr, "%s", "Error, incorrect usage!\n -a ACCTNUM NAME\n");
		return 1;
	}
	else if( argc == 3 && argv[1][1] == 'w' )
	{
		fprintf(stderr,"%s", "Error, incorrect usage!\n -w ACCTNUM DATE AMOUNT\n");
		return 1;
	}	
	else if( argc == 4 && argv[1][1] == 'd')
	{
		fprintf(stderr,"%s", "Error, incorrect usage!\n -d ACCTNUM DATE AMOUNT\n");
		return 1;
	}
	else
		// user inputs correct number of arguments
	{
		FILE *file = fopen("bankdata.csv", "r");		//open file in read mode
		if(file == NULL)		//verify file does exist
		{
			fprintf(stderr,"%s", "Error, unable to locate the data file bankdata.csv\n");
			return 100;
		}
		else
		{
			fclose(file);
			file = fopen("bankdata.csv", "a+");		//open file in append mode
			char checker = argv[1][1];				//swictch to determine creating account, depositing in account or withdrawing from account		

			if(checker == 'a' && addAccount(argv[2], file) == 50)
			{
				fprintf(stderr, "Error, account number %s already exist\n", argv[2]);	
				fclose(file);				
				return 50;
			}
			else if(checker == 'a' && addAccount(argv[2], file) == 75)
			{
				fprintf(file, "AC,%s,%s\n", argv[2], argv[3]);  
			}
			else if(checker == 'd' && makeDeposit(argv[2], file, argv[4]) == 50 )
			{
				fprintf(stderr, "Error, account number %s does not exist\n", argv[2]); 
				fclose(file);
				return 50;
			}
			else if(checker == 'd' && makeDeposit(argv[2], file, argv[4]) == 75 )
			{
				fprintf(file, "TX,%s,%s,%s\n", argv[2], argv[3], argv[4]);
				fclose(file);
			}
			else if (checker == 'w' && makeWithdrawals(argv[2], file, argv[4]) == 50)
			{
				fprintf(stderr, "Error, account number %s does not exist\n", argv[2]); 
				fclose(file);
				return 50;		
			}
			else{
               		char arr[100];
					float sum = 0;
               		while(!feof(file)){
						fgets(arr, 100, file);
						if(strncmp(arr,"TX", 2) == 0 && strncmp(arr+3, argv[2], 4) == 0){
							sum += strtof(arr+19, NULL);	
							}
                	}
                
               		float withdraw = strtof(argv[4], NULL);
               		if(sum < withdraw){			//insufficient to make withdrawal
						fprintf(stderr, "Error, account number %s has only %.2f\n", argv[2], sum);
                   		 return 60;          
               		}else{
						fprintf(file, "TX,%s,%s,%.2f\n",argv[2],argv[3],withdraw * -1);
						fclose(file);
                    	return 0;          
                }
       		 }
		}
	}
}
// checkes if account exist before creating it

int addAccount(char* accNum, FILE *file){
	char arr[100];		//to store contents of file

	while(!feof(file)){
		fgets(arr, 100, file);
		char *fileArray = arr;
		char *ptr = strtok(fileArray, ",");
		while(ptr != NULL){
			if(strcmp(accNum, ptr) == 0){
 				return 50;				//returns 50 if account exist
			}else{
				ptr = strtok(NULL, ",");
			}	
		}
	}	
	return 75;		//returns 75 if account does not exist
}


//checks if account exist before making deposits 

int makeDeposit(char* accNum, FILE *file, char* deposit){
	//if account does not exist
	if (addAccount(accNum, file) == 75){
		return 50;	
	}
	else{
		return 75;	
	}

}
/*
method to make withdrawals from account
*/

int makeWithdrawals(char* accNum, FILE *file, char* withdrawals){
	//if account does not exist
	if(addAccount(accNum, file) == 75){
		return 50;
	}
	return 0;		//default return value
}	

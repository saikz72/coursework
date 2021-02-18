#include <stdio.h> 
#include <string.h>
#include <stdlib.h>

char *ram[1000];
int size = 0;
int idx = 0;        //keeps track of the next free cell in memory
int failureFlag;

//removes a process from ram
//start = process start address; end = process end address
int removeFromRam(int start, int end){
    for(int i = start; i <= end; ++i){
        ram[i] = NULL;
    }
    idx = 0;
    return 0;
}

//adds a process to ram
void addToRam(FILE *file, int *start, int *end){

    //need to store the proces start and end address in the pcb
    failureFlag = 0;
    *start = idx;  
    char buffer[1000];
    fgets(buffer, 999, file);
    strtok(buffer, "\n");
    while(!feof(file)){
        ram[idx] = strdup(buffer);
        ++idx;
        ++size;
        if(idx == 999){     //ram is full
            removeFromRam(0, idx);
            idx = *start;
            failureFlag = -1;          //error while laoding
            printf("ERROR: Not enough RAM to add program\n");
            return;
        }
        fgets(buffer, 999, file);
        strtok(buffer, "\n");
    }
    *end = idx - 1;      //end cell of the program

}




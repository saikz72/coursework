#include "shell.h"
#include "pcb.h"
#include "ram.h"
#include "cpu.h"

#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>

//defining the node for the ready queue
typedef struct Node{
    PCB *pcb;
    struct Node *next;      //points to the next node   
} Node;

Node* head = NULL;
Node* tail = NULL;

void printStuff(){
    Node *node = (Node *)malloc(sizeof(Node));
    node = head;
    while(node != NULL){
        printf("| PC = %d & START = %d & END = %d |\n", node->pcb->PC, node->pcb->start, node->pcb->end);
        node = node->next;
    }
    free(node);
}

void detail(PCB *pcb){
    printf("| PC = %d & START = %d & END = %d |\n", pcb->PC, pcb->start, pcb->end);
}
//private helper ----_!!!problem
void enqueue(Node *node){
    if(head == NULL){
        head = node;
        tail = node;
    }else{
        tail->next = node;  //value pointed to by tail->next = node
        tail = node;
    }
}

//adds a pcb into the round robin ready queue
void addToReady(PCB *pcb){
    Node *node = (Node *)malloc(sizeof(Node));
    node->pcb = pcb;
    node->next = NULL;
    enqueue(node);
}

//removes a pcb from a ready list
Node* removeFromReady(){
    Node *node = (Node *)malloc(sizeof(Node));
    node = head;
    if(head == NULL){
        return NULL;
    }
    head = head->next;
    return node;
}

void terminateProcess(Node *node, int start, int end){
    removeFromRam(start, end);
    free(node);
}

int myinit(char *filename){
    FILE *file = fopen(filename, "r");
    int s = 0;
    int e = 0;
    addToRam(file, &s, &e);  
    if(failureFlag == -1){
        return 1;      //error while loading file into RAM
    } 
    PCB *pcb = makePCB(s, e);
    pcb->PC = s;
    addToReady(pcb);
    fclose(file);
    return 0;
}

int scheduler(){
    Node *node = NULL; 
    while((node = removeFromReady()) != NULL){        //cpu is available, DOUBLE CHECK!!!!
        PCB *pcb = node->pcb;
        setIP(pcb->PC);
        if(pcb->end >= pcb->PC + 1){
            run(2); //run for 2 quanta
            pcb->PC = getIP();
            addToReady(pcb);
        }else if(pcb->PC == pcb->end){
           // printf("1 quanta\n");
            run(1);
            pcb->PC = getIP();
            addToReady(pcb);
        }else{
           //printf("0 quanta\n");
           terminateProcess(node, pcb->start, pcb->end);
        }
    }   
    return 0;
}

int main(int argc, const char *argv[]){
    shellUI();
    return 0;
}

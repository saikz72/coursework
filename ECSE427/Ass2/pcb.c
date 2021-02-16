#include <stdlib.h>

typedef struct PCB{
    int PC;     //instruction to execute in RAM
    int start;  //first instruction in RAM
    int end;    //last instruction in RAM
} PCB;

PCB* makePCB(int start, int end){
    PCB *pcb = (PCB *) malloc(sizeof(PCB));
    pcb->start = start;
    pcb->end = end;
    return pcb;
}

#include "interpreter.h"
#include "ram.h"

#include <stdlib.h>
#include <string.h>

typedef struct CPU{
    int IP;     //points to the next instruction in RAM
    char IR[1000];     // points to the currently executing instruction
    int quanta;
} CPU;

CPU cpu;

void setQuanta(int quanta){
    cpu.quanta = quanta;
}

int run(int quanta){
    int errorCode;
    setQuanta(quanta);
    //IP = PCB's PC before execution (kernel.c)
    while(cpu.quanta > 0) {
        strcpy(cpu.IR, ram[cpu.IP]);
        errorCode = interpret(cpu.IR);
        cpu.IP = cpu.IP + 1;
        cpu.quanta = cpu.quanta - 1;
    }
    //quanta finish, update PCB's PC
    return errorCode;
}

//sets PCB's PC to CPU's IP
void setIP(int PC){
    cpu.IP = PC;
}

//returns the IP of the cpu
int getIP(){
    return cpu.IP;
}


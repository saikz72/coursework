#ifndef CPU_H
#define CPU_H

typedef struct CPU{
    int IP;     //points to the next instruction in RAM
    char IR[1000];     // points to the currently executing instruction
    int quanta;
} CPU;

struct CPU cpu;
int run(int quanta);
void setIP(int PC);
int getIP();
int isCpuAvailable;

#endif
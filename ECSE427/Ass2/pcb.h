typedef struct{
    int PC;     //instruction to execute in RAM
    int start;  //first instruction in RAM
    int end;    //last instruction in RAM
} PCB;

PCB* makePCB(int start, int end);
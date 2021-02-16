CC=gcc -g 

all : interpreter.o shell.o shellmemory.o cpu.o kernel.o pcb.o ram.o 
	$(CC) interpreter.o shell.o shellmemory.o cpu.o kernel.o ram.o pcb.o -o mykernel

interpreter.o : interpreter.c 
	$(CC) -c interpreter.c 

shell.o : shell.c 
	$(CC) -c shell.c 

shellmemory.o : shellmemory.c
	$(CC) -c shellmemory.c 

cpu.o : cpu.c
	$(CC) -c cpu.c

kernel.o : kernel.c
	$(CC) -c kernel.c

pcb.o	: pcb.c
	$(CC) -c pcb.c

ram.o 	: ram.c
	$(CC) -c ram.c

.PHONY:
clean:
	rm -f interpreter.o shell.o shellmemory.o
	rm -f mysh

CC=gcc -g 

all : interpreter.o shell.o shellmemory.o cpu.o kernel.o
	$(CC) interpreter.o shell.o shellmemory.o cpu.o kernel.o -o mykernel

interpreter.o : interpreter.c 
	$(CC) -c interpreter.c 

shell.o : shell.c 
	$(CC) -c shell.c 

shellmemory.o : shellmemory.c
	$(CC) -c shellmemory.c 

cpu.o : cpu.c
	$(CC) -c cpu.c

kernel : kernel.c
	$(CC) -c kernel.c

.PHONY:
clean:
	rm -f interpreter.o shell.o shellmemory.o
	rm -f mysh
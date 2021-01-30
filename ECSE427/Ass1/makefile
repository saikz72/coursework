mysh: shell.o interpreter.o shellmemory.o
	gcc -o mysh shell.o interpreter.o shellmemory.o
shell.o: shell.c
	gcc -c shell.c
interpreter.o: interpreter.c
	gcc -c interpreter.c
shellmemory.o: shellmemory.c
	gcc -c shellmemory.c
clean:
	rm interpreter.o shell.o shellmemory.o 

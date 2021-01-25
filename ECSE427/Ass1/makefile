mysh: shell.o interpreter.o
	gcc -o mysh shell.o interpreter.o
shell.o: shell.c
	gcc -c shell.c
interpreter.o: interpreter.c
	gcc -c interpreter.c
clean:
	rm interpreter.o shell.o 
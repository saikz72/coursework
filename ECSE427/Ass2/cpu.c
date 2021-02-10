#include "interpreter.h"
#include "shellmemory.h"

#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>

int main(int argc, const char *argv[])
{
    printf("Welcome to the <your name goes here> shell!\n"
           "Version 1.0 Created January 2020\n");

    shell_memory_initialize();

    while (!feof(stdin))
    {
        printf("$ ");
        fflush(stdout);

        char *line = NULL;
        size_t linecap = 0;
        if (getline(&line, &linecap, stdin) == -1)
            break;

        (void)interpret(line);
        free(line);
    }

    shell_memory_destory();

    return 0;
}
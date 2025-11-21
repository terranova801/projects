# Make file for todo list C program

build: main.c utility.c
	gcc -c main.c utility.c
	gcc main.o utility.o -o todo

run: todo
	./todo

clean:
	rm *.o
	rm todo

#include "utility.h"

typedef struct {
    char name[MAX_NAME_LENGTH];
    int completed;
} Task;

Task tasks[MAX_TASKS];
int numTasks = 0;

/* Function: prints program options for user selection

* called from main method

* returns: void, prints to terminal

*/
void displayMenu() {
    printf("\n===== To-Do List Menu =====\n");
    printf("1. Add Task\n");
    printf("2. Mark Task as Completed\n");
    printf("3. Remove Task\n");
    printf("4. View To-Do List\n");
    printf("5. Exit\n");
}


/* Function: adds a task to the users to do list as long as the list is not too full

* returns: void, adds task to list and prints result to terminal

*/
void addTask() {
    if (numTasks >= MAX_TASKS) {
        printf("Cannot add more tasks. To-do list is full.\n");
        return;
    }

    printf("Enter task name: ");
    fgets(tasks[numTasks].name, MAX_NAME_LENGTH, stdin);
    tasks[numTasks].name[strcspn(tasks[numTasks].name, "\n")] = '\0'; // Remove newline character
    tasks[numTasks].completed = 0; // Not completed
    numTasks++;
    printf("Task added to the to-do list.\n");
}


/* Function: marks a task in the to do list as completed

* takes in user provided task to mark as completed

* finds line matching the task and appends to "Completed"

* prints out result of task

*/
void markTaskAsCompleted() {
    char taskName[MAX_NAME_LENGTH];
    printf("Enter task name to mark as completed: ");
    fgets(taskName, MAX_NAME_LENGTH, stdin);
    taskName[strcspn(taskName, "\n")] = '\0'; // Remove newline character

    for (int i = 0; i < numTasks; i++) {
        if (strcmp(tasks[i].name, taskName) == 0) {
            tasks[i].completed = 1; // Mark as completed
            printf("Task marked as completed.\n");
            return;
        }
    }

    printf("Task not found in the to-do list.\n");
}

/* Function: removes a task from the todo list

* takes in user provided task, searches for matching line for corresponding task

* removes line of task to be removed

*/
void removeTask() {
    char taskName[MAX_NAME_LENGTH];
    printf("Enter task name to remove: ");
    fgets(taskName, MAX_NAME_LENGTH, stdin);
    taskName[strcspn(taskName, "\n")] = '\0'; // Remove newline character

    for (int i = 0; i < numTasks; i++) {
        if (strcmp(tasks[i].name, taskName) == 0) {
            // Move tasks after the removed task one position to the left
            for (int j = i; j < numTasks - 1; j++) {
                strcpy(tasks[j].name, tasks[j + 1].name);
                tasks[j].completed = tasks[j + 1].completed;
            }
            numTasks--;
            printf("Task removed from the to-do list.\n");
            return;
        }
    }

    printf("Task not found in the to-do list.\n");
}


/* Function: prints all tasks in todo list, each with a corresponding line number

* also includes a task completion note

*/
void viewTodoList() {
    printf("\n===== To-Do List =====\n");
    for (int i = 0; i < numTasks; i++) {
        printf("%d. %s - %s\n", i + 1, tasks[i].name, tasks[i].completed ? "Completed" : "Not Completed");
    }
}



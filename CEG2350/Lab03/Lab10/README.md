## Lab 10

- Name: Alex Feyh
- Email: alexfeyh@proton.me

## Part 0 - Issues & Branches

**`git` branch guide**

| Task                                                    | Command(s) / Steps | Notes |
| ------------------------------------------------------- | ------------------ | ----- |
| **Create a branch**                                     | git switch -c <name> | Branches from current branch (in this case main)       |
| **Change to branch**                                    | git switch <name>  | Used after branch has been created   |
| **Add branch to remote if created locally (GitHub)**    | git push -u origin <name>    | Pushes local branch to GitHub   |
| **Steps to `merge` changes to another branch (`main`)** | git switch main -> git merge <name>  |  Does not remove anything only adds branched items to main  |
| **Steps to resolve a `merge` conflict**                 | Review conflicting files, look for '=====' at the "center" of the conflict. Resolve conflicts by editing files. -> git add <file> -> git commit -m "merged/resolved conflict   |       |


## Part 1 - Compilers

**Installing the compiler for different OSes**

| Language         | Compiler           | Linux (Ubuntu/Debian)    | macOS       |Windows        | 
| ---------------- | ------------------ | ------------------------ | ----------- | ------------- | 
| **C / C++**      | GCC / G++          | `sudo apt install build-essential`     | `xcode-select --install`  | Install **MinGW** via [MSYS2](https://www.msys2.org/) <br>→ `pacman -S mingw-w64-x86_64-gcc` |
| **Java**         | OpenJDK            |  `sudo apt install default-jdk`          |  `brew install openjdk`           |    `choco install openjdk`    |
| **Python**       | Python             |  `sudo apt install python3 python3`    | `brew install nuitka` or `brew install python`     |    `choco install python`    |

**Verifying install location & verison**

| Language       | OS                    | Confirm Installation Command | Check Version Command | Expected Output Example                          |
| -------------- | --------------------- | ---------------------------- | --------------------- | ------------------------------------------------ |
| **C (GCC)**    | Linux                 | `which gcc`                  | `gcc --version`       | `gcc (Ubuntu 13.2.0) 13.2.0`                    |
|                | macOS                 | `which gcc`                  | `gcc --version`       | `Apple clang version 15.0.0 (clang-1500.0.40.1)` |
|                | Windows (MSYS2/MinGW) | `where gcc`                  | `gcc --version`       | `gcc.exe (Rev10, Built by MSYS2 project) 13.2.0` |
| **C++ (G++)**  | Linux                 | `which g++`                  | `g++ --version`       | `g++ (Ubuntu 13.2.0) 13.2.0`                     |
|                | macOS                 | `which clang++`              | `clang++ --version`   | `Apple clang version 15.0.0`                     |
|                | Windows               | `where g++`                  | `g++ --version`       | `g++.exe (Rev10, Built by MSYS2 project) 13.2.0` |
| **Java (JDK)** | Linux                 | `which java`                 | `java -version`       | `openjdk version "21.0.8" 2025-07-15...OpenJDK Runtime Environment (build 21.0.8+9-Ubuntu-0ubuntu124.04.1)...OpenJDK 64-Bit Server VM (build 21.0.8+9-Ubuntu-0ubuntu124.04.1, mixed mode, sharing)`                                                 |
|                | macOS                 | `which java`                 | `java -version`       | `openjdk version "21"`                           |
|                | Windows               | `where java`                 | `java -version`       | `openjdk version "21"`                           |
| **Python 3**   | Linux                 | `which python3`              | `python3 --version`   |  `Python 3.12.3`                                 |
|                | macOS                 | `which python3`              | `python3 --version`   |  `Python 3.12.3`                                 |
|                | Windows               | `where python`               | `py --version`        |  `Python 3.12.3`                                 |


## Part 2 - Compiling

1. Method & command to get a copy of the source code files: 
`wget https://raw.githubusercontent.com/pattonsgirl/CEG2350/main/Labs/Lab10/TODO-C/utility.c`
`wget https://raw.githubusercontent.com/pattonsgirl/CEG2350/main/Labs/Lab10/TODO-C/main.c`
`wget https://raw.githubusercontent.com/pattonsgirl/CEG2350/main/Labs/Lab10/TODO-C/utility.h`
2. Command(s) to install the C/C++ compiler on Ubuntu: `sudo apt install build-essential`
3. Command(s) to confirm the installation of the C/C++ compiler on Ubuntu: `which gcc`
4. Command(s) to compile the source code into an executable program: 
`gcc -c main.c utility.c`
`gcc main.o utility.o -o todo`
5. Command(s) to execute the program: `./todo`

## Part 3 - Ignore That

[`.gitignore` file in root of repository](../.gitignore)

## Part 4 - Document

### TODO Program User Guide

#### Program Description
When compiled, allows user to add to do tasks to a list. All tasks can be printed out for user
tracking. Tasks can be marked as completed, or removed if tracking is no longer required.

#### How to Build and Run Program

Compile program by using C compiler within the folder containing the code files:

`gcc -c main.c utility.c`
`gcc main.o utility.o -o todo`

#### How to Use Program

To iniate program:
`./todo`

## Part 5 - `makey makey`

Using `Makefile` to build executable:

`make build`

Using `Makefile` to run program:

`make run`

Using `Makefile` to remove compilation files, such as the executable: 

`make clean`

## Extra Credit - `git` tools

`git` tool selected:

How to install this tool:

Screenshot proof of it in action!

![Insert screenshot here](path/to/screenshot)

## Citations

- [Git Cheat Sheet](https://git-scm.com/cheat-sheet)
- [Git Branching - Basic Branching and Merging](https://git-scm.com/book/en/v2/Git-Branching-Basic-Branching-and-Merging)
- [‘make’ Linux Command | File System Automation Guide](https://ioflood.com/blog/make-linux-command/)
- [Git merge conflicts - Atlassian](https://www.atlassian.com/git/tutorials/using-branches/merge-conflicts)
- [Emory - The correct way to compile a multiple files C program](https://www.cs.emory.edu/~cheung/Courses/255/Syllabus/1-C-intro/declare-func1b.html)
- [pattonsgirl - .gitignore example](https://github.com/pattonsgirl/CEG2350/blob/main/.gitignore)

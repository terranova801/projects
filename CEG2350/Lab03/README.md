## Lab 03
## Lab 03

- Name: Alex Feyh
- Email: alexfeyh@proton.me

Instructions for this lab: https://pattonsgirl.github.io/CEG2350/Labs/Lab03/Instructions.html

## Part 1 - git Guide

| git command         | Description |
| ---                 | ---         |
| `git clone repo_URI`| Clones the repository located at the given URL   |
| `git status`        | Checks the repository status            |
| `git add filename`  | Stages file changes (including modifying, adding or deleting)            |
| `git commit`        | Commits staged changes            |
| `git push`          | Pushes commited changes            |
| `git pull`          | Pulls latest changes            |

## Part 2 - clone

1. Command to generate an SSH key with ed25519:  ssh-keygen -t ed25519 -C alexfeyh@proton.me
2. Command(s) to read & copy text of the *public* key: cat /home/alex/.ssh/id_ed25519.pub
3. Summary of steps to place *public* key in user profile: Copy public key printed from command used above. In github settings > SSH & GPG Keys > New SSH key > Paste public key to add
4. Command to *clone* your `ceg2350s25-YOURGITHUBUSERNAME` with SSH for authentication: git clone git@github.com:WSU-kduncan/ceg2350f25-terranova801.git
git clone git@github.com:WSU-kduncan/ceg2350f25-terranova801

## Part 3 - IO Redirection

1. `printenv HOME > thishouse`
   - Explanation: Prints the environment details. Given the HOME variable, the home directory /home/ubuntu is printed and redirected to the file 'thishouse'. Given the redirection is an output overwrite, if the file thishouse exists, its contents will be overwritten, otherwise a new file will be created and the output of printenv will be written to this file. 
2. `cat doesnotexist 2>> hush.txt`
   - Explanation: The command cat doesnotexist generates an error since the file does not exist.  Redirects error output to a textfile; hush.txt, creating it if it doesn't exist or appending it if it does exist.
3. `cat nums.txt | sort -n >> all_nums.txt`
   - Explanation: These are piped command operations. First the file nums.txt is printed out, the output is then piped to the sort program, which sorts lines of text files, and given the -n option; numeric sort, the file is sorted numerically by line, from smallest to largest value. The output of this command is then redirected to a file named all_nums.txt, if this file exists it is appended, otherwise a new file is created 
4. `cat << "DONE" > here.txt`
   - Explanation: Appends standard user input, creating a file named here.txt... Allows the user to write lines of text, until the user inputs DONE. The lines will then be written to the file, overwriting any previous text.
5. `ls -lt ~ | head`
   - Explanation: Lists files/folders of ubuntu home directory, with long listing format, sorted by time, newest first. The output is then piped to the head program, which outputs the first part of the files input.
6. `history | grep ".md"`
   - Explanation: The history command lists out standard input history, which is piped to the grep program that prints the lines which contain ".md"... Prints out standard input lines that were related to markdown files

## Part 4 - Rolling the Dice

Verify that `roll` made it to your GitHub repository for this course and is in your `Lab03` folder.  No answers will be written here unless you would like to leave a note to the TAs

## Part 5 - Retrospective Answers

1. Where and when did it go wrong while working on your script tasks?
> Your reflection here
2. Was anything familiar working with a new language compared to one you are used to?
> Your reflection here. 
3. Did you write good `commit` messages that refer to what tasks were completed at each commit?  What would you improve?
> Your reflection here

## Extra Credit

1. Note here *what* you did to the script for the extra credit.

## Citations

To add citations, provide the site and a summary of what it assisted you with.  If generative AI was used, include which generative AI system was used and what prompt(s) you fed it.  Generative AI may not write your script for you, only assist with component and how to type questions.


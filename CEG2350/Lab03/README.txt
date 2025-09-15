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
4. Command to *clone* your `ceg2350s25-YOURGITHUBUSERNAME` with SSH for authentication:
git clone git@github.com:WSU-kduncan/ceg2350f25-terranova801

## Part 3 - IO Redirection

1. `printenv HOME > thishouse`
   - Explanation:
2. `cat doesnotexist 2>> hush.txt`
   - Explanation:
3. `cat nums.txt | sort -n >> all_nums.txt`
   - Explanation:
4. `cat << "DONE" > here.txt`
   - Explanation:
5. `ls -lt ~ | head`
   - Explanation:
6. `history | grep ".md"`
   - Explanation:

## Part 4 - Rolling the Dice

Verify that `roll` made it to your GitHub repository for this course and is in your `Lab03` folder.  No answers will be written here unless you would like to leave a note to the TAs

## Part 5 - Retrospective Answers

1. Where and when did it go wrong while working on your script tasks?
> Your reflection here
2. Was anything familiar working with a new language compared to one you are used to?
> Your reflection here
3. Did you write good `commit` messages that refer to what tasks were completed at each commit?  What would you improve?
> Your reflection here

## Extra Credit

1. Note here *what* you did to the script for the extra credit.

## Citations

To add citations, provide the site and a summary of what it assisted you with.  If generative AI was used, include which generative AI system was used and what prompt(s) you fed it.  Generative AI may not write your script for you, only assist with component and how to type questions.


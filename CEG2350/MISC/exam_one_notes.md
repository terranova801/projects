===========================
 CEG 2350 EXAM 1 CHEAT SHEET
===========================

-------------------------------------------------------
SHELLS & SSH
-------------------------------------------------------
Common shells:
  bash       – Bourne Again SHell (default Linux)
  sh         – minimal POSIX shell (portable)
  zsh        – bash-compatible, extras (Mac default)
  PowerShell – object-based Windows shell
  cmd        – classic Windows command prompt

SSH (Secure Shell)
  ssh user@host              → connect to remote system
  ssh -i <keyfile> user@host → specify private key
  ssh -p 2222 user@host      → specify port
  ssh-keygen -t rsa -b 4096  → generate SSH key pair
  chmod 600 ~/.ssh/id_rsa    → fix key permissions
  ssh-copy-id user@host      → copy your key to remote
  authorized_keys file: ~/.ssh/authorized_keys

-------------------------------------------------------
FILES, DIRECTORIES, AND PERMISSIONS
-------------------------------------------------------
Navigation & viewing:
  pwd                     → print working directory
  ls                      → list files
  ls -l                   → long listing (permissions, owner)
  ls -a                   → show hidden files
  cd <dir>                → change directory
  cd ..                   → up one level
  cd ~                    → go to home directory

File ops:
  touch <file>            → create empty file / update timestamp
  cat <file>              → print contents
  less <file>             → view paginated (q to quit)
  head -n 10 <file>       → first 10 lines
  tail -n 10 <file>       → last 10 lines
  cp <src> <dest>         → copy file
  mv <src> <dest>         → move/rename
  rm <file>               → delete file
  mkdir <dir>             → make directory
  rmdir <dir>             → remove empty directory
  ln <target> <link>      → hard link
  ln -s <target> <link>   → symbolic link (shortcut)

File permissions:
  ls -l → shows -rwxr-xr--
  r = read (4), w = write (2), x = execute (1)
  chmod 755 file      → rwxr-xr-x
  chmod u+x file      → add exec for user
  chown user:group f  → change ownership
  chgrp group file    → change group
  sudo command        → run as root
  adduser <name>      → create new user
  deluser <name>      → remove user
  groups              → show your groups

-------------------------------------------------------
ENVIRONMENT VARIABLES
-------------------------------------------------------
  echo $HOME           → current home directory
  echo $USER           → current username
  echo $SHELL          → current shell
  echo $PATH           → directories searched for executables
  export VAR=value     → set variable
  printenv             → list environment variables
  set                  → list shell variables

.bashrc / .profile → startup files for environment setup
alias ll='ls -la'   → create alias
unalias ll          → remove alias

-------------------------------------------------------
I/O STREAMS & REDIRECTION
-------------------------------------------------------
  stdin  = 0  (input)
  stdout = 1  (normal output)
  stderr = 2  (error output)

  cmd > file        → redirect stdout (overwrite)
  cmd >> file       → append stdout
  cmd 2> errors.log → redirect stderr
  cmd > out 2>&1    → redirect both to same file
  cmd < input.txt   → use file as input
  cmd1 | cmd2       → pipe output of cmd1 into cmd2
  tee file          → output to screen + file

-------------------------------------------------------
COMMON TEXT/UTILITY COMMANDS
-------------------------------------------------------
  diff f1 f2                 → show line differences
  find /path -name "*.txt"   → find files by name
  file <filename>            → show file type
  wc -l <file>               → count lines
  sort <file>                → sort lines
  uniq <file>                → remove duplicates (needs sorted input)
  whereis bash               → locate program binaries/docs
  which bash                 → show executable path
  grep "pattern" file        → search for text
  grep -i "pattern" file     → ignore case
  grep -n "pattern" file     → show line numbers
  grep -E "regex" file       → use extended regex
  regex basics:
      ^  start of line
      $  end of line
      .  any single character
      *  zero or more
      [abc] character set
      [0-9] digit range
      \d  digit (extended)
      \s  whitespace

-------------------------------------------------------
BASH SCRIPTING
-------------------------------------------------------
Running scripts:
  source script.sh       → run in current shell
  bash script.sh         → run in subshell
  ./script.sh            → execute (requires #!/bin/bash and chmod +x)

Script basics:
  #!/bin/bash
  # comments start with #
  echo "Hello World"

Variables:
  name="Alex"
  echo $name
  read -p "Enter value: " var

Arithmetic:
  let x=5+3
  echo $((x+2))

Conditionals:
  if [ condition ]; then
     commands
  elif [ other ]; then
     ...
  else
     ...
  fi

Common test conditions:
  -f file   → file exists
  -d dir    → directory exists
  -e file   → file exists (any type)
  -r file   → readable
  -w file   → writable
  -x file   → executable
  -z str    → empty string
  -n str    → non-empty string
  =, !=     → string compare
  -eq, -ne, -gt, -lt, -ge, -le → integer compare

Loops:
  for i in 1 2 3; do echo $i; done
  for file in *.txt; do echo $file; done
  while read line; do echo $line; done < file.txt

case statement:
  case $var in
    start) echo "Starting";;
    stop)  echo "Stopping";;
    *)     echo "Unknown";;
  esac

printf vs echo:
  echo just prints text
  printf "Name: %s\nAge: %d\n" "$name" "$age"

-------------------------------------------------------
GIT VERSION CONTROL
-------------------------------------------------------
Setup & cloning:
  ssh-keygen -t rsa -b 4096         → generate SSH key
  git clone git@github.com:user/repo.git → clone via SSH

Workflow:
  git status        → show current changes
  git add .         → stage all files
  git commit -m "msg" → commit staged changes
  git push origin main → push to remote
  git pull          → fetch + merge latest
  git fetch         → fetch only (no merge)
  git log           → show commit history
  git diff          → show unstaged changes
  git rm file       → remove from repo
  git mv old new    → rename tracked file

Branches:
  git branch              → list branches
  git checkout -b newbr   → create & switch
  git switch main         → change branch

Remote issues:
  Permission denied (publickey) → fix SSH key on GitHub

-------------------------------------------------------
MISC / SYSTEM INFO
-------------------------------------------------------
  whoami            → current user
  hostname          → system hostname
  date              → current date/time
  uptime            → system uptime
  df -h             → disk usage
  du -sh <dir>      → directory size
  history           → show command history
  clear             → clear terminal
  man <command>     → show manual
  exit              → close shell

-------------------------------------------------------
PERMISSIONS QUICK REFERENCE
-------------------------------------------------------
  Symbol  Meaning  Octal
  r--     read     4
  -w-     write    2
  --x     execute  1
  rw-     read/write   6
  rwx     full access  7

Examples:
  chmod 700 file → owner full, group/other none
  chmod 755 file → everyone read/execute, owner write
  chmod 644 file → owner write/read, others read only

-------------------------------------------------------
COMMON PATHS & CONFIG
-------------------------------------------------------
  /bin, /usr/bin, /usr/local/bin  → executables
  /etc                            → system config
  /home/<user>                    → user home
  /var/log                        → logs
  ~/.bashrc, ~/.profile           → user environment files
  ~/.ssh                          → SSH keys, configs

-------------------------------------------------------
END OF CHEAT SHEET
-------------------------------------------------------

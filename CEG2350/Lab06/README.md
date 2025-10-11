
## Lab 06

- Name: Alex Feyh
- Email: alexfeyh@proton.me

Instructions for this lab: https://pattonsgirl.github.io/CEG2350/Labs/Lab06/Instructions.html

## Part 1 - bash aliases

It is important that the following is added to or exists in the user's `.bashrc` file
```
# Alias definitions.
# You may want to put all your additions into a separate file like
# ~/.bash_aliases, instead of adding them here directly.
# See /usr/share/doc/bash-doc/examples in the bash-doc package.

if [ -f ~/.bash_aliases ]; then
    . ~/.bash_aliases
fi
```

What this section does:
> It firsts checks to see if the file bash_aliases exists. If it does exist it then executres the file using ./ 

Make sure you copied your `.bash_aliases` file to your GitHub repository.

## Part 2 - Dot Install Script

Nothing to write here, just a reminder to make sure your `dotinstall` script is in 
your GitHub repository

## Part 3 - Retrospective

1. It is a built in tool that allows your script to loop through the options your script has and store the values of the arguments provided for each option if applicable
2. The script got confusing when dealing with different copies of the .bash_aliases files. I'm still having problems with the remove function not working correctly. Correct input is also hard, for example, ensuring that an appropriate alias argument is accepted and written to the .bash_aliases file has proven difficult, so currently there is no method to validate if a user input will break the alias function
3. The ability to verify that an alias that a user is trying to add will function as intended before appending the file.

## Part 4 - `dotinstall` Usage Guide
This is a script that allows a user to easily manage aliases that are used in the linux shell. The script focuses on the .bash_aliases file, which is responsible for shortcuts the user wants for more complicated tasks. Key features of this script include:
    - Having a master .bash_aliases file within the git repository which allows for easy copying/implementation onto other PC's
    - The ability to add or remove aliases with ease
    - No need to directly interface with text files

### `.bash_aliases`
The master .bash_aliases file contains a few basic alias entries:
```
# Shortcut to CEG directory
alias ceg="cd /home/ubuntu/ceg2350f25-terranova801"

# Cowsay piped command
alias cowquote='fortune | cowsay'

# Another cow with a dragon
alias mootivation='fortune | cowsay -f dragon-and-cow'

# One more cow for good measure 
alias lolcow='fortune -a | cowsay -f vader'

# Screensaver
alias relax='asciiquarium'

# Better cat commands 
alias kitty='lolcat -a -t'

# Launch dotinstall
alias dotinstall='./dotinstall'
```


### `dotinstall`
The script can be ran in shell using: 
```
./dotinstall -h
```
or 
```
bash dotinstall -h
```
This will display the help section, where a user can find details about the functions of the script:
```
Usage: dotinstall [-OPTION] [ARG]
  -s setup      - attempts to create a symbolic link .bash_aliases file to user's home directory
  -d disconnect - removes symbolic link
  -a append     - adds a new alias to .bash_aliases file
  -r remove     - removes an alias from .bash_aliases file
```
After .bash_aliases is setup:
```
dotinstall [-OPTION] [ARG]
```
Will run the script.

### Examples

#### Setting up a symbolic link

```
./dotinstall -s
```
Output:
    If you currently have no .bash_aliases file:
    ```
    No current alias in "Your_home_directory"
    *Sets up new alias*
    Alias created
    ```
    If you wish to override your current .bash_aliases file:
     
     ```
     .bash_aliases already exists in "Your_home_directory"
     Continue setup (y/n) | Enter "y" if you wish to override your current .bash_aliases file
     *Creates new symbolic link for .bash_aliases*
     New alias link established
     ```
   
By using the triple quotes, you can enclose a block of code
And code blocks look very professional


## Citations

To add citations, provide the site and a summary of what it assisted you with.  If generative AI was used, include which generative AI system was used and what prompt(s) you fed it.  Generative AI may not write your script for you, only assist with component and how to type questions.
[Advanced Bash-Scripting Guide](https://tldp.org/LDP/abs/html/)

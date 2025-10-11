
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


### `.bash_aliases`
Describe your script in plain English, nothing too technical.  Think about this as describing what you made over the dinner table.

### `dotinstall`

Describe your script in plain English, nothing too technical.  Think about this as describing what you made over the dinner table.

### Examples

```
By using the triple quotes, you can enclose a block of code
And code blocks look very professional
```

## Citations

To add citations, provide the site and a summary of what it assisted you with.  If generative AI was used, include which generative AI system was used and what prompt(s) you fed it.  Generative AI may not write your script for you, only assist with component and how to type questions.


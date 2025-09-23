#!/usr/bin/bash

b=0
userValue=$1 ##assigns inputted command line value
echo "The value provided by user is $userValue"




function while_iteration {
while [[ b -lt userValue ]]
do 
	echo "The current iteration number is $b"
	((b++))
done
}

while_iteration ##calls the function above !!! must be below the function in order to properly run

##program made 9232025

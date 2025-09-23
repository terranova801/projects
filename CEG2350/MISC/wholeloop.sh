#!/usr/bin/bash

function loop_func () {
	i=$1
	while [ "$i" -lt 5 ]
	do
	echo "Inside loop, executing mission $1 "
	echo "Iteration $i"
	i=$((i+1))
	done
}

loop_func





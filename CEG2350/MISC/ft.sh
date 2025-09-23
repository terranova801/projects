#!/usr/bin/bash
action=$1
string2=$2
tasklocation="$HOME/.finance"


case $action in

add)
if  [[ -z "$string2" ]]; then 
echo "Input record description:"
read string2
fi

echo $string2 >> $tasklocation
echo "The record has been added"
;;

remove)
echo -n "remove"
;;

view)
echo -n "view"
;;

clear)
echo -n "clear"
;;

help)
echo -n "help"
;;

*)
echo -n "Invalid"
;;

esac
echo " "











#!/usr/bin/bash
action=$1
string2=$2
tasklocation="$HOME/.finance"


case $action in

add)
if  [[ -z "$string2" ]]; then 
echo "Input record description to add:"
read string2
fi

echo $string2 >> $tasklocation
echo "The record has been added"
;;

remove)
if  [[ -z "$string2" ]]; then 
echo "Input record description to delete:"
read string2
fi

if grep -i $string2 "$tasklocation";
then
echo "Found string"
else
echo "String not found"
exit
fi

echo "The record has been deleted"

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











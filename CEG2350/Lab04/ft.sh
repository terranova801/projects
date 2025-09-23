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




##needs work not deleting strings 
remove)
if  [[ -z "$string2" ]]; then 
echo "Input record description to delete:"
read string2
fi

if grep -iq $string2 "$tasklocation";
then
toDel=grep $string2 "$tasklocation"

echo "Found string"
sed -i 's/ $toDel//' "$tasklocation"


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











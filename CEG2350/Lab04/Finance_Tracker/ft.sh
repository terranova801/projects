#!/usr/bin/bash

action=$1
string2=$2
tasklocation="$HOME/.finance"
scriptlocation="$(dirname "$0")" #citation 1


function show_help {
cat "$scriptlocation/finance_tracker_help.txt"
}




case $action in

add)
if  [[ -z "$string2" ]]; then 
echo "Input record description to add:"
read string2
fi

echo $string2 >> $tasklocation
echo "The record has been added"
;;




#Functional remove function
#Removes a record that matches users description

remove)
if  [[ -z "$string2" ]]; then 
echo "Input record description to delete:"
read string2
fi

if grep -iq "$string2" "$tasklocation";
then
##toDel=grep $string2 "$tasklocation"

echo "Found record to remove"
sed -i "/$string2/d" "$tasklocation" ##Needed to use double quotes for sed to actually remove line

else
echo "Record not found"
exit
fi

echo "The record has been deleted"

;;




##Prints out all records 

view)
function view_record {
echo -n "view"
cat $tasklocation
}

view_record

;;


##Deletes file with users records

clear)
echo "Are you sure you want to delete all records? (y/n)"
read choice

if [[ "$choice" == "yes" || "$choice" == "Yes" || "$choice" == "y" || "$choice" == "Y" ]]; then
rm "$tasklocation"
echo "Records cleared"
else
echo "Records not cleared"
fi

;;

help)
show_help
;;




*)
echo "Invalid"
echo "For help enter './ft.sh help'"
;;

esac
echo " "











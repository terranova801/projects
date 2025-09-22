#!/bin/bash
action=$1

case $action in

add)
echo  -n "add"
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

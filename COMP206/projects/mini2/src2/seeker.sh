#!/bin/bash

#initialise an array and store all the command line arguments
arr=()
arr=$@

#variables

checkPattern=0
checkC=0
checkA=0
checkDir=0
pattern=""

for var in ${arr[@]}
do
	if [ "$var" == "-c" ] ; then
		checkC=1
	elif [ "$var" == "-a" ] ; then
		checkA=1
	elif [ -d "$var" ] ; then
		checkDir=1
	else
	    checkPattern=1
	fi	
done

#verify argument was found
if [ $checkPattern == 0 ] ; then
     echo -e "Error missing the pattern argument.\nUsage $0 [-c] [-a] pattern [path]"
fi

#check if a directory exist
for var in ${arr[@]}
do
	#verify directory exist
	if [ $checkDir == 0 ] && [[ "$var" == /* ]] ; then
		echo "Error "$var" is not a valid directory"
	elif [[ $var != -* ]] && [[ $var == /* ]] ; then
		pattern=$var
	fi
done

#if argument not found
if [ $checkDir == 1 ] && ([ "$#" == 3 ] && [ "$2" != "-a" ] && [ "$(ls "$3" | grep "$2")" == "" ]) ; then
	echo "Unable to locate any files that has pattern $2 in its name in $3."

#check if a and c are not pass and print one occurence of file
elif [ $checkDir == 1 ] && [ $1 != "-a" ] && [ $1 != "-c" ]; then
	file=$(ls "$2" | grep -m 1 "$1")
	echo "$2/$file"

#check if a is pass and print all files with the same pattern
elif [ $checkDir == 1 ] && [ $1 == "-a" ] && [ $checkPattern == 1 ] ; then
	list=$(ls "$3" | grep "$2")
	for var in $list
	do 
		echo "$3/"$var"";
	done
#check if c is pass and a not pass and display content of one occurence of file
elif [ $checkDir == 1 ] && [ $# == 3 ] && [ $1 == "-c" ] && [ $2 != "-a" ] ; then
	file="$(ls "$3" | grep -m 1 "$2")"
	echo "==== Contents of: $3/$file ==="
	cat $3/$file

#check if directory is not pass and display content of file in current directory
elif [ $# == 3 ] && [ $1 == "-c" ] && [ $2 == "-a" ] && [ $checkPattern == 1 ] ; then
        list=$(ls "$PWD" | grep "$3")
	
  	for var in $list
       	do
                echo "=== Contents of: $PWD/$var ==="
                cat $PWD/$var
     	done

#check if all arguments occur and display all contents of every file 
elif [ $checkPattern == 1 ] && [ $checkDir == 1 ] && [ $# == 4 ] ;then
	list=$(ls "$4" | grep "$3")
	for var in $list
	do 
		echo "=== Contents of : $4/$i ==="
		cat $4/$var
	done

fi

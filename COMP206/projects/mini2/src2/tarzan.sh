#!/bin/bash

#if the user does not pass in two arguments
if [ $# != 2 ] ; then
	echo "Usage $0 filename tarfile"
	exit

#if the tar file is found
elif [ ! -f "$2" ]
then
	echo "Error cannot find tar file $2"

#otherwise file is found
else
	file=$(tar -tvf $2 | grep -i "$1")

	#check if the file does exist or not
	if [ "$file" == "" ] ; then
		echo "$1 does not exist in $2"
	else
		echo "$1 exists in $2"
	fi	
fi


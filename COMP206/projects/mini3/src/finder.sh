#!/bin/bash

#check if a3config exists
#a3config="/home/2020/sceesa2/mini3/a3config"

#source a3config 

if [ -e a3config ] ; then
	source a3config
else
	echo "Error cannot find a3config"
	exit 1
fi

#check if dirname or extension is set
if [[ "$DIRNAME" &&  "$EXTENSION" ]] ; then
	 #check if directory exits
	if [[ ! -d $DIRNAME ]] ; then
		echo "Error directory $DIRNAME does not exit"
		exit 3
	else
		list=`ls -d $DIRNAME/*.${EXTENSION}* 2>/dev/null`
		if [[ $list == "" ]] ; then
			echo "Unable to locate any files with extension msg in $DIRNAME"
		fi

		for file in $list ; do
			if [[ -f $file ]] ; then  	#if file exist
				if [[ $SHOW == "true" ]] ; then	#file exist and show set to true
					echo $file
					cat $file		#display details of the file
				else
					echo $file		#otherwise, just display the file name
				fi
			fi
		done	
       	fi
else
	echo "Error DIRNAME and EXTENSION must be set "
	exit 2
fi


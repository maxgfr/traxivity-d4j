#!/bin/bash

path="mobile/src/main/res/raw/"

if [ "$#" -ne 2 ]; then
    echo "Illegal number of parameters"
	exit
fi

sed 's/^ *//' "$path$1" | cut -d "," -f2- > "$path$2" # Delete the first column

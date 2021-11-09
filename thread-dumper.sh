#!/bin/bash -xe

while true; do 
	sleep 10
	echo "Dumping Threads"
	jstack $(pgrep -n java)
done
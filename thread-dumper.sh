#!/bin/bash -e

while true; do 
	sleep 10
	jstack $(pgrep -n java) > /dumps/thread-dump-$(date --iso-8601=seconds).thread
done
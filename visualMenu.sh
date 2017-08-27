#!/bin/bash

HEIGHT=12
WIDTH=50
CHOICE_HEIGHT=10
BACKTITLE="MICROSERVICE REMOTE DEBUG EXAMPLE"
TITLE="Grizzly Image"
MENU="Choose one of the following options:"

OPTIONS=(1 "Build"
	 2 "Start"
         3 "Delete"
         4 "Delete All")

CHOICE=$(dialog --clear \
                --backtitle "$BACKTITLE" \
                --title "$TITLE" \
                --menu "$MENU" \
                $HEIGHT $WIDTH $CHOICE_HEIGHT \
                "${OPTIONS[@]}" \
                2>&1 >/dev/tty)

clear
case $CHOICE in
	1)
	    mvn clean package docker:build
	    read -p "Press enter to continue"
	    clear
	    ;;
	2)
	    docker run -it -p 8080:8080 -p 8787:8787 engcpp/grizzly:latest
	    break
	    ;;
	3)
	    docker rmi engcpp/grizzly:latest -f
	    read -p "Press enter to continue"
	    clear
	    ;;
	4)
	    docker images -q |xargs docker rmi -f
	    read -p "Press enter to continue"
	    clear
	    ;;
esac

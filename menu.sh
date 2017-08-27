#!/bin/bash
PS3='Docker Image ( Select an option ): '
options=("build" "delete" "delete all" "start" "exit")
select opt in "${options[@]}"
do
    case $opt in
        "build")
	    mvn clean package docker:build
	    read -p "Press enter to continue"
	    clear
            ;;
        "delete")
	    docker rmi engcpp/grizzly:latest -f
	    read -p "Press enter to continue"
	    clear
            ;;
        "delete all")
	    docker images -q |xargs docker rmi -f
	    read -p "Press enter to continue"
	    clear
            ;;
        "start")
	    docker run --rm -it -p 8080:8080 -p 8787:8787 engcpp/grizzly:latest
            break
            ;;
        "exit")
	    clear
            break
            ;;
         *) 
	    echo "invalid option"
	    ;;
    esac

    clear
done

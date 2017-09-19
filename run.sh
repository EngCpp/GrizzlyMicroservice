#!/bin/bash

port=9180
services=("segunda" "terca" "quarta" "quinta" "sexta" "sabado" "doming")

createConfigFile(){
  echo "Creating config files"

  for((idx=0; idx<${#services[@]}; idx++))
  do
     srvPort=$(($port+$idx))
     echo $srvPort' - '${services[$idx]}
  done
}

startMicroservices(){
  createConfigFile
  echo "Starting microservices"
  
  
}


PS3='IQConnect Dev: '
options=("start" "stop" "update" "remove imgs" "exit")
select opt in "${options[@]}"
do
    case $opt in
        "start")
            clear	    
	    startMicroservices
	    read -p "Press enter to continue"
	    clear
            ;;
        "stop")
            clear
	    killall screen
	    read -p "Press enter to continue"
	    clear
            ;;
        "update")
            clear
	    read -p "Press enter to continue"
	    clear
            ;;
        "remove imgs")
            clear
	    docker images -q |xargs docker rmi -f
	    read -p "Press enter to continue"
	    clear
            ;;
        "exit")
	    clear
	    echo "bye bye ;)"
            break
            ;;
         *) 	    
	    echo "Invalid option"
	    ;;
    esac

    clear
done

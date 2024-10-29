#!/bin/bash

while true; do
    echo "Menu:"
    echo "1. LS Command"
    echo "2. Status Of Process"
    echo "3. Show Date and Time"
    echo "4. Users logged in"
    echo "5. Quit"
    echo -n "Please enter your choice [1-5]: "
    read choice

    case $choice in
        1)
            echo "LS Command:"
            ls
            ;;
        2)
            echo "Status Of Process:"
            ps -aux
            ;;
        3)
            echo "Show Date and Time:"
            date
            ;;
        4)
            echo "Users logged in:"
            who
            ;;
        5)
            echo "EXIT"
            break
            ;;
        *)
            echo "Invalid choice"
            ;;
    esac

    echo ""  
done


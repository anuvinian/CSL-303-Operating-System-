#!/bin/bash


echo "Enter the first number: "
read a
echo "Enter the second number: "
read b
echo "Enter the third number: "
read c


if [ $a -gt $b ] && [ $a -gt $c ]; then
    echo "$a is the largest number"
elif [ $b -gt $a ] && [ $b -gt $c ]; then
    echo "$b is the largest number"
else
    echo "$c is the largest number"
fi


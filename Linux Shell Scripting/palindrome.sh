#!/bin/bash
echo "Enter a number:"
read num

reverse=0
temp=$num

while [ $temp -gt 0 ]; do
    remainder=$((temp % 10))
    reverse=$((reverse * 10 + remainder))
    temp=$((temp / 10))
done

if [ $num -eq $reverse ]; then
    echo "$num is a palindrome."
else
    echo "$num is not a palindrome."
fi

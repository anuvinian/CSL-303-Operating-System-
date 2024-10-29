#!/bin/bash

# Input number of elements
echo "Enter the number of elements: "
read N

# Initialize sum to 0
sum=0

# Input N numbers separately
for (( i=1; i<=N; i++ ))
do
    echo "Enter number $i: "
    read num
    sum=$((sum + num))
done

# Calculate average using awk for floating-point division
average=$(awk "BEGIN {print $sum/$N}")

# Output the average
echo "The average of the entered numbers is: $average"

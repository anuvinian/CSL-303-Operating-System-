#!/bin/bash

# Fibonacci series up to N terms
echo "Enter the number of terms: "
read N

# Initialize first two terms
a=0
b=1

echo "The Fibonacci series is:"

# Print Fibonacci series
for (( i=0; i<N; i++ ))
do
    echo -n "$a "
    next=$((a + b))
    a=$b
    b=$next
done

echo

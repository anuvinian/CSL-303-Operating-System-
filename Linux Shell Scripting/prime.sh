#!/bin/bash
echo "Enter a number:"
read num


if ! [[ "$num" =~ ^[0-9]+$ ]]; then
    echo "Invalid input. Please enter a valid number."
    exit 1
fi

if [ "$num" -le 1 ]; then
    echo "$num is not a prime number."
    exit 1
fi

for ((i = 2; i * i <= num; i++)); do
    if [ $((num % i)) -eq 0 ]; then
        echo "$num is not a prime number."
        exit 1
    fi
done

echo "$num is a prime number."

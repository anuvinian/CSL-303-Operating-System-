#!/bin/bash

# Prompt the user to enter the base salary
read -p "Enter the Base Salary: " base_salary

# Calculate HRA and DA as percentages of the base salary (multiplied by 100 to keep integers)
hra=$((10 * base_salary / 100))
da=$((3 * base_salary / 100))

# Calculate the gross salary
gross_salary=$((hra + base_salary + da))

# Print the gross salary
echo "The gross salary is: $gross_salary"

chmod +x gross_salary.sh

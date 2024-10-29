factorial(){
	local num=$1
	local result=1
	while [ $num -gt 1 ]; do
		result=$((result*num))
		num=$((num-1))
	done
	echo $result
}
read -p  "Enter a Number :" number
echo "Factorial of $number is $(factorial $number)"
chmod +x factorial.sh


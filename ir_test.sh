pass_num=0
test_num=0

sysy_filename="./src/test/resources/functional/21_if_test2.sy"

((test_num++))
echo "${test_num}: ${sysy_filename}"
input_file="${sysy_filename%.sy}.in"

rm ans out answer.txt output.txt out_without_sylib.ll

echo "#include \"./src/test/resources/sylib.c\"" > sample.c
cat ${sysy_filename} >> sample.c

clang sample.c -w -o ans
if [ -f $input_file ]; then
    ./ans < $input_file > answer.txt
else
    ./ans > answer.txt
fi
echo $? >> answer.txt

java -Dfile.encoding=UTF-8 -classpath ./target/classes:./lib/antlr4-runtime-4.9.1.jar Main ${sysy_filename} out_without_sylib.ll
clang out_without_sylib.ll ./src/test/resources/sylib.c -w -o out
if [ -f $input_file ]; then
    timeout 60s ./out < $input_file > output.txt
else
    timeout 60s ./out > output.txt
fi
echo $? >> output.txt

diff answer.txt output.txt > /dev/null
if [ $? == 0 ]; then
    ((pass_num++))
    echo -e "\e[32mPass"
else
    echo "Wrong return value in $sysy_filename"
fi
if [[ "$1" == "d" ]]; then
    read
fi
echo -e "\e[0m"

echo "Pass cases ($pass_num/$test_num)"
rm ans out answer.txt output.txt out_without_sylib.ll sample.c
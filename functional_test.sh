# this test only run in risc-v machine
# if can replace gcc -> riscv64-unknown-elf-gcc, and run this test in riscv-qemu

pass_num=0
test_num=0

testcases=$(find ./src/test/resources/functional -name "*.sy" | sort)
for sysy_filename in ${testcases}
do
    ((test_num++))
    echo "${test_num}: ${sysy_filename}"
    input_file="${sysy_filename%.sy}.in"

    rm ans out answer.txt output.txt out_without_sylib.ll

    # standard result (output + return value)
    echo "#include \"./src/test/resources/sylib.c\"" > sample.c
    cat ${sysy_filename} >> sample.c

    clang sample.c -w -o ans
    if [ -f $input_file ]; then
        ./ans < $input_file > answer.txt
    else
        ./ans > answer.txt
    fi
    echo $? >> answer.txt

    java -Dfile.encoding=UTF-8 -classpath ./target/classes:./lib/antlr-4.12.0-complete.jar Main ${sysy_filename} out_without_sylib.ll test.s
    riscv64-unknown-elf-gcc test.s ./src/test/resources/sylib.c -o out
    if [ -f $input_file ]; then
        timeout 60s qemu-riscv64 out < $input_file > output.txt
    else
        timeout 60s qemu-riscv64 out > output.txt
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
done

echo "Pass cases ($pass_num/$test_num)"
rm ans out answer.txt output.txt out_without_sylib.ll sample.c
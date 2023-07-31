test_num=0

testcases=$(find ./src/test/resources/functional -name "*.sy" | sort)
for sysy_filename in ${testcases}
do
    ((test_num++))
    echo "${test_num}: ${sysy_filename}"
    output_file="./src/test/gen/${test_num}"
    rm ans out answer.txt output.txt out_without_sylib.ll

    java -Dfile.encoding=UTF-8 -classpath ./target/classes:./lib/antlr-4.12.0-complete.jar Main ${sysy_filename} out_without_sylib.ll test.s
    riscv64-unknown-elf-gcc test.s ./src/test/resources/sylib.c -o ${output_file}
done
rm ans out answer.txt output.txt out_without_sylib.ll sample.c
clang test.c -o ans
./ans
echo $? > sample.ans
java -Dfile.encoding=UTF-8 -classpath ./target/classes:./lib/antlr4-runtime-4.9.1.jar Main test.c test.ll
llvm-as test.ll -o test.bc
lli test.bc
echo $? > sample.out
diff sample.ans sample.out > /dev/null
if [ $? == 0 ]; then
    echo "Pass test.c"
else
    echo "Wrong return value"
fi


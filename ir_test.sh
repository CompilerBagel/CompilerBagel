gcc test.sy -o ans
./ans
echo $? > sample.ans
java -Dfile.encoding=UTF-8 -classpath /home/nboxff/CompilerBagel/CompilerBagel/target/classes:/home/nboxff/.m2/repository/org/antlr/antlr4-runtime/4.9.1/antlr4-runtime-4.9.1.jar Main test.sy test.ll
llvm-as test.ll test.bc
lli test.bc
echo $? > sample.out
diff sample.ans sample.out > /dev/null
if [ $0 == 0 ]; then
    echo "Pass test.sy"
else
    echo "Wrong return value"
fi


pass_num=0
test_num=0

for c_file in $(find ./test_data -name "*.c")
do
  clang ${c_file} -o ans
  ./ans
  echo $? > sample.ans
  java -Dfile.encoding=UTF-8 -classpath ./target/classes:./lib/antlr4-runtime-4.9.1.jar Main ${c_file} test.ll
  llvm-as test.ll -o test.bc
  lli test.bc
  echo $? > sample.out
  diff sample.ans sample.out > /dev/null
  if [ $? == 0 ]; then
      ((pass_num++))
      echo "Pass $c_file"
  else
      echo "Wrong return value in $c_file"
  fi
  ((test_num++))
done
echo "Pass cases ($pass_num/$test_num)"

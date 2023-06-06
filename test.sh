
err_count=0
wrong_num=0

for sy_file in $(find ./test -name "*.sy")
do
  (java org.antlr.v4.gui.TestRig SysY program -tokens < ${sy_file} | grep line) >& output.txt
  err_count=`cat output.txt | wc -l`
  if [ ${err_count} -gt 0 ]
  then
      echo ${sy_file}
      ((wrong_num++))
  fi
done
echo ${wrong_num}

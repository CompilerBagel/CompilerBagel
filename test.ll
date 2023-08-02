; ModuleID = 'module'
source_filename = "module"

declare i32 @getint()
declare i32 @getch()
declare float @getfloat()
declare i32 @getarray(i32* %0)
declare i32 @getfarray(float* %0)
declare void @putint(i32 %0)
declare void @putch(i32 %0)
declare void @putarray(i32 %0, i32* %1)
declare void @putfloat(float %0)
declare void @putfarray(i32 %0, float* %1)
declare void @putf(i32* %0, ...)
declare void @before_main()
declare void @after_main()
declare void @_sysy_starttime(i32 %0)
declare void @_sysy_stoptime(i32 %0)

define i32 @main() {
mainEntry1:
  %a0 = alloca [2 x [3 x i32]], align 4
  %array1 = getelementptr [2 x [3 x i32]], [2 x [3 x i32]]* %a0, i32 0, i32 0
  store i32 1, [3 x i32]* %array1, align 4
  %array3 = getelementptr [2 x [3 x i32]], [2 x [3 x i32]]* %a0, i32 0, i32 0
  store i32 2, [3 x i32]* %array3, align 4
  %array5 = getelementptr [2 x [3 x i32]], [2 x [3 x i32]]* %a0, i32 0, i32 0
  store i32 3, [3 x i32]* %array5, align 4
  %array7 = getelementptr [2 x [3 x i32]], [2 x [3 x i32]]* %a0, i32 0, i32 1
  store i32 4, [3 x i32]* %array7, align 4
  %array9 = getelementptr [2 x [3 x i32]], [2 x [3 x i32]]* %a0, i32 0, i32 1
  store i32 5, [3 x i32]* %array9, align 4
  %array11 = getelementptr [2 x [3 x i32]], [2 x [3 x i32]]* %a0, i32 0, i32 1
  store i32 6, [3 x i32]* %array11, align 4
  %a13 = getelementptr [2 x [3 x i32]], [2 x [3 x i32]]* %a0, i32 0, i32 1
  %a14 = getelementptr [3 x i32], [3 x i32]* %a13, i32 0, i32 2
  %a15 = load i32, i32* %a14, align 4
  ret i32 %a15
  ret i32 0
}

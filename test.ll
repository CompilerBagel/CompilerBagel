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
  %a11 = alloca [10000 x i32], align 4
  %a12 = getelementptr [10000 x i32], [10000 x i32]* %a11, i32 0, i32 1
  store i32 2, i32* %a12, align 4
  %a14 = getelementptr [10000 x i32], [10000 x i32]* %a11, i32 0, i32 1
  %a15 = load i32, i32* %a14, align 4
  ret i32 %a15
  ret i32 0
}

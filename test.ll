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

@arr0 = global [2 x [2 x i32]] [[2 x i32] [i32 1, i32 2], [2 x i32] [i32 3, i32 4]]
define i32 @main() {
mainEntry1:
  %arr11 = getelementptr [2 x [2 x i32]], [2 x [2 x i32]]* @arr0, i32 0, i32 1
  %arr12 = getelementptr [2 x i32], [2 x i32]* %arr11, i32 0, i32 0
  %arr13 = load i32, i32* %arr12, align 4
  ret i32 %arr13
  ret i32 0
}

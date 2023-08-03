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

@N0 = global i32 -1
@arr1 = global [6 x i32]  [i32 1, i32 2, i32 33, i32 4, i32 5, i32 6]
define i32 @main() {
mainEntry1:
  %arr11 = getelementptr [6 x i32], [6 x i32]* @arr1, i32 0, i32 0
  %arr12 = load i32, i32* %arr11, align 4
  %arr13 = getelementptr [6 x i32], [6 x i32]* @arr1, i32 0, i32 1
  %arr14 = load i32, i32* %arr13, align 4
  %add_15 = add i32 %arr12, %arr14
  ret i32 %add_15
  ret i32 0
}

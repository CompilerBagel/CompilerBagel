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
  %b12 = alloca i32, align 4
  store i32 1, i32* %b12, align 4
  %c14 = alloca i32, align 4
  store i32 2, i32* %c14, align 4
  %d16 = alloca [10000 x i32], align 4
  ret i32 0
  ret i32 0
}

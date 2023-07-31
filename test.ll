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
  %a0 = alloca i32, align 4
  store i32 2, i32* %a0, align 4
  %b2 = alloca i32, align 4
  %a3 = load i32, i32* %a0, align 4
  %add_4 = add i32 %a3, 1
  store i32 %add_4, i32* %b2, align 4
  %c6 = alloca i32, align 4
  %b7 = load i32, i32* %b2, align 4
  %add_8 = add i32 %b7, 2
  store i32 %add_8, i32* %c6, align 4
  %d10 = alloca i32, align 4
  %c11 = load i32, i32* %c6, align 4
  %add_12 = add i32 %c11, 3
  store i32 %add_12, i32* %d10, align 4
  ret i32 0
  ret i32 0
}

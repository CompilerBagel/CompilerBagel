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
  %a11 = alloca i32, align 4
  store i32 2, i32* %a11, align 4
  %b13 = alloca i32, align 4
  %a14 = load i32, i32* %a11, align 4
  %add_15 = add i32 %a14, 1
  store i32 %add_15, i32* %b13, align 4
  %c17 = alloca i32, align 4
  %b18 = load i32, i32* %b13, align 4
  %add_19 = add i32 %b18, 2
  store i32 %add_19, i32* %c17, align 4
  %d21 = alloca i32, align 4
  %c22 = load i32, i32* %c17, align 4
  %add_23 = add i32 %c22, 3
  store i32 %add_23, i32* %d21, align 4
  ret i32 0
  ret i32 0
}

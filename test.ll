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

define i32 @f(i32 %f00, i32 %f11) {
fEntry1:
  %x2 = alloca i32, align 4
  store i32 %f00, i32* %x2, align 4
  %y4 = alloca i32, align 4
  store i32 %f11, i32* %y4, align 4
  %x6 = load i32, i32* %x2, align 4
  %y7 = load i32, i32* %y4, align 4
  %add_8 = add i32 %x6, %y7
  ret i32 %add_8
  ret i32 0
}
define i32 @main() {
mainEntry2:
  %a9 = alloca i32, align 4
  store i32 1, i32* %a9, align 4
  %b11 = alloca i32, align 4
  store i32 2, i32* %b11, align 4
  %c13 = alloca i32, align 4
  store i32 3, i32* %c13, align 4
  %d15 = alloca i32, align 4
  store i32 0, i32* %d15, align 4
  %c17 = load i32, i32* %c13, align 4
  %a18 = load i32, i32* %a9, align 4
  %b19 = load i32, i32* %b11, align 4
  %f20 = call i32 @f(i32 %a18, i32 %b19)
  %add_21 = add i32 %c17, %f20
  store i32 %add_21, i32* %d15, align 4
  %d23 = load i32, i32* %d15, align 4
  ret i32 %d23
  ret i32 0
}

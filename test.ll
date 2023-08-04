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

define i32 @f(i32 %f011, i32 %f112) {
fEntry1:
  %x13 = alloca i32, align 4
  store i32 %f011, i32* %x13, align 4
  %y15 = alloca i32, align 4
  store i32 %f112, i32* %y15, align 4
  %x17 = load i32, i32* %x13, align 4
  %y18 = load i32, i32* %y15, align 4
  %add_19 = add i32 %x17, %y18
  ret i32 %add_19
  ret i32 0
}
define i32 @main() {
mainEntry2:
  %x20 = alloca i32, align 4
  store i32 3, i32* %x20, align 4
  %y22 = alloca i32, align 4
  store i32 4, i32* %y22, align 4
  %x24 = load i32, i32* %x20, align 4
  %y25 = load i32, i32* %y22, align 4
  %f26 = call i32 @f(i32 %x24, i32 %y25)
  %add_27 = add i32 2, %f26
  ret i32 %add_27
  ret i32 0
}

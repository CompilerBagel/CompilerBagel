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

define i32 @f(i32 %f011) {
fEntry1:
  %a12 = alloca i32, align 4
  store i32 %f011, i32* %a12, align 4
  %a14 = load i32, i32* %a12, align 4
  ret i32 %a14
  ret i32 0
}
define i32 @main() {
mainEntry2:
  %n15 = alloca i32, align 4
  store i32 1, i32* %n15, align 4
  %n17 = load i32, i32* %n15, align 4
  %f18 = call i32 @f(i32 %n17)
  ret i32 0
  ret i32 0
}

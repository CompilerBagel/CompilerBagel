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

define i32 @f(i32 %f00) {
fEntry1:
  %f1 = alloca i32, align 4
  store i32 %f00, i32* %f1, align 4
  %f3 = load i32, i32* %f1, align 4
  %mul_4 = mul i32 %f3, 2
  ret i32 %mul_4
  ret i32 0
}
define i32 @main() {
mainEntry2:
  %f5 = call i32 @f(i32 10)
  ret i32 %f5
  ret i32 0
}

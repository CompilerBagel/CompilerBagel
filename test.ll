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

@a0 = global i32 1
define i32 @main() {
mainEntry1:
  %b11 = alloca i32, align 4
  %a12 = load i32, i32* @a0, align 4
  %mul_13 = mul i32 %a12, 2
  store i32 %mul_13, i32* %b11, align 4
  %b15 = load i32, i32* %b11, align 4
  ret i32 %b15
  ret i32 0
}

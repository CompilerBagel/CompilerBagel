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
  store i32 1, i32* %a0, align 4
  %b2 = alloca i32, align 4
  store i32 2, i32* %b2, align 4
  %c4 = alloca i32, align 4
  %a5 = load i32, i32* %a0, align 4
  %b6 = load i32, i32* %b2, align 4
  %b7 = load i32, i32* %b2, align 4
  %mul_8 = mul i32 %b6, %b7
  %add_9 = add i32 %a5, %mul_8
  store i32 %add_9, i32* %c4, align 4
  %d11 = alloca i32, align 4
  %a12 = load i32, i32* %a0, align 4
  %b13 = load i32, i32* %b2, align 4
  %add_14 = add i32 %a12, %b13
  %c15 = load i32, i32* %c4, align 4
  %add_16 = add i32 %add_14, %c15
  store i32 %add_16, i32* %d11, align 4
  %d18 = load i32, i32* %d11, align 4
  ret i32 %d18
  ret i32 0
}

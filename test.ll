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
  store i32 1, i32* %a11, align 4
  %b13 = alloca i32, align 4
  store i32 2, i32* %b13, align 4
  %c15 = alloca i32, align 4
  %b16 = load i32, i32* %b13, align 4
  %b17 = load i32, i32* %b13, align 4
  %mul_18 = mul i32 %b16, %b17
  %a19 = load i32, i32* %a11, align 4
  %add_20 = add i32 %mul_18, %a19
  store i32 %add_20, i32* %c15, align 4
  %c22 = load i32, i32* %c15, align 4
  ret i32 %c22
  ret i32 0
}

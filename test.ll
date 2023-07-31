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

@a0 = global i32 10
define i32 @main() {
mainEntry1:
  %b0 = alloca i32, align 4
  %a1 = load i32, i32* @a0, align 4
  %sub_2 = sub i32 %a1, 3
  store i32 %sub_2, i32* %b0, align 4
  %a4 = load i32, i32* @a0, align 4
  ret i32 %a4
  ret i32 0
}

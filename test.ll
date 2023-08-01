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
  %e0 = alloca i32, align 4
  store i32 1, i32* %e0, align 4
  %e2 = load i32, i32* %e0, align 4
  %sub_3 = sub i32 %e2, 1
  store i32 %sub_3, i32* %e0, align 4
  %e5 = load i32, i32* %e0, align 4
  ret i32 %e5
  ret i32 0
}

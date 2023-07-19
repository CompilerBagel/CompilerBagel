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

@a0 = global i32 0
define i32 @func(i32 %func011) {
funcEntry1:
  %p12 = alloca i32, align 4
  store i32 %func011, i32* %p12, align 4
  %p14 = load i32, i32* %p12, align 4
  %sub_15 = sub i32 %p14, 1
  store i32 %sub_15, i32* %p12, align 4
  %p17 = load i32, i32* %p12, align 4
  ret i32 %p17
}
define i32 @main() {
mainEntry2:
  %b18 = alloca i32, align 4
  store i32 0, i32* %b18, align 4
  store i32 10, i32* @a0, align 4
  %a21 = load i32, i32* @a0, align 4
  %func22 = call i32 @func(i32 %a21)
  store i32 %func22, i32* %b18, align 4
  %b24 = load i32, i32* %b18, align 4
  ret i32 %b24
}

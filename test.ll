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
  %b13 = alloca float, align 4
  store float 2.0, float* %b13, align 4
  %c15 = alloca i32, align 4
  %a16 = load i32, i32* %a11, align 4
  %b17 = load float, float* %b13, align 4
  %conv18 = sitofp i32 %a16 to float
  %add_19 = add float %conv18, %b17
  %conv20 = fptosi float %add_19 to i32
  store i32* %conv20, i32* %c15, align 4
  %a22 = load i32, i32* %a11, align 4
  ret i32 %a22
  ret i32 0
}

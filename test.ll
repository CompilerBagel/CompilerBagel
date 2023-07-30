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
  %a15 = load i32, i32* %a11, align 4
  %b16 = load i32, i32* %b13, align 4
  %icmp_LT17 = icmp slt i32 %a15, %b16
  %zext_18 = zext i1 %icmp_LT17 to i32
  %icmp_19 = icmp ne i32 %zext_18, 0
  br i1 %icmp_19, label %trueBlock2, label %falseBlock3
trueBlock2:
  store i32 3, i32* %a11, align 4
  br label %afterBlock4
falseBlock3:
  store i32 3, i32* %b13, align 4
  br label %afterBlock4
afterBlock4:
  ret i32 0
  ret i32 0
}

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

define i32 @ififElse() {
ififElseEntry1:
  %a0 = alloca i32, align 4
  store i32 0, i32* %a0, align 4
  store i32 5, i32* %a0, align 4
  %b3 = alloca i32, align 4
  store i32 0, i32* %b3, align 4
  store i32 10, i32* %b3, align 4
  %a6 = load i32, i32* %a0, align 4
  %icmp_EQ7 = icmp eq i32 %a6, 5
  br i1 %icmp_EQ7, label %trueBlock2, label %falseBlock3
trueBlock2:
  %b8 = load i32, i32* %b3, align 4
  %icmp_EQ9 = icmp eq i32 %b8, 10
  br i1 %icmp_EQ9, label %trueBlock5, label %falseBlock6
falseBlock3:
  br label %afterBlock4
afterBlock4:
  %a14 = load i32, i32* %a0, align 4
  ret i32 %a14
  ret i32 0
trueBlock5:
  store i32 25, i32* %a0, align 4
  br label %afterBlock7
falseBlock6:
  %a11 = load i32, i32* %a0, align 4
  %add_12 = add i32 %a11, 15
  store i32 %add_12, i32* %a0, align 4
  br label %afterBlock7
afterBlock7:
  br label %afterBlock4
}
define i32 @main() {
mainEntry8:
  %ififElse15 = call i32 @ififElse()
  ret i32 %ififElse15
  ret i32 0
}

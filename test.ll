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

define i32 @deepWhileBr(i32 %deepWhileBr00, i32 %deepWhileBr11) {
deepWhileBrEntry1:
  %a2 = alloca i32, align 4
  store i32 %deepWhileBr00, i32* %a2, align 4
  %b4 = alloca i32, align 4
  store i32 %deepWhileBr11, i32* %b4, align 4
  %c6 = alloca i32, align 4
  store i32 0, i32* %c6, align 4
  %a8 = load i32, i32* %a2, align 4
  %b9 = load i32, i32* %b4, align 4
  %add_10 = add i32 %a8, %b9
  store i32 %add_10, i32* %c6, align 4
  br label %condBlock2
condBlock2:
  %c12 = load i32, i32* %c6, align 4
  %icmp_LT13 = icmp slt i32 %c12, 75
  br i1 %icmp_LT13, label %bodyBlock3, label %afterBlock4
bodyBlock3:
  %d14 = alloca i32, align 4
  store i32 0, i32* %d14, align 4
  store i32 42, i32* %d14, align 4
  %c17 = load i32, i32* %c6, align 4
  %icmp_LT18 = icmp slt i32 %c17, 100
  br i1 %icmp_LT18, label %trueBlock5, label %falseBlock6
afterBlock4:
  %c34 = load i32, i32* %c6, align 4
  ret i32 %c34
  ret i32 0
trueBlock5:
  %c19 = load i32, i32* %c6, align 4
  %d20 = load i32, i32* %d14, align 4
  %add_21 = add i32 %c19, %d20
  store i32 %add_21, i32* %c6, align 4
  %c23 = load i32, i32* %c6, align 4
  %icmp_GT24 = icmp sgt i32 %c23, 99
  br i1 %icmp_GT24, label %trueBlock8, label %falseBlock9
falseBlock6:
  br label %afterBlock7
afterBlock7:
  br label %condBlock2
trueBlock8:
  %e25 = alloca i32, align 4
  store i32 0, i32* %e25, align 4
  %d27 = load i32, i32* %d14, align 4
  %mul_28 = mul i32 %d27, 2
  store i32 %mul_28, i32* %e25, align 4
  %icmp_EQ30 = icmp eq i32 1, 1
  br i1 %icmp_EQ30, label %trueBlock11, label %falseBlock12
falseBlock9:
  br label %afterBlock10
afterBlock10:
  br label %afterBlock7
trueBlock11:
  %e31 = load i32, i32* %e25, align 4
  %mul_32 = mul i32 %e31, 2
  store i32 %mul_32, i32* %c6, align 4
  br label %afterBlock13
falseBlock12:
  br label %afterBlock13
afterBlock13:
  br label %afterBlock10
}
define i32 @main() {
mainEntry14:
  %p35 = alloca i32, align 4
  store i32 0, i32* %p35, align 4
  store i32 2, i32* %p35, align 4
  %p38 = load i32, i32* %p35, align 4
  %p39 = load i32, i32* %p35, align 4
  %deepWhileBr40 = call i32 @deepWhileBr(i32 %p38, i32 %p39)
  ret i32 %deepWhileBr40
  ret i32 0
}

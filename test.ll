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

define i32 @deepWhileBr(i32 %deepWhileBr011, i32 %deepWhileBr112) {
deepWhileBrEntry1:
  %a13 = alloca i32, align 4
  store i32 %deepWhileBr011, i32* %a13, align 4
  %b15 = alloca i32, align 4
  store i32 %deepWhileBr112, i32* %b15, align 4
  %c17 = alloca i32, align 4
  store i32 0, i32* %c17, align 4
  %a19 = load i32, i32* %a13, align 4
  %b20 = load i32, i32* %b15, align 4
  %add_21 = add i32 %a19, %b20
  store i32 %add_21, i32* %c17, align 4
  br label %condBlock2
condBlock2:
  %c23 = load i32, i32* %c17, align 4
  %icmp_LT24 = icmp slt i32 %c23, 75
  %tmp_25 = zext i1 %icmp_LT24 to i32
  %icmp_26 = icmp ne i32 %tmp_25, 0
  br i1 %icmp_26, label %bodyBlock3, label %afterBlock4
bodyBlock3:
  %d27 = alloca i32, align 4
  store i32 0, i32* %d27, align 4
  store i32 42, i32* %d27, align 4
  %c30 = load i32, i32* %c17, align 4
  %icmp_LT31 = icmp slt i32 %c30, 100
  %tmp_32 = zext i1 %icmp_LT31 to i32
  %icmp_33 = icmp ne i32 %tmp_32, 0
  br i1 %icmp_33, label %trueBlock5, label %falseBlock6
afterBlock4:
  %c53 = load i32, i32* %c17, align 4
  ret i32 %c53
  ret i32 0
trueBlock5:
  %c34 = load i32, i32* %c17, align 4
  %d35 = load i32, i32* %d27, align 4
  %add_36 = add i32 %c34, %d35
  store i32 %add_36, i32* %c17, align 4
  %c38 = load i32, i32* %c17, align 4
  %icmp_GT39 = icmp sgt i32 %c38, 99
  %tmp_40 = zext i1 %icmp_GT39 to i32
  %icmp_41 = icmp ne i32 %tmp_40, 0
  br i1 %icmp_41, label %trueBlock8, label %falseBlock9
falseBlock6:
  br label %afterBlock7
afterBlock7:
  br label %condBlock2
trueBlock8:
  %e42 = alloca i32, align 4
  store i32 0, i32* %e42, align 4
  %d44 = load i32, i32* %d27, align 4
  %mul_45 = mul i32 %d44, 2
  store i32 %mul_45, i32* %e42, align 4
  %icmp_EQ47 = icmp eq i32 1, 1
  %tmp_48 = zext i1 %icmp_EQ47 to i32
  %icmp_49 = icmp ne i32 %tmp_48, 0
  br i1 %icmp_49, label %trueBlock11, label %falseBlock12
falseBlock9:
  br label %afterBlock10
afterBlock10:
  br label %afterBlock7
trueBlock11:
  %e50 = load i32, i32* %e42, align 4
  %mul_51 = mul i32 %e50, 2
  store i32 %mul_51, i32* %c17, align 4
  br label %afterBlock13
falseBlock12:
  br label %afterBlock13
afterBlock13:
  br label %afterBlock10
}
define i32 @main() {
mainEntry14:
  %p54 = alloca i32, align 4
  store i32 0, i32* %p54, align 4
  store i32 2, i32* %p54, align 4
  %p57 = load i32, i32* %p54, align 4
  %p58 = load i32, i32* %p54, align 4
  %deepWhileBr_call59 = call i32 @deepWhileBr(i32 %p57, i32 %p58)
  ret i32 %deepWhileBr_call59
  ret i32 0
}

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
  store i32 0, i32* %a11, align 4
  %b13 = alloca i32, align 4
  store i32 0, i32* %b13, align 4
  %c15 = alloca i32, align 4
  store i32 0, i32* %c15, align 4
  %d17 = alloca i32, align 4
  store i32 0, i32* %d17, align 4
  %result19 = alloca i32, align 4
  store i32 0, i32* %result19, align 4
  store i32 5, i32* %a11, align 4
  store i32 5, i32* %b13, align 4
  store i32 1, i32* %c15, align 4
  store i32 -2, i32* %d17, align 4
  store i32 2, i32* %result19, align 4
  %d26 = load i32, i32* %d17, align 4
  %mul_27 = mul i32 %d26, 1
  %sdiv_28 = sdiv i32 %mul_27, 2
  %icmp_LT29 = icmp slt i32 %sdiv_28, 0
  %a30 = load i32, i32* %a11, align 4
  %b31 = load i32, i32* %b13, align 4
  %sub_32 = sub i32 %a30, %b31
  %icmp_NE33 = icmp ne i32 %sub_32, 0
  %c34 = load i32, i32* %c15, align 4
  %add_35 = add i32 %c34, 3
  %sdiv_36 = sdiv i32 %add_35, 2
  %icmp_NE37 = icmp ne i32 %sdiv_36, 0
  %and_38 = and i1 %icmp_NE33, %icmp_NE37
  %or_39 = or i1 %icmp_LT29, %and_38
  %icmp_40 = icmp ne i1 %or_39, 0
  br i1 %icmp_40, label %trueBlock2, label %falseBlock3
trueBlock2:
  store i32 3, i32* %result19, align 4
  br label %afterBlock4
falseBlock3:
  br label %afterBlock4
afterBlock4:
  %d42 = load i32, i32* %d17, align 4
  %add_43 = add i32 %d42, 2
  %add_44 = add i32 %add_43, 67
  %icmp_LT45 = icmp slt i32 %add_44, 0
  %a46 = load i32, i32* %a11, align 4
  %b47 = load i32, i32* %b13, align 4
  %sub_48 = sub i32 %a46, %b47
  %icmp_NE49 = icmp ne i32 %sub_48, 0
  %c50 = load i32, i32* %c15, align 4
  %add_51 = add i32 %c50, 2
  %sdiv_52 = sdiv i32 %add_51, 2
  %icmp_NE53 = icmp ne i32 %sdiv_52, 0
  %and_54 = and i1 %icmp_NE49, %icmp_NE53
  %or_55 = or i1 %icmp_LT45, %and_54
  %icmp_56 = icmp ne i1 %or_55, 0
  br i1 %icmp_56, label %trueBlock5, label %falseBlock6
trueBlock5:
  store i32 4, i32* %result19, align 4
  br label %afterBlock7
falseBlock6:
  br label %afterBlock7
afterBlock7:
  %result58 = load i32, i32* %result19, align 4
  ret i32 %result58
  ret i32 0
}

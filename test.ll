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
  %sum0 = alloca i32, align 4
  store i32 0, i32* %sum0, align 4
  %i2 = alloca i32, align 4
  store i32 0, i32* %i2, align 4
  br label %condBlock2
condBlock2:
  %i4 = load i32, i32* %i2, align 4
  %icmp_LT5 = icmp slt i32 %i4, 20
  br i1 %icmp_LT5, label %bodyBlock3, label %afterBlock4
bodyBlock3:
  %j6 = alloca i32, align 4
  store i32 0, i32* %j6, align 4
  br label %condBlock5
afterBlock4:
  %sum57 = load i32, i32* %sum0, align 4
  ret i32 %sum57
  ret i32 0
condBlock5:
  %j8 = load i32, i32* %j6, align 4
  %icmp_LT9 = icmp slt i32 %j8, 10
  br i1 %icmp_LT9, label %bodyBlock6, label %afterBlock7
bodyBlock6:
  %k10 = alloca i32, align 4
  store i32 0, i32* %k10, align 4
  br label %condBlock8
afterBlock7:
  %i54 = load i32, i32* %i2, align 4
  %add_55 = add i32 %i54, 1
  store i32 %add_55, i32* %i2, align 4
  br label %condBlock2
condBlock8:
  %k12 = load i32, i32* %k10, align 4
  %icmp_LT13 = icmp slt i32 %k12, 5
  br i1 %icmp_LT13, label %bodyBlock9, label %afterBlock10
bodyBlock9:
  %m14 = alloca i32, align 4
  store i32 0, i32* %m14, align 4
  br label %condBlock11
afterBlock10:
  %j48 = load i32, i32* %j6, align 4
  %add_49 = add i32 %j48, 1
  store i32 %add_49, i32* %j6, align 4
  br label %condBlock5
  %j51 = load i32, i32* %j6, align 4
  %add_52 = add i32 %j51, 1
  store i32 %add_52, i32* %j6, align 4
  br label %condBlock5
condBlock11:
  %m16 = load i32, i32* %m14, align 4
  %icmp_LT17 = icmp slt i32 %m16, 3
  br i1 %icmp_LT17, label %bodyBlock12, label %afterBlock13
bodyBlock12:
  %m18 = load i32, i32* %m14, align 4
  %add_19 = add i32 %m18, 1
  %icmp_GE20 = icmp sge i32 %add_19, 3
  br i1 %icmp_GE20, label %trueBlock14, label %falseBlock15
afterBlock13:
  br label %condBlock29
trueBlock14:
  %m21 = load i32, i32* %m14, align 4
  br i32 %m21, label %trueBlock17, label %falseBlock18
falseBlock15:
  br label %afterBlock16
afterBlock16:
  %n29 = alloca i32, align 4
  store i32 0, i32* %n29, align 4
  br label %condBlock26
trueBlock17:
  %m22 = load i32, i32* %m14, align 4
  %m23 = load i32, i32* %m14, align 4
  %icmp_24 = icmp ne i32 0, %m23
  %or_25 = or i32 %m22, %icmp_24
  br i32 %or_25, label %trueBlock20, label %falseBlock21
falseBlock18:
  br label %afterBlock19
afterBlock19:
  br label %afterBlock16
trueBlock20:
  %m26 = load i32, i32* %m14, align 4
  %sub_27 = sub i32 %m26, -1
  %icmp_GE28 = icmp sge i32 %sub_27, 3
  br i1 %icmp_GE28, label %trueBlock23, label %falseBlock24
falseBlock21:
  br label %afterBlock22
afterBlock22:
  br label %afterBlock19
trueBlock23:
  br label %afterBlock13
  br label %condBlock11
  br label %afterBlock25
falseBlock24:
  br label %afterBlock25
afterBlock25:
  br label %afterBlock22
condBlock26:
  %n31 = load i32, i32* %n29, align 4
  %icmp_LT32 = icmp slt i32 %n31, 2
  br i1 %icmp_LT32, label %bodyBlock27, label %afterBlock28
bodyBlock27:
  %n33 = load i32, i32* %n29, align 4
  %add_34 = add i32 %n33, 1
  store i32 %add_34, i32* %n29, align 4
  br label %condBlock26
  br label %afterBlock28
  %sum36 = load i32, i32* %sum0, align 4
  %add_37 = add i32 %sum36, 1
  store i32 %add_37, i32* %sum0, align 4
  br label %condBlock26
afterBlock28:
  %m39 = load i32, i32* %m14, align 4
  %add_40 = add i32 %m39, 1
  store i32 %add_40, i32* %m14, align 4
  %sum42 = load i32, i32* %sum0, align 4
  %add_43 = add i32 %sum42, 1
  store i32 %add_43, i32* %sum0, align 4
  br label %condBlock11
condBlock29:
  br i32 1, label %bodyBlock30, label %afterBlock31
bodyBlock30:
  br label %condBlock32
afterBlock31:
  %k45 = load i32, i32* %k10, align 4
  %add_46 = add i32 %k45, 1
  store i32 %add_46, i32* %k10, align 4
  br label %condBlock8
condBlock32:
  br i32 1, label %bodyBlock33, label %afterBlock34
bodyBlock33:
  br label %afterBlock34
  br label %condBlock32
afterBlock34:
  br label %afterBlock31
  br label %condBlock29
}

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

@g0 = global i32 0
@h1 = global i32 0
@f2 = global i32 0
@e3 = global i32 0
define i32 @EightWhile() {
EightWhileEntry1:
  %a0 = alloca i32, align 4
  store i32 0, i32* %a0, align 4
  store i32 5, i32* %a0, align 4
  %b3 = alloca i32, align 4
  store i32 0, i32* %b3, align 4
  %c5 = alloca i32, align 4
  store i32 0, i32* %c5, align 4
  store i32 6, i32* %b3, align 4
  store i32 7, i32* %c5, align 4
  %d9 = alloca i32, align 4
  store i32 0, i32* %d9, align 4
  store i32 10, i32* %d9, align 4
  br label %condBlock2
condBlock2:
  %a12 = load i32, i32* %a0, align 4
  %icmp_LT13 = icmp slt i32 %a12, 20
  %icmp_14 = icmp ne i1 %icmp_LT13, 0
  br i1 %icmp_14, label %bodyBlock3, label %afterBlock4
bodyBlock3:
  %a15 = load i32, i32* %a0, align 4
  %add_16 = add i32 %a15, 3
  store i32 %add_16, i32* %a0, align 4
  br label %condBlock5
afterBlock4:
  %a81 = load i32, i32* %a0, align 4
  %b82 = load i32, i32* %b3, align 4
  %d83 = load i32, i32* %d9, align 4
  %add_84 = add i32 %b82, %d83
  %add_85 = add i32 %a81, %add_84
  %c86 = load i32, i32* %c5, align 4
  %add_87 = add i32 %add_85, %c86
  %e88 = load i32, i32* @e3, align 4
  %d89 = load i32, i32* %d9, align 4
  %add_90 = add i32 %e88, %d89
  %g91 = load i32, i32* @g0, align 4
  %sub_92 = sub i32 %add_90, %g91
  %h93 = load i32, i32* @h1, align 4
  %add_94 = add i32 %sub_92, %h93
  %sub_95 = sub i32 %add_87, %add_94
  ret i32 %sub_95
  ret i32 0
condBlock5:
  %b18 = load i32, i32* %b3, align 4
  %icmp_LT19 = icmp slt i32 %b18, 10
  %icmp_20 = icmp ne i1 %icmp_LT19, 0
  br i1 %icmp_20, label %bodyBlock6, label %afterBlock7
bodyBlock6:
  %b21 = load i32, i32* %b3, align 4
  %add_22 = add i32 %b21, 1
  store i32 %add_22, i32* %b3, align 4
  br label %condBlock8
afterBlock7:
  %b78 = load i32, i32* %b3, align 4
  %sub_79 = sub i32 %b78, 2
  store i32 %sub_79, i32* %b3, align 4
  br label %condBlock2
condBlock8:
  %c24 = load i32, i32* %c5, align 4
  %icmp_EQ25 = icmp eq i32 %c24, 7
  %icmp_26 = icmp ne i1 %icmp_EQ25, 0
  br i1 %icmp_26, label %bodyBlock9, label %afterBlock10
bodyBlock9:
  %c27 = load i32, i32* %c5, align 4
  %sub_28 = sub i32 %c27, 1
  store i32 %sub_28, i32* %c5, align 4
  br label %condBlock11
afterBlock10:
  %c75 = load i32, i32* %c5, align 4
  %add_76 = add i32 %c75, 1
  store i32 %add_76, i32* %c5, align 4
  br label %condBlock5
condBlock11:
  %d30 = load i32, i32* %d9, align 4
  %icmp_LT31 = icmp slt i32 %d30, 20
  %icmp_32 = icmp ne i1 %icmp_LT31, 0
  br i1 %icmp_32, label %bodyBlock12, label %afterBlock13
bodyBlock12:
  %d33 = load i32, i32* %d9, align 4
  %add_34 = add i32 %d33, 3
  store i32 %add_34, i32* %d9, align 4
  br label %condBlock14
afterBlock13:
  %d72 = load i32, i32* %d9, align 4
  %sub_73 = sub i32 %d72, 1
  store i32 %sub_73, i32* %d9, align 4
  br label %condBlock8
condBlock14:
  %e36 = load i32, i32* @e3, align 4
  %icmp_GT37 = icmp sgt i32 %e36, 1
  %icmp_38 = icmp ne i1 %icmp_GT37, 0
  br i1 %icmp_38, label %bodyBlock15, label %afterBlock16
bodyBlock15:
  %e39 = load i32, i32* @e3, align 4
  %sub_40 = sub i32 %e39, 1
  store i32 %sub_40, i32* @e3, align 4
  br label %condBlock17
afterBlock16:
  %e69 = load i32, i32* @e3, align 4
  %add_70 = add i32 %e69, 1
  store i32 %add_70, i32* @e3, align 4
  br label %condBlock11
condBlock17:
  %f42 = load i32, i32* @f2, align 4
  %icmp_GT43 = icmp sgt i32 %f42, 2
  %icmp_44 = icmp ne i1 %icmp_GT43, 0
  br i1 %icmp_44, label %bodyBlock18, label %afterBlock19
bodyBlock18:
  %f45 = load i32, i32* @f2, align 4
  %sub_46 = sub i32 %f45, 2
  store i32 %sub_46, i32* @f2, align 4
  br label %condBlock20
afterBlock19:
  %f66 = load i32, i32* @f2, align 4
  %add_67 = add i32 %f66, 1
  store i32 %add_67, i32* @f2, align 4
  br label %condBlock14
condBlock20:
  %g48 = load i32, i32* @g0, align 4
  %icmp_LT49 = icmp slt i32 %g48, 3
  %icmp_50 = icmp ne i1 %icmp_LT49, 0
  br i1 %icmp_50, label %bodyBlock21, label %afterBlock22
bodyBlock21:
  %g51 = load i32, i32* @g0, align 4
  %add_52 = add i32 %g51, 10
  store i32 %add_52, i32* @g0, align 4
  br label %condBlock23
afterBlock22:
  %g63 = load i32, i32* @g0, align 4
  %sub_64 = sub i32 %g63, 8
  store i32 %sub_64, i32* @g0, align 4
  br label %condBlock17
condBlock23:
  %h54 = load i32, i32* @h1, align 4
  %icmp_LT55 = icmp slt i32 %h54, 10
  %icmp_56 = icmp ne i1 %icmp_LT55, 0
  br i1 %icmp_56, label %bodyBlock24, label %afterBlock25
bodyBlock24:
  %h57 = load i32, i32* @h1, align 4
  %add_58 = add i32 %h57, 8
  store i32 %add_58, i32* @h1, align 4
  br label %condBlock23
afterBlock25:
  %h60 = load i32, i32* @h1, align 4
  %sub_61 = sub i32 %h60, 1
  store i32 %sub_61, i32* @h1, align 4
  br label %condBlock20
}
define i32 @main() {
mainEntry26:
  store i32 1, i32* @g0, align 4
  store i32 2, i32* @h1, align 4
  store i32 4, i32* @e3, align 4
  store i32 6, i32* @f2, align 4
  %EightWhile100 = call i32 @EightWhile()
  ret i32 %EightWhile100
  ret i32 0
}

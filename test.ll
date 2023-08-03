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
  %arr11 = alloca [2 x i32], align 4
  %sum12 = alloca i32, align 4
  store i32 0, i32* %sum12, align 4
  %count14 = alloca i32, align 4
  store i32 0, i32* %count14, align 4
  br label %condBlock2
condBlock2:
  %count16 = load i32, i32* %count14, align 4
  %icmp_NE17 = icmp ne i32 %count16, 2
  %tmp_18 = zext i1 %icmp_NE17 to i32
  %icmp_19 = icmp ne i32 %tmp_18, 0
  br i1 %icmp_19, label %bodyBlock3, label %afterBlock4
bodyBlock3:
  %count20 = load i32, i32* %count14, align 4
  %arr21 = getelementptr [2 x i32], [2 x i32]* %arr11, i32 0, i32 %count20
  %count22 = load i32, i32* %count14, align 4
  store i32 %count22, i32* %arr21, align 4
  %count24 = load i32, i32* %count14, align 4
  %add_25 = add i32 %count24, 1
  store i32 %add_25, i32* %count14, align 4
  br label %condBlock2
afterBlock4:
  br label %condBlock5
condBlock5:
  %count27 = load i32, i32* %count14, align 4
  %icmp_NE28 = icmp ne i32 %count27, 2
  %tmp_29 = zext i1 %icmp_NE28 to i32
  %icmp_30 = icmp ne i32 %tmp_29, 0
  br i1 %icmp_30, label %bodyBlock6, label %afterBlock7
bodyBlock6:
  %count31 = load i32, i32* %count14, align 4
  %sub_32 = sub i32 %count31, 1
  store i32 %sub_32, i32* %count14, align 4
  %sum34 = load i32, i32* %sum12, align 4
  %count35 = load i32, i32* %count14, align 4
  %arr36 = getelementptr [2 x i32], [2 x i32]* %arr11, i32 0, i32 %count35
  %arr37 = load i32, i32* %arr36, align 4
  %add_38 = add i32 %sum34, %arr37
  store i32 %add_38, i32* %sum12, align 4
  br label %condBlock5
afterBlock7:
  %sum40 = load i32, i32* %sum12, align 4
  ret i32 %sum40
  ret i32 0
}

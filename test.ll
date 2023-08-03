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

define i32 @ifElseIf() {
ifElseIfEntry1:
  %a11 = alloca i32, align 4
  store i32 0, i32* %a11, align 4
  store i32 5, i32* %a11, align 4
  %b14 = alloca i32, align 4
  store i32 0, i32* %b14, align 4
  store i32 10, i32* %b14, align 4
  %a17 = load i32, i32* %a11, align 4
  %icmp_EQ18 = icmp eq i32 %a17, 6
  %tmp_19 = zext i1 %icmp_EQ18 to i32
  %icmp_NE20 = icmp ne i32 0, %tmp_19
  %or_21 = alloca i32, align 4
  store i32 %tmp_19, i32* %or_21, align 4
  br i1 %icmp_NE20, label %true_2, label %false_3
true_2:
  br label %after_4
false_3:
  %b23 = load i32, i32* %b14, align 4
  %icmp_EQ24 = icmp eq i32 %b23, 11
  %tmp_25 = zext i1 %icmp_EQ24 to i32
  store i32 %tmp_25, i32* %or_21, align 4
  br label %after_4
after_4:
  %load_27 = load i32, i32* %or_21, align 4
  %icmp_28 = icmp ne i32 %load_27, 0
  br i1 %icmp_28, label %trueBlock5, label %falseBlock6
trueBlock5:
  %a29 = load i32, i32* %a11, align 4
  ret i32 %a29
  br label %afterBlock7
falseBlock6:
  %b30 = load i32, i32* %b14, align 4
  %icmp_EQ31 = icmp eq i32 %b30, 10
  %tmp_32 = zext i1 %icmp_EQ31 to i32
  %icmp_NE33 = icmp eq i32 0, %tmp_32
  %and_34 = alloca i32, align 4
  store i32 %tmp_32, i32* %and_34, align 4
  br i1 %icmp_NE33, label %true_8, label %false_9
afterBlock7:
  %a61 = load i32, i32* %a11, align 4
  ret i32 %a61
  ret i32 0
true_8:
  br label %after_10
false_9:
  %a36 = load i32, i32* %a11, align 4
  %icmp_EQ37 = icmp eq i32 %a36, 1
  %tmp_38 = zext i1 %icmp_EQ37 to i32
  store i32 %tmp_38, i32* %and_34, align 4
  br label %after_10
after_10:
  %load_40 = load i32, i32* %and_34, align 4
  %icmp_41 = icmp ne i32 %load_40, 0
  br i1 %icmp_41, label %trueBlock11, label %falseBlock12
trueBlock11:
  store i32 25, i32* %a11, align 4
  br label %afterBlock13
falseBlock12:
  %b43 = load i32, i32* %b14, align 4
  %icmp_EQ44 = icmp eq i32 %b43, 10
  %tmp_45 = zext i1 %icmp_EQ44 to i32
  %icmp_NE46 = icmp eq i32 0, %tmp_45
  %and_47 = alloca i32, align 4
  store i32 %tmp_45, i32* %and_47, align 4
  br i1 %icmp_NE46, label %true_14, label %false_15
afterBlock13:
  br label %afterBlock7
true_14:
  br label %after_16
false_15:
  %a49 = load i32, i32* %a11, align 4
  %icmp_EQ50 = icmp eq i32 %a49, -5
  %tmp_51 = zext i1 %icmp_EQ50 to i32
  store i32 %tmp_51, i32* %and_47, align 4
  br label %after_16
after_16:
  %load_53 = load i32, i32* %and_47, align 4
  %icmp_54 = icmp ne i32 %load_53, 0
  br i1 %icmp_54, label %trueBlock17, label %falseBlock18
trueBlock17:
  %a55 = load i32, i32* %a11, align 4
  %add_56 = add i32 %a55, 15
  store i32 %add_56, i32* %a11, align 4
  br label %afterBlock19
falseBlock18:
  %a58 = load i32, i32* %a11, align 4
  %neg_59 = sub i32 0, %a58
  store i32 %neg_59, i32* %a11, align 4
  br label %afterBlock19
afterBlock19:
  br label %afterBlock13
}
define i32 @main() {
mainEntry20:
  %ifElseIf62 = call i32 @ifElseIf()
  call void @putint(i32 %ifElseIf62)
  ret i32 0
  ret i32 0
}

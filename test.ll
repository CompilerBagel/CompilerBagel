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
  %a0 = alloca i32, align 4
  store i32 0, i32* %a0, align 4
  store i32 10, i32* %a0, align 4
  %a3 = load i32, i32* %a0, align 4
  %icmp_4 = icmp ne i32 0, %a3
  %xor_5 = xor i1 %icmp_4, true
  %zext_6 = zext i1 %xor_5 to i32
  %icmp_7 = icmp ne i32 0, %zext_6
  %xor_8 = xor i1 %icmp_7, true
  %zext_9 = zext i1 %xor_8 to i32
  %icmp_10 = icmp ne i32 0, %zext_9
  %xor_11 = xor i1 %icmp_10, true
  %zext_12 = zext i1 %xor_11 to i32
  %neg_13 = sub i32 0, %zext_12
  %icmp_14 = icmp ne i32 %neg_13, 0
  br i1 %icmp_14, label %trueBlock2, label %falseBlock3
trueBlock2:
  store i32 -1, i32* %a0, align 4
  br label %afterBlock4
falseBlock3:
  store i32 0, i32* %a0, align 4
  br label %afterBlock4
afterBlock4:
  %a17 = load i32, i32* %a0, align 4
  ret i32 %a17
  ret i32 0
}

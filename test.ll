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

@N0 = global i32 -1
@arr1 = global [6 x i32]  [i32 1, i32 2, i32 33, i32 4, i32 5, i32 6]
define i32 @main() {
mainEntry1:
  %i11 = alloca i32, align 4
  store i32 0, i32* %i11, align 4
  %sum13 = alloca i32, align 4
  store i32 0, i32* %sum13, align 4
  br label %condBlock2
condBlock2:
  %i15 = load i32, i32* %i11, align 4
  %icmp_LT16 = icmp slt i32 %i15, 6
  %tmp_17 = zext i1 %icmp_LT16 to i32
  %icmp_18 = icmp ne i32 %tmp_17, 0
  br i1 %icmp_18, label %bodyBlock3, label %afterBlock4
bodyBlock3:
  %sum19 = load i32, i32* %sum13, align 4
  %i20 = load i32, i32* %i11, align 4
  %arr21 = getelementptr [6 x i32], [6 x i32]* @arr1, i32 0, i32 %i20
  %arr22 = load i32, i32* %arr21, align 4
  %add_23 = add i32 %sum19, %arr22
  store i32 %add_23, i32* %sum13, align 4
  %i25 = load i32, i32* %i11, align 4
  %add_26 = add i32 %i25, 1
  store i32 %add_26, i32* %i11, align 4
  br label %condBlock2
afterBlock4:
  %sum28 = load i32, i32* %sum13, align 4
  ret i32 %sum28
  ret i32 0
}

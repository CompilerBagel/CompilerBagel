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
  %i13 = alloca i32, align 4
  store i32 0, i32* %i13, align 4
  %sum15 = alloca i32, align 4
  store i32 0, i32* %sum15, align 4
  br label %condBlock2
condBlock2:
  %i17 = load i32, i32* %i13, align 4
  %icmp_LT18 = icmp slt i32 %i17, 6
  %tmp_19 = zext i1 %icmp_LT18 to i32
  %icmp_20 = icmp ne i32 %tmp_19, 0
  br i1 %icmp_20, label %bodyBlock3, label %afterBlock4
bodyBlock3:
  %sum21 = load i32, i32* %sum15, align 4
  %i22 = load i32, i32* %i13, align 4
  %arr23 = getelementptr [6 x i32], [6 x i32]* @arr1, i32 0, i32 %i22
  %arr24 = load i32, i32* %arr23, align 4
  %add_25 = add i32 %sum21, %arr24
  store i32 %add_25, i32* %sum15, align 4
  %i27 = load i32, i32* %i13, align 4
  %add_28 = add i32 %i27, 1
  store i32 %add_28, i32* %i13, align 4
  br label %condBlock2
afterBlock4:
  %sum30 = load i32, i32* %sum15, align 4
  ret i32 %sum30
  ret i32 0
}

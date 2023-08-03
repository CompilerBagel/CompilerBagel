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
  %icmp_17 = icmp ne i1 %icmp_LT16, 0
  br i1 %icmp_17, label %bodyBlock3, label %afterBlock4
bodyBlock3:
  %sum18 = load i32, i32* %sum13, align 4
  %i19 = load i32, i32* %i11, align 4
  %arr20 = getelementptr [6 x i32], [6 x i32]* @arr1, i32 0, i32 %i19
  %arr21 = load i32, i32* %arr20, align 4
  %add_22 = add i32 %sum18, %arr21
  store i32 %add_22, i32* %sum13, align 4
  %i24 = load i32, i32* %i11, align 4
  %add_25 = add i32 %i24, 1
  store i32 %add_25, i32* %i11, align 4
  br label %condBlock2
afterBlock4:
  %sum27 = load i32, i32* %sum13, align 4
  ret i32 %sum27
  ret i32 0
}

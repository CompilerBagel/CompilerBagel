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
  %arr11 = alloca [100 x i32], align 4
  %i12 = alloca i32, align 4
  store i32 0, i32* %i12, align 4
  %sum14 = alloca i32, align 4
  store i32 0, i32* %sum14, align 4
  br label %condBlock2
condBlock2:
  %getint16 = call i32 @getint()
  %icmp_17 = icmp ne i32 %getint16, 0
  br i1 %icmp_17, label %bodyBlock3, label %afterBlock4
bodyBlock3:
  %i18 = load i32, i32* %i12, align 4
  %arr19 = getelementptr [100 x i32], [100 x i32]* %arr11, i32 0, i32 %i18
  %getint20 = call i32 @getint()
  store i32 %getint20, i32* %arr19, align 4
  %i22 = load i32, i32* %i12, align 4
  %add_23 = add i32 %i22, 1
  store i32 %add_23, i32* %i12, align 4
  br label %condBlock2
afterBlock4:
  br label %condBlock5
condBlock5:
  %i25 = load i32, i32* %i12, align 4
  %icmp_26 = icmp ne i32 %i25, 0
  br i1 %icmp_26, label %bodyBlock6, label %afterBlock7
bodyBlock6:
  %i27 = load i32, i32* %i12, align 4
  %sub_28 = sub i32 %i27, 1
  store i32 %sub_28, i32* %i12, align 4
  %sum30 = load i32, i32* %sum14, align 4
  %i31 = load i32, i32* %i12, align 4
  %arr32 = getelementptr [100 x i32], [100 x i32]* %arr11, i32 0, i32 %i31
  %arr33 = load i32, i32* %arr32, align 4
  %add_34 = add i32 %sum30, %arr33
  store i32 %add_34, i32* %sum14, align 4
  br label %condBlock5
afterBlock7:
  %sum36 = load i32, i32* %sum14, align 4
  ret i32 %sum36
  ret i32 0
}

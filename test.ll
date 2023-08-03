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
  %a11 = alloca [4 x [2 x i32]], align 4
  %array12 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %a11, i32 0, i32 0
  %array13 = getelementptr [2 x i32], [2 x i32]* %array12, i32 0, i32 0
  store i32 1, i32* %array13, align 4
  %array15 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %a11, i32 0, i32 0
  %array16 = getelementptr [2 x i32], [2 x i32]* %array15, i32 0, i32 1
  store i32 2, i32* %array16, align 4
  %array18 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %a11, i32 0, i32 1
  %array19 = getelementptr [2 x i32], [2 x i32]* %array18, i32 0, i32 0
  store i32 3, i32* %array19, align 4
  %array21 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %a11, i32 0, i32 1
  %array22 = getelementptr [2 x i32], [2 x i32]* %array21, i32 0, i32 1
  store i32 4, i32* %array22, align 4
  %array24 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %a11, i32 0, i32 2
  %array25 = getelementptr [2 x i32], [2 x i32]* %array24, i32 0, i32 0
  store i32 0, i32* %array25, align 4
  %array27 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %a11, i32 0, i32 2
  %array28 = getelementptr [2 x i32], [2 x i32]* %array27, i32 0, i32 1
  store i32 0, i32* %array28, align 4
  %array30 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %a11, i32 0, i32 3
  %array31 = getelementptr [2 x i32], [2 x i32]* %array30, i32 0, i32 0
  store i32 7, i32* %array31, align 4
  %array33 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %a11, i32 0, i32 3
  %array34 = getelementptr [2 x i32], [2 x i32]* %array33, i32 0, i32 1
  store i32 0, i32* %array34, align 4
  %N36 = alloca i32, align 4
  store i32 3, i32* %N36, align 4
  %d38 = alloca [4 x [2 x i32]], align 4
  %a39 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %a11, i32 0, i32 3
  %a40 = getelementptr [2 x i32], [2 x i32]* %a39, i32 0, i32 0
  %a41 = load i32, i32* %a40, align 4
  %array42 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %d38, i32 0, i32 0
  %array43 = getelementptr [2 x i32], [2 x i32]* %array42, i32 0, i32 0
  store i32 1, i32* %array43, align 4
  %array45 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %d38, i32 0, i32 0
  %array46 = getelementptr [2 x i32], [2 x i32]* %array45, i32 0, i32 1
  store i32 2, i32* %array46, align 4
  %array48 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %d38, i32 0, i32 1
  %array49 = getelementptr [2 x i32], [2 x i32]* %array48, i32 0, i32 0
  store i32 3, i32* %array49, align 4
  %array51 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %d38, i32 0, i32 1
  %array52 = getelementptr [2 x i32], [2 x i32]* %array51, i32 0, i32 1
  store i32 0, i32* %array52, align 4
  %array54 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %d38, i32 0, i32 2
  %array55 = getelementptr [2 x i32], [2 x i32]* %array54, i32 0, i32 0
  store i32 5, i32* %array55, align 4
  %array57 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %d38, i32 0, i32 2
  %array58 = getelementptr [2 x i32], [2 x i32]* %array57, i32 0, i32 1
  store i32 0, i32* %array58, align 4
  %array60 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %d38, i32 0, i32 3
  %array61 = getelementptr [2 x i32], [2 x i32]* %array60, i32 0, i32 0
  store i32 %a41, i32* %array61, align 4
  %array63 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %d38, i32 0, i32 3
  %array64 = getelementptr [2 x i32], [2 x i32]* %array63, i32 0, i32 1
  store i32 8, i32* %array64, align 4
  %d66 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %d38, i32 0, i32 3
  %d67 = getelementptr [2 x i32], [2 x i32]* %d66, i32 0, i32 1
  %d68 = load i32, i32* %d67, align 4
  ret i32 %d68
  ret i32 0
}

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
  %e11 = alloca [4 x [2 x [1 x i32]]], align 4
  %array12 = getelementptr [4 x [2 x [1 x i32]]], [4 x [2 x [1 x i32]]]* %e11, i32 0, i32 0
  %array13 = getelementptr [2 x [1 x i32]], [2 x [1 x i32]]* %array12, i32 0, i32 0
  %array14 = getelementptr [1 x i32], [1 x i32]* %array13, i32 0, i32 0
  store i32 0, i32* %array14, align 4
  %array16 = getelementptr [4 x [2 x [1 x i32]]], [4 x [2 x [1 x i32]]]* %e11, i32 0, i32 0
  %array17 = getelementptr [2 x [1 x i32]], [2 x [1 x i32]]* %array16, i32 0, i32 1
  %array18 = getelementptr [1 x i32], [1 x i32]* %array17, i32 0, i32 0
  store i32 6, i32* %array18, align 4
  %array20 = getelementptr [4 x [2 x [1 x i32]]], [4 x [2 x [1 x i32]]]* %e11, i32 0, i32 1
  %array21 = getelementptr [2 x [1 x i32]], [2 x [1 x i32]]* %array20, i32 0, i32 0
  %array22 = getelementptr [1 x i32], [1 x i32]* %array21, i32 0, i32 0
  store i32 3, i32* %array22, align 4
  %array24 = getelementptr [4 x [2 x [1 x i32]]], [4 x [2 x [1 x i32]]]* %e11, i32 0, i32 1
  %array25 = getelementptr [2 x [1 x i32]], [2 x [1 x i32]]* %array24, i32 0, i32 1
  %array26 = getelementptr [1 x i32], [1 x i32]* %array25, i32 0, i32 0
  store i32 4, i32* %array26, align 4
  %array28 = getelementptr [4 x [2 x [1 x i32]]], [4 x [2 x [1 x i32]]]* %e11, i32 0, i32 2
  %array29 = getelementptr [2 x [1 x i32]], [2 x [1 x i32]]* %array28, i32 0, i32 0
  %array30 = getelementptr [1 x i32], [1 x i32]* %array29, i32 0, i32 0
  store i32 5, i32* %array30, align 4
  %array32 = getelementptr [4 x [2 x [1 x i32]]], [4 x [2 x [1 x i32]]]* %e11, i32 0, i32 2
  %array33 = getelementptr [2 x [1 x i32]], [2 x [1 x i32]]* %array32, i32 0, i32 1
  %array34 = getelementptr [1 x i32], [1 x i32]* %array33, i32 0, i32 0
  store i32 6, i32* %array34, align 4
  %array36 = getelementptr [4 x [2 x [1 x i32]]], [4 x [2 x [1 x i32]]]* %e11, i32 0, i32 3
  %array37 = getelementptr [2 x [1 x i32]], [2 x [1 x i32]]* %array36, i32 0, i32 0
  %array38 = getelementptr [1 x i32], [1 x i32]* %array37, i32 0, i32 0
  store i32 7, i32* %array38, align 4
  %array40 = getelementptr [4 x [2 x [1 x i32]]], [4 x [2 x [1 x i32]]]* %e11, i32 0, i32 3
  %array41 = getelementptr [2 x [1 x i32]], [2 x [1 x i32]]* %array40, i32 0, i32 1
  %array42 = getelementptr [1 x i32], [1 x i32]* %array41, i32 0, i32 0
  store i32 8, i32* %array42, align 4
  %e44 = getelementptr [4 x [2 x [1 x i32]]], [4 x [2 x [1 x i32]]]* %e11, i32 0, i32 0
  %e45 = getelementptr [2 x [1 x i32]], [2 x [1 x i32]]* %e44, i32 0, i32 1
  %e46 = getelementptr [1 x i32], [1 x i32]* %e45, i32 0, i32 0
  %e47 = load i32, i32* %e46, align 4
  ret i32 %e47
  ret i32 0
}

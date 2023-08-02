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
  %c0 = alloca [1 x [4 x [2 x [3 x i32]]]], align 4
  %array1 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array2 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array1, i32 0, i32 0
  %array3 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array2, i32 0, i32 0
  %array4 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array3, i32 0, i32 0
  store i32 0, [3 x i32]* %array4, align 4
  %array6 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array7 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array6, i32 0, i32 0
  %array8 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array7, i32 0, i32 0
  %array9 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array8, i32 0, i32 1
  store i32 0, [3 x i32]* %array9, align 4
  %array11 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array12 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array11, i32 0, i32 0
  %array13 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array12, i32 0, i32 0
  %array14 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array13, i32 0, i32 2
  store i32 0, [3 x i32]* %array14, align 4
  %array16 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array17 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array16, i32 0, i32 0
  %array18 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array17, i32 0, i32 1
  %array19 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array18, i32 0, i32 0
  store i32 0, [3 x i32]* %array19, align 4
  %array21 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array22 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array21, i32 0, i32 0
  %array23 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array22, i32 0, i32 1
  %array24 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array23, i32 0, i32 1
  store i32 0, [3 x i32]* %array24, align 4
  %array26 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array27 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array26, i32 0, i32 0
  %array28 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array27, i32 0, i32 1
  %array29 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array28, i32 0, i32 2
  store i32 0, [3 x i32]* %array29, align 4
  %array31 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array32 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array31, i32 0, i32 1
  %array33 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array32, i32 0, i32 0
  %array34 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array33, i32 0, i32 0
  store i32 0, [3 x i32]* %array34, align 4
  %array36 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array37 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array36, i32 0, i32 1
  %array38 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array37, i32 0, i32 0
  %array39 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array38, i32 0, i32 1
  store i32 0, [3 x i32]* %array39, align 4
  %array41 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array42 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array41, i32 0, i32 1
  %array43 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array42, i32 0, i32 0
  %array44 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array43, i32 0, i32 2
  store i32 0, [3 x i32]* %array44, align 4
  %array46 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array47 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array46, i32 0, i32 1
  %array48 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array47, i32 0, i32 1
  %array49 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array48, i32 0, i32 0
  store i32 0, [3 x i32]* %array49, align 4
  %array51 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array52 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array51, i32 0, i32 1
  %array53 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array52, i32 0, i32 1
  %array54 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array53, i32 0, i32 1
  store i32 0, [3 x i32]* %array54, align 4
  %array56 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array57 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array56, i32 0, i32 1
  %array58 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array57, i32 0, i32 1
  %array59 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array58, i32 0, i32 2
  store i32 0, [3 x i32]* %array59, align 4
  %array61 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array62 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array61, i32 0, i32 2
  %array63 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array62, i32 0, i32 0
  %array64 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array63, i32 0, i32 0
  store i32 0, [3 x i32]* %array64, align 4
  %array66 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array67 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array66, i32 0, i32 2
  %array68 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array67, i32 0, i32 0
  %array69 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array68, i32 0, i32 1
  store i32 0, [3 x i32]* %array69, align 4
  %array71 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array72 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array71, i32 0, i32 2
  %array73 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array72, i32 0, i32 0
  %array74 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array73, i32 0, i32 2
  store i32 0, [3 x i32]* %array74, align 4
  %array76 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array77 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array76, i32 0, i32 2
  %array78 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array77, i32 0, i32 1
  %array79 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array78, i32 0, i32 0
  store i32 0, [3 x i32]* %array79, align 4
  %array81 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array82 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array81, i32 0, i32 2
  %array83 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array82, i32 0, i32 1
  %array84 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array83, i32 0, i32 1
  store i32 0, [3 x i32]* %array84, align 4
  %array86 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array87 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array86, i32 0, i32 2
  %array88 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array87, i32 0, i32 1
  %array89 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array88, i32 0, i32 2
  store i32 0, [3 x i32]* %array89, align 4
  %array91 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array92 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array91, i32 0, i32 3
  %array93 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array92, i32 0, i32 0
  %array94 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array93, i32 0, i32 0
  store i32 0, [3 x i32]* %array94, align 4
  %array96 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array97 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array96, i32 0, i32 3
  %array98 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array97, i32 0, i32 0
  %array99 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array98, i32 0, i32 1
  store i32 0, [3 x i32]* %array99, align 4
  %array101 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array102 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array101, i32 0, i32 3
  %array103 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array102, i32 0, i32 0
  %array104 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array103, i32 0, i32 2
  store i32 0, [3 x i32]* %array104, align 4
  %array106 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array107 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array106, i32 0, i32 3
  %array108 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array107, i32 0, i32 1
  %array109 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array108, i32 0, i32 0
  store i32 0, [3 x i32]* %array109, align 4
  %array111 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array112 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array111, i32 0, i32 3
  %array113 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array112, i32 0, i32 1
  %array114 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array113, i32 0, i32 1
  store i32 0, [3 x i32]* %array114, align 4
  %array116 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %array117 = getelementptr [4 x [2 x [3 x i32]]]*, [4 x [2 x [3 x i32]]]* %array116, i32 0, i32 3
  %array118 = getelementptr [2 x [3 x i32]]*, [4 x [2 x [3 x i32]]]* %array117, i32 0, i32 1
  %array119 = getelementptr [3 x i32]*, [2 x [3 x i32]]* %array118, i32 0, i32 2
  store i32 0, [3 x i32]* %array119, align 4
  %c121 = getelementptr [1 x [4 x [2 x [3 x i32]]]], [1 x [4 x [2 x [3 x i32]]]]* %c0, i32 0, i32 0
  %c122 = getelementptr [4 x [2 x [3 x i32]]], [4 x [2 x [3 x i32]]]* %c121, i32 0, i32 0
  %c123 = getelementptr [2 x [3 x i32]], [2 x [3 x i32]]* %c122, i32 0, i32 0
  %c124 = getelementptr [3 x i32], [3 x i32]* %c123, i32 0, i32 0
  %c125 = load i32, i32* %c124, align 4
  ret i32 %c125
  ret i32 0
}

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
  store i32 0, i32* %array13, align 4
  %array15 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %a11, i32 0, i32 0
  %array16 = getelementptr [2 x i32], [2 x i32]* %array15, i32 0, i32 1
  store i32 0, i32* %array16, align 4
  %array18 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %a11, i32 0, i32 1
  %array19 = getelementptr [2 x i32], [2 x i32]* %array18, i32 0, i32 0
  store i32 0, i32* %array19, align 4
  %array21 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %a11, i32 0, i32 1
  %array22 = getelementptr [2 x i32], [2 x i32]* %array21, i32 0, i32 1
  store i32 0, i32* %array22, align 4
  %array24 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %a11, i32 0, i32 2
  %array25 = getelementptr [2 x i32], [2 x i32]* %array24, i32 0, i32 0
  store i32 0, i32* %array25, align 4
  %array27 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %a11, i32 0, i32 2
  %array28 = getelementptr [2 x i32], [2 x i32]* %array27, i32 0, i32 1
  store i32 0, i32* %array28, align 4
  %array30 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %a11, i32 0, i32 3
  %array31 = getelementptr [2 x i32], [2 x i32]* %array30, i32 0, i32 0
  store i32 0, i32* %array31, align 4
  %array33 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %a11, i32 0, i32 3
  %array34 = getelementptr [2 x i32], [2 x i32]* %array33, i32 0, i32 1
  store i32 0, i32* %array34, align 4
  %b36 = alloca [4 x [2 x i32]], align 4
  %array37 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %b36, i32 0, i32 0
  %array38 = getelementptr [2 x i32], [2 x i32]* %array37, i32 0, i32 0
  store i32 1, i32* %array38, align 4
  %array40 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %b36, i32 0, i32 0
  %array41 = getelementptr [2 x i32], [2 x i32]* %array40, i32 0, i32 1
  store i32 2, i32* %array41, align 4
  %array43 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %b36, i32 0, i32 1
  %array44 = getelementptr [2 x i32], [2 x i32]* %array43, i32 0, i32 0
  store i32 3, i32* %array44, align 4
  %array46 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %b36, i32 0, i32 1
  %array47 = getelementptr [2 x i32], [2 x i32]* %array46, i32 0, i32 1
  store i32 4, i32* %array47, align 4
  %array49 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %b36, i32 0, i32 2
  %array50 = getelementptr [2 x i32], [2 x i32]* %array49, i32 0, i32 0
  store i32 5, i32* %array50, align 4
  %array52 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %b36, i32 0, i32 2
  %array53 = getelementptr [2 x i32], [2 x i32]* %array52, i32 0, i32 1
  store i32 6, i32* %array53, align 4
  %array55 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %b36, i32 0, i32 3
  %array56 = getelementptr [2 x i32], [2 x i32]* %array55, i32 0, i32 0
  store i32 7, i32* %array56, align 4
  %array58 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %b36, i32 0, i32 3
  %array59 = getelementptr [2 x i32], [2 x i32]* %array58, i32 0, i32 1
  store i32 8, i32* %array59, align 4
  %c61 = alloca [4 x [2 x i32]], align 4
  %array62 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %c61, i32 0, i32 0
  %array63 = getelementptr [2 x i32], [2 x i32]* %array62, i32 0, i32 0
  store i32 1, i32* %array63, align 4
  %array65 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %c61, i32 0, i32 0
  %array66 = getelementptr [2 x i32], [2 x i32]* %array65, i32 0, i32 1
  store i32 2, i32* %array66, align 4
  %array68 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %c61, i32 0, i32 1
  %array69 = getelementptr [2 x i32], [2 x i32]* %array68, i32 0, i32 0
  store i32 3, i32* %array69, align 4
  %array71 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %c61, i32 0, i32 1
  %array72 = getelementptr [2 x i32], [2 x i32]* %array71, i32 0, i32 1
  store i32 4, i32* %array72, align 4
  %array74 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %c61, i32 0, i32 2
  %array75 = getelementptr [2 x i32], [2 x i32]* %array74, i32 0, i32 0
  store i32 5, i32* %array75, align 4
  %array77 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %c61, i32 0, i32 2
  %array78 = getelementptr [2 x i32], [2 x i32]* %array77, i32 0, i32 1
  store i32 6, i32* %array78, align 4
  %array80 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %c61, i32 0, i32 3
  %array81 = getelementptr [2 x i32], [2 x i32]* %array80, i32 0, i32 0
  store i32 7, i32* %array81, align 4
  %array83 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %c61, i32 0, i32 3
  %array84 = getelementptr [2 x i32], [2 x i32]* %array83, i32 0, i32 1
  store i32 8, i32* %array84, align 4
  %d86 = alloca [4 x [2 x i32]], align 4
  %array87 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %d86, i32 0, i32 0
  %array88 = getelementptr [2 x i32], [2 x i32]* %array87, i32 0, i32 0
  store i32 1, i32* %array88, align 4
  %array90 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %d86, i32 0, i32 0
  %array91 = getelementptr [2 x i32], [2 x i32]* %array90, i32 0, i32 1
  store i32 2, i32* %array91, align 4
  %array93 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %d86, i32 0, i32 1
  %array94 = getelementptr [2 x i32], [2 x i32]* %array93, i32 0, i32 0
  store i32 3, i32* %array94, align 4
  %array96 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %d86, i32 0, i32 1
  %array97 = getelementptr [2 x i32], [2 x i32]* %array96, i32 0, i32 1
  store i32 0, i32* %array97, align 4
  %array99 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %d86, i32 0, i32 2
  %array100 = getelementptr [2 x i32], [2 x i32]* %array99, i32 0, i32 0
  store i32 5, i32* %array100, align 4
  %array102 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %d86, i32 0, i32 2
  %array103 = getelementptr [2 x i32], [2 x i32]* %array102, i32 0, i32 1
  store i32 0, i32* %array103, align 4
  %array105 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %d86, i32 0, i32 3
  %array106 = getelementptr [2 x i32], [2 x i32]* %array105, i32 0, i32 0
  store i32 7, i32* %array106, align 4
  %array108 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %d86, i32 0, i32 3
  %array109 = getelementptr [2 x i32], [2 x i32]* %array108, i32 0, i32 1
  store i32 8, i32* %array109, align 4
  %e111 = alloca [4 x [2 x i32]], align 4
  %d112 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %d86, i32 0, i32 2
  %d113 = getelementptr [2 x i32], [2 x i32]* %d112, i32 0, i32 1
  %d114 = load i32, i32* %d113, align 4
  %c115 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %c61, i32 0, i32 2
  %c116 = getelementptr [2 x i32], [2 x i32]* %c115, i32 0, i32 1
  %c117 = load i32, i32* %c116, align 4
  %array118 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %e111, i32 0, i32 0
  %array119 = getelementptr [2 x i32], [2 x i32]* %array118, i32 0, i32 0
  store i32 %d114, i32* %array119, align 4
  %array121 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %e111, i32 0, i32 0
  %array122 = getelementptr [2 x i32], [2 x i32]* %array121, i32 0, i32 1
  store i32 %c117, i32* %array122, align 4
  %array124 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %e111, i32 0, i32 1
  %array125 = getelementptr [2 x i32], [2 x i32]* %array124, i32 0, i32 0
  store i32 3, i32* %array125, align 4
  %array127 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %e111, i32 0, i32 1
  %array128 = getelementptr [2 x i32], [2 x i32]* %array127, i32 0, i32 1
  store i32 4, i32* %array128, align 4
  %array130 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %e111, i32 0, i32 2
  %array131 = getelementptr [2 x i32], [2 x i32]* %array130, i32 0, i32 0
  store i32 5, i32* %array131, align 4
  %array133 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %e111, i32 0, i32 2
  %array134 = getelementptr [2 x i32], [2 x i32]* %array133, i32 0, i32 1
  store i32 6, i32* %array134, align 4
  %array136 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %e111, i32 0, i32 3
  %array137 = getelementptr [2 x i32], [2 x i32]* %array136, i32 0, i32 0
  store i32 7, i32* %array137, align 4
  %array139 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %e111, i32 0, i32 3
  %array140 = getelementptr [2 x i32], [2 x i32]* %array139, i32 0, i32 1
  store i32 8, i32* %array140, align 4
  %e142 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %e111, i32 0, i32 3
  %e143 = getelementptr [2 x i32], [2 x i32]* %e142, i32 0, i32 1
  %e144 = load i32, i32* %e143, align 4
  %e145 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %e111, i32 0, i32 0
  %e146 = getelementptr [2 x i32], [2 x i32]* %e145, i32 0, i32 0
  %e147 = load i32, i32* %e146, align 4
  %add_148 = add i32 %e144, %e147
  %e149 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %e111, i32 0, i32 0
  %e150 = getelementptr [2 x i32], [2 x i32]* %e149, i32 0, i32 1
  %e151 = load i32, i32* %e150, align 4
  %add_152 = add i32 %add_148, %e151
  %a153 = getelementptr [4 x [2 x i32]], [4 x [2 x i32]]* %a11, i32 0, i32 2
  %a154 = getelementptr [2 x i32], [2 x i32]* %a153, i32 0, i32 0
  %a155 = load i32, i32* %a154, align 4
  %add_156 = add i32 %add_152, %a155
  ret i32 %add_156
  ret i32 0
}

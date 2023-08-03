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

define i32 @foo() {
fooEntry1:
  %arr11 = alloca [16 x i32], align 4
  %array12 = getelementptr [16 x i32], [16 x i32]* %arr11, i32 0, i32 0
  store i32 0, i32* %array12, align 4
  %array14 = getelementptr [16 x i32], [16 x i32]* %arr11, i32 0, i32 1
  store i32 1, i32* %array14, align 4
  %array16 = getelementptr [16 x i32], [16 x i32]* %arr11, i32 0, i32 2
  store i32 2, i32* %array16, align 4
  %array18 = getelementptr [16 x i32], [16 x i32]* %arr11, i32 0, i32 3
  store i32 3, i32* %array18, align 4
  %array20 = getelementptr [16 x i32], [16 x i32]* %arr11, i32 0, i32 4
  store i32 0, i32* %array20, align 4
  %array22 = getelementptr [16 x i32], [16 x i32]* %arr11, i32 0, i32 5
  store i32 1, i32* %array22, align 4
  %array24 = getelementptr [16 x i32], [16 x i32]* %arr11, i32 0, i32 6
  store i32 2, i32* %array24, align 4
  %array26 = getelementptr [16 x i32], [16 x i32]* %arr11, i32 0, i32 7
  store i32 3, i32* %array26, align 4
  %array28 = getelementptr [16 x i32], [16 x i32]* %arr11, i32 0, i32 8
  store i32 0, i32* %array28, align 4
  %array30 = getelementptr [16 x i32], [16 x i32]* %arr11, i32 0, i32 9
  store i32 1, i32* %array30, align 4
  %array32 = getelementptr [16 x i32], [16 x i32]* %arr11, i32 0, i32 10
  store i32 2, i32* %array32, align 4
  %array34 = getelementptr [16 x i32], [16 x i32]* %arr11, i32 0, i32 11
  store i32 3, i32* %array34, align 4
  %array36 = getelementptr [16 x i32], [16 x i32]* %arr11, i32 0, i32 12
  store i32 0, i32* %array36, align 4
  %array38 = getelementptr [16 x i32], [16 x i32]* %arr11, i32 0, i32 13
  store i32 1, i32* %array38, align 4
  %array40 = getelementptr [16 x i32], [16 x i32]* %arr11, i32 0, i32 14
  store i32 2, i32* %array40, align 4
  %array42 = getelementptr [16 x i32], [16 x i32]* %arr11, i32 0, i32 15
  store i32 3, i32* %array42, align 4
  %a44 = alloca i32, align 4
  store i32 3, i32* %a44, align 4
  %b46 = alloca i32, align 4
  store i32 7, i32* %b46, align 4
  %c48 = alloca i32, align 4
  store i32 5, i32* %c48, align 4
  %d50 = alloca i32, align 4
  store i32 6, i32* %d50, align 4
  %e52 = alloca i32, align 4
  store i32 1, i32* %e52, align 4
  %f54 = alloca i32, align 4
  store i32 0, i32* %f54, align 4
  %g56 = alloca i32, align 4
  store i32 3, i32* %g56, align 4
  %h58 = alloca i32, align 4
  store i32 5, i32* %h58, align 4
  %i60 = alloca i32, align 4
  store i32 4, i32* %i60, align 4
  %j62 = alloca i32, align 4
  store i32 2, i32* %j62, align 4
  %k64 = alloca i32, align 4
  store i32 7, i32* %k64, align 4
  %l66 = alloca i32, align 4
  store i32 9, i32* %l66, align 4
  %m68 = alloca i32, align 4
  store i32 8, i32* %m68, align 4
  %n70 = alloca i32, align 4
  store i32 1, i32* %n70, align 4
  %o72 = alloca i32, align 4
  store i32 4, i32* %o72, align 4
  %p74 = alloca i32, align 4
  store i32 6, i32* %p74, align 4
  %sum176 = alloca i32, align 4
  %a77 = load i32, i32* %a44, align 4
  %b78 = load i32, i32* %b46, align 4
  %add_79 = add i32 %a77, %b78
  %c80 = load i32, i32* %c48, align 4
  %add_81 = add i32 %add_79, %c80
  %d82 = load i32, i32* %d50, align 4
  %add_83 = add i32 %add_81, %d82
  %e84 = load i32, i32* %e52, align 4
  %add_85 = add i32 %add_83, %e84
  %f86 = load i32, i32* %f54, align 4
  %add_87 = add i32 %add_85, %f86
  %g88 = load i32, i32* %g56, align 4
  %add_89 = add i32 %add_87, %g88
  %h90 = load i32, i32* %h58, align 4
  %add_91 = add i32 %add_89, %h90
  store i32 %add_91, i32* %sum176, align 4
  %sum293 = alloca i32, align 4
  %i94 = load i32, i32* %i60, align 4
  %j95 = load i32, i32* %j62, align 4
  %add_96 = add i32 %i94, %j95
  %k97 = load i32, i32* %k64, align 4
  %add_98 = add i32 %add_96, %k97
  %l99 = load i32, i32* %l66, align 4
  %add_100 = add i32 %add_98, %l99
  %m101 = load i32, i32* %m68, align 4
  %add_102 = add i32 %add_100, %m101
  %n103 = load i32, i32* %n70, align 4
  %add_104 = add i32 %add_102, %n103
  %o105 = load i32, i32* %o72, align 4
  %add_106 = add i32 %add_104, %o105
  %p107 = load i32, i32* %p74, align 4
  %add_108 = add i32 %add_106, %p107
  store i32 %add_108, i32* %sum293, align 4
  %sum1110 = load i32, i32* %sum176, align 4
  %sum2111 = load i32, i32* %sum293, align 4
  %add_112 = add i32 %sum1110, %sum2111
  %a113 = load i32, i32* %a44, align 4
  %arr114 = getelementptr [16 x i32], [16 x i32]* %arr11, i32 0, i32 %a113
  %arr115 = load i32, i32* %arr114, align 4
  %add_116 = add i32 %add_112, %arr115
  ret i32 %add_116
  ret i32 0
}
define i32 @main() {
mainEntry2:
  %a117 = alloca i32, align 4
  store i32 3, i32* %a117, align 4
  %b119 = alloca i32, align 4
  store i32 7, i32* %b119, align 4
  %c121 = alloca i32, align 4
  store i32 5, i32* %c121, align 4
  %d123 = alloca i32, align 4
  store i32 6, i32* %d123, align 4
  %e125 = alloca i32, align 4
  store i32 1, i32* %e125, align 4
  %f127 = alloca i32, align 4
  store i32 0, i32* %f127, align 4
  %g129 = alloca i32, align 4
  store i32 3, i32* %g129, align 4
  %h131 = alloca i32, align 4
  store i32 5, i32* %h131, align 4
  %i133 = alloca i32, align 4
  store i32 4, i32* %i133, align 4
  %j135 = alloca i32, align 4
  store i32 2, i32* %j135, align 4
  %k137 = alloca i32, align 4
  store i32 7, i32* %k137, align 4
  %l139 = alloca i32, align 4
  store i32 9, i32* %l139, align 4
  %m141 = alloca i32, align 4
  store i32 8, i32* %m141, align 4
  %n143 = alloca i32, align 4
  store i32 1, i32* %n143, align 4
  %o145 = alloca i32, align 4
  store i32 4, i32* %o145, align 4
  %p147 = alloca i32, align 4
  store i32 6, i32* %p147, align 4
  %sum1149 = alloca i32, align 4
  %a150 = load i32, i32* %a117, align 4
  %b151 = load i32, i32* %b119, align 4
  %add_152 = add i32 %a150, %b151
  %c153 = load i32, i32* %c121, align 4
  %add_154 = add i32 %add_152, %c153
  %d155 = load i32, i32* %d123, align 4
  %add_156 = add i32 %add_154, %d155
  %e157 = load i32, i32* %e125, align 4
  %add_158 = add i32 %add_156, %e157
  %f159 = load i32, i32* %f127, align 4
  %add_160 = add i32 %add_158, %f159
  %g161 = load i32, i32* %g129, align 4
  %add_162 = add i32 %add_160, %g161
  %h163 = load i32, i32* %h131, align 4
  %add_164 = add i32 %add_162, %h163
  store i32 %add_164, i32* %sum1149, align 4
  %sum2166 = alloca i32, align 4
  %i167 = load i32, i32* %i133, align 4
  %j168 = load i32, i32* %j135, align 4
  %add_169 = add i32 %i167, %j168
  %k170 = load i32, i32* %k137, align 4
  %add_171 = add i32 %add_169, %k170
  %l172 = load i32, i32* %l139, align 4
  %add_173 = add i32 %add_171, %l172
  %m174 = load i32, i32* %m141, align 4
  %add_175 = add i32 %add_173, %m174
  %n176 = load i32, i32* %n143, align 4
  %add_177 = add i32 %add_175, %n176
  %o178 = load i32, i32* %o145, align 4
  %add_179 = add i32 %add_177, %o178
  %p180 = load i32, i32* %p147, align 4
  %add_181 = add i32 %add_179, %p180
  store i32 %add_181, i32* %sum2166, align 4
  %sum1183 = load i32, i32* %sum1149, align 4
  %foo184 = call i32 @foo()
  %add_185 = add i32 %sum1183, %foo184
  store i32 %add_185, i32* %sum1149, align 4
  %q187 = alloca i32, align 4
  store i32 4, i32* %q187, align 4
  %r189 = alloca i32, align 4
  store i32 7, i32* %r189, align 4
  %s191 = alloca i32, align 4
  store i32 2, i32* %s191, align 4
  %t193 = alloca i32, align 4
  store i32 5, i32* %t193, align 4
  %u195 = alloca i32, align 4
  store i32 8, i32* %u195, align 4
  %v197 = alloca i32, align 4
  store i32 0, i32* %v197, align 4
  %w199 = alloca i32, align 4
  store i32 6, i32* %w199, align 4
  %x201 = alloca i32, align 4
  store i32 3, i32* %x201, align 4
  %sum2203 = load i32, i32* %sum2166, align 4
  %foo204 = call i32 @foo()
  %add_205 = add i32 %sum2203, %foo204
  store i32 %add_205, i32* %sum2166, align 4
  %i207 = load i32, i32* %i133, align 4
  store i32 %i207, i32* %a117, align 4
  %j209 = load i32, i32* %j135, align 4
  store i32 %j209, i32* %b119, align 4
  %k211 = load i32, i32* %k137, align 4
  store i32 %k211, i32* %c121, align 4
  %l213 = load i32, i32* %l139, align 4
  store i32 %l213, i32* %d123, align 4
  %m215 = load i32, i32* %m141, align 4
  store i32 %m215, i32* %e125, align 4
  %n217 = load i32, i32* %n143, align 4
  store i32 %n217, i32* %f127, align 4
  %o219 = load i32, i32* %o145, align 4
  store i32 %o219, i32* %g129, align 4
  %p221 = load i32, i32* %p147, align 4
  store i32 %p221, i32* %h131, align 4
  %sum3223 = alloca i32, align 4
  %q224 = load i32, i32* %q187, align 4
  %r225 = load i32, i32* %r189, align 4
  %add_226 = add i32 %q224, %r225
  %s227 = load i32, i32* %s191, align 4
  %add_228 = add i32 %add_226, %s227
  %t229 = load i32, i32* %t193, align 4
  %add_230 = add i32 %add_228, %t229
  %u231 = load i32, i32* %u195, align 4
  %add_232 = add i32 %add_230, %u231
  %v233 = load i32, i32* %v197, align 4
  %add_234 = add i32 %add_232, %v233
  %w235 = load i32, i32* %w199, align 4
  %add_236 = add i32 %add_234, %w235
  %x237 = load i32, i32* %x201, align 4
  %add_238 = add i32 %add_236, %x237
  store i32 %add_238, i32* %sum3223, align 4
  %sum240 = alloca i32, align 4
  %sum1241 = load i32, i32* %sum1149, align 4
  %sum2242 = load i32, i32* %sum2166, align 4
  %add_243 = add i32 %sum1241, %sum2242
  %sum3244 = load i32, i32* %sum3223, align 4
  %add_245 = add i32 %add_243, %sum3244
  store i32 %add_245, i32* %sum240, align 4
  %sum247 = load i32, i32* %sum240, align 4
  ret i32 %sum247
  ret i32 0
}

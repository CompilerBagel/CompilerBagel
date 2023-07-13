; ModuleID = 'module'
source_filename = "module"

@c0 = global i32 3
@g1 = global i32 1
define i32 @main() {
mainEntry1:
  %a0 = alloca i32, align 4
  %c1 = load i32, i32* @c0, align 4
  %add_2 = add i32 %c1, 2
  store i32 %add_2, i32* %a0, align 4
  %b4 = alloca i32, align 4
  %a5 = load i32, i32* %a0, align 4
  %g6 = load i32, i32* @g1, align 4
  %sub_7 = sub i32 %a5, %g6
  store i32 %sub_7, i32* %b4, align 4
  %a9 = load i32, i32* %a0, align 4
  %b10 = load i32, i32* %b4, align 4
  %sub_11 = sub i32 %a9, %b10
  store i32 %sub_11, i32* %a0, align 4
  %b13 = load i32, i32* %b4, align 4
  %a14 = load i32, i32* %a0, align 4
  %mul_15 = mul i32 %b13, %a14
  store i32 %mul_15, i32* %b4, align 4
  %e17 = alloca i32, align 4
  %b18 = load i32, i32* %b4, align 4
  %div_19 = div i32 100, %b18
  store i32 %div_19, i32* %e17, align 4
  %e21 = load i32, i32* %e17, align 4
  ret i32 %e21
}

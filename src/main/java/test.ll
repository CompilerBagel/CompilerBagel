; ModuleID = 'module'
source_filename = "module"

@c0 = global i32* 1
define i32 @main() {
mainEntry:
  %a0 = alloca i32, align 4
  store i32 1, i32* %a0, align 4
  %a2 = load i32, i32* %a0, align 4
  %icmp_3 = icmp ne i32 0, %a2
  %xor_4 = xor i1 %icmp_3, true
  %zext_5 = zext i1 %xor_4 to i32
  %b6 = alloca i32, align 4
  store i32 %zext_5, i32* %b6, align 4
  %b8 = load i32, i32* %b6, align 4
  %c9 = load i32, i32* @c0, align 4
  %add_10 = add i32 %b8, %c9
  %d11 = alloca i32, align 4
  store i32 %add_10, i32* %d11, align 4
  %a13 = load i32, i32* %a0, align 4
  %d14 = load i32, i32* %d11, align 4
  %mul_15 = mul i32 %a13, %d14
  ret i32 %mul_15
}

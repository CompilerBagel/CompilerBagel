; ModuleID = 'module'
source_filename = "module"

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
  ret i32 1
}

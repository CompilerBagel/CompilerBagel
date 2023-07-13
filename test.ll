; ModuleID = 'module'
source_filename = "module"

define i32 @main() {
mainEntry1:
  %a0 = alloca i32, align 4
  store i32 10, i32* %a0, align 4
  %b2 = alloca i32, align 4
  store i32 5, i32* %b2, align 4
  %b4 = load i32, i32* %b2, align 4
  ret i32 %b4
}

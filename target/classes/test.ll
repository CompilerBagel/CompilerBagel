; ModuleID = 'module'
source_filename = "module"

@c0 = global i32 1
define i32 @main() {
mainEntry:
  %a0 = alloca i32, align 4
  store i32 0, i32* %a0, align 4
  ret i32 0
}

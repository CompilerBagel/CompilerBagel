; ModuleID = 'module'
source_filename = "module"
define i32 @main() {
mainEntry:
  %a0 = allocai32, , align 4
  store i32* %a0, i32 1, , align 4
  ret i32 8
}

; ModuleID = 'module'
source_filename = "module"

@g_var0 = global i32* 2
define i32 @main() {
mainEntry:
  %a0 = alloca i32, align 4
  store i32 1, i32* %a0, align 4
  %a2 = load i32, i32* %a0, align 4
  %g_var3 = load i32, i32* @g_var0, align 4
  %add_4 = add i32 %a2, %g_var3
  ret i32 %add_4
}

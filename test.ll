; ModuleID = 'module'
source_filename = "module"

@a0 = global i32 10
define i32 @main() {
mainEntry:
}
define i32 @main() {
mainEntry:
  %a0 = load i32, i32* @a0, align 4
  %icmp_NE1 = icmp ne i32 %a0, 10
  %zext_2 = zext i1 %icmp_NE1 to i32
  %icmp_3 = icmp ne i32 %zext_2, 0
  br i1 %icmp_3, label %trueBlock, label %falseBlock
trueBlock:
  store i32 2, i32* @a0, align 4
  br label %afterBlock
falseBlock:
  store i32 20, i32* @a0, align 4
  br label %afterBlock
afterBlock:
  %a6 = load i32, i32* @a0, align 4
  ret i32 %a6
}

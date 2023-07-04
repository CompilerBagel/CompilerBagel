; ModuleID = 'module'
source_filename = "module"

define i32 @main() {
mainEntry:
  %a0 = alloca i32, align 4
  store i32 0, i32* %a0, align 4
  %b2 = alloca i32, align 4
  store i32 0, i32* %b2, align 4
  %a4 = load i32, i32* %a0, align 4
  %icmp_EQ5 = icmp eq i32 %a4, 0
  %zext_6 = zext i1 %icmp_EQ5 to i32
  %icmp_7 = icmp ne i32 %zext_6, 0
  br i1 %icmp_7, label %trueBlock, label %falseBlock
trueBlock:
  store i32* %b2, i32 1, align 4
  br label %afterBlock
falseBlock:
  br label %afterBlock
afterBlock:
  ret i32 0
}

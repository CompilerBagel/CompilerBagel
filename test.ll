; ModuleID = 'module'
source_filename = "module"

@c0 = global i32 0
define i32 @main() {
mainEntry:
  %a0 = alloca i32, align 4
  store i32 5, i32* %a0, align 4
  %b2 = alloca i32, align 4
  store i32 4, i32* %b2, align 4
  %a4 = load i32, i32* %a0, align 4
  %b5 = load i32, i32* %b2, align 4
  %icmp_GE6 = icmp sge i32 %a4, %b5
  %zext_7 = zext i1 %icmp_GE6 to i32
  %icmp_8 = icmp ne i32 %zext_7, 0
  br i1 %icmp_8, label %trueBlock, label %falseBlock
trueBlock:
  %a9 = load i32, i32* %a0, align 4
  store i32 %a9, i32* @c0, align 4
  br label %afterBlock
falseBlock:
  %b11 = load i32, i32* %b2, align 4
  store i32 %b11, i32* @c0, align 4
  br label %afterBlock
afterBlock:
  %d13 = alloca i32, align 4
  %c14 = load i32, i32* @c0, align 4
  store i32 %c14, i32* %d13, align 4
condBlock:
  %d16 = load i32, i32* %d13, align 4
  %icmp_GT17 = icmp sgt i32 %d16, 0
  %zext_18 = zext i1 %icmp_GT17 to i32
  %icmp_19 = icmp ne i32 %zext_18, 0
  br i1 %icmp_19, label %bodyBlock, label %afterBlock
bodyBlock:
  %d20 = load i32, i32* %d13, align 4
  %sub_21 = sub i32 %d20, 1
  store i32 %sub_21, i32* %d13, align 4
  %c23 = load i32, i32* @c0, align 4
  %add_24 = add i32 %c23, 1
  store i32 %add_24, i32* @c0, align 4
  br label %afterBlock
afterBlock:
  %c26 = load i32, i32* @c0, align 4
  ret i32 %c26
}

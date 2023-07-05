; ModuleID = 'module'
source_filename = "module"

define i32 @whileIf() {
whileIfEntry:
  %a0 = alloca i32, align 4
  store i32 0, i32* %a0, align 4
  store i32 5, i32* %a0, align 4
  %b3 = alloca i32, align 4
  store i32 0, i32* %b3, align 4
  store i32 0, i32* %b3, align 4
  %a6 = load i32, i32* %a0, align 4
  %icmp_EQ7 = icmp eq i32 %a6, 5
  %zext_8 = zext i1 %icmp_EQ7 to i32
  %icmp_9 = icmp ne i32 %zext_8, 0
  br i1 %icmp_9, label %trueBlock, label %falseBlock
trueBlock:
  store i32 25, i32* %b3, align 4
  br label %afterBlock
falseBlock:
  %a11 = load i32, i32* %a0, align 4
  %icmp_EQ12 = icmp eq i32 %a11, 10
  %zext_13 = zext i1 %icmp_EQ12 to i32
  %icmp_14 = icmp ne i32 %zext_13, 0
  br i1 %icmp_14, label %trueBlock, label %falseBlock
afterBlock:
  %b19 = load i32, i32* %b3, align 4
trueBlock:
  store i32 42, i32* %b3, align 4
  br label %afterBlock
falseBlock:
  %a16 = load i32, i32* %a0, align 4
  %mul_17 = mul i32 %a16, 2
  store i32 %mul_17, i32* %b3, align 4
  br label %afterBlock
afterBlock:
  br label %afterBlock
}
define i32 @main() {
mainEntry:
  %whileIf20 = call i32 @whileIf()
}

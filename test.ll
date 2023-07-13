; ModuleID = 'module'
source_filename = "module"

@delta0 = global i32 1
define i32 @main() {
mainEntry1:
  %a0 = alloca i32, align 4
  store i32 1, i32* %a0, align 4
  %b2 = alloca i32, align 4
  store i32 2, i32* %b2, align 4
  %c4 = alloca i32, align 4
  store i32 3, i32* %c4, align 4
  %a6 = load i32, i32* %a0, align 4
  %b7 = load i32, i32* %b2, align 4
  %icmp_GT8 = icmp sgt i32 %a6, %b7
  %zext_9 = zext i1 %icmp_GT8 to i32
  %icmp_10 = icmp ne i32 %zext_9, 0
  br i1 %icmp_10, label %trueBlock2, label %falseBlock3
trueBlock2:
  %b11 = load i32, i32* %b2, align 4
  %c12 = load i32, i32* %c4, align 4
  %icmp_GT13 = icmp sgt i32 %b11, %c12
  %zext_14 = zext i1 %icmp_GT13 to i32
  %icmp_15 = icmp ne i32 %zext_14, 0
  br i1 %icmp_15, label %trueBlock5, label %falseBlock6
falseBlock3:
  %a19 = load i32, i32* %a0, align 4
  %b20 = load i32, i32* %b2, align 4
  %icmp_EQ21 = icmp eq i32 %a19, %b20
  %zext_22 = zext i1 %icmp_EQ21 to i32
  %icmp_23 = icmp ne i32 %zext_22, 0
  br i1 %icmp_23, label %trueBlock8, label %falseBlock9
afterBlock4:
  %c34 = load i32, i32* %c4, align 4
  ret i32 %c34
trueBlock5:
  %c16 = load i32, i32* %c4, align 4
  %mul_17 = mul i32 %c16, 2
  store i32 %mul_17, i32* %c4, align 4
  br label %afterBlock7
falseBlock6:
  br label %afterBlock7
afterBlock7:
  br label %afterBlock4
trueBlock8:
  %c24 = load i32, i32* %c4, align 4
  %a25 = load i32, i32* %a0, align 4
  %add_26 = add i32 %c24, %a25
  %b27 = load i32, i32* %b2, align 4
  %add_28 = add i32 %add_26, %b27
  store i32 %add_28, i32* %c4, align 4
  br label %afterBlock10
falseBlock9:
  %a30 = load i32, i32* %a0, align 4
  %b31 = load i32, i32* %b2, align 4
  %sub_32 = sub i32 %a30, %b31
  store i32 %sub_32, i32* %c4, align 4
  br label %afterBlock10
afterBlock10:
  br label %afterBlock4
}

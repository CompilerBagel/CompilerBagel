main:
    addi sp, sp, -32
    sd sp, 24(ra)
    sd sp, 16(s0)
    addi s0, sp, 32
mainEntry1:
    li a0, 1
    sw a0, 24(s0)
    li a0, 2
    sw a0, 28(s0)
    lw a0, 24(s0)
    lw a1, 28(s0)
    blt a0, a1, Branch1
    j	Branch1_else
Branch1:
    li a2, 1
    j	Branch1_end
Branch1_else:
    li a2, 0
Branch1_end:
    mv a2, a0
    li a1, 0
    bne a0, a1, Branch2
    j	Branch2_else
Branch2:
    li a2, 1
    j	Branch2_end
Branch2_else:
    li a2, 0
Branch2_end:
    li a0, 1
    bne	a2, a0, trueBlock2
    j	falseBlock3
trueBlock2:
    li a0, 3
    sw a0, 24(s0)
    j	afterBlock4
falseBlock3:
    li a0, 3
    sw a0, 28(s0)
    j	afterBlock4
afterBlock4:
    li a0, 0
    lw ra, 24(sp)
    lw s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    lw ra, 24(sp)
    lw s0, 16(sp)
    addi sp, sp, 32
    ret
getch:
before_main:
getfarray:
putint:
putfarray:
end_time:
main:
getarray:
after_main:
getint:
putarray:
start_time:
putfloat:
putch:
getfloat:

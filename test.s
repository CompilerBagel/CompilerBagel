.global main
.data
.text
main:
mainEntry1:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
    li a0, 0
    sw a0, -20(s0)
    li a0, 10
    sw a0, -20(s0)
    lw a0, -20(s0)
    li a1, 0
    subw a0, a1, a0
    snez a0, a0
    xori a0, a0, 1
    mv a0, a1
    li a2, 0
    subw a1, a2, a1
    snez a1, a1
    xori a1, a1, 1
    mv a1, a2
    li a3, 0
    subw a2, a3, a2
    snez a2, a2
    xori a2, a2, 1
    mv a2, a3
    addiw a3, a3, 0
    li a4, 0
    subw a3, a3, a4
    snez a3, a3
    bne a3, zero, trueBlock2
    j	falseBlock3
trueBlock2:
    li a3, -1
    sw a3, -20(s0)
    j	afterBlock4
falseBlock3:
    li a3, 0
    sw a3, -20(s0)
    j	afterBlock4
afterBlock4:
    lw a3, -20(s0)
    addw a0, a3, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    ret

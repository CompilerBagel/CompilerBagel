.global main
.data
.text
deepWhileBr:
deepWhileBrEntry1:
    addi sp, sp, -48
    sd ra, 40(sp)
    sd s0, 32(sp)
    addi s0, sp, 48
    sw a0, -28(s0)
    sw a0, -32(s0)
    li a0, 0
    sw a0, -36(s0)
    lw a1, -28(s0)
    lw a2, -32(s0)
    addw a1, a1, a2
    sw a1, -36(s0)
    j	condBlock2
condBlock2:
    lw a1, -36(s0)
    li a2, 75
    slt a1, a1, a2
    bne a1, zero, bodyBlock3
    j	afterBlock4
bodyBlock3:
    li a1, 0
    sw a1, -40(s0)
    li a1, 42
    sw a1, -40(s0)
    lw a1, -36(s0)
    li a2, 100
    slt a1, a1, a2
    bne a1, zero, trueBlock5
    j	falseBlock6
afterBlock4:
    lw a1, -36(s0)
    addw a0, a1, 0
    ld sp, 40(ra)
    ld sp, 32(s0)
    addi sp, sp, 48
    ret
    li a0, 0
    ret
trueBlock5:
    lw a1, -36(s0)
    lw a2, -40(s0)
    addw a1, a1, a2
    sw a1, -36(s0)
    lw a1, -36(s0)
    li a2, 99
    slt a1, a2, a1
    bne a1, zero, trueBlock8
    j	falseBlock9
falseBlock6:
    j	afterBlock7
afterBlock7:
    j	condBlock2
trueBlock8:
    li a1, 0
    sw a1, -44(s0)
    lw a1, -40(s0)
    li a2, 2
    mulw a1, a1, a2
    sw a1, -44(s0)
    li a1, 1
    li a2, 1
    subw a1, a1, a2
    seqz a1, a1
    bne a1, zero, trueBlock11
    j	falseBlock12
falseBlock9:
    j	afterBlock10
afterBlock10:
    j	afterBlock7
trueBlock11:
    lw a1, -44(s0)
    li a2, 2
    mulw a1, a1, a2
    sw a1, -36(s0)
    j	afterBlock13
falseBlock12:
    j	afterBlock13
afterBlock13:
    j	afterBlock10
main:
mainEntry14:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
    li a0, 0
    sw a0, -20(s0)
    li a1, 2
    sw a1, -20(s0)
    lw a1, -20(s0)
    lw a2, -20(s0)
    mv a1, a1
    mv a1, a2
    call deepWhileBr
    addw a0, a1, 0
    ld sp, 24(ra)
    ld sp, 16(s0)
    addi sp, sp, 32
    ret
    li a0, 0
    ret

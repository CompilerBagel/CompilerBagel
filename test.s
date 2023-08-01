.global main
.data
.text
deepWhileBr:
    addi sp, sp, -48
    sd ra, 40(sp)
    sd s0, 32(sp)
    addi s0, sp, 48
deepWhileBrEntry1:
    sw a0, -28(s0)
    sw a0, -32(s0)
    li a0, 0
    sw a0, -36(s0)
    lw a0, -28(s0)
    lw a1, -32(s0)
    addw a2, a0, a1
    sw a2, -36(s0)
    j	condBlock2
condBlock2:
    lw a0, -36(s0)
    li a1, 75
    slt a2, a0, a1
    bne a2, zero, bodyBlock3
    j	afterBlock4
bodyBlock3:
    li a0, 0
    sw a0, -40(s0)
    li a0, 42
    sw a0, -40(s0)
    lw a0, -36(s0)
    li a1, 100
    slt a2, a0, a1
    bne a2, zero, trueBlock5
    j	falseBlock6
afterBlock4:
    lw a0, -36(s0)
    addw a0, a0, 0
    ld ra, 40(sp)
    ld s0, 32(sp)
    addi sp, sp, 48
    ret
    li a0, 0
    ld ra, 40(sp)
    ld s0, 32(sp)
    addi sp, sp, 48
    ret
trueBlock5:
    lw a0, -36(s0)
    lw a1, -40(s0)
    addw a2, a0, a1
    sw a2, -36(s0)
    lw a0, -36(s0)
    li a1, 99
    slt a2, a1, a0
    bne a2, zero, trueBlock8
    j	falseBlock9
falseBlock6:
    j	afterBlock7
afterBlock7:
    j	condBlock2
trueBlock8:
    li a0, 0
    sw a0, -44(s0)
    lw a0, -40(s0)
    li a1, 2
    mulw a1, a0, a1
    sw a1, -44(s0)
    li a0, 1
    li a1, 1
    subw a2, a0, a1
    seqz a0, a2
    bne a0, zero, trueBlock11
    j	falseBlock12
falseBlock9:
    j	afterBlock10
afterBlock10:
    j	afterBlock7
trueBlock11:
    lw a0, -44(s0)
    li a1, 2
    mulw a1, a0, a1
    sw a1, -36(s0)
    j	afterBlock13
falseBlock12:
    j	afterBlock13
afterBlock13:
    j	afterBlock10
main:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
mainEntry14:
    li a0, 0
    sw a0, -20(s0)
    li a0, 2
    sw a0, -20(s0)
    lw a0, -20(s0)
    lw a0, -20(s0)
    lw a0, 0(a0)
    lw a0, 0(a0)
    call deepWhileBr
    addw a0, a0, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret

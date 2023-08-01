.global main
.data
.text
main:
    addi sp, sp, -48
    sd ra, 40(sp)
    sd s0, 32(sp)
    addi s0, sp, 48
mainEntry1:
    li a0, 0
    sw a0, -20(s0)
    li a1, 0
    sw a1, -24(s0)
    j	condBlock2
condBlock2:
    lw a1, -24(s0)
    li a2, 20
    slt a3, a1, a2
    bne a3, zero, bodyBlock3
    j	afterBlock4
bodyBlock3:
    li a1, 0
    sw a1, -28(s0)
    j	condBlock5
afterBlock4:
    lw a1, -20(s0)
    addw a0, a1, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
condBlock5:
    lw a1, -28(s0)
    li a2, 10
    slt a3, a1, a2
    bne a3, zero, bodyBlock6
    j	afterBlock7
bodyBlock6:
    li a1, 0
    sw a1, -32(s0)
    j	condBlock8
afterBlock7:
    lw a1, -24(s0)
    addiw a2, a1, 1
    sw a2, -24(s0)
    j	condBlock2
condBlock8:
    lw a1, -32(s0)
    li a2, 5
    slt a3, a1, a2
    bne a3, zero, bodyBlock9
    j	afterBlock10
bodyBlock9:
    li a1, 0
    sw a1, -36(s0)
    j	condBlock11
afterBlock10:
    lw a1, -28(s0)
    addiw a2, a1, 1
    sw a2, -28(s0)
    j	condBlock5
    lw a1, -28(s0)
    addiw a2, a1, 1
    sw a2, -28(s0)
    j	condBlock5
condBlock11:
    lw a1, -36(s0)
    li a2, 3
    slt a3, a1, a2
    bne a3, zero, bodyBlock12
    j	afterBlock13
bodyBlock12:
    lw a1, -36(s0)
    addiw a2, a1, 1
    li a1, 3
    slt a3, a2, a1
    xori a1, a3, 1
    bne a1, zero, trueBlock14
    j	falseBlock15
afterBlock13:
    j	condBlock29
trueBlock14:
    lw a1, -36(s0)
    bne a1, zero, trueBlock17
    j	falseBlock18
falseBlock15:
    j	afterBlock16
afterBlock16:
    li a1, 0
    sw a1, -40(s0)
    j	condBlock26
trueBlock17:
    lw a1, -36(s0)
    lw a1, -36(s0)
    li a2, 0
    subw a3, a2, a1
    snez a1, a3
    bne a1, zero, trueBlock20
    j	falseBlock21
falseBlock18:
    j	afterBlock19
afterBlock19:
    j	afterBlock16
trueBlock20:
    lw a1, -36(s0)
    addiw a2, a1, 1
    li a1, 3
    slt a3, a2, a1
    xori a1, a3, 1
    bne a1, zero, trueBlock23
    j	falseBlock24
falseBlock21:
    j	afterBlock22
afterBlock22:
    j	afterBlock19
trueBlock23:
    j	afterBlock13
    j	condBlock11
    j	afterBlock25
falseBlock24:
    j	afterBlock25
afterBlock25:
    j	afterBlock22
condBlock26:
    lw a1, -40(s0)
    li a2, 2
    slt a3, a1, a2
    bne a3, zero, bodyBlock27
    j	afterBlock28
bodyBlock27:
    lw a1, -40(s0)
    addiw a2, a1, 1
    sw a2, -40(s0)
    j	condBlock26
    j	afterBlock28
    lw a1, -20(s0)
    addiw a2, a1, 1
    sw a2, -20(s0)
    j	condBlock26
afterBlock28:
    lw a1, -36(s0)
    addiw a2, a1, 1
    sw a2, -36(s0)
    lw a1, -20(s0)
    addiw a2, a1, 1
    sw a2, -20(s0)
    j	condBlock11
condBlock29:
    li a1, 1
    bne a1, zero, bodyBlock30
    j	afterBlock31
bodyBlock30:
    j	condBlock32
afterBlock31:
    lw a1, -32(s0)
    addiw a2, a1, 1
    sw a2, -32(s0)
    j	condBlock8
condBlock32:
    li a1, 1
    bne a1, zero, bodyBlock33
    j	afterBlock34
bodyBlock33:
    j	afterBlock34
    j	condBlock32
afterBlock34:
    j	afterBlock31
    j	condBlock29

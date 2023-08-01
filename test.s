.global main
.data
    e:
        .word 0
    f:
        .word 0
    g:
        .word 0
    h:
        .word 0
.text
EightWhile:
EightWhileEntry1:
    addi sp, sp, -48
    sd ra, 40(sp)
    sd s0, 32(sp)
    addi s0, sp, 48
    li a0, 0
    sw a0, -20(s0)
    li a0, 5
    sw a0, -20(s0)
    li a0, 0
    sw a0, -24(s0)
    li a0, 0
    sw a0, -28(s0)
    li a0, 6
    sw a0, -24(s0)
    li a0, 7
    sw a0, -28(s0)
    li a0, 0
    sw a0, -32(s0)
    li a0, 10
    sw a0, -32(s0)
    j	condBlock2
condBlock2:
    lw a0, -20(s0)
    li a1, 20
    slt a0, a0, a1
    li a1, 0
    subw a0, a0, a1
    snez a0, a0
    bne a0, zero, bodyBlock3
    j	afterBlock4
bodyBlock3:
    lw a0, -20(s0)
    addiw a0, a0, 3
    sw a0, -20(s0)
    j	condBlock5
afterBlock4:
    lw a0, -20(s0)
    lw a1, -24(s0)
    lw a2, -32(s0)
    addw a1, a1, a2
    addw a0, a0, a1
    lw a1, -28(s0)
    addw a0, a0, a1
    la t0, e
    lw a2, 0(t0)
    lw a1, -32(s0)
    addw a1, a2, a1
    la t0, g
    lw a1, 0(t0)
    subw a1, a1, a1
    la t0, h
    lw a1, 0(t0)
    addw a1, a1, a1
    subw a0, a0, a1
    addw a0, a0, 0
    ld ra, 40(sp)
    ld s0, 32(sp)
    addi sp, sp, 48
    ret
    li a0, 0
    ret
condBlock5:
    lw a0, -24(s0)
    li a1, 10
    slt a0, a0, a1
    li a1, 0
    subw a0, a0, a1
    snez a0, a0
    bne a0, zero, bodyBlock6
    j	afterBlock7
bodyBlock6:
    lw a0, -24(s0)
    addiw a0, a0, 1
    sw a0, -24(s0)
    j	condBlock8
afterBlock7:
    lw a0, -24(s0)
    addiw a0, a0, -2
    sw a0, -24(s0)
    j	condBlock2
condBlock8:
    lw a0, -28(s0)
    li a1, 7
    subw a0, a0, a1
    seqz a0, a0
    li a1, 0
    subw a0, a0, a1
    snez a0, a0
    bne a0, zero, bodyBlock9
    j	afterBlock10
bodyBlock9:
    lw a0, -28(s0)
    addiw a0, a0, -1
    sw a0, -28(s0)
    j	condBlock11
afterBlock10:
    lw a0, -28(s0)
    addiw a0, a0, 1
    sw a0, -28(s0)
    j	condBlock5
condBlock11:
    lw a0, -32(s0)
    li a1, 20
    slt a0, a0, a1
    li a1, 0
    subw a0, a0, a1
    snez a0, a0
    bne a0, zero, bodyBlock12
    j	afterBlock13
bodyBlock12:
    lw a0, -32(s0)
    addiw a0, a0, 3
    sw a0, -32(s0)
    j	condBlock14
afterBlock13:
    lw a0, -32(s0)
    addiw a0, a0, -1
    sw a0, -32(s0)
    j	condBlock8
condBlock14:
    la t0, e
    lw a0, 0(t0)
    li a0, 1
    slt a0, a0, a0
    li a1, 0
    subw a0, a0, a1
    snez a0, a0
    bne a0, zero, bodyBlock15
    j	afterBlock16
bodyBlock15:
    la t0, e
    lw a0, 0(t0)
    addiw a0, a0, -1
    la t0, e
    sw a0, 0(t0)
    j	condBlock17
afterBlock16:
    la t0, e
    lw a0, 0(t0)
    addiw a0, a0, 1
    la t0, e
    sw a0, 0(t0)
    j	condBlock11
condBlock17:
    la t0, f
    lw a0, 0(t0)
    li a0, 2
    slt a0, a0, a0
    li a1, 0
    subw a0, a0, a1
    snez a0, a0
    bne a0, zero, bodyBlock18
    j	afterBlock19
bodyBlock18:
    la t0, f
    lw a0, 0(t0)
    addiw a0, a0, -2
    la t0, f
    sw a0, 0(t0)
    j	condBlock20
afterBlock19:
    la t0, f
    lw a0, 0(t0)
    addiw a0, a0, 1
    la t0, f
    sw a0, 0(t0)
    j	condBlock14
condBlock20:
    la t0, g
    lw a1, 0(t0)
    li a0, 3
    slt a0, a1, a0
    li a1, 0
    subw a0, a0, a1
    snez a0, a0
    bne a0, zero, bodyBlock21
    j	afterBlock22
bodyBlock21:
    la t0, g
    lw a0, 0(t0)
    addiw a0, a0, 10
    la t0, g
    sw a0, 0(t0)
    j	condBlock23
afterBlock22:
    la t0, g
    lw a0, 0(t0)
    addiw a0, a0, -8
    la t0, g
    sw a0, 0(t0)
    j	condBlock17
condBlock23:
    la t0, h
    lw a1, 0(t0)
    li a0, 10
    slt a0, a1, a0
    li a1, 0
    subw a0, a0, a1
    snez a0, a0
    bne a0, zero, bodyBlock24
    j	afterBlock25
bodyBlock24:
    la t0, h
    lw a0, 0(t0)
    addiw a0, a0, 8
    la t0, h
    sw a0, 0(t0)
    j	condBlock23
afterBlock25:
    la t0, h
    lw a0, 0(t0)
    addiw a0, a0, -1
    la t0, h
    sw a0, 0(t0)
    j	condBlock20
main:
mainEntry26:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
    li a0, 1
    la t0, g
    sw a0, 0(t0)
    li a0, 2
    la t0, h
    sw a0, 0(t0)
    li a0, 4
    la t0, e
    sw a0, 0(t0)
    li a0, 6
    la t0, f
    sw a0, 0(t0)
    call EightWhile
    addw a0, a0, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    ret

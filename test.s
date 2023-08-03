.global main
.data
.text
main:
mainEntry1:
    addi sp, sp, -48
    sd ra, 40(sp)
    sd s0, 32(sp)
    addi s0, sp, 48
    li a1, 0
    sw a1, -32(s0)
    li a1, 0
    sw a1, -36(s0)
    j	condBlock2
condBlock2:
    lw a1, -36(s0)
    li a2, 2
    subw a1, a1, a2
    snez a1, a1
    li a2, 0
    subw a1, a1, a2
    snez a1, a1
    bne a1, zero, bodyBlock3
    j	afterBlock4
bodyBlock3:
    lw a1, -36(s0)
    li a2, 4
    mulw a1, a1, a2
    addi a1, a1, 24
    sub a1, s0, a1
    lw a2, -36(s0)
    sw a2, 0(a1)
    lw a1, -36(s0)
    addiw a1, a1, 1
    sw a1, -36(s0)
    j	condBlock2
afterBlock4:
    j	condBlock5
condBlock5:
    lw a1, -36(s0)
    li a2, 2
    subw a1, a1, a2
    snez a1, a1
    li a2, 0
    subw a1, a1, a2
    snez a1, a1
    bne a1, zero, bodyBlock6
    j	afterBlock7
bodyBlock6:
    lw a1, -36(s0)
    addiw a1, a1, -1
    sw a1, -36(s0)
    lw a1, -32(s0)
    lw a2, -36(s0)
    li a3, 4
    mulw a2, a2, a3
    addi a2, a2, 24
    sub a2, s0, a2
    lw a2, 0(a2)
    addw a1, a1, a2
    sw a1, -32(s0)
    j	condBlock5
afterBlock7:
    ld ra, 40(sp)
    ld s0, 32(sp)
    addi sp, sp, 48
    lw a1, -32(s0)
    addi a0, a1, 0
    ret

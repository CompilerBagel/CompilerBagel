.global main
.data
    arr:
        .word 1
        .word 2
        .word 33
        .word 4
        .word 5
        .word 6
    N:
        .word -1
.text
main:
mainEntry1:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
    li a1, 0
    sw a1, -24(s0)
    li a1, 0
    sw a1, -28(s0)
    j	condBlock2
condBlock2:
    lw a1, -24(s0)
    li a2, 6
    slt a1, a1, a2
    mv a1, a1
    li a2, 0
    subw a1, a1, a2
    snez a2, a1
    bne a2, zero, bodyBlock3
    j	afterBlock4
bodyBlock3:
    lw a2, -28(s0)
    lw a3, -24(s0)
    la a4, arr
    li a4, 4
    mulw a3, a3, a4
    add a3, a4, a3
    lw a2, 0(a3)
    addw a2, a2, a2
    sw a2, -28(s0)
    lw a2, -24(s0)
    addiw a2, a2, 1
    sw a2, -24(s0)
    j	condBlock2
afterBlock4:
    lw a2, -28(s0)
    addw a0, a2, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    ret

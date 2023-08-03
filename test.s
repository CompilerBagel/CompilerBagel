.global main
.data
    getch:
    arr:
        .word 1
        .word 2
        .word 33
        .word 4
        .word 5
        .word 6
    before_main:
    getfarray:
    putint:
    putfarray:
    end_time:
    getarray:
    N:
        .word -1
    after_main:
    getint:
    putarray:
    start_time:
    putfloat:
    putch:
    getfloat:
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
    li a2, 0
    subw a1, a1, a2
    snez a1, a1
    bne a1, zero, bodyBlock3
    j	afterBlock4
bodyBlock3:
    lw a1, -28(s0)
    lw a2, -24(s0)
    la a3, arr
    li a3, 4
    mulw a2, a2, a3
    add a2, a3, a2
    lw a1, 0(a2)
    addw a1, a1, a1
    sw a1, -28(s0)
    lw a1, -24(s0)
    addiw a1, a1, 1
    sw a1, -24(s0)
    j	condBlock2
afterBlock4:
    lw a1, -28(s0)
    addw a0, a1, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    ret

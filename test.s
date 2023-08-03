.global main
.data
.text
main:
mainEntry1:
    addi sp, sp, -448
    sd ra, 440(sp)
    sd s0, 432(sp)
    addi s0, sp, 448
    addi sp, sp, -448
    sd ra, 440(sp)
    sd s0, 432(sp)
    addi s0, sp, 448
    addi sp, sp, -448
    sd ra, 440(sp)
    sd s0, 432(sp)
    addi s0, sp, 448
    li a1, 0
    sw a1, -444(s0)
    li a1, 10
    sw a1, -448(s0)
    j	condBlock2
condBlock2:
    lw a1, -448(s0)
    li a2, 0
    subw a1, a1, a2
    snez a2, a1
    mv a2, a2
    li a3, 0
    subw a2, a2, a3
    snez a3, a2
    bne a3, zero, bodyBlock3
    j	afterBlock4
bodyBlock3:
    lw a3, -448(s0)
    li a4, 4
    mulw a3, a3, a4
    addi a3, a3, 420
    sub a3, s0, a3
    lw a4, -448(s0)
    sw a4, 0(a3)
    lw a4, -448(s0)
    addiw a4, a4, -1
    sw a4, -448(s0)
    j	condBlock2
afterBlock4:
    j	condBlock5
condBlock5:
    lw a4, -448(s0)
    li a5, 10
    subw a4, a4, a5
    snez a5, a4
    mv a5, a5
    li a6, 0
    subw a5, a5, a6
    snez a6, a5
    bne a6, zero, bodyBlock6
    j	afterBlock7
bodyBlock6:
    lw a6, -448(s0)
    addiw a6, a6, 1
    sw a6, -448(s0)
    lw a6, -444(s0)
    lw a7, -448(s0)
    li a7, 4
    mulw a7, a7, a7
    addi a7, a7, 420
    sub a7, s0, a7
    lw a6, 0(a7)
    addw a6, a6, a6
    sw a6, -444(s0)
    j	condBlock5
afterBlock7:
    lw a6, -444(s0)
    addw a0, a6, 0
    ld ra, 440(sp)
    ld s0, 432(sp)
    addi sp, sp, 448
    ld ra, 440(sp)
    ld s0, 432(sp)
    addi sp, sp, 448
    ld ra, 440(sp)
    ld s0, 432(sp)
    addi sp, sp, 448
    ret
    li a0, 0
    ret

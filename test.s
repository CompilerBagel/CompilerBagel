.global main
.data
.text
main:
    addi sp, sp, -48
    sd ra, 40(sp)
    sd s0, 32(sp)
    addi s0, sp, 48
mainEntry1:
    li a0, 1
    sw a0, -20(s0)
    li a0, 2
    sw a0, -24(s0)
    lw a0, -20(s0)
    lw a1, -24(s0)
    lw a2, -24(s0)
    mulw a1, a1, a2
    addw a0, a0, a1
    sw a0, -28(s0)
    lw a0, -20(s0)
    lw a1, -24(s0)
    addw a0, a0, a1
    lw a1, -28(s0)
    addw a0, a0, a1
    sw a0, -32(s0)
    lw a0, -32(s0)
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

.global main
.data
.text
main:
mainEntry1:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
    li a1, 1
    sw a1, -24(s0)
    li a1, 2
    sw a1, -28(s0)
    lw a1, -28(s0)
    lw a2, -28(s0)
    mulw a1, a1, a2
    lw a2, -24(s0)
    addw a1, a1, a2
    sw a1, -32(s0)
    lw a1, -32(s0)
    addw a0, a1, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    ret

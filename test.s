main:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
mainEntry1:
    li a0, 0
    sw a0, -20(s0)
    li a0, 10
    sw a0, -20(s0)
    lw a0, -20(s0)
    addiw a1, a0, 3
    addiw a0, a1, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret

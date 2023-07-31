main:
    addi sp, sp, -48
    sd ra, 40(sp)
    sd s0, 32(sp)
    addi s0, sp, 48
mainEntry1:
    li a0, 2
    sw a0, -20(s0)
    lw a0, -20(s0)
    addiw a1, a0, 1
    sw a1, -24(s0)
    lw a0, -24(s0)
    addiw a1, a0, 2
    sw a1, -28(s0)
    lw a0, -28(s0)
    addiw a1, a0, 3
    sw a1, -32(s0)
    li a0, 0
    lw ra, 40(sp)
    lw s0, 32(sp)
    addi sp, sp, 48
    ret
    li a0, 0
    lw ra, 40(sp)
    lw s0, 32(sp)
    addi sp, sp, 48
    ret

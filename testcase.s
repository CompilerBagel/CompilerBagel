main:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
mainEntry1:
    li a0, 0
    lw ra, 24(sp)
    lw s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    lw ra, 24(sp)
    lw s0, 16(sp)
    addi sp, sp, 32
    ret

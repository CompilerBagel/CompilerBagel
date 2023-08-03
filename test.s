.global main
.data
.text
main:
mainEntry1:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
    li a0, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    ret

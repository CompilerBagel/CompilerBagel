.global main
.data
.text
main:
mainEntry1:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
    li a0, 0
    ret
    li a0, 0
    ld sp, 24(ra)
    ld sp, 16(s0)
    addi sp, sp, 32
    ret

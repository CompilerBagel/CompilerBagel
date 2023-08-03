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
    sw a1, -20(s0)
    lw a1, -20(s0)
    addw a0, a1, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    ret

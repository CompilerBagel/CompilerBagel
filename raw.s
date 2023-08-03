.global main
.data
    a:
        .word 1
.text
main:
mainEntry1:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
    la t0, a
    lw a, 0(t0)
    li li, 2
    mulw mul_, a, li
    sw mul_, -24(s0)
    lw b, -24(s0)
    addw a0, b, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    ret

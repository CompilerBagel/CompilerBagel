.global main
.data
    a:
        .word 10
.text
main:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
mainEntry1:
    la t0, a
    lw a1, 0(t0)
    addiw a0, a1, -3
    sw a0, -20(s0)
    la t0, a
    lw a0, 0(t0)
    addiw a0, a0, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret

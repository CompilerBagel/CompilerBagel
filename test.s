.global main
.data
.text
main:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
mainEntry1:
    li a0, 10
    sw a0, -20(s0)
    lw a0, -20(s0)
    subiw a1, a0, 3
    sw a1, -24(s0)
    lw a0, -20(s0)
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

.global main
.data
.text
f:
fEntry1:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
    sw f0, -24(s0)
    lw a, -24(s0)
    addi a0, a, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    ret
main:
mainEntry2:
    addi sp, sp, -48
    sd ra, 40(sp)
    sd s0, 32(sp)
    addi s0, sp, 48
    li li, 1
    sw li, -24(s0)
    lw n, -24(s0)
    sd a1, -32(s0)
    sd a2, -40(s0)
    sd a3, -48(s0)
    mv a0, n
    call f
    ld a1, -32(s0)
    ld a2, -40(s0)
    ld a3, -48(s0)
    mv f, a0
    li a0, 0
    ld ra, 40(sp)
    ld s0, 32(sp)
    addi sp, sp, 48
    ret
    li a0, 0
    ret

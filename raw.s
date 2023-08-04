.global main
.data
.text
f:
fEntry1:
    addi sp, sp, -48
    sd ra, 40(sp)
    sd s0, 32(sp)
    addi s0, sp, 48
    sw a0, -32(s0)
    sw a1, -36(s0)
    lw x, -32(s0)
    lw y, -36(s0)
    addw add_, x, y
    addi a0, add_, 0
    ld ra, 40(sp)
    ld s0, 32(sp)
    addi sp, sp, 48
    ret
    li a0, 0
    ret
main:
mainEntry2:
    addi sp, sp, -64
    sd ra, 56(sp)
    sd s0, 48(sp)
    addi s0, sp, 64
    li li, 3
    sw li, -24(s0)
    li li, 4
    sw li, -28(s0)
    lw x, -24(s0)
    lw y, -28(s0)
    sd a1, -36(s0)
    sd a2, -44(s0)
    sd a3, -52(s0)
    mv a0, x
    mv a1, y
    call f
    ld a1, -36(s0)
    ld a2, -44(s0)
    ld a3, -52(s0)
    mv f, a0
    addiw add_, f, 2
    addi a0, add_, 0
    ld ra, 56(sp)
    ld s0, 48(sp)
    addi sp, sp, 64
    ret
    li a0, 0
    ret

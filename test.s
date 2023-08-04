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
    lw a1, -32(s0)
    lw a2, -36(s0)
    addw a1, a1, a2
    addi a0, a1, 0
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
    li a1, 3
    sw a1, -24(s0)
    li a1, 4
    sw a1, -28(s0)
    lw a1, -24(s0)
    lw a2, -28(s0)
    sd a1, -36(s0)
    sd a2, -44(s0)
    sd a3, -52(s0)
    mv a0, a1
    mv a1, a2
    call f
    ld a1, -36(s0)
    ld a2, -44(s0)
    ld a3, -52(s0)
    mv a1, a0
    addiw a2, a1, 2
    addi a0, a2, 0
    ld ra, 56(sp)
    ld s0, 48(sp)
    addi sp, sp, 64
    ret
    li a0, 0
    ret

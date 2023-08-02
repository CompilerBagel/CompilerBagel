.global main
.data
.text
main:
mainEntry1:
    addi sp, sp, -64
    sd ra, 56(sp)
    sd s0, 48(sp)
    addi s0, sp, 64
    addi a1, s0, -56
    li a1, 1
    sw a1, -56(s0)
    addi a1, s0, -56
    li a1, 2
    sw a1, -56(s0)
    addi a1, s0, -56
    li a1, 3
    sw a1, -56(s0)
    addi a1, s0, -56
    li a1, 4
    sw a1, -56(s0)
    addi a1, s0, -56
    li a1, 5
    sw a1, -56(s0)
    addi a1, s0, -56
    li a1, 6
    sw a1, -56(s0)
    addi a1, s0, -56
    addi a1, s0, -68
    lw a1, -68(s0)
    addw a0, a1, 0
    ld ra, 56(sp)
    ld s0, 48(sp)
    addi sp, sp, 64
    ret
    li a0, 0
    ret

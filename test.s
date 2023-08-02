.global main
.data
.text
main:
mainEntry1:
    addi sp, sp, -80
    sd ra, 72(sp)
    sd s0, 64(sp)
    addi s0, sp, 80
    addi a1, s0, -52
    addi a1, s0, -52
    addi a1, s0, -52
    li a1, 0
    sw a1, -52(s0)
    addi a1, s0, -52
    addi a1, s0, -56
    addi a1, s0, -56
    li a1, 6
    sw a1, -56(s0)
    addi a1, s0, -60
    addi a1, s0, -60
    addi a1, s0, -60
    li a1, 3
    sw a1, -60(s0)
    addi a1, s0, -60
    addi a1, s0, -64
    addi a1, s0, -64
    li a1, 4
    sw a1, -64(s0)
    addi a1, s0, -68
    addi a1, s0, -68
    addi a1, s0, -68
    li a1, 5
    sw a1, -68(s0)
    addi a1, s0, -68
    addi a1, s0, -72
    addi a1, s0, -72
    li a1, 6
    sw a1, -72(s0)
    addi a1, s0, -76
    addi a1, s0, -76
    addi a1, s0, -76
    li a1, 7
    sw a1, -76(s0)
    addi a1, s0, -76
    addi a1, s0, -80
    addi a1, s0, -80
    li a1, 8
    sw a1, -80(s0)
    addi a1, s0, -52
    addi a1, s0, -56
    addi a1, s0, -56
    lw a1, -56(s0)
    addw a0, a1, 0
    ld ra, 72(sp)
    ld s0, 64(sp)
    addi sp, sp, 80
    ret
    li a0, 0
    ret

.global main
.data
.text
main:
mainEntry1:
    addi sp, sp, -96
    sd ra, 88(sp)
    sd s0, 80(sp)
    addi s0, sp, 96
    addi sp, sp, -96
    sd ra, 88(sp)
    sd s0, 80(sp)
    addi s0, sp, 96
    addi sp, sp, -96
    sd ra, 88(sp)
    sd s0, 80(sp)
    addi s0, sp, 96
    addi a1, s0, -20
    addi a1, s0, -20
    li a1, 1
    sw a1, -20(s0)
    addi a1, s0, -20
    addi a1, s0, -24
    li a1, 2
    sw a1, -24(s0)
    addi a1, s0, -28
    addi a1, s0, -28
    li a1, 3
    sw a1, -28(s0)
    addi a1, s0, -28
    addi a1, s0, -32
    li a1, 4
    sw a1, -32(s0)
    addi a1, s0, -36
    addi a1, s0, -36
    li a1, 0
    sw a1, -36(s0)
    addi a1, s0, -36
    addi a1, s0, -40
    li a1, 0
    sw a1, -40(s0)
    addi a1, s0, -44
    addi a1, s0, -44
    li a1, 7
    sw a1, -44(s0)
    addi a1, s0, -44
    addi a1, s0, -48
    li a1, 0
    sw a1, -48(s0)
    li a1, 3
    sw a1, -56(s0)
    addi a1, s0, -44
    addi a1, s0, -44
    lw a1, -44(s0)
    addi a2, s0, -56
    addi a2, s0, -56
    li a2, 1
    sw a2, -56(s0)
    addi a2, s0, -56
    addi a2, s0, -60
    li a2, 2
    sw a2, -60(s0)
    addi a2, s0, -64
    addi a2, s0, -64
    li a2, 3
    sw a2, -64(s0)
    addi a2, s0, -64
    addi a2, s0, -68
    li a2, 0
    sw a2, -68(s0)
    addi a2, s0, -72
    addi a2, s0, -72
    li a2, 5
    sw a2, -72(s0)
    addi a2, s0, -72
    addi a2, s0, -76
    li a2, 0
    sw a2, -76(s0)
    addi a2, s0, -80
    addi a2, s0, -80
    sw a1, -80(s0)
    addi a1, s0, -80
    addi a1, s0, -84
    li a1, 8
    sw a1, -84(s0)
    addi a1, s0, -80
    addi a1, s0, -84
    lw a1, -84(s0)
    addw a0, a1, 0
    ld ra, 88(sp)
    ld s0, 80(sp)
    addi sp, sp, 96
    ld ra, 88(sp)
    ld s0, 80(sp)
    addi sp, sp, 96
    ld ra, 88(sp)
    ld s0, 80(sp)
    addi sp, sp, 96
    ret
    li a0, 0
    ret

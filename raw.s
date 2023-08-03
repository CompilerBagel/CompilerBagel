.global main
.data
.text
main:
mainEntry1:
    addi sp, sp, -96
    sd ra, 88(sp)
    sd s0, 80(sp)
    addi s0, sp, 96
    addi %a11, s0, -20
    addi %array12, s0, -20
    li li, 1
    sw li, -20(s0)
    addi %a11, s0, -20
    addi %array15, s0, -24
    li li, 2
    sw li, -24(s0)
    addi %a11, s0, -28
    addi %array18, s0, -28
    li li, 3
    sw li, -28(s0)
    addi %a11, s0, -28
    addi %array21, s0, -32
    li li, 4
    sw li, -32(s0)
    addi %a11, s0, -36
    addi %array24, s0, -36
    li li, 0
    sw li, -36(s0)
    addi %a11, s0, -36
    addi %array27, s0, -40
    li li, 0
    sw li, -40(s0)
    addi %a11, s0, -44
    addi %array30, s0, -44
    li li, 7
    sw li, -44(s0)
    addi %a11, s0, -44
    addi %array33, s0, -48
    li li, 0
    sw li, -48(s0)
    li li, 3
    sw li, -56(s0)
    addi %a11, s0, -44
    addi %a39, s0, -44
    lw a, -44(s0)
    addi %d38, s0, -56
    addi %array42, s0, -56
    li li, 1
    sw li, -56(s0)
    addi %d38, s0, -56
    addi %array45, s0, -60
    li li, 2
    sw li, -60(s0)
    addi %d38, s0, -64
    addi %array48, s0, -64
    li li, 3
    sw li, -64(s0)
    addi %d38, s0, -64
    addi %array51, s0, -68
    li li, 0
    sw li, -68(s0)
    addi %d38, s0, -72
    addi %array54, s0, -72
    li li, 5
    sw li, -72(s0)
    addi %d38, s0, -72
    addi %array57, s0, -76
    li li, 0
    sw li, -76(s0)
    addi %d38, s0, -80
    addi %array60, s0, -80
    sw a, -80(s0)
    addi %d38, s0, -80
    addi %array63, s0, -84
    li li, 8
    sw li, -84(s0)
    addi %d38, s0, -80
    addi %d66, s0, -84
    lw d, -84(s0)
    addw a0, d, 0
    ld ra, 88(sp)
    ld s0, 80(sp)
    addi sp, sp, 96
    ret
    li a0, 0
    ret

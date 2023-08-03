.global main
.data
.text
main:
mainEntry1:
    addi sp, sp, -448
    sd ra, 440(sp)
    sd s0, 432(sp)
    addi s0, sp, 448
    li li, 0
    sw li, -444(s0)
    li li, 10
    sw li, -448(s0)
    j	condBlock2
condBlock2:
    lw count, -448(s0)
    li li, 0
    subw tmp, count, li
    snez icmp_NE, tmp
    mv tmp_, icmp_NE
    li li, 0
    subw tmp, tmp_, li
    snez icmp_, tmp
    bne icmp_, zero, bodyBlock3
    j	afterBlock4
bodyBlock3:
    lw count, -448(s0)
    li li, 4
    mulw offset, count, li
    addi offset, offset, 420
    sub %arr11, s0, offset
    lw count, -448(s0)
    sw count, 0(%arr11)
    lw count, -448(s0)
    addiw sub_, count, -1
    sw sub_, -448(s0)
    j	condBlock2
afterBlock4:
    j	condBlock5
condBlock5:
    lw count, -448(s0)
    li li, 10
    subw tmp, count, li
    snez icmp_NE, tmp
    mv tmp_, icmp_NE
    li li, 0
    subw tmp, tmp_, li
    snez icmp_, tmp
    bne icmp_, zero, bodyBlock6
    j	afterBlock7
bodyBlock6:
    lw count, -448(s0)
    addiw add_, count, 1
    sw add_, -448(s0)
    lw sum, -444(s0)
    lw count, -448(s0)
    li li, 4
    mulw offset, count, li
    addi offset, offset, 420
    sub %arr11, s0, offset
    lw arr, 0(%arr11)
    addw add_, sum, arr
    sw add_, -444(s0)
    j	condBlock5
afterBlock7:
    lw sum, -444(s0)
    addw a0, sum, 0
    ld ra, 440(sp)
    ld s0, 432(sp)
    addi sp, sp, 448
    ret
    li a0, 0
    ret

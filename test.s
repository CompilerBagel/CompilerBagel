.global main
.data
.text
main:
mainEntry1:
    addi sp, sp, -48
    sd ra, 40(sp)
    sd s0, 32(sp)
    addi s0, sp, 48
    li li, 0
    sw li, -32(s0)
    li li, 0
    sw li, -36(s0)
    j	condBlock2
condBlock2:
    lw count, -36(s0)
    li li, 2
    subw tmp, count, li
    snez icmp_NE, tmp
    mv tmp_, icmp_NE
    li li, 0
    subw tmp, tmp_, li
    snez icmp_, tmp
    bne icmp_, zero, bodyBlock3
    j	afterBlock4
bodyBlock3:
    lw count, -36(s0)
    li li, 4
    mulw offset, count, li
    addi offset, offset, 24
    sub %arr11, s0, offset
    lw count, -36(s0)
    sw count, 0(%arr11)
    lw count, -36(s0)
    addiw add_, count, 1
    sw add_, -36(s0)
    j	condBlock2
afterBlock4:
    j	condBlock5
condBlock5:
    lw count, -36(s0)
    li li, 2
    subw tmp, count, li
    snez icmp_NE, tmp
    mv tmp_, icmp_NE
    li li, 0
    subw tmp, tmp_, li
    snez icmp_, tmp
    bne icmp_, zero, bodyBlock6
    j	afterBlock7
bodyBlock6:
    lw count, -36(s0)
    addiw sub_, count, -1
    sw sub_, -36(s0)
    lw sum, -32(s0)
    lw count, -36(s0)
    li li, 4
    mulw offset, count, li
    addi offset, offset, 24
    sub %arr11, s0, offset
    lw arr, 0(%arr11)
    addw add_, sum, arr
    sw add_, -32(s0)
    j	condBlock5
afterBlock7:
    lw sum, -32(s0)
    addi a0, sum, 0
    ld ra, 40(sp)
    ld s0, 32(sp)
    addi sp, sp, 48
    ret
    li a0, 0
    ret

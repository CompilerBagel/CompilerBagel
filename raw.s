.global main
.data
    arr:
        .word 1
        .word 2
        .word 33
        .word 4
        .word 5
        .word 6
    N:
        .word -1
.text
main:
mainEntry1:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
    li li, 0
    sw li, -24(s0)
    li li, 0
    sw li, -28(s0)
    j	condBlock2
condBlock2:
    lw i, -24(s0)
    li li, 6
    slt icmp_LT, i, li
    mv tmp_, icmp_LT
    li li, 0
    subw tmp, tmp_, li
    snez icmp_, tmp
    bne icmp_, zero, bodyBlock3
    j	afterBlock4
bodyBlock3:
    lw sum, -28(s0)
    lw i, -24(s0)
    la @arr1, arr
    li li, 4
    mulw offset, i, li
    add @arr1, @arr1, offset
    lw arr, 0(@arr1)
    addw add_, sum, arr
    sw add_, -28(s0)
    lw i, -24(s0)
    addiw add_, i, 1
    sw add_, -24(s0)
    j	condBlock2
afterBlock4:
    lw sum, -28(s0)
    addw a0, sum, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    ret

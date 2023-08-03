.global main
.data
.text
ifElseIf:
ifElseIfEntry1:
    addi sp, sp, -48
    sd ra, 40(sp)
    sd s0, 32(sp)
    addi s0, sp, 48
    li a1, 0
    sw a1, -24(s0)
    li a1, 5
    sw a1, -24(s0)
    li a1, 0
    sw a1, -28(s0)
    li a1, 10
    sw a1, -28(s0)
    lw a1, -24(s0)
    li a2, 6
    subw a1, a1, a2
    seqz a1, a1
    li a2, 0
    subw a2, a2, a1
    snez a2, a2
    sw a1, -32(s0)
    bne a2, zero, true_2
    j	false_3
true_2:
    j	after_4
false_3:
    lw a1, -28(s0)
    li a2, 11
    subw a1, a1, a2
    seqz a1, a1
    sw a1, -32(s0)
    j	after_4
after_4:
    lw a1, -32(s0)
    li a2, 0
    subw a1, a1, a2
    snez a1, a1
    bne a1, zero, trueBlock5
    j	falseBlock6
trueBlock5:
    ld ra, 40(sp)
    ld s0, 32(sp)
    addi sp, sp, 48
    lw a1, -24(s0)
    addw a0, a1, 0
    ret
falseBlock6:
    lw a1, -28(s0)
    li a2, 10
    subw a1, a1, a2
    seqz a1, a1
    li a2, 0
    subw a2, a2, a1
    seqz a2, a2
    sw a1, -36(s0)
    bne a2, zero, true_8
    j	false_9
afterBlock7:
    ld ra, 40(sp)
    ld s0, 32(sp)
    addi sp, sp, 48
    lw a1, -24(s0)
    addw a0, a1, 0
    ret
true_8:
    j	after_10
false_9:
    lw a1, -24(s0)
    li a2, 1
    subw a1, a1, a2
    seqz a1, a1
    sw a1, -36(s0)
    j	after_10
after_10:
    lw a1, -36(s0)
    li a2, 0
    subw a1, a1, a2
    snez a1, a1
    bne a1, zero, trueBlock11
    j	falseBlock12
trueBlock11:
    li a1, 25
    sw a1, -24(s0)
    j	afterBlock13
falseBlock12:
    lw a1, -28(s0)
    li a2, 10
    subw a1, a1, a2
    seqz a1, a1
    li a2, 0
    subw a2, a2, a1
    seqz a2, a2
    sw a1, -40(s0)
    bne a2, zero, true_14
    j	false_15
afterBlock13:
    j	afterBlock7
true_14:
    j	after_16
false_15:
    lw a1, -24(s0)
    li a2, -5
    subw a1, a1, a2
    seqz a1, a1
    sw a1, -40(s0)
    j	after_16
after_16:
    lw a1, -40(s0)
    li a2, 0
    subw a1, a1, a2
    snez a1, a1
    bne a1, zero, trueBlock17
    j	falseBlock18
trueBlock17:
    lw a1, -24(s0)
    addiw a1, a1, 15
    sw a1, -24(s0)
    j	afterBlock19
falseBlock18:
    lw a1, -24(s0)
    addiw a1, a1, 0
    sw a1, -24(s0)
    j	afterBlock19
afterBlock19:
    j	afterBlock13
main:
mainEntry20:
    addi sp, sp, -80
    sd ra, 72(sp)
    sd s0, 64(sp)
    addi s0, sp, 80
    sw a1, -20(s0)
    sw a2, -28(s0)
    sw a3, -36(s0)
    call ifElseIf
    lw a1, -20(s0)
    lw a2, -28(s0)
    lw a3, -36(s0)
    sw a1, -44(s0)
    sw a2, -52(s0)
    sw a3, -60(s0)
    call putint
    lw a1, -44(s0)
    lw a2, -52(s0)
    ld ra, 72(sp)
    ld s0, 64(sp)
    addi sp, sp, 80
    lw a3, -60(s0)
    li a0, 0
    ret

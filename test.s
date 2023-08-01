.global main
.data
.text
ififElse:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
ififElseEntry1:
    li a0, 0
    sw a0, -20(s0)
    li a0, 5
    sw a0, -20(s0)
    li a0, 0
    sw a0, -24(s0)
    li a0, 10
    sw a0, -24(s0)
    lw a0, -20(s0)
    li a1, 5
    subw a1, a0, 5
    seqz a0, a1
    bne a0, zero, trueBlock2
    j	falseBlock3
trueBlock2:
    lw a0, -24(s0)
    li a1, 10
    subw a1, a0, 10
    seqz a0, a1
    bne a0, zero, trueBlock5
    j	falseBlock6
falseBlock3:
    j	afterBlock4
afterBlock4:
    lw a0, -20(s0)
    addw a0, a0, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
trueBlock5:
    li a0, 25
    sw a0, -20(s0)
    j	afterBlock7
falseBlock6:
    lw a0, -20(s0)
    addiw a1, a0, 15
    sw a1, -20(s0)
    j	afterBlock7
afterBlock7:
    j	afterBlock4
main:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
mainEntry8:
    call ififElse
    addw a0, a0, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret

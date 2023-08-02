.global main
.data
    getch:
    before_main:
    getfarray:
    putint:
    putfarray:
    end_time:
    getarray:
    after_main:
    getint:
    putarray:
    start_time:
    putfloat:
    putch:
    getfloat:
.text
main:
mainEntry1:
    addi sp, sp, -48
    sd ra, 40(sp)
    sd s0, 32(sp)
    addi s0, sp, 48
    li a1, 0
    sw a1, -20(s0)
    li a1, 0
    sw a1, -24(s0)
    li a1, 0
    sw a1, -28(s0)
    li a1, 0
    sw a1, -32(s0)
    li a1, 0
    sw a1, -36(s0)
    li a1, 5
    sw a1, -20(s0)
    li a1, 5
    sw a1, -24(s0)
    li a1, 1
    sw a1, -28(s0)
    li a1, -2
    sw a1, -32(s0)
    li a1, 2
    sw a1, -36(s0)
    lw a1, -32(s0)
    li a2, 1
    mulw a1, a1, a2
    li a2, 2
    divw a1, a1, a2
    li a2, 0
    slt a1, a1, a2
    lw a1, -20(s0)
    lw a2, -24(s0)
    subw a1, a1, a2
    li a2, 0
    subw a1, a1, a2
    snez a1, a1
    lw a1, -28(s0)
    addiw a1, a1, 3
    li a2, 2
    divw a1, a1, a2
    li a2, 0
    subw a1, a1, a2
    snez a1, a1
    li a1, 0
    subw a1, a2, a1
    snez a1, a1
    bne a1, zero, trueBlock2
    j	falseBlock3
trueBlock2:
    li a1, 3
    sw a1, -36(s0)
    j	afterBlock4
falseBlock3:
    j	afterBlock4
afterBlock4:
    lw a1, -32(s0)
    addiw a1, a1, 2
    addiw a1, a1, 67
    li a2, 0
    slt a1, a1, a2
    lw a1, -20(s0)
    lw a2, -24(s0)
    subw a1, a1, a2
    li a2, 0
    subw a1, a1, a2
    snez a1, a1
    lw a1, -28(s0)
    addiw a1, a1, 2
    li a2, 2
    divw a1, a1, a2
    li a2, 0
    subw a1, a1, a2
    snez a1, a1
    li a1, 0
    subw a1, a2, a1
    snez a1, a1
    bne a1, zero, trueBlock5
    j	falseBlock6
trueBlock5:
    li a1, 4
    sw a1, -36(s0)
    j	afterBlock7
falseBlock6:
    j	afterBlock7
afterBlock7:
    lw a1, -36(s0)
    addw a0, a1, 0
    ld ra, 40(sp)
    ld s0, 32(sp)
    addi sp, sp, 48
    ret
    li a0, 0
    ret

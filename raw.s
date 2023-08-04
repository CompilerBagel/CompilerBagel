.global main
.data
    a:
        .word -1
    b:
        .word 1
.text
inc_a:
inc_aEntry1:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
    la t0, a
    lw a, 0(t0)
    sw a, -24(s0)
    lw b, -24(s0)
    addiw add_, b, 1
    sw add_, -24(s0)
    lw b, -24(s0)
    la t0, a
    sw b, 0(t0)
    la t0, a
    lw a, 0(t0)
    addi a0, a, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    ret
main:
mainEntry2:
    addi sp, sp, -480
    sd ra, 472(sp)
    sd s0, 464(sp)
    addi s0, sp, 480
    li li, 5
    sw li, -24(s0)
    j	condBlock3
condBlock3:
    lw k, -24(s0)
    li li, 0
    slt tmp, k, li
    xori icmp_GE, tmp, 1
    mv tmp_, icmp_GE
    li li, 0
    subw tmp, tmp_, li
    snez icmp_, tmp
    bne icmp_, zero, bodyBlock4
    j	afterBlock5
bodyBlock4:
    sd a1, -32(s0)
    sd a2, -40(s0)
    sd a3, -48(s0)
    call inc_a
    ld a1, -32(s0)
    ld a2, -40(s0)
    ld a3, -48(s0)
    li li, 0
    subw tmp, li, a0
    seqz icmp_EQ, tmp
    sw a0, -52(s0)
    bne icmp_EQ, zero, false_7
    j	true_6
afterBlock5:
    la t0, a
    lw a, 0(t0)
    sd a1, -60(s0)
    sd a2, -68(s0)
    sd a3, -76(s0)
    mv a0, a
    call putint
    ld a1, -60(s0)
    ld a2, -68(s0)
    ld a3, -76(s0)
    sd a1, -84(s0)
    sd a2, -92(s0)
    sd a3, -100(s0)
    li a0, 32
    call putch
    ld a1, -84(s0)
    ld a2, -92(s0)
    ld a3, -100(s0)
    la t0, b
    lw b, 0(t0)
    sd a1, -108(s0)
    sd a2, -116(s0)
    sd a3, -124(s0)
    mv a0, b
    call putint
    ld a1, -108(s0)
    ld a2, -116(s0)
    ld a3, -124(s0)
    sd a1, -132(s0)
    sd a2, -140(s0)
    sd a3, -148(s0)
    li a0, 10
    call putch
    ld a1, -132(s0)
    ld a2, -140(s0)
    ld a3, -148(s0)
    la t0, a
    lw a, 0(t0)
    addi a0, a, 0
    ld ra, 472(sp)
    ld s0, 464(sp)
    addi sp, sp, 480
    ret
    li a0, 0
    ret
true_6:
    sd a1, -156(s0)
    sd a2, -164(s0)
    sd a3, -172(s0)
    call inc_a
    ld a1, -156(s0)
    ld a2, -164(s0)
    ld a3, -172(s0)
    sw a0, -52(s0)
    j	after_8
false_7:
    j	after_8
after_8:
    lw load_, -52(s0)
    li li, 0
    subw tmp, li, load_
    seqz icmp_EQ, tmp
    sw load_, -176(s0)
    bne icmp_EQ, zero, false_10
    j	true_9
true_9:
    sd a1, -184(s0)
    sd a2, -192(s0)
    sd a3, -200(s0)
    call inc_a
    ld a1, -184(s0)
    ld a2, -192(s0)
    ld a3, -200(s0)
    sw a0, -176(s0)
    j	after_11
false_10:
    j	after_11
after_11:
    lw load_, -176(s0)
    li li, 0
    subw tmp, load_, li
    snez icmp_, tmp
    bne icmp_, zero, trueBlock12
    j	falseBlock13
trueBlock12:
    la t0, a
    lw a, 0(t0)
    sd a1, -208(s0)
    sd a2, -216(s0)
    sd a3, -224(s0)
    mv a0, a
    call putint
    ld a1, -208(s0)
    ld a2, -216(s0)
    ld a3, -224(s0)
    sd a1, -232(s0)
    sd a2, -240(s0)
    sd a3, -248(s0)
    li a0, 32
    call putch
    ld a1, -232(s0)
    ld a2, -240(s0)
    ld a3, -248(s0)
    la t0, b
    lw b, 0(t0)
    sd a1, -256(s0)
    sd a2, -264(s0)
    sd a3, -272(s0)
    mv a0, b
    call putint
    ld a1, -256(s0)
    ld a2, -264(s0)
    ld a3, -272(s0)
    sd a1, -280(s0)
    sd a2, -288(s0)
    sd a3, -296(s0)
    li a0, 10
    call putch
    ld a1, -280(s0)
    ld a2, -288(s0)
    ld a3, -296(s0)
    j	afterBlock14
falseBlock13:
    j	afterBlock14
afterBlock14:
    sd a1, -304(s0)
    sd a2, -312(s0)
    sd a3, -320(s0)
    call inc_a
    ld a1, -304(s0)
    ld a2, -312(s0)
    ld a3, -320(s0)
    li li, 14
    slt icmp_LT, a0, li
    mv tmp_, icmp_LT
    li li, 0
    subw tmp, li, tmp_
    snez icmp_NE, tmp
    sw tmp_, -324(s0)
    bne icmp_NE, zero, true_15
    j	false_16
true_15:
    j	after_17
false_16:
    sd a1, -332(s0)
    sd a2, -340(s0)
    sd a3, -348(s0)
    call inc_a
    ld a1, -332(s0)
    ld a2, -340(s0)
    ld a3, -348(s0)
    li li, 0
    subw tmp, li, a0
    seqz icmp_EQ, tmp
    sw a0, -352(s0)
    bne icmp_EQ, zero, false_19
    j	true_18
after_17:
    lw load_, -324(s0)
    li li, 0
    subw tmp, load_, li
    snez icmp_, tmp
    bne icmp_, zero, trueBlock21
    j	falseBlock22
true_18:
    sd a1, -360(s0)
    sd a2, -368(s0)
    sd a3, -376(s0)
    call inc_a
    ld a1, -360(s0)
    ld a2, -368(s0)
    ld a3, -376(s0)
    sd a1, -384(s0)
    sd a2, -392(s0)
    sd a3, -400(s0)
    call inc_a
    ld a1, -384(s0)
    ld a2, -392(s0)
    ld a3, -400(s0)
    subw sub_, a0, a0
    addiw add_, sub_, 1
    sw add_, -352(s0)
    j	after_20
false_19:
    j	after_20
after_20:
    lw load_, -352(s0)
    sw load_, -324(s0)
    j	after_17
trueBlock21:
    la t0, a
    lw a, 0(t0)
    sd a1, -408(s0)
    sd a2, -416(s0)
    sd a3, -424(s0)
    mv a0, a
    call putint
    ld a1, -408(s0)
    ld a2, -416(s0)
    ld a3, -424(s0)
    sd a1, -432(s0)
    sd a2, -440(s0)
    sd a3, -448(s0)
    li a0, 10
    call putch
    ld a1, -432(s0)
    ld a2, -440(s0)
    ld a3, -448(s0)
    la t0, b
    lw b, 0(t0)
    li li, 2
    mulw mul_, b, li
    la t0, b
    sw mul_, 0(t0)
    j	afterBlock23
falseBlock22:
    sd a1, -456(s0)
    sd a2, -464(s0)
    sd a3, -472(s0)
    call inc_a
    ld a1, -456(s0)
    ld a2, -464(s0)
    ld a3, -472(s0)
    j	afterBlock23
afterBlock23:
    lw k, -24(s0)
    addiw sub_, k, -1
    sw sub_, -24(s0)
    j	condBlock3

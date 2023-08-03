.global main
.data
.text
foo:
fooEntry1:
    addi sp, sp, -160
    sd ra, 152(sp)
    sd s0, 144(sp)
    addi s0, sp, 160
    addi a1, s0, -24
    li a1, 0
    sw a1, -24(s0)
    addi a1, s0, -28
    li a1, 1
    sw a1, -28(s0)
    addi a1, s0, -32
    li a1, 2
    sw a1, -32(s0)
    addi a1, s0, -36
    li a1, 3
    sw a1, -36(s0)
    addi a1, s0, -40
    li a1, 0
    sw a1, -40(s0)
    addi a1, s0, -44
    li a1, 1
    sw a1, -44(s0)
    addi a1, s0, -48
    li a1, 2
    sw a1, -48(s0)
    addi a1, s0, -52
    li a1, 3
    sw a1, -52(s0)
    addi a1, s0, -56
    li a1, 0
    sw a1, -56(s0)
    addi a1, s0, -60
    li a1, 1
    sw a1, -60(s0)
    addi a1, s0, -64
    li a1, 2
    sw a1, -64(s0)
    addi a1, s0, -68
    li a1, 3
    sw a1, -68(s0)
    addi a1, s0, -72
    li a1, 0
    sw a1, -72(s0)
    addi a1, s0, -76
    li a1, 1
    sw a1, -76(s0)
    addi a1, s0, -80
    li a1, 2
    sw a1, -80(s0)
    addi a1, s0, -84
    li a1, 3
    sw a1, -84(s0)
    li a1, 3
    sw a1, -88(s0)
    li a1, 7
    sw a1, -92(s0)
    li a1, 5
    sw a1, -96(s0)
    li a1, 6
    sw a1, -100(s0)
    li a1, 1
    sw a1, -104(s0)
    li a1, 0
    sw a1, -108(s0)
    li a1, 3
    sw a1, -112(s0)
    li a1, 5
    sw a1, -116(s0)
    li a1, 4
    sw a1, -120(s0)
    li a1, 2
    sw a1, -124(s0)
    li a1, 7
    sw a1, -128(s0)
    li a1, 9
    sw a1, -132(s0)
    li a1, 8
    sw a1, -136(s0)
    li a1, 1
    sw a1, -140(s0)
    li a1, 4
    sw a1, -144(s0)
    li a1, 6
    sw a1, -148(s0)
    lw a1, -88(s0)
    lw a2, -92(s0)
    addw a1, a1, a2
    lw a2, -96(s0)
    addw a1, a1, a2
    lw a2, -100(s0)
    addw a1, a1, a2
    lw a2, -104(s0)
    addw a1, a1, a2
    lw a2, -108(s0)
    addw a1, a1, a2
    lw a2, -112(s0)
    addw a1, a1, a2
    lw a2, -116(s0)
    addw a1, a1, a2
    sw a1, -152(s0)
    lw a1, -120(s0)
    lw a2, -124(s0)
    addw a1, a1, a2
    lw a2, -128(s0)
    addw a1, a1, a2
    lw a2, -132(s0)
    addw a1, a1, a2
    lw a2, -136(s0)
    addw a1, a1, a2
    lw a2, -140(s0)
    addw a1, a1, a2
    lw a2, -144(s0)
    addw a1, a1, a2
    lw a2, -148(s0)
    addw a1, a1, a2
    sw a1, -156(s0)
    lw a1, -152(s0)
    lw a2, -156(s0)
    addw a1, a1, a2
    lw a2, -88(s0)
    li a3, 4
    mulw a2, a2, a3
    addi a2, a2, 24
    sub a2, s0, a2
    lw a2, 0(a2)
    addw a1, a1, a2
    addi a0, a1, 0
    ld ra, 152(sp)
    ld s0, 144(sp)
    addi sp, sp, 160
    ret
    li a0, 0
    ret
main:
mainEntry2:
    addi sp, sp, -192
    sd ra, 184(sp)
    sd s0, 176(sp)
    addi s0, sp, 192
    li a1, 3
    sw a1, -24(s0)
    li a1, 7
    sw a1, -28(s0)
    li a1, 5
    sw a1, -32(s0)
    li a1, 6
    sw a1, -36(s0)
    li a1, 1
    sw a1, -40(s0)
    li a1, 0
    sw a1, -44(s0)
    li a1, 3
    sw a1, -48(s0)
    li a1, 5
    sw a1, -52(s0)
    li a1, 4
    sw a1, -56(s0)
    li a1, 2
    sw a1, -60(s0)
    li a1, 7
    sw a1, -64(s0)
    li a1, 9
    sw a1, -68(s0)
    li a1, 8
    sw a1, -72(s0)
    li a1, 1
    sw a1, -76(s0)
    li a1, 4
    sw a1, -80(s0)
    li a1, 6
    sw a1, -84(s0)
    lw a1, -24(s0)
    lw a2, -28(s0)
    addw a1, a1, a2
    lw a2, -32(s0)
    addw a1, a1, a2
    lw a2, -36(s0)
    addw a1, a1, a2
    lw a2, -40(s0)
    addw a1, a1, a2
    lw a2, -44(s0)
    addw a1, a1, a2
    lw a2, -48(s0)
    addw a1, a1, a2
    lw a2, -52(s0)
    addw a1, a1, a2
    sw a1, -88(s0)
    lw a1, -56(s0)
    lw a2, -60(s0)
    addw a1, a1, a2
    lw a2, -64(s0)
    addw a1, a1, a2
    lw a2, -68(s0)
    addw a1, a1, a2
    lw a2, -72(s0)
    addw a1, a1, a2
    lw a2, -76(s0)
    addw a1, a1, a2
    lw a2, -80(s0)
    addw a1, a1, a2
    lw a2, -84(s0)
    addw a1, a1, a2
    sw a1, -92(s0)
    lw a1, -88(s0)
    sw a1, -92(s0)
    sw a2, -100(s0)
    sw a3, -108(s0)
    call foo
    lw a1, -92(s0)
    lw a2, -100(s0)
    lw a3, -108(s0)
    addw a1, a1, a0
    sw a1, -88(s0)
    li a1, 4
    sw a1, -120(s0)
    li a1, 7
    sw a1, -124(s0)
    li a1, 2
    sw a1, -128(s0)
    li a1, 5
    sw a1, -132(s0)
    li a1, 8
    sw a1, -136(s0)
    li a1, 0
    sw a1, -140(s0)
    li a1, 6
    sw a1, -144(s0)
    li a1, 3
    sw a1, -148(s0)
    lw a1, -92(s0)
    sw a1, -148(s0)
    sw a2, -156(s0)
    sw a3, -164(s0)
    call foo
    lw a1, -148(s0)
    lw a2, -156(s0)
    lw a3, -164(s0)
    addw a1, a1, a0
    sw a1, -92(s0)
    lw a1, -56(s0)
    sw a1, -24(s0)
    lw a1, -60(s0)
    sw a1, -28(s0)
    lw a1, -64(s0)
    sw a1, -32(s0)
    lw a1, -68(s0)
    sw a1, -36(s0)
    lw a1, -72(s0)
    sw a1, -40(s0)
    lw a1, -76(s0)
    sw a1, -44(s0)
    lw a1, -80(s0)
    sw a1, -48(s0)
    lw a1, -84(s0)
    sw a1, -52(s0)
    lw a1, -120(s0)
    lw a2, -124(s0)
    addw a1, a1, a2
    lw a2, -128(s0)
    addw a1, a1, a2
    lw a2, -132(s0)
    addw a1, a1, a2
    lw a2, -136(s0)
    addw a1, a1, a2
    lw a2, -140(s0)
    addw a1, a1, a2
    lw a2, -144(s0)
    addw a1, a1, a2
    lw a2, -148(s0)
    addw a1, a1, a2
    sw a1, -176(s0)
    lw a1, -88(s0)
    lw a2, -92(s0)
    addw a1, a1, a2
    lw a2, -176(s0)
    addw a1, a1, a2
    sw a1, -180(s0)
    lw a1, -180(s0)
    addi a0, a1, 0
    ld ra, 184(sp)
    ld s0, 176(sp)
    addi sp, sp, 192
    ret
    li a0, 0
    ret

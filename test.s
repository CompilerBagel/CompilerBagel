.global main
.data
.text
f:
fEntry1:
    addi sp, sp, -48
    sd ra, 40(sp)
    sd s0, 32(sp)
    addi s0, sp, 48
    sw a0, -28(s0)
    sw a1, -32(s0)
    lw a0, -28(s0)
    lw a1, -32(s0)
    addw a0, a0, a1
    addw a0, a0, 0
    ld ra, 40(sp)
    ld s0, 32(sp)
    addi sp, sp, 48
    ret
    li a0, 0
    ret
main:
mainEntry2:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
    li a0, 1
    sw a0, -20(s0)
    li a0, 2
    sw a0, -24(s0)
    lw a0, -20(s0)
    mv a0, a0
    li a1, 2
    call f
    sw a0, -28(s0)
    lw a0, -28(s0)
    addw a0, a0, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    ret

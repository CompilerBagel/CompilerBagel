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
    sw a0, -32(s0)
    lw a0, -28(s0)
    lw a1, -32(s0)
    addw a0, a0, a1
    addw a0, a0, 0
    ld sp, 40(ra)
    ld sp, 32(s0)
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
    li a0, 3
    sw a0, -20(s0)
    li a0, 4
    sw a0, -24(s0)
    li a0, 2
    sw a0, -28(s0)
    lw a0, -28(s0)
    lw a1, -20(s0)
    lw a2, -24(s0)
    mv a1, a1
    mv a2, a2
    call f
    addw a0, a0, a0
    sw a0, -28(s0)
    lw a0, -28(s0)
    addw a0, a0, 0
    ld sp, 24(ra)
    ld sp, 16(s0)
    addi sp, sp, 32
    ret
    li a0, 0
    ret

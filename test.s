.global main
.data
.text
f:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
fEntry1:
    sw a0, -24(s0)
    lw a0, -24(s0)
    li a1, 2
    mulw a1, a0, a1
    add a0, a1, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
main:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
mainEntry2:
    li a0, 10
    call f
    add a0, a0, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret

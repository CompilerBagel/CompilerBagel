.global main
.data
    arr:
        .word 1
        .word 2
        .word 33
        .word 4
        .word 5
        .word 6
.text
main:
mainEntry1:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
    la a1, arr
    addi a1, a1, 0
    lw a3, 0(a1)
    la a2, arr
    addi a2, a2, 4
    lw a3, 0(a2)
    addw a3, a3, a3
    addw a0, a3, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret


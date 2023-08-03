.global main
.data
    getch:
    arr:
    .word 1
    .word 2
    .word 3
    .word 4
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
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
    la a1, arr
    addi a1, a1, 8
    addi a1, a1, 0
    lw a2, 0(a1)
    addw a0, a2, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    ret

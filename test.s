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
    addi sp, sp, -80
    sd ra, 72(sp)
    sd s0, 64(sp)
    addi s0, sp, 80
    addi a1, s0, -44
    li a1, 1
    sw a1, -44(s0)
    addi a1, s0, -48
    li a1, 2
    sw a1, -48(s0)
    addi a1, s0, -52
    li a1, 33
    sw a1, -52(s0)
    addi a1, s0, -56
    li a1, 4
    sw a1, -56(s0)
    addi a1, s0, -60
    li a1, 5
    sw a1, -60(s0)
    addi a1, s0, -64
    li a1, 6
    sw a1, -64(s0)
    li a1, 0
    sw a1, -68(s0)
    lw a1, -68(s0)
    addiw a1, a1, 1
    sw a1, -68(s0)
    lw a1, -68(s0)
    li a2, 4
    mulw a1, a1, a2
    addi a1, a1, 44
    sub a1, s0, a1
    lw a2, 0(a1)
    addw a0, a2, 0
    ld ra, 72(sp)
    ld s0, 64(sp)
    addi sp, sp, 80
    ret
    li a0, 0
    ret

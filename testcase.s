main:
    addi sp, sp, -32
    sd sp, 24(ra)
    sd sp, 16(s0)
    addi s0, sp, 32
mainEntry1:
    li a0, 0
    lw ra, 24(sp)
    lw s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    lw ra, 24(sp)
    lw s0, 16(sp)
    addi sp, sp, 32
    ret
getch:
before_main:
getfarray:
putint:
putfarray:
end_time:
main:
getarray:
after_main:
getint:
putarray:
start_time:
putfloat:
putch:
getfloat:

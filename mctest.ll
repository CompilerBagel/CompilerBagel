main:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
mainEntry1:
    sw %a11, 0(2)
    lw %a11, 0(%a14)
    addiw %add_15, %a14, 1
    sw %b13, 0(%add_15)
    lw %b13, 0(%b18)
    addiw %add_19, %b18, 2
    sw %c17, 0(%add_19)
    lw %c17, 0(%c22)
    addiw %add_23, %c22, 3
    sw %d21, 0(%add_23)
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

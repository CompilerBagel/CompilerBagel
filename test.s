main:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
mainEntry1:
    sw %a11, 0(0)
    sw %b13, 0(0)
    sw %a11, 0(15)
    sw %b13, 0(12)
    lw %a11, 0(%a17)
    lw %b13, 0(%b18)
    addw %add_19, %a17, %b18
    addiw %add_20, %add_19, 61
    lw a0, 0(%add_20)
    lw ra, 20(sp)
    lw s0, 12(sp)
    addi sp, sp, 28
    ret
    li a0, 0
    lw ra, 20(sp)
    lw s0, 12(sp)
    addi sp, sp, 28
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

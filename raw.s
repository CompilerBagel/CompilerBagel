.global main
.data
.text
main:
mainEntry1:
    addi sp, sp, -32
    sd ra, 24(sp)
    sd s0, 16(sp)
    addi s0, sp, 32
    lui lui, 260096
    addiw lui, lui, 0
    sw lui, -24(s0)
    lui lui, 262144
    addiw lui, lui, 0
    sw lui, -28(s0)
    lui lui, 263168
    addiw lui, lui, 0
    sw lui, -32(s0)
    flw a, -24(s0)
    flw b, -28(s0)
    fadd.s fadd_, a, b
    fsw fadd_, -32(s0)
    li a0, 0
    ld ra, 24(sp)
    ld s0, 16(sp)
    addi sp, sp, 32
    ret
    li a0, 0
    ret

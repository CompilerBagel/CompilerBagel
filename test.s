.global main
.data
.text
main:
mainEntry1:
    addi sp, sp, -80
    sd ra, 72(sp)
    sd s0, 64(sp)
    addi s0, sp, 80
    addi a0, s0, -84
    addi a0, s0, -116
    addi a0, s0, -124
    li a0, 1
    sw a0, -124(s0)
    addi a0, s0, -84
    addi a0, s0, -116
    addi a0, s0, -124
    li a0, 0
    sw a0, -124(s0)
    addi a0, s0, -84
    addi a0, s0, -116
    addi a0, s0, -124
    li a0, 0
    sw a0, -124(s0)
    addi a0, s0, -84
    addi a0, s0, -116
    addi a0, s0, -124
    li a0, 0
    sw a0, -124(s0)
    addi a0, s0, -84
    addi a0, s0, -116
    addi a0, s0, -124
    li a0, 0
    sw a0, -124(s0)
    addi a0, s0, -84
    addi a0, s0, -116
    addi a0, s0, -124
    li a0, 0
    sw a0, -124(s0)
    addi a0, s0, -84
    addi a0, s0, -116
    addi a0, s0, -124
    li a0, 0
    sw a0, -124(s0)
    addi a0, s0, -84
    addi a0, s0, -116
    addi a0, s0, -124
    li a0, 0
    sw a0, -124(s0)
    addi a0, s0, -84
    addi a0, s0, -116
    addi a0, s0, -148
    lw a0, -148(s0)
    addw a0, a0, 0
    ld ra, 72(sp)
    ld s0, 64(sp)
    addi sp, sp, 80
    ret
    li a0, 0
    ret

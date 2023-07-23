package backend.machineCode;

public class Immeidiate extends MachineOperand {
    private int immValue;
    private float immFloatValue;
    private boolean isImmFloat;

    public Immeidiate(int immValue) {
        super(operandType.imm, String.valueOf(immValue));
        this.isImmFloat = false;
        this.immValue = immValue;
    }

    public Immeidiate(float immFloatValue) {
        super(operandType.imm, String.valueOf(immFloatValue));
        this.isImmFloat = true;
        this.immFloatValue = immFloatValue;
    }

    public int getImmValue() {
        return immValue;
    }
}

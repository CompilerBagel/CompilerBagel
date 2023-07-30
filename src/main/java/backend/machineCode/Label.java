package backend.machineCode;

import IRBuilder.Symbol;
import Type.ArrayType;
import Type.PointerType;

public class Label extends MachineOperand {
    private String label;
    private Symbol symbol;
    private boolean isAddress;

    public Label(String label, Symbol symbol) {
        super(operandType.label, label);
        this.label = label;
        this.symbol = symbol;
    }

    public boolean isAddress() {
        return symbol.getType() instanceof ArrayType || symbol.getType() instanceof PointerType;
    }
}

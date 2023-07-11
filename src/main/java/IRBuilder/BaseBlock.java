package IRBuilder;

import Type.Type;
import instruction.Instruction;

import java.util.ArrayList;
import java.util.List;

public class BaseBlock implements ValueRef{
    public static int baseBlockCounter = 0;
    private final StringBuilder codeBuilder;
    private final String label;
    private final int baseBlockId;
    private final List<Instruction> instructions;

    private List<ValueRef> predList = new ArrayList<>();
    private List<ValueRef> succList = new ArrayList<>();

    /** -------- static methods --------*/
    public static BaseBlock IRAppendBasicBlock(FunctionBlock function, String label) {
        BaseBlock baseBlock = new BaseBlock(label);
        function.addBaseBlock(baseBlock);
        return baseBlock;
    }

    /** -------- member methods --------*/
    public BaseBlock(String label) {
        this.codeBuilder = new StringBuilder();
        this.label = label;
        this.baseBlockId = baseBlockCounter++;
        this.instructions = new ArrayList<>();
    }

    public void emit(String code) {
        codeBuilder.append("  ").append(code).append("\n");
    }

    public void emit(String code, int align) {
        codeBuilder.append("  ").append(code).append(", align ").append(align).append("\n");
    }

    @Override
    public String toString() {
        return codeBuilder.toString();
    }

    public String getLabel() {
        return this.label;
    }

    public StringBuilder getCodeBuilder() {
        return this.codeBuilder;
    }

    public void appendInstr(Instruction instr) {
        instructions.add(instr);
    }

    public void addPredBaseBlock(ValueRef pred){
        predList.add(pred);
    }

    public List<ValueRef> getPredList(){return predList;}

    public void addSuccBaseBlock(ValueRef succ){
        succList.add(succ);
    }

    public List<ValueRef> getSuccList(){return succList;}

    public String getText(){
        return "block " + baseBlockId;
    }

    public String getTypeText(){
        //
        return "";
    };

    public Type getType(){
        //
        return null;
    };

}

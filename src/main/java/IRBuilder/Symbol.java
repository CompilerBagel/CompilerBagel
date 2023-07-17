package IRBuilder;

import Type.*;
import instruction.Instruction;

import java.util.ArrayList;
import java.util.List;

public class Symbol {
    String name;
    Type type;
    List<Float> initValue; // handle both non-array and array

    public Symbol(String name, Type type){
        this.name = name;
        this.type = type;
        this.initValue = new ArrayList<>();
    }

    private Instruction def = null;
    private List<Instruction> uses = new ArrayList<>();

    public void setDef(Instruction defIns){
        def = defIns;
    }

    public Instruction getDef(){
        return def;
    }

    public void addUse(Instruction use){
        uses.add(use);
    }

    public List<Instruction> getUses(){
        return uses;
    }

    public void setInitValue(float value){
        initValue.add(value);
    }

    public void setInitValue(List<Float> value){
        initValue = value;
    }
}
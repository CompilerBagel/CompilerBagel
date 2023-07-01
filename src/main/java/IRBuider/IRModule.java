package IRBuider;

import Type.FunctionType;

import java.util.ArrayList;
import java.util.List;

public class IRModule {
    private StringBuilder stringBuilder;
    private final List<FunctionBlock> functionBlocks;
    private final String name;

    /** -------- static methods --------*/
    public static IRModule IRModuleCreateWithName(String name) {
        return new IRModule(name);
    }

    public static FunctionBlock IRAddFunction(IRModule module, String funcName, FunctionType functionType) {
        FunctionBlock function = new FunctionBlock(funcName, functionType);
        module.addFunction(function);
        return function;
    }

    /** -------- member methods --------*/
    private IRModule(String name) {
        this.name = name;
        this.functionBlocks = new ArrayList<>();
    }

    public void addFunction(FunctionBlock function) {
        functionBlocks.add(function);
    }


}

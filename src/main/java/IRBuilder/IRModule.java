package IRBuilder;

import Type.FunctionType;
import Type.Type;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

    public static void PrintModuleToFile(IRModule module, String dest) {
        for (FunctionBlock function: module.functionBlocks) {
            module.stringBuilder.append(function.genIRCodes());
        }
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(dest));
            out.write(module.stringBuilder.toString());
            out.close();
        } catch (IOException e) {
            System.err.println("failed to print module to file " + dest);
        }
    }

    /** -------- member methods --------*/
    private IRModule(String name) {
        this.name = name;
        this.functionBlocks = new ArrayList<>();
        this.stringBuilder = new StringBuilder();
        this.stringBuilder.append("; ModuleID = '").append(name).append("'\n");
        this.stringBuilder.append("source_filename = \"").append(name).append("\"\n");
    }

    public void addFunction(FunctionBlock function) {
        functionBlocks.add(function);
    }

    public void emit(String code) {
        this.stringBuilder.append(code).append("\n");
    }
}

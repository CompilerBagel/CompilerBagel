package pass.deadCode;

import IRBuilder.BaseBlock;
import IRBuilder.FunctionBlock;
import IRBuilder.IRModule;
import backend.codeGen;
import backend.machineCode.MachineBlock;
import backend.machineCode.MachineCode;
import backend.machineCode.MachineOperand;
import instruction.AllocaInstruction;
import instruction.Instruction;

import java.util.List;
import java.util.function.Function;

public class DeadCodeScan {
    //参考实验：https://tai-e.pascal-lab.net/pa3.html

    codeGen mcCode;
    
    public void deadCodeScan(codeGen mcCode) {
        this.mcCode = mcCode;
        unreachableCode(mcCode);
        deadVariable(mcCode);
    }

    /**
     * 不可达代码
     */
    public void unreachableCode(codeGen mcCode) {
        controlFlowUnreachable(mcCode);
        branchUnreachable(mcCode);
    }

    /**
     * 控制流不可达
     * @param mcCode
     */
    public void controlFlowUnreachable(codeGen mcCode) {

    }

    /**
     * 分支不可达
     * @param mcCode
     */
    public void branchUnreachable(codeGen mcCode) {

    }


    /**
     * 无用变量
     */
    public void deadVariable(codeGen mcCode) {
        for(FunctionBlock functionBlock: mcCode.getModule().getFunctionBlocks()){
            for(BaseBlock baseBlock: functionBlock.getBaseBlocks()){
                MachineBlock mcBlock = mcCode.getBlockMap().get(baseBlock);
                for(MachineCode code: mcBlock.getMachineCodes()){
                    List<MachineOperand> defs = code.getDef();
                    List<MachineOperand> uses = code.getUse();
                    for(MachineOperand def: defs) {
                        if (def.noUser()) {
                            mcBlock.getMachineCodes().remove(code);
                        }
                    }
                }
            }
        }
    }



}
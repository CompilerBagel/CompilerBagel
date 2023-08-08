package pass.deadCode;

import IRBuilder.IRModule;

public class DeadCodeScan {
    //参考实验：https://tai-e.pascal-lab.net/pa3.html

    public void deadCodeScan(IRModule module) {
        unreachableCode(module);
        deadVariable(module);
    }

    /**
     * 不可达代码
     */
    public void unreachableCode(IRModule module) {
        controlFlowUnreachable(module);
        branchUnreachable(module);
    }

    /**
     * 控制流不可达
     * @param module
     */
    public void controlFlowUnreachable(IRModule module) {

    }

    /**
     * 分支不可达
     * @param module
     */
    public void branchUnreachable(IRModule module) {

    }


    /**
     * 无用变量
     */
    public void deadVariable(IRModule module) {

    }


}

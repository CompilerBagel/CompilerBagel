package pass.deadCode;

import IRBuilder.IRModule;

public class IRDeadCodeScan {
    IRModule irModule;
    public void deadCodeScan(IRModule irModule) {
        this.irModule = irModule;
        deadVariable();
    }

    public void deadVariable() {

    }
}

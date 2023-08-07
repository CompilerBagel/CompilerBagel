package IRBuilder;

import Type.Type;

public interface ValueRef {

    boolean getUsedByFunction();
    void setUsedByFunction(boolean usedByFunction);
    int getFloatNO();
    int getNoFloatNO();
    int getSpillIndex();
    void setSpillIndex(int spillIndex);
    void setFloatNO(int floatNO);
    void setNoFloatNO(int noFloatNO);
    String getText();
    String getTypeText();
    Type getType();



}

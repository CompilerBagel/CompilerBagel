package IRBuilder;

import Type.Type;

public interface ValueRef {

    boolean getUsedByFunction();
    void setUsedByFunction(boolean usedByFunction);
    int getFloatNO();
    int getNoFloatNO();
    void setFloatNO(int floatNO);
    void setNoFloatNO(int noFloatNO);
    String getText();
    String getTypeText();
    Type getType();



}

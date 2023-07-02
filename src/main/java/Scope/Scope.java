package Scope;

import IRBuilder.Register;
import Type.Type;

public interface Scope {
    Scope getEnclosingScope();
    void define(String name, Register register, Type type);
    Register getRegister(String name);
    Type getType(String name);
    String getScopeName();
    void setScopeName(String scopeName);

}

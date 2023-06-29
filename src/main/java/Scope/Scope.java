package Scope;

import IRBuider.Register;
import Type.Type;

import java.util.Map;

public interface Scope {
    Scope getEnclosingScope();
    void define(String name, Register register, Type type);
    Register getRegister(String name);
    Type getType(String name);
    String getScopeName();
    void setScopeName(String scopeName);

}

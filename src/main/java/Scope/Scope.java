package Scope;

import IRBuider.Register;
import Type.Type;

import java.util.Map;

public interface Scope {
    Scope getEnclosingScope();
    void define(String name, Register register, Type type);
    void getRegister(String name);
    void getType(String name);
    String getScopeName();
    void setScopeName(String scopeName);

}

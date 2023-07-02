package Scope;

import IRBuilder.ValueRef;
import Type.Type;

public interface Scope {
    Scope getEnclosingScope();
    void define(String name, ValueRef valueRef, Type type);
    ValueRef getValueRef(String name);
    Type getType(String name);
    String getScopeName();
    void setScopeName(String scopeName);

}

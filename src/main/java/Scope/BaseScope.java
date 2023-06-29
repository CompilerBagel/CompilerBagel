package Scope;

import IRBuider.Register;
import Type.Type;

public class BaseScope implements Scope {
    private final Scope enclosingScope;
    private String scopeName;

    public BaseScope(String scopeName, Scope enclosingScope) {
        this.scopeName = scopeName;
        this.enclosingScope = enclosingScope;
    }

    public Scope getEnclosingScope() {
        return enclosingScope;
    }

    public void define(String name, Register register, Type type) {
        // TODO:
    }

    public void getRegister(String name) {
        // TODO:
    }

    public void getType(String name) {
        // TODO:
    }

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }
}

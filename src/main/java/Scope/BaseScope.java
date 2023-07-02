package Scope;

import IRBuilder.Register;
import Type.Type;

import java.util.LinkedHashMap;
import java.util.Map;

public class BaseScope implements Scope {
    private final Scope enclosingScope;
    private String scopeName;
    private final Map<String, Register> symbols = new LinkedHashMap<>();
    private final Map<String, Type> types = new LinkedHashMap<>();

    public BaseScope(String scopeName, Scope enclosingScope) {
        this.scopeName = scopeName;
        this.enclosingScope = enclosingScope;
    }

    @Override
    public Scope getEnclosingScope() {
        return enclosingScope;
    }

    @Override
    public void define(String name, Register register, Type type) {
        symbols.put(name, register);
        types.put(name, type);
    }

    @Override
    public Register getRegister(String name) {
        Register register = symbols.get(name);
        if (register != null) {
            return register;
        }

        if (enclosingScope != null) {
            return enclosingScope.getRegister(name);
        }

        return null;
    }

    @Override
    public Type getType(String name) {
        Type type = types.get(name);
        if (type != null) {
            return type;
        }

        if (enclosingScope != null) {
            return enclosingScope.getType(name);
        }
        return null;
    }

    @Override
    public String getScopeName() {
        return scopeName;
    }
    @Override
    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }
}
